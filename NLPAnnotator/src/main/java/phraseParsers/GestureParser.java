package phraseParsers;

import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;

public class GestureParser implements PhraseParser {

	
	@Override
	public String findInformation(SpokenPhrase phrase) {
		for(WordProperties word: phrase.sentence){
			if(word.dependencyEdge.equalsIgnoreCase("DET") 
					&& word.partOfSpeech.equalsIgnoreCase("DET")){
				return word.lemma;
			}
		}
		return "";
	}

}
