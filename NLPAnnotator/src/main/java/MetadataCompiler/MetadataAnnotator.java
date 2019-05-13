package MetadataCompiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SpatialRelationGenerator.InnerBlock;
import annotatorServer.Annotator;

public class MetadataAnnotator extends Annotator{
	
	List<String> relationKeywords;
	List<Integer> degrees;
	MetaBlock startBlock;
	
	public MetadataAnnotator() {
		super();
		relationKeywords = new ArrayList<>();
		degrees = new ArrayList<>();
	}
	

	@Override
	public String process(String request) {
		parseJson(request);
		
		MetadataCompiler compiler = new MetadataCompiler();
		
		MetaBlock output = compiler.chooseBlock(relationKeywords, degrees, startBlock);
		
		//Convert to JSON
		MetadataAnnotationType annotation= new MetadataAnnotationType("\"edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock\"", output);
		
		return "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
	}
	
	public void parseJson(String request){
		//Loop to create all blocks in a Map of id's to MetaBlocks
		Map<Integer, MetaBlock> blocksFromJson = new HashMap<>();
		
			//--------------------- test input ---------------------
			MetaBlock origin = new MetaBlock(0,0,0,0, "origin");
			blocksFromJson.put(0,origin);
					
			MetaBlock front = new MetaBlock(1,0,0,10, "front");
			blocksFromJson.put(1,front);
			
			MetaBlock leftFront = new MetaBlock(2,-7,0,11, "leftFront");
			blocksFromJson.put(2,leftFront);
			//--------------------- test input ---------------------
		
		//Then Loop through JSON again, populate spatial relation lists of each block
		
			//--------------------- test input ---------------------
			blocksFromJson.get(0).front.add(blocksFromJson.get(1));
			blocksFromJson.get(1).left.add(blocksFromJson.get(2));
			//--------------------- test input ---------------------
		
		//Find the starting block
			
			//--------------------- test input ---------------------
			this.startBlock = blocksFromJson.get(0);
			//--------------------- test input ---------------------
		
		//Find the relation keywords and degree
			
			//--------------------- test input ---------------------
			this.degrees.add(1);
			this.degrees.add(1);
			
			this.relationKeywords.add("FRONT");
			this.relationKeywords.add("LEFT");
			//--------------------- test input ---------------------
	}

}
