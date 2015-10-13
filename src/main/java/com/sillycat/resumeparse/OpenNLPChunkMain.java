package com.sillycat.resumeparse;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.util.Span;

public class OpenNLPChunkMain {

	public static void main(String[] args) {
		InputStream modelIn = OpenNLPParserMain.class.getClassLoader()
				.getResourceAsStream("models/en-chunker.bin");

		ChunkerModel model = null;
		try {
			model = new ChunkerModel(modelIn);
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

//		I 0.9732879282256719 PRP
//		am 0.964606681960317 VBP
//		Carl 0.9816758912754017 NNP
//		. 0.3823051156140692 .
//		I 0.95524464076097 PRP
//		am 0.9801383116579873 VBP
//		a 0.9863774195781929 DT
//		software 0.9071380751356256 NN
//		engineer 0.9836540552245981 NN
//		. 0.985789375461335 .
		
		String[] data = new String[] { "I", "am", "Carl", ".",
				"I", "am", "a", "software","engineer", "." };
		String[] tags2 = new String[] { "PRP", "VBP", "NNP", ".", "PRP", "VBP", "DT", "NN", "NN", "." };

		ChunkerME chunker = new ChunkerME(model);
		Span[] spans = chunker.chunkAsSpans(data, tags2);
		double[] probs = chunker.probs();

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
			System.out.println(probs[i] + " " + value);
		}
	}

}
