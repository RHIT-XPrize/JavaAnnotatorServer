package Feedback;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import MetadataCompiler.OutputBlock;
import annotatorServer.AnnotationType;

public class FeedbackAnnotationType extends AnnotationType {

	OutputBlock chosenBlock;
	String feedback;

	public FeedbackAnnotationType(String name, OutputBlock finalBlock, String feedback) {
		super(name);
		this.chosenBlock = finalBlock;
		this.feedback = feedback;

		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> getFields() {
		// TODO Auto-generated method stub
		List<String> output = new ArrayList<>();
		Gson gson = new Gson();

		output.add(gson.toJson(this.chosenBlock));
		output.add(gson.toJson(this.feedback));

		return output;
	}

}
