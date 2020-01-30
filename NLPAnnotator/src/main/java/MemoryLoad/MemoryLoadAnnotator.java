package MemoryLoad;

import MemorySave.MemorySaveAnnotationType;
import MetadataCompiler.MetaBlock;
import annotatorServer.Annotator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MemoryLoadAnnotator extends Annotator {

    @Override
    public String process(String request) throws IOException {
        ArrayList<MetaBlock> blockList = getBlockList();

        MemorySaveAnnotationType annotation= new MemorySaveAnnotationType("\"edu.rosehulman.aixprize.pipeline.types.Memory\"",  blockList);
        String output = "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
        return output;
    }

    private ArrayList<MetaBlock> getBlockList() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("MemorySave.txt"));
        String json = "";
        String line;
        while((line = reader.readLine()) != null) {
            json +=line;
        }


        JSONObject jsonObj = new JSONObject(json);

        JSONArray NLPJsonArray = jsonObj.getJSONArray("edu.rosehulman.aixprize.pipeline.types.Memory").getJSONArray(0);
        ArrayList<MetaBlock> blockList = new ArrayList<>();
        for(int i = 0; i < NLPJsonArray.length(); i++){
            JSONObject block = NLPJsonArray.getJSONObject(i);

            MetaBlock metaBlock = new MetaBlock(block.getInt("id"),
                    block.getDouble("x"),
                    block.getDouble("y"),
                    block.getDouble("z"),
                    block.getString("name"));

            blockList.add(metaBlock);
        }
        return blockList;
    }

}
