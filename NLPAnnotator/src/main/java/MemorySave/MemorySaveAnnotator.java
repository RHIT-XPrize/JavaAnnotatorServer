package MemorySave;

import MetadataCompiler.MetaBlock;
import annotatorServer.Annotator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MemorySaveAnnotator extends Annotator {

    String request;
    private MetaBlock block;

    @Override
    public String process(String request) throws IOException {
        this.request = request;
        try {parseJson();
        }
        catch(JSONException je) {
            System.out.println(je);
        }


        MemorySaveAnnotationType annotation= new MemorySaveAnnotationType("\"edu.rosehulman.aixprize.pipeline.types.MemoryType\"",  this.block);
        String output = "{" + annotation.getName() + ": "+ annotation.getFields() + "}";

        saveToFile(output);
        return "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
    }

    private void saveToFile(String output) throws IOException {
        File target = new File("MemorySave.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(target));

        writer.write(output);

        writer.close();
    }

    private void parseJson() {
        JSONObject jsonObj = new JSONObject(request);

        JSONArray NLPJsonArray = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("NLPProcessor");
        JSONObject NLPJson = NLPJsonArray.getJSONObject(0);
        JSONObject jsonObject = NLPJson.getJSONObject("output");
        String command = jsonObject.getString("Command");
        String name = "";
        if(command.equals("Name")){
            name = "Testing Name";
        }
        this.block = getBlock("Testing Name");

    }

    private MetaBlock getBlock(String name) {
        JSONObject jsonObj = new JSONObject(request);
        JSONArray jsonArray = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("MetadataSelectedBlock");
        JSONObject block = jsonArray.getJSONObject(0);
        MetaBlock selectedBlock = new MetaBlock(block.getInt("id"),
                block.getDouble("x"),
                block.getDouble("y"),
                block.getDouble("z"),
                name);
        return selectedBlock;
    }
}
