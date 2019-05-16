package annotatorServer;
import static spark.Spark.*;

import com.google.gson.Gson;

import MetadataCompiler.MetadataAnnotator;
import SpatialRelationGenerator.SpatialRelationAnnotator;

public class TestMain {

	public static void main(String[] args) {

		port(3001);		
		Annotator speech = new SpeechToTextAnnotator();
		post("/Speech", speech);
		Annotator metaData = new MetadataAnnotator();
		post("/MetadataCompiler", metaData);
		Annotator spatial = new SpatialRelationAnnotator();
		post("/SpatialRelationGen", spatial);
		Annotator handle = new NLPAnnotatorUnit();
		post("/NLPUnit", handle);
	}

}