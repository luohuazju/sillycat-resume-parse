package com.sillycat.resumeparse;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class OpenNLPPersonNameMain {

	public static void main(String[] args) {

		String[] data = new String[] { "John", "Smith", "works", "for", "the",
				"United", "Nations", "." , "Kevin", "is", "a", "good", "engineer", "."};

		InputStream modelIn = OpenNLPParserMain.class.getClassLoader()
				.getResourceAsStream("models/en-ner-person.bin");

		TokenNameFinderModel model = null;
		try {
			model = new TokenNameFinderModel(modelIn);
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

		NameFinderME tokenNameFinder = new NameFinderME(model);
		Span[] spans = tokenNameFinder.find(data);
		double[] probs = tokenNameFinder.probs();
		for (int i = 0; i < spans.length; i++) {
			int start = spans[i].getStart();
			int end = spans[i].getEnd();
			StringBuilder buffer = new StringBuilder();
			for (int j = start; j < end; j++) {
				buffer.append(data[j]);
				if (j != (end - 1)) {
					buffer.append(' ');
				}
			}
			String value = buffer.toString();
			System.out.println(value + " " + probs[i] + " ");
		}

	}

}
