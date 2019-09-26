package actionArtifacts;

import com.google.gson.Gson;

public class putdownArtifact implements CommandArtifact{
	boolean usesGesture;
	String assignedName;
	String mods;
	String command;
	
	public putdownArtifact(boolean usesGesture, String mods) {
		this.command = "pick up";
		this.usesGesture = usesGesture;
		this.assignedName = "";
		this.mods = mods;
	}
	
	@Override
	public Gson generateOutput() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getString() {
		String output = "{Put Down: ";
		output += "Uses Gesture: " + usesGesture + ", ";
		output += "Mods(" + mods + ")";

		output += "}";
		return output;
	}

}
