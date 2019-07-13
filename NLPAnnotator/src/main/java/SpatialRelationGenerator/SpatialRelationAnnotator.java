package SpatialRelationGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import MetadataCompiler.MetaBlock;
import annotatorServer.Annotator;

public class SpatialRelationAnnotator extends Annotator{

	double WIDTH_OF_WORKING_SPACE = 2;
	
	@Override
	public String process(String request) {
		List<InnerBlock> blocks = parseJson(request);
		
		Grapher grapher = new Grapher(blocks, WIDTH_OF_WORKING_SPACE);
		grapher.makeGraph(); //blocks spatial relationship fields are now populated
		
		for(InnerBlock b: blocks){
			System.out.println(b);
		}
		
		List<OutputBlock> output = convertToOutputBlocks(blocks);
		
		
		//Convert to JSON and return
		SpatialRelationBlockType annotation = new SpatialRelationBlockType("\"edu.rosehulman.aixprize.pipeline.types.SpatialRelationBlock\"",output);

		return "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
	}
	
	public List<InnerBlock> parseJson (String request){
		List<InnerBlock> output = new ArrayList<InnerBlock>();
		
		//TODO: convert to InnerBlocks
		
		//--------------------- test input ---------------------
		InnerBlock origin = new InnerBlock(1,0,0,1.5, "origin");
		output.add(origin);
		
		InnerBlock front = new InnerBlock(3,0.3,0,2.70, "front");
		output.add(front);
		
		InnerBlock left = new InnerBlock(5,-0.98,0,1.69, "left");
		output.add(left);
		
		InnerBlock right = new InnerBlock(4,1.20,0,1.30, "right");
		output.add(right);
		
		InnerBlock behind = new InnerBlock(2,0.30,0,1.10, "behind");
		output.add(behind);
		
		InnerBlock faraway = new InnerBlock(6,-3.00,0,1.25, "faraway");
		output.add(faraway);
		//--------------------- test input ---------------------
		
		return output;
	}
	
	private List<OutputBlock> convertToOutputBlocks(List<InnerBlock> blocks) {
		//Convert inner blocks to output blocks for neat JSON
		List<OutputBlock> output = new ArrayList<>();
		for(InnerBlock b: blocks){
			output.add(new OutputBlock(b));
		}
		return output;
	}



}
