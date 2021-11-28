package com.practice.demo.lucene.indexing.handlers.xml;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

public class SAXXMLHandler extends DefaultHandler {

	private final StringBuffer elementBuffer = new StringBuffer();
	private HashMap<String, String> attributeMap;
	private Document doc;
	
	public SAXXMLHandler(){
		super();
	}
	
	public Document getDocument(InputStream is) {
		try{
			XMLReader xmlReader=XMLReaderFactory.createXMLReader();
			xmlReader.setContentHandler(this);
			xmlReader.parse(new InputSource(is));
		}catch(SAXException se){
			System.out.println("Problem pri parsiranju XML-a");
		}catch(IOException ioe){
			System.out.println("Nemoguce je otvoriti fajl");
		}
		return doc;
	}
	
	//bice pozvana kad pocne parsing
	public void startDocument(){
		System.out.println("Start parsinga");
		doc=new Document();
	}
	
	//bice pozvana svaki put kad se pristupi novom XML elementu
	public void startElement(String uri, String localName, String qName, Attributes atts) {
		System.out.println("Start elementa: "+qName);
		elementBuffer.setLength(0);
		if(attributeMap!=null){
			attributeMap.clear();
		}
		if(atts.getLength()>0){
			if(attributeMap==null)
				attributeMap= new HashMap<>();
			for(int i=0;i<atts.getLength();i++){
				attributeMap.put(atts.getQName(i), atts.getValue(i));
			}
		}
	}
	
	//kada se stigne do cdata delova
	public void characters(char[] ch, int start, int length) {
		elementBuffer.append(ch,start, length);
	}
	
	//kada se dostigne kraj elementa
	public void endElement(String uri, String localName, String qName) {
		switch (qName) {
			case "bookstore":
				return;
			case "book": {
				Set<String> keySet = attributeMap.keySet();
				for (String key : keySet) {
					String value = attributeMap.get(key);
					doc.add(new StringField(key, value, Store.YES));
				}
				break;
			}
			case "title": {
				Set<String> keySet = attributeMap.keySet();
				for (String key : keySet) {
					String value = attributeMap.get(key);
					doc.add(new StringField(key, value, Store.YES));
				}
				doc.add(new TextField(qName, elementBuffer.toString(), Store.YES));
				break;
			}
			default:
				doc.add(new TextField(qName, elementBuffer.toString(), Store.NO));
				break;
		}
		
	}
}
