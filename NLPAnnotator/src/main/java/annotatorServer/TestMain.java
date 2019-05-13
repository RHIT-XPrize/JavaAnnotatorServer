package annotatorServer;
import static spark.Spark.*;

import com.google.gson.Gson;

import MetadataCompiler.MetadataAnnotator;
import SpatialRelationGenerator.SpatialRelationAnnotator;

public class TestMain {

	public static void main(String[] args) {
		port(3001);
		Annotator meta = new MetadataAnnotator();
		post("/MetadataCompiler", meta);
		
		Annotator spatial = new SpatialRelationAnnotator();
		post("/SpatialRelationGen", spatial);
		Annotator handle = new NLPAnnotatorUnit();
		post("/NLPUnit", handle);
	}

}
