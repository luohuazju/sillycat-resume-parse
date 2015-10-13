package com.sillycat.resumeparse;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class OpenNLPTokenizerMain {

	static final String SAMPLE_STR = "I am Carl. I am a software engineer.";

	public static void main(String[] args) {

		InputStream modelIn = OpenNLPParserMain.class.getClassLoader()
				.getResourceAsStream("models/en-token.bin");
		TokenizerModel model = null;
		try {
			model = new TokenizerModel(modelIn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
				}
			}
		}

		TokenizerME tokenizer = new TokenizerME(model);
		Span[] spans = tokenizer.tokenizePos(SAMPLE_STR);
		double[] tokenProbabilities = tokenizer.getTokenProbabilities();
		for (int i = 0; i < spans.length; i++) {
			int start = spans[i].getStart();
			int end = spans[i].getEnd();
			String value = SAMPLE_STR.substring(start, end);
			System.out.println(i + " possibility: " + tokenProbabilities[i]
					+ " string:" + value);
		}
	}

}
