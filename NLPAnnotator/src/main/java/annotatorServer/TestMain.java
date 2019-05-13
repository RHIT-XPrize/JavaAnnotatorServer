package annotatorServer;
import static spark.Spark.*;

import com.google.gson.Gson;

import MetadataCompiler.MetadataAnnotator;
import SpatialRelationGenerator.SpatialRelationAnnotator;

public class TestMain {

	public static void main(String[] args) {
		port(3000);
		Annotator speechToText = new SpeechToTextAnnotator();
		post("/hello", speechToText);
	}

}