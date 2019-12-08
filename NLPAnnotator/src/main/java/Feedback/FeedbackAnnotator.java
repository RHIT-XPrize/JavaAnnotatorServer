package Feedback;

import org.json.JSONArray;
import org.json.JSONObject;

import MetadataCompiler.MetaBlock;
import MetadataCompiler.MetadataAnnotationType;
import MetadataCompiler.OutputBlock;
import annotatorServer.Annotator;

public class FeedbackAnnotator extends Annotator {
	
	private double confidenceValue;
	private MetaBlock selectedBlock;
	//Kinect confidences range from 0 to 1 (inclusive)
	private final static double CONFIDENCE_THRESHOLD = 0.5;

	@Override
	public String process(String request) {
		parseJson(request);
		OutputBlock finalBlock = new OutputBlock(this.selectedBlock, this.confidenceValue);
		String feedback = evaluateConfidence();
		
		FeedbackAnnotationType annotation= new FeedbackAnnotationType("\"edu.rosehulman.aixprize.pipeline.types.Feedback\"", finalBlock, feedback);
		
		System.out.println("{" + annotation.getName() + ": "+ annotation.getFields() + "}");
		
		return "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
	}

	private void parseJson(String request) {
		JSONObject jsonObj = new JSONObject(request);
		JSONArray jsonArray = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("MetadataSelectedBlock");
		
		JSONObject block = jsonArray.getJSONObject(0);
		
		this.selectedBlock = new MetaBlock(block.getInt("id"),
				block.getDouble("x"),
				block.getDouble("y"),
				block.getDouble("z"),
				block.getString("name"));
		
		this.confidenceValue = block.getDouble("confidenceValue");
		
	}
	
	private String evaluateConfidence() {
		if(this.confidenceValue >= this.CONFIDENCE_THRESHOLD) {
			return "True";
		} else {
			return "Is this the block you want?";
		}
	}
	

}
