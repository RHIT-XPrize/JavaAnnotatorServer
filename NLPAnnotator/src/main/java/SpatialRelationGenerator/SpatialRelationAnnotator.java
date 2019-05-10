package SpatialRelationGenerator;

import java.util.ArrayList;
import java.util.List;

import annotatorServer.Annotator;

public class SpatialRelationAnnotator extends Annotator{

	@Override
	public String process(String request) {
		
		List<InnerBlock> blocks = parseJson(request);
		
		
		Grapher grapher = new Grapher(blocks);
		grapher.makeGraph(); //blocks spatial relationship fields are now populated
		
		List<OutputBlock> output = convertToOutputBlocks(blocks);
		
		
		return null;
	}
	
	public List<InnerBlock> parseJson (String request){
		List<InnerBlock> output = new ArrayList<InnerBlock>();
		
		//TODO: convert to InnerBlocks
		
		return output;
	}
	
	private List<OutputBlock> convertToOutputBlocks(List<InnerBlock> blocks) {
		List<OutputBlock> output = new ArrayList<>();
		for(InnerBlock b: blocks){
			output.add(new OutputBlock(b));
		}
		return output;
	}



}
