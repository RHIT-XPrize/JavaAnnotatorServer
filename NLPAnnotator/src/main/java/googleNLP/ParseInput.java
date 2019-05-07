package googleNLP;

import dataStructures.SpokenPhrase;

public interface ParseInput {
	public SpokenPhrase buildDependencyTree(String toParse);
}
