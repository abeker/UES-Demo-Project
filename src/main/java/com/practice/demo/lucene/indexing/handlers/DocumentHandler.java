package com.practice.demo.lucene.indexing.handlers;

import com.practice.demo.model.Book;

import java.io.File;

public abstract class DocumentHandler {
	/**
	 * Od prosledjene datoteke se konstruise Lucene Document
	 * 
	 * @param file
	 *            datoteka u kojoj se nalaze informacije
	 * @return Lucene Document
	 */
	public abstract Book getIndexUnit(File file);
	public abstract String getText(File file);

}
