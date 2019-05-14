package phraseParsers;

import java.util.ArrayList;
import java.util.List;

import actionArtifacts.Directions;
import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;

public class FindBlockMods implements PhraseParser{

	List<String> directions;
	public FindBlockMods() {
		directions = new ArrayList<>();
//		directions.add("above");
//		directions.add("below");
		directions.add("left");
		directions.add("right");
		directions.add("front");
		directions.add("behind");
	}
	
	@Override
	public String findInformation(SpokenPhrase phrase) {
		String output = "";
		for(int i = 0; i < phrase.sentence.size(); i ++){
			WordProperties word = phrase.sentence.get(i);
			String tempout = "";
			if(word.lemma.equalsIgnoreCase("Block") && word.partOfSpeech.equalsIgnoreCase("Noun")){
				tempout += word.lemma + ">";
				for(int j = i-1; j >= 0; j--){
					WordProperties subWord = phrase.sentence.get(j);
					if(subWord.partOfSpeech.equalsIgnoreCase("ADJ") && subWord.parent.equals(word)){
						tempout += subWord.lemma + "|";
					}
					for(String dir: directions){
						if(dir.equalsIgnoreCase(subWord.lemma)){
							tempout += subWord.lemma + "|";
						}
					}
					if(subWord.partOfSpeech.equalsIgnoreCase("NOUN")){
						break;
					}
				}
				if(!tempout.equals(word.lemma + ">")){
					output += tempout + ",";					
				}
			}
		}
		return output;
		
	}
	
}
