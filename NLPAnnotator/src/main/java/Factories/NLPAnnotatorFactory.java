package Factories;

import java.util.ArrayList;
import java.util.List;

import Actions.NameAction;
import Actions.PickUpAction;
import Actions.PutDownAction;
import Actions.VerbalAction;
import annotatorServer.NLPAnnotatorUnit;
import googleNLP.GoogleNLPTokenAPIRequest;
import googleNLP.NLPTokenParser;
import phraseParsers.FindBlockMods;
import phraseParsers.GestureParser;
import phraseParsers.NameParser;
import phraseParsers.PhraseParser;

public class NLPAnnotatorFactory {
	
	public NLPAnnotatorUnit createNLPAnnotator(){			
		//define the verbal actions accepted by the nlp Unit
		List<VerbalAction> actions = new ArrayList<>();
		PhraseParser nameParser = new NameParser();
		PhraseParser gestureParser = new GestureParser();
		PhraseParser findBlocksParser = new FindBlockMods();		
		actions.add(new NameAction(nameParser, gestureParser, findBlocksParser));
		actions.add(new PickUpAction(gestureParser, findBlocksParser));
		actions.add(new PutDownAction(gestureParser, findBlocksParser));
		
		//define the parser the nlp unit will use
		NLPTokenParser parser = new GoogleNLPTokenAPIRequest();
		
		//create the NLP Annotator
		return new NLPAnnotatorUnit(parser, actions);
	}
}
