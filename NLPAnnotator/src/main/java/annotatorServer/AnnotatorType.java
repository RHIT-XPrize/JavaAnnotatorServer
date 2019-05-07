package annotatorServer;

import java.util.List;

public abstract class AnnotatorType{

	private String name;
	public AnnotatorType(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract List<String> getFields();
	
}
