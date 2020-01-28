package MemoryLoad;

import annotatorServer.Annotator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MemoryLoadAnnotator extends Annotator {

    @Override
    public String process(String request) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("MemorySave.txt"));
        String json = "";
        String line;
        while((line = reader.readLine()) != null) {
            json +=line;
        }
        return json;
    }

}
