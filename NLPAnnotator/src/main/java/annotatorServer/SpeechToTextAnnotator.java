package annotatorServer;

import org.json.JSONObject;

import com.google.gson.Gson;

public class SpeechToTextAnnotator extends Annotator{

	private Gson gson;
	private GoogleSpeechRecognition recognition;
	private final String s = "\"edu.rosehulman.aixprize.pipeline.types.SpokenText\":";
	public SpeechToTextAnnotator() {
		this.gson = new Gson();
		this.recognition = new GoogleSpeechRecognition();
	}
	
	@Override
	public String process(String request) {

		String output = recognition.getResponse();
		SpokenTextJava type = new SpokenTextJava(output);
		return "{"+s+"["+gson.toJson(type)+"]"+"}";
	}

}
