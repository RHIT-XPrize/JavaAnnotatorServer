package MemorySave;

import MetadataCompiler.MetaBlock;
import annotatorServer.AnnotationType;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MemorySaveAnnotationType extends AnnotationType {
    private List<MetaBlock> namedBlocks = new ArrayList<>();

    public MemorySaveAnnotationType(String name, List<MetaBlock> blockList) {
        super(name);
        namedBlocks = blockList;
    }

    @Override
    public List<String> getFields() {
        List<String> output = new ArrayList<>();
        Gson gson = new Gson();
        output.add(gson.toJson(this.namedBlocks));
        return output;
    }
}
