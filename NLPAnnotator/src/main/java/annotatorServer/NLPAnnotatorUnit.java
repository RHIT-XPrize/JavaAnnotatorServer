package annotatorServer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;

import Actions.NameAction;
import Actions.PickUpAction;
import Actions.PutDownAction;
import Actions.VerbalAction;
import actionArtifacts.UnknownArtifact;
import dataStructures.SpokenPhrase;
import googleNLP.GoogleNLPTokenAPIRequest;
import googleNLP.NLPTokenParser;
import phraseParsers.FindBlockMods;
import phraseParsers.GestureParser;
import phraseParsers.NameParser;
import phraseParsers.PhraseParser;

public class NLPAnnotatorUnit extends Annotator {

	public NLPTokenParser request;
	public List<VerbalAction> actions;
	public Gson gson;
	public String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.NLPProcessor\"";
	
	public NLPAnnotatorUnit(NLPTokenParser parser, List<VerbalAction> actions) {
		this.request = parser;
		this.actions = actions;
		this.gson = new Gson();
	}
	
	@Override
	public String process(String JSONRequest) {
		JSONObject jsonObj = new JSONObject(JSONRequest);
		String result =	jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("SpokenText").getJSONObject(0).getString("text");
		SpokenPhrase phrase = request.buildDependencyTree(result);
		String gsonString = "{" + unitWrapper + ": [";
		boolean found = false;
		for(VerbalAction action: actions){
			if(action.isAction(phrase)){
				found = true;
				System.out.println(action.parseImportant(phrase).getString());
				gsonString += gson.toJson(action.parseImportant(phrase));
				break;
			}
		}
		if(!found){
			gsonString += gson.toJson(new UnknownArtifact().getString());
		}
		gsonString += "]}";
		return gsonString;
	}

}
