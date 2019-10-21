package executables;
import java.util.ArrayList;
import java.util.List;

import Actions.NameAction;
import Actions.PickUpAction;
import Actions.PutDownAction;
import Actions.VerbalAction;
import actionArtifacts.CommandArtifact;
import actionArtifacts.UnknownArtifact;
import dataStructures.SpokenPhrase;
import googleNLP.GoogleNLPTokenAPIRequest;
import googleNLP.NLPTokenParser;
import phraseParsers.FindBlockMods;
import phraseParsers.GestureParser;
import phraseParsers.NameParser;
import phraseParsers.PhraseParser;

public class Main {
	public static void main(String[] args) {
		String in = "Hello my name is Jeff.";
		NLPTokenParser request = new GoogleNLPTokenAPIRequest();
		System.out.println(in);
		SpokenPhrase phrase = request.buildDependencyTree(in);
		List<VerbalAction> actions = new ArrayList<>();
		PhraseParser nameParser = new NameParser();
		PhraseParser gestureParser = new GestureParser();
		PhraseParser findBlocksParser = new FindBlockMods();
		
		actions.add(new NameAction(nameParser, gestureParser, findBlocksParser));
		actions.add(new PickUpAction(gestureParser, findBlocksParser));
		actions.add(new PutDownAction(gestureParser, findBlocksParser));
		boolean found = false;
		for(VerbalAction action: actions){
			if(action.isAction(phrase)){
				System.out.println(action.parseImportant(phrase).getString());
				found = true;
				break;
			}
		}
		if(!found){
			System.out.println(new UnknownArtifact().getString());
		}
	}
}
