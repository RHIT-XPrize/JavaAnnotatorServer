package TextToSpeech;

import java.util.Locale;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import Feedback.FeedbackAnnotationType;
import MetadataCompiler.MetaBlock;
import annotatorServer.Annotator;
import annotatorServer.SpokenTextJava;

public class TextToSpeechAnnotator extends Annotator{

	@Override
	public String process(String request) {
		System.out.println("requeest "+request);
		String feedback = parseJSON(request);
		TextToSpeechType annotation= new TextToSpeechType("\"edu.rosehulman.aixprize.pipeline.types.TextToSpeech\"", "true");

		System.out.println("{" + annotation.getName() + ": "+ annotation.getFields() + "}");
		
		return "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
		
	}
	
	private String parseJSON(String request) {
		System.out.println(request);
		JSONObject jsonObj = new JSONObject(request);
		JSONArray jsonArray = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("Feedback");
		
		JSONObject feedback  = jsonArray.getJSONObject(0);
		
		String feedbackString = feedback.getString("feedback");
		System.out.println(feedbackString);

		return feedbackString;
	}
	
	private boolean speak(String feedback) {
		try { 
        	System.setProperty("FreeTTSSynthEngineCentral",
        		    "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
        		System.setProperty("freetts.voices",
        		    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        		Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

        		SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general",
        		    Locale.US, null, null);

        		Synthesizer synth = Central.createSynthesizer(desc);
        		synth.allocate();
        		desc = (SynthesizerModeDesc) synth.getEngineModeDesc();
        		Voice voice = new Voice("kevin16", Voice.AGE_YOUNGER_ADULT,
        		    Voice.GENDER_FEMALE, null);

        		synth.getSynthesizerProperties().setVoice(voice);
        		synth.resume();
        		synth.speakPlainText(feedback, null);
        		synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
        		synth.deallocate();
        		return true;
        } 
  
        catch (Exception e) { 
            e.printStackTrace(); 
            return false;
        }
	}
	
}
