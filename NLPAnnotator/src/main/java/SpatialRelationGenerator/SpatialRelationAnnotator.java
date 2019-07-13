package SpatialRelationGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import MetadataCompiler.MetaBlock;
import annotatorServer.Annotator;

public class SpatialRelationAnnotator extends Annotator{

	@Override
	public String process(String request) {
		List<InnerBlock> blocks = parseJson(request);
		
		Grapher grapher = new Grapher(blocks);
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
		InnerBlock origin = new InnerBlock(0,0,0,0, "origin");
		output.add(origin);
		
		InnerBlock front = new InnerBlock(1,0,0,10, "front");
		output.add(front);
		
		InnerBlock left = new InnerBlock(2,-7,0,-1, "left");
		output.add(left);
		
		InnerBlock right = new InnerBlock(3,5,0,1, "right");
		output.add(right);
		
		InnerBlock behind = new InnerBlock(4,0,0,-5, "behind");
		output.add(behind);
		
		InnerBlock faraway = new InnerBlock(5,-1000,0,1, "faraway");
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
