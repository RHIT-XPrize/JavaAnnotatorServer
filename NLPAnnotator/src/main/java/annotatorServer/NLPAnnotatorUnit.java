package annotatorServer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;

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

public class NLPAnnotatorUnit extends Annotator {

	public ParseInput request;
	public List<VerbalAction> actions;
	public Gson gson;
	public String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.NLPProcessor\"";
	
	public NLPAnnotatorUnit() {
		this.request = new GoogleNLPAPIRequest();
		this.actions = new ArrayList<>();
		this.gson = new Gson();
		PhraseParser nameParser = new NameParser();
		PhraseParser gestureParser = new GestureParser();
		PhraseParser findBlocksParser = new FindBlockMods();
		
		actions.add(new NameAction(nameParser, gestureParser, findBlocksParser));
		actions.add(new PickUpAction(gestureParser, findBlocksParser));
	}
	
	@Override
	public String process(String JSONRequest) {
		JSONObject jsonObj = new JSONObject(JSONRequest);
		String result =	jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("SpokenText").getJSONObject(0).getString("text");
		SpokenPhrase phrase = request.buildDependencyTree(result);
		String gsonString = "{" + unitWrapper + ": [";
		for(VerbalAction action: actions){
			if(action.isAction(phrase)){
//				System.out.println(action.parseImportant(phrase).getString());
				gsonString += gson.toJson(action.parseImportant(phrase));
			}
		}
		gsonString += "]}";
		return gsonString;
	}

}
