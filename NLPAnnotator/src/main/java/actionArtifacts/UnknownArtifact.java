package actionArtifacts;

import com.google.gson.Gson;

public class UnknownArtifact implements CommandArtifact {

	@Override
	public Gson generateOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString() {
		String output = "{Unknown:}";
		return output;
	}

}
