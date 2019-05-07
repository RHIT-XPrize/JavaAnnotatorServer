package annotatorServer;

import java.util.ArrayList;
import java.util.List;

public abstract class Annotator {
	
	List<AnnotatorType> annotatorTypes;
	List<String> nameTypes;
	public Annotator() {
		nameTypes = new ArrayList<>();
		annotatorTypes = new ArrayList<>();
	}

}
