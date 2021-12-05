package com.practice.demo.service.implementation;

import com.practice.demo.dto.BookRequestDto;
import com.practice.demo.dto.BookResponseDto;
import com.practice.demo.dto.SimpleQueryEs;
import com.practice.demo.dto.mapper.BookMapper;
import com.practice.demo.dto.mapper.ReaderMapper;
import com.practice.demo.lucene.indexing.handlers.*;
import com.practice.demo.model.Book;
import com.practice.demo.model.Reader;
import com.practice.demo.model.Writer;
import com.practice.demo.repository.IBookRepository;
import com.practice.demo.repository.IWriterRepository;
import com.practice.demo.service.interfaces.IBookService;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@SuppressWarnings("SpellCheckingInspection")
@Service
public class BookService implements IBookService {

    @Value("${files.path}")
    private String dataFilesPath;

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    private final IBookRepository _bookRepository;
    private final IWriterRepository _writerRepository;
    private final ElasticsearchRestTemplate _elasticsearchRestTemplate;

    public BookService(IBookRepository bookRepository, IWriterRepository writerRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        _bookRepository = bookRepository;
        _writerRepository = writerRepository;
        _elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public void index(BookRequestDto bookRequestDto) {
        _bookRepository.save(BookMapper.mapModel(bookRequestDto));
    }

    @Override
    public void index(Book book) {
        _bookRepository.save(book);
    }

    @Override
    public List<BookResponseDto> findByText(String text) {
        List<Book> books = _bookRepository.findAllByText(text);
        return mapBooksToBookResponseDto(books);
    }

    @Override
    public void reindex() {
        File dataDir = getResourceFilePath(dataFilesPath);
        indexUnitFromFile(dataDir);
    }

    @Override
    public int indexUnitFromFile(File file) {
        DocumentHandler handler;
        String fileName;
        int retVal = 0;
        try {
            File[] files;
            if(file.isDirectory()){
                files = file.listFiles();
            }else{
                files = new File[1];
                files[0] = file;
            }
            assert files != null;
            for(File newFile : files){
                if(newFile.isFile()){
                    fileName = newFile.getName();
                    handler = getHandler(fileName);
                    if(handler == null){
                        System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
                        continue;
                    }
                    index(handler.getIndexUnit(newFile));
                    retVal++;
                } else if (newFile.isDirectory()){
                    retVal += indexUnitFromFile(newFile);
                }
            }
            System.out.println("indexing done");
        } catch (Exception e) {
            System.out.println("indexing NOT done");
        }
        return retVal;
    }

    @Override
    public void indexUploadedFile(BookRequestDto bookRequestDto) throws IOException {
        Writer writer = _writerRepository.findOneByUsername(bookRequestDto.getAuthor());
        for (MultipartFile file : bookRequestDto.getFiles()) {
            if (file.isEmpty()) {
                continue;
            }

            String fileName = saveUploadedFileInFolder(file);
            if(fileName != null){
                Book bookIndexUnit = getHandler(fileName).getIndexUnit(new File(fileName));
                bookIndexUnit.setAuthor(writer.getFirstName() + " " + writer.getLastName());
                bookIndexUnit.setPrice(bookRequestDto.getPrice());
                bookIndexUnit.setTitle(bookRequestDto.getHeadline());
                bookIndexUnit.setGenres(bookRequestDto.getGenreNames());
                index(bookIndexUnit);
            }
        }
    }

    public DocumentHandler getHandler(String fileName){
        return getDocumentHandler(fileName);
    }

    @Override
    public File getResourceFilePath(String path) {
        URL url = this.getClass().getClassLoader().getResource(path);
        File file = null;
        try {
            if(url != null) {
                file = new File(url.toURI());
            }
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        return file;
    }

    @Override
    public List<BookResponseDto> findByPrice(double from, double to) {
        String range = from + "-" + to;
        QueryBuilder priceQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryEs("price", range));

        BoolQueryBuilder boolQueryPrice = QueryBuilders
                .boolQuery()
                .must(priceQuery);

        return BookMapper.mapDtos(searchByBoolQuery(boolQueryPrice));
    }

    private SearchHits<Book> searchByBoolQuery(BoolQueryBuilder boolQueryBuilder) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();

        return _elasticsearchRestTemplate.search(searchQuery, Book.class,  IndexCoordinates.of("books"));
    }

    private QueryBuilder createNestedQueryBuilder(SimpleQueryEs simpleQueryEs, String fieldName) {
        BoolQueryBuilder boolQueryBuilder = boolQuery();
        BoolQueryBuilder nestedBoolQueryBuilder =
                boolQuery().must(
                    boolQuery()
                    .should(termQuery(fieldName, simpleQueryEs.getValue())));
        QueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery(simpleQueryEs.getField(), nestedBoolQueryBuilder, ScoreMode.Avg);
        return boolQueryBuilder.must(nestedQueryBuilder);
    }

    public static DocumentHandler getDocumentHandler(String fileName) {
        if(fileName.endsWith(".txt")){
            return new TextDocHandler();
        }else if(fileName.endsWith(".pdf")){
            return new PDFHandler();
        }else if(fileName.endsWith(".doc")){
            return new WordHandler();
        }else if(fileName.endsWith(".docx")){
            return new Word2007Handler();
        }else{
            return null;
        }
    }

    private String saveUploadedFileInFolder(MultipartFile file) throws IOException {
        String retVal = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(getResourceFilePath(dataFilesPath).getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            Path filepath = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            Files.write(filepath, bytes);
            retVal = path.toString();
        }
        return retVal;
    }

    private List<BookResponseDto> mapBooksToBookResponseDto(List<Book> books) {
        List<BookResponseDto> bookDtos = new ArrayList<>();
        for (Book book: books) {
            bookDtos.add(BookMapper.mapResponseDto(book));
        }

        return bookDtos;
    }
}
