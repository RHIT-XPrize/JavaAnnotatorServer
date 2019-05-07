package actionArtifacts;

import java.util.List;

import com.google.gson.Gson;

public class PickUpArtifact implements CommandArtifact {

	boolean usesGesture;
	List<String> mods;
	
	public PickUpArtifact(boolean usesGesture, List<String> mods) {
		this.usesGesture = usesGesture;
		this.mods = mods;
	}
	
	@Override
	public Gson generateOutput() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getString() {
		String output = "{Pick Up: ";
		output += "Uses Gesture: " + usesGesture + ", ";
		for(String str: mods){
			output += "(Block: " + str + "), ";
		}
		output += "}";
		return output;
	}

}
