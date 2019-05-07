package executables;
import java.util.ArrayList;
import java.util.List;

import Actions.NameAction;
import Actions.PickUpAction;
import Actions.VerbalAction;
import dataStructures.SpokenPhrase;
import googleNLP.GoogleNLPAPIRequest;
import googleNLP.ParseInput;
import phraseParsers.FindBlockMods;
import phraseParsers.GestureParser;
import phraseParsers.NameParser;
import phraseParsers.PhraseParser;

public class Main {
	public static void main(String[] args) {
		ParseInput request = new GoogleNLPAPIRequest();
		SpokenPhrase phrase = request.buildDependencyTree("Pick up the block left of this one.");
		
		List<VerbalAction> actions = new ArrayList<>();
		PhraseParser nameParser = new NameParser();
		PhraseParser gestureParser = new GestureParser();
		PhraseParser findBlocksParser = new FindBlockMods();
		
		actions.add(new NameAction(nameParser, gestureParser, findBlocksParser));
		actions.add(new PickUpAction(gestureParser, findBlocksParser));
		for(VerbalAction action: actions){
			if(action.isAction(phrase)){
				System.out.println(action.parseImportant(phrase).getString());
			}
		}
	}
}
