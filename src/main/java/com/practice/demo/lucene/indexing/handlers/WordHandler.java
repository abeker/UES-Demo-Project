package com.practice.demo.lucene.indexing.handlers;

import com.practice.demo.model.Book;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class WordHandler extends DocumentHandler {

	public Book getIndexUnit(File file) {
		Book retVal = new Book();
		InputStream is;

		try {
			is = new FileInputStream(file);
			WordExtractor we = new WordExtractor(is);
			String text = we.getText();
			retVal.setText(text);
			
			SummaryInformation si = we.getSummaryInformation();
			String title = si.getTitle();
			retVal.setTitle(title);

			String keywords = si.getKeywords();
			retVal.setKeywords(keywords);
			
			retVal.setFilename(file.getCanonicalPath());
			
			we.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Dokument ne postoji");
		} catch (Exception e) {
			System.out.println("Problem pri parsiranju doc fajla");
		}

		return retVal;
	}

	@Override
	public String getText(File file) {
		String text = null;
		try {
			WordExtractor we = new WordExtractor(new FileInputStream(file));
			text = we.getText();
			we.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Dokument ne postoji");
		} catch (Exception e) {
			System.out.println("Problem pri parsiranju doc fajla");
		}
		return text;
	}

}
