package MetadataCompiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

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

		OutputBlock finalBlock = new OutputBlock(output);
		//Convert to JSON
		MetadataAnnotationType annotation= new MetadataAnnotationType("\"edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock\"", finalBlock);
		
		return "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
	}
	
	public void parseJson(String request){
//----------Loop to create all blocks in a Map of id's to MetaBlocks
		Map<Integer, MetaBlock> blocksFromJson = new HashMap<>();
		
		JSONObject jsonObj = new JSONObject(request);
		
		
		
//----------Find the relation keywords and degree
			
			//--------------------- test input ---------------------
			this.degrees.add(1);
			this.degrees.add(1);
			
			
			//--------------------- test input ---------------------
//			this.relationKeywords.add("FRONT");
//			this.relationKeywords.add("LEFT");
			
		JSONArray spatialKeywords = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("NLPProcessor");
		
		String nounModifierPairs = spatialKeywords.getJSONObject(0).getString("mods");
		
		String[] arrayOfNouns = nounModifierPairs.split(",");
		
		
		List<String> reverseOrderMods = new ArrayList<>();
		for (int i = 0; i < arrayOfNouns.length; i++){
			String[] nounModifiers = arrayOfNouns[i].split(">");
			
			String noun = nounModifiers[0];
			
			if(nounModifiers[1] != ""){
				String[] modifiers = nounModifiers[1].split("\\|");
				for(int j = 0; j < modifiers.length; j++){
					String modifier = modifiers[j].toUpperCase();
					if(modifier.equals("FRONT") || modifier.equals("LEFT") || modifiers.equals("BEHIND") || modifiers.equals("RIGHT")){
						reverseOrderMods.add(modifier);
					}
				}
			}
		
		}
		
		for(int i = reverseOrderMods.size() - 1; i >= 0; i--){
			this.relationKeywords.add(reverseOrderMods.get(i));	
		}
		

		
		
		
		
		
		
	
		
		JSONArray blockData = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("SpatialRelationBlock");
		
		for(int i = 0; i < blockData.length(); i++){
			JSONObject block = blockData.getJSONObject(i);
			
			//create block from the jsondata (SpacialRelationAnnotation)
			MetaBlock blockForMap = new MetaBlock(block.getInt("id"),
					block.getInt("x"),
					block.getInt("y"),
					block.getInt("z"),
					block.getString("name"));
			
			blocksFromJson.put(blockForMap.id,blockForMap);
		}
		
//----------Then Loop through JSON again, populate spatial relation lists of each block
		for(int i = 0; i < blockData.length(); i++){
			JSONObject block = blockData.getJSONObject(i);
			
			String[] directions = {"left", "right", "behind", "front"};
			
			for(String dir : directions)
			{
				String leftList = block.getString(dir);
				leftList = leftList.substring(1,leftList.length()-1);
				
				String[] arrOfStr = leftList.split(", ");
				
				for(String s : arrOfStr)
				{
					if(!s.equals("")){
						switch(dir){
							case "front":
								blocksFromJson.get(i).front.add(blocksFromJson.get(Integer.parseInt(s)));
								break;
							case "left":
								blocksFromJson.get(i).left.add(blocksFromJson.get(Integer.parseInt(s)));
								break;
							case "right":
								blocksFromJson.get(i).right.add(blocksFromJson.get(Integer.parseInt(s)));
								break;
							case "behind":
								blocksFromJson.get(i).behind.add(blocksFromJson.get(Integer.parseInt(s)));
								break;
						}
					}
				}
			}
		}
				
//----------Find the starting block
			
			//--------------------- test input ---------------------
			this.startBlock = blocksFromJson.get(0);
			//--------------------- test input ---------------------

	}

}
