package dataStructures;

import java.util.List;

public class Construct {

	public String identifier;
	public List<String> aliases;
	public List<String> builtWith;
	
	public Construct(String identifier, List<String> aliases, List<String> builtWith) {
		this.identifier = identifier;
		this.aliases = aliases;
		this.builtWith = builtWith;
	}
		
}
