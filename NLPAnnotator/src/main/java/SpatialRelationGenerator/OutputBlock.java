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
	
	public List<Integer> left;
	public List<Integer> right;
	public List<Integer> front;
	public List<Integer> behind;
	
	public OutputBlock(InnerBlock block){
		this.id = block.id;
		this.name = block.name;
		this.x = block.x;
		this.y = block.y;
		this.z = block.z;
		this.left = toListOfIDs(block.left);
		this.right = toListOfIDs(block.right);
		this.behind = toListOfIDs(block.behind);
		this.front = toListOfIDs(block.front);
	}
	
	
	public OutputBlock(int id, double x1, double y1, double z1, String name){
		this.id = id;
		this.x = x1;
		this.y = y1;
		this.z = z1;
		this.name = name;
		
		this.left = new ArrayList<>();
		this.right = new ArrayList<>();
		this.front = new ArrayList<>();
		this.behind = new ArrayList<>();
	}
	
	public List<Integer> toListOfIDs (PriorityQueue<BlockWrapper> blocks){
		List<Integer> output = new ArrayList<>();
		for(BlockWrapper b : blocks){
			output.add(b.block.id);
		}
		return output;	
	}
	
	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append("This is the block " + name + "\n");
		out.append("\tLeft: "+ left);
		out.append("\tRight: " + right);
		out.append("\tFront: " + front);
		out.append("\tBehind: " + behind);
		
		return out.toString();
	}
}
