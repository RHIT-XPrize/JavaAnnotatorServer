package actionArtifacts;

import java.util.List;

import com.google.gson.Gson;

public class NameArtifact implements CommandArtifact {

	boolean usesGesture;
	String assignedName;
	List<String> mods;
	
	public NameArtifact(boolean usesGesture, String assignedName, List<String> mods) {
		this.usesGesture = usesGesture;
		this.assignedName = assignedName;
		this.mods = mods;
	}

	@Override
	public Gson generateOutput() {
		return null;
	}

	@Override
	public String getString() {
		String output = "{Name: ";
		output += "Uses Gesture: " + usesGesture + ", ";
		output += "Assigned Name: " + assignedName + ", ";
		for(String str: mods){
			output += "(Block: " + str + "), ";
		}
		output += "}";
		return output;
	}

}
