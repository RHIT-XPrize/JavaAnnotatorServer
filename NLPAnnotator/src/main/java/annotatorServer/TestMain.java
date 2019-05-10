package annotatorServer;
import static spark.Spark.*;

import com.google.gson.Gson;

public class TestMain {

	public static void main(String[] args) {
		port(3000);
		Annotator handle = new NLPAnnotatorUnit();
		post("/NLPUnit", handle);
	}

}
