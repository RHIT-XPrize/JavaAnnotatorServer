package annotatorServer;
import static spark.Spark.*;

import com.google.gson.Gson;

import MetadataCompiler.MetadataAnnotator;
import SpatialRelationGenerator.SpatialRelationAnnotator;

public class TestMain {

	public static void main(String[] args) {

		Annotator handle = new NLPAnnotatorUnit();
		post("/NLPUnit", handle);
	}

}