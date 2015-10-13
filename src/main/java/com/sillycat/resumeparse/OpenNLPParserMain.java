package com.sillycat.resumeparse;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

public class OpenNLPParserMain {

	public static void main(String[] args) {
		InputStream modelIn = OpenNLPParserMain.class.getClassLoader()
				.getResourceAsStream("models/en-parser-chunking.bin");
		
		ParserModel model = null;
		try {
			model = new ParserModel(modelIn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Parser parser = ParserFactory.create(model);
		String sentence = "I am carl. I worked in US for about 3 years. Before that I was working in China for 8 years.";
		Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
		for (int i = 0 ; i< topParses.length;i++){
			System.out.println(i + " " + topParses[i]);
		}
	}

}
