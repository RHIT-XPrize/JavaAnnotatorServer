package actionArtifacts;

import org.json.JSONObject;

import com.google.gson.Gson;

public class putdownArtifact implements CommandArtifact{
	
	
	
	private static final String COMMAND = "pick up";
	JSONObject compiledArtifact;
	
	public putdownArtifact(JSONObject object) {
		compiledArtifact = new JSONObject();
		compiledArtifact.put(COMMAND_TAG, COMMAND);
		compiledArtifact.put(INFO_TAG, compiledArtifact); 
	}
	
	@Override
	public Gson generateOutput() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getString() {
		return compiledArtifact.toString();
	}
	
	public JSONObject getJson(){
		return compiledArtifact;
	}

}
