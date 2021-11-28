package com.practice.demo.lucene.indexing.filters;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

public class CyrilicToLatinFilter extends TokenFilter {

	private final CharTermAttribute termAttribute;

	public CyrilicToLatinFilter(TokenStream input) {
		super(input);
		termAttribute = input.addAttribute(CharTermAttribute.class);
	}
	
	public boolean incrementToken()throws IOException {
        if (input.incrementToken()) {
        	String text=termAttribute.toString();
        	termAttribute.setEmpty();
        	termAttribute.append(CyrillicLatinConverter.cir2lat(text));
        	return true;
        }
        return false;
    }
	
}