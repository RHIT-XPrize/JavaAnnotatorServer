package MetadataCompiler;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import annotatorServer.AnnotationType;

public class MetadataAnnotationType extends AnnotationType {
	
	MetaBlock chosenBlock;

	public MetadataAnnotationType(String name, MetaBlock finalBlock) {
		super(name);
		
		this.chosenBlock = finalBlock;
	}

	@Override
	public List<String> getFields() {
		List<String> output = new ArrayList<>();
		Gson gson = new Gson();
		
		output.add(gson.toJson(this.chosenBlock));
		
		return output;
	}
}
