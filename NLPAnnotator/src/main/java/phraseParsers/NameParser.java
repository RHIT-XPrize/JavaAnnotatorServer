package phraseParsers;

import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;

public class NameParser implements PhraseParser{


	@Override
	public String findInformation(SpokenPhrase phrase) {
		for(WordProperties word: phrase.sentence){
			if(word.isProper && word.isSingular && word.partOfSpeech.equalsIgnoreCase("NOUN")){
				return word.lemma;
			}
		}
		return "";
	}
}
