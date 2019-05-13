package SpatialRelationGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;



public class OutputBlock {
	
	int id;	
	String name;
	double x;
	double y;
	double z;
	
	public String left;
	public String right;
	public String front;
	public String behind;
	
	public OutputBlock(InnerBlock block){
		this.id = block.id;
		this.name = block.name;
		this.x = block.x;
		this.y = block.y;
		this.z = block.z;
		this.left = toStringOfIDs(block.left);
		this.right = toStringOfIDs(block.right);
		this.behind = toStringOfIDs(block.behind);
		this.front = toStringOfIDs(block.front);
	}
	
	
	public OutputBlock(int id, double x1, double y1, double z1, String name){
		this.id = id;
		this.x = x1;
		this.y = y1;
		this.z = z1;
		this.name = name;
		
		this.left = "";
		this.right = "";
		this.front = "";
		this.behind = "";
	}
	
	public String toStringOfIDs (PriorityQueue<BlockWrapper> blocks){
		List<Integer> output = new ArrayList<>();
		for(BlockWrapper b : blocks){
			output.add(b.block.id);
		}
		return output.toString();	
	}
	
	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append("This is the block " + name + "\n");
		out.append("\tLeft: "+ left.length());
		out.append("\tRight: " + right.length());
		out.append("\tFront: " + front.length());
		out.append("\tBehind: " + behind.length());
		
		return out.toString();
	}
}
