package phraseParsers;

import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;

public class NameParser implements PhraseParser{


	@Override
	public String findInformation(SpokenPhrase phrase) {
		
		for(int i = phrase.sentence.size() - 1; i >= 0; i--){
			WordProperties word = phrase.sentence.get(i);
			if(word.isProper && word.isSingular && word.partOfSpeech.equalsIgnoreCase("NOUN")){
				return word.lemma;
			}
		}
		return "";
	}
}
