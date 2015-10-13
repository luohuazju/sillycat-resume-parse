package com.sillycat.resumeparse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class TestFunMain {

	static final String file = "/opt/data/resume/3-duffy.pdf";

	public static void main(String[] args) {
		Tika tika = new Tika();
		String text = null;
		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		ParseContext context = new ParseContext();
		Metadata metadata = new Metadata();

		// fetch the content
		try {
			text = tika.parseToString(new File(file));
		} catch (IOException | TikaException e) {
			e.printStackTrace();
		}
		// System.out.print(text);

		// fetch the meta
		try {
			parser.parse(new FileInputStream(file), handler, metadata, context);
		} catch (IOException | SAXException | TikaException e) {
			e.printStackTrace();
		}
		// System.out.println(handler.toString());

		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			// System.out.println(name + ": " + metadata.get(name));
		}

		// identify language
		try {
			parser.parse(new FileInputStream(file), handler, metadata,
					new ParseContext());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}

		LanguageIdentifier object = new LanguageIdentifier(handler.toString());
		System.out.println("Language name :" + object.getLanguage());

	}

}
