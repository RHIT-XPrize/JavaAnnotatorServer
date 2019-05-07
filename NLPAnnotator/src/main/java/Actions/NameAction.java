package Actions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import actionArtifacts.BlockArtifact;
import actionArtifacts.CommandArtifact;
import actionArtifacts.NameArtifact;
import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;
import phraseParsers.PhraseParser;

public class NameAction implements VerbalAction {

	public static final String ACTIONPHRASE = "name";
	public static final String ACTIONPARTOFSPEECH = "verb";
	PhraseParser nameParser;
	PhraseParser gestureParser;
	PhraseParser blockModParser;
	
	public NameAction(PhraseParser nameParser, PhraseParser gestureParser, PhraseParser blockModParser) {
		this.nameParser = nameParser;
		this.gestureParser = gestureParser;
		this.blockModParser = blockModParser;
	}
	
	@Override
	public boolean isAction(SpokenPhrase phrase) {
		for(WordProperties word: phrase.sentence){
			if(word.lemma.equalsIgnoreCase(ACTIONPHRASE) && word.partOfSpeech.equalsIgnoreCase(ACTIONPARTOFSPEECH)){
				return true;
			}
		}
		return false;
	}

	@Override
	public CommandArtifact parseImportant(SpokenPhrase phrase) {
		String assignedName = nameParser.findInformation(phrase);
		String chain = blockModParser.findInformation(phrase);
		System.out.println(chain.split(",").length);
		List<String> chains = Arrays.asList(chain.split(","));
		System.out.println(chains.size());
		boolean usesGesture = false;
		if(!gestureParser.findInformation(phrase).equals("")){
			usesGesture = true;
		}
		return new NameArtifact(usesGesture, assignedName, chains);
		
	}

}
