package com.sillycat.resumeparse;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class OpenNLPTokenizerMain {
	
	static final String SAMPLE_STR = "I am Carl. I am a software engineer. Totally I worked 12 years. About 9 years in China, 3 years in US.";

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

		Tokenizer tokenizer = new TokenizerME(model);
		String tokens[] = tokenizer.tokenize(SAMPLE_STR);
		for (int i = 0 ; i< tokens.length;i++){
			System.out.println(i + " " + tokens[i]);
		}
	}

}
