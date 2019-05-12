package MetadataCompiler;

import java.util.ArrayList;
import java.util.List;

import annotatorServer.Annotator;

public class MetadataAnnotator extends Annotator{
	
	List<String> relationKeywords;
	List<Integer> degrees;
	MetaBlock startBlock;
	
	public MetadataAnnotator() {
		super();
		relationKeywords = new ArrayList<>();
		degrees = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public String process(String request) {
		parseJson(request);
		
		MetadataCompiler compiler = new MetadataCompiler();
		
		MetaBlock output = compiler.chooseBlock(relationKeywords, degrees, startBlock);
		
		//Convert to JSON
		return null;
	}
	
	public void parseJson(String request){
		//Loop to create all blocks in a Map of id's to MetaBlocks
		
		//Then Loop through JSON again, populate spatial relation lists of each block
		
		
		//Find the starting block
		
		//Find the relation keywords and degree
	}

}
