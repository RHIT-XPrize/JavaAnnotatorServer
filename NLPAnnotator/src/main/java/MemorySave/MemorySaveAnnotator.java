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
import java.util.ArrayList;

public class MemorySaveAnnotator extends Annotator {

    String request;
    private ArrayList<MetaBlock> namedBlocks;

    @Override
    public String process(String request) throws IOException {
        this.request = request;
        try {parseJson();
        }
        catch(JSONException je) {
            System.out.println(je);
        }


        MemorySaveAnnotationType annotation= new MemorySaveAnnotationType("\"edu.rosehulman.aixprize.pipeline.types.Memory\"",  this.namedBlocks);
        String output = "{" + annotation.getName() + ": "+ annotation.getFields() + "}";

        saveToFile(output);
        return output;
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
        String output = NLPJson.getString("output");
        JSONObject jsonObject = new JSONObject(output);
        String command = jsonObject.getString("command");
        String name = jsonObject.getJSONObject("info").getString("Name");
        MetaBlock block = getBlock(name);
        this.namedBlocks = getBlockList();
        namedBlocks.add(block);
    }

    private ArrayList<MetaBlock> getBlockList() {
        JSONObject jsonObj = new JSONObject(request);
        JSONArray blockData = new JSONArray();
        try{
        	blockData = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("Memory");
        } catch(Exception e){
        	return new ArrayList<>();
        }
        JSONObject blocksObject = blockData.getJSONObject(0);
        String namedBlocksString = blocksObject.getString("namedBlocks");
        JSONArray jsonList  = new JSONArray(namedBlocksString);
        ArrayList<MetaBlock> blockList = new ArrayList<>();
        for(int i = 0; i < jsonList.length(); i++){
            JSONObject block = jsonList.getJSONObject(i);
            System.err.println(block);

            MetaBlock metaBlock = new MetaBlock(block.getInt("id"),
                    block.getDouble("x"),
                    block.getDouble("y"),
                    block.getDouble("z"),
                    block.getString("name"));

            blockList.add(metaBlock);
        }
        return blockList;
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
