package Actions;

import java.util.Arrays;
import java.util.List;

import actionArtifacts.CommandArtifact;
import actionArtifacts.PickUpArtifact;
import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;
import phraseParsers.PhraseParser;

public class PickUpAction implements VerbalAction {

	public final static String WORDONE = "Pick";
	public final static String WORDTWO = "UP";
	public final static String PARTOFSPEECHONE = "VERB";
	public final static String PARTOFSPEECHTWO = "PRT";
	
	PhraseParser gestureParser;
	PhraseParser blockModParser;
	
	public PickUpAction(PhraseParser gestureParser, PhraseParser blockModParser) {
		this.gestureParser = gestureParser;
		this.blockModParser = blockModParser;
	}
	
	@Override
	public boolean isAction(SpokenPhrase phrase) {
		for(WordProperties word: phrase.sentence){
			if(word.lemma.equalsIgnoreCase(WORDTWO) && word.partOfSpeech.equalsIgnoreCase(PARTOFSPEECHTWO)){
				if(word.parent.lemma.equalsIgnoreCase(WORDONE) && word.parent.partOfSpeech.equalsIgnoreCase(PARTOFSPEECHONE)){
					System.out.println("here");
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public CommandArtifact parseImportant(SpokenPhrase phrase) {
		String chain = blockModParser.findInformation(phrase);
		List<String> chains = Arrays.asList(chain.split(","));
		boolean usesGesture = false;
		if(!gestureParser.findInformation(phrase).equals("")){
			usesGesture = true;
		}
		return new PickUpArtifact(usesGesture, chains);
	}

}
