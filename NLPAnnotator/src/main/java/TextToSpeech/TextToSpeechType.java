package TextToSpeech;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale; 
import javax.speech.Central; 
import javax.speech.synthesis.Synthesizer; 
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import com.google.gson.Gson;

import annotatorServer.AnnotationType; 
  
public class TextToSpeechType extends AnnotationType{
	
	String success;

	public TextToSpeechType(String name, String success) {
		super(name);
		this.success = success;
	}

	@Override
	public List<String> getFields() {
		List<String> output = new ArrayList<>();
		Gson gson = new Gson();

		output.add(gson.toJson(this.success));

		return output;
	} 
  
}