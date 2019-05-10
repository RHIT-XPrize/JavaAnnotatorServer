package annotatorServer;

import org.json.JSONObject;

import com.google.gson.Gson;

public class SpeechToTextAnnotator extends Annotator{

	private Gson gson;
	private GoogleSpeechRecognition recognition;

	public SpeechToTextAnnotator() {
		this.gson = new Gson();
		this.recognition = new GoogleSpeechRecognition();
	}
	
	@Override
	public String process(String request) {
		String output = recognition.getResponse();
		SpokenTextType type = new SpokenTextType(output);
		return gson.toJson(type);
	}

}
