package com.sillycat.resumeparse;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class OpenNLPSentenceMain {

	static final String SAMPLE_STR = "Carl is a Chinese. He worked in China for 9 years. Then he relocated to Austin, Texas, USA. And he spends 3 years there till now.";

	public static void main(String[] args) {
		InputStream modelIn = OpenNLPParserMain.class.getClassLoader()
				.getResourceAsStream("models/en-sent.bin");

		SentenceModel model = null;
		try {
			model = new SentenceModel(modelIn);
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

		SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
		Span[] spans = sentenceDetector.sentPosDetect(SAMPLE_STR);
		double[] sentenceProbabilities = sentenceDetector
				.getSentenceProbabilities();

		for(int i = 0;i<spans.length; i++){
			int start = spans[i].getStart();
	        int end = spans[i].getEnd();
	        String value = SAMPLE_STR.substring( start, end );
	        System.out.println( i + " possibility: " + sentenceProbabilities[i] + " string:" + value);
		}
	}

}
