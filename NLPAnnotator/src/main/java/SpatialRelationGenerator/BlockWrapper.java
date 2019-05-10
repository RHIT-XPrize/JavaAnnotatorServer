package SpatialRelationGenerator;

public class BlockWrapper implements Comparable<BlockWrapper>{

	public double distance;
	public InnerBlock block;
	
	public BlockWrapper(InnerBlock b, double distance){
		this.distance = distance;
		this.block = b;
	}



	@Override
	public int compareTo(BlockWrapper otherBlock) {
		return (int) (this.distance - otherBlock.distance);
	}
	
	@Override
	public String toString()
	{
		return block.name;
	}
	
}
