package executables;
import java.util.ArrayList;
import java.util.List;

import Actions.NameAction;
import Actions.PickUpAction;
import Actions.PutDown;
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
		String in = "Pick up both green blocks.";
		ParseInput request = new GoogleNLPAPIRequest();
		System.out.println(in);
		SpokenPhrase phrase = request.buildDependencyTree(in);
		List<VerbalAction> actions = new ArrayList<>();
		PhraseParser nameParser = new NameParser();
		PhraseParser gestureParser = new GestureParser();
		PhraseParser findBlocksParser = new FindBlockMods();
		
		actions.add(new NameAction(nameParser, gestureParser, findBlocksParser));
		actions.add(new PickUpAction(gestureParser, findBlocksParser));
		actions.add(new PutDown(gestureParser, findBlocksParser));
		for(VerbalAction action: actions){
			if(action.isAction(phrase)){
				System.out.println(action.parseImportant(phrase).getString());
			}
		}
	}
}
