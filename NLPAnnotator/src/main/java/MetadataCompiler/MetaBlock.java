package MetadataCompiler;

import java.util.LinkedList;
import java.util.PriorityQueue;

import SpatialRelationGenerator.BlockWrapper;

public class MetaBlock {
	
	int id;	
	String name;
	double x;
	double y;
	double z;
	

	public LinkedList<MetaBlock> left;
	public LinkedList<MetaBlock> right;
	public LinkedList<MetaBlock> front;
	public LinkedList<MetaBlock> behind;
	
	
	public MetaBlock(int id, double x1, double y1, double z1, String name){
		this.id = id;
		this.x = x1;
		this.y = y1;
		this.z = z1;
		this.name = name;
		
		this.left = new LinkedList<>();
		this.right = new LinkedList<>();
		this.front = new LinkedList<>();
		this.behind = new LinkedList<>();
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
