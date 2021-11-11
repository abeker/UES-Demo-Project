package com.practice.demo.service.implementation;

import com.practice.demo.dto.WriterRequestDto;
import com.practice.demo.dto.WriterResponseDto;
import com.practice.demo.dto.mapper.WriterMapper;
import com.practice.demo.model.Writer;
import com.practice.demo.repository.IWriterRepository;
import com.practice.demo.service.interfaces.IWriterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@Service
public class WriterService implements IWriterService {

    private final IWriterRepository _writerRepository;

    public WriterService(IWriterRepository writerRepository) {
        _writerRepository = writerRepository;
    }

    @Override
    public void index(WriterRequestDto writerRequestDto) {
        _writerRepository.save(WriterMapper.mapModel(writerRequestDto));
    }

    @Override
    public List<WriterResponseDto> getWritersByFirstName(String firstName) {
        List<Writer> writers = _writerRepository.findAllByFirstName(firstName);
        return mapWritersToWritersDto(writers);
    }

    @Override
    public List<WriterResponseDto> getWritersByFirstNameAndLastName(String firstName, String lastName) {
        List<Writer> writers = _writerRepository.findAllByFirstNameAndLastName(firstName, lastName);
        return mapWritersToWritersDto(writers);
    }

    @Override
    public List<WriterResponseDto> getWritersWithMoreThanPublishedBooks(int nrOfPublishedBooks) {
        List<Writer> writers = _writerRepository.findByNrPublishedBooksGreaterThan(nrOfPublishedBooks);
        return mapWritersToWritersDto(writers);
    }

    private List<WriterResponseDto> mapWritersToWritersDto(List<Writer> writers) {
        List<WriterResponseDto> writerDtos = new ArrayList<>();
        for (Writer writer: writers) {
            writerDtos.add(WriterMapper.mapResponseDto(writer));
        }

        return writerDtos;
    }

}
