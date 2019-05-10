package SpatialRelationGenerator;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {
	
	
	
	

	public static void main(String[] args) throws JsonProcessingException{
		
		InnerBlock origin = new InnerBlock(0,0,0,0, "origin");
		
		InnerBlock front = new InnerBlock(1,0,0,10, "front");
		
		InnerBlock behind = new InnerBlock(2,0, 0, -5, "behind");
		
		InnerBlock right = new InnerBlock(3,5, 0, 1, "right");
		
		InnerBlock left = new InnerBlock(4,-7, 0, -1, "left");
		
		InnerBlock farAway = new InnerBlock(5, -1000, 0, 1, "faraway");
		
		List<InnerBlock> blocks  = new ArrayList<>();
		
		blocks.add(origin);
		blocks.add(front);
		blocks.add(behind);
		blocks.add(right);
		blocks.add(left);
		blocks.add(farAway);
		
		Grapher grapher = new Grapher(blocks);
		
		grapher.makeGraph();
		
		for(InnerBlock b: blocks){
			System.out.println(b);
		}
		
		System.out.println("\n\n\nYEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEET\n\n\n\n");
		
		System.out.println(grapher.generateAnnotationResponse());
		
				
	}
}
