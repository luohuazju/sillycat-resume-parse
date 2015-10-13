package com.sillycat.resumeparse;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class OpenNLPPOSMain {

	public static void main(String[] args) {
		
//		0 possibility: 1.0 string:I
//		1 possibility: 1.0 string:am
//		2 possibility: 0.9384159590982916 string:Carl
//		3 possibility: 1.0 string:.
//		4 possibility: 1.0 string:I
//		5 possibility: 1.0 string:am
//		6 possibility: 1.0 string:a
//		7 possibility: 1.0 string:software
//		8 possibility: 0.9492906803839625 string:engineer
//		9 possibility: 1.0 string:.
		
		String[] data = new String[]{"I","am","Carl",".","I","am" ,"a", "software","engineer", "."};

		InputStream modelIn = OpenNLPParserMain.class.getClassLoader()
				.getResourceAsStream("models/en-pos-maxent.bin");
		
		POSModel model = null;
		try {
			model = new POSModel(modelIn);
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
		
		POSTaggerME posTagger = new POSTaggerME( model );
	      String[] tags = posTagger.tag( data );
	      double[] probs = posTagger.probs();
	      for ( int i = 0; i < tags.length; i++ )
	      {
	        System.out.println(data[i] + " " + probs[i] + " " + tags[i] );      
	      }
		
	}
}
