package annotatorServer;
import static spark.Spark.*;

import MemorySave.MemorySaveAnnotator;
import com.google.gson.Gson;

import Factories.NLPAnnotatorFactory;
import Feedback.FeedbackAnnotator;
import MetadataCompiler.MetadataAnnotator;
import SpatialRelationGenerator.SpatialRelationAnnotator;
import TextToSpeech.TextToSpeechAnnotator;

public class TestMain {

	public static void main(String[] args) {

		NLPAnnotatorFactory NLPFactory = new NLPAnnotatorFactory();
		
		port(3001);
		
//		Annotator speech = new SpeechToTextAnnotator();
//		post("/Speech", speech);
//		Annotator metaData = new MetadataAnnotator();
//		post("/MetadataCompiler", metaData);
//		Annotator spatial = new SpatialRelationAnnotator();
//		post("/SpatialRelationGen", spatial);
//		Annotator handle = NLPFactory.createNLPAnnotator();
//		post("/NLPUnit", handle);
//		
//		Annotator handle = new FeedbackAnnotator();
//		post("/Feedback", handle);
//
//		Annotator handle = new TextToSpeechAnnotator();
//		post("/TextToSpeech", handle);
		Annotator memorySave = new MemorySaveAnnotator();
		post("/MemorySave", memorySave);
	}
}