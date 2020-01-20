package MemorySave;

import MetadataCompiler.MetaBlock;
import annotatorServer.AnnotationType;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MemorySaveAnnotationType extends AnnotationType {
    private final MetaBlock namedBlock;

    public MemorySaveAnnotationType(String name, MetaBlock block) {
        super(name);
        this.namedBlock = block;
    }

    @Override
    public List<String> getFields() {
        List<String> output = new ArrayList<>();
        Gson gson = new Gson();

        output.add(gson.toJson(this.namedBlock));
        return output;
    }
}
