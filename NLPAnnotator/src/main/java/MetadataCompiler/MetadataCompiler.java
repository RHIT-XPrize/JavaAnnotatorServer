package MetadataCompiler;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class MetadataCompiler {
	
	public MetaBlock chooseBlock(List<String> relationKeywords, List<Integer> degrees, MetaBlock startBlock){
		MetaBlock block = null;
		
		//make queue with initial starting block
		Queue<MetaBlock> initialBlockQueue = new LinkedList<MetaBlock>();
		initialBlockQueue.add(startBlock);

		
		//index is the number of relation keywords gone through thusfar
		int index = 0;
		Queue<MetaBlock> returnedFinalList = recursivlySearchForBlock(initialBlockQueue,relationKeywords,degrees,index);
		
		if(returnedFinalList == null){
			//nothing found
		}
		else
		{
			block = returnedFinalList.poll();
		}
		
		return block;
	}
	
	private Queue<MetaBlock> recursivlySearchForBlock(Queue<MetaBlock> initialBlockQueue, List<String> relationKeywords, List<Integer> degrees, int index) {
		Queue<MetaBlock> blocks = null;
		
		if(index == relationKeywords.size())
		{
			return initialBlockQueue;
		}
		
		while(!initialBlockQueue.isEmpty() && blocks == null) 
		{
			MetaBlock currentBlock = initialBlockQueue.poll();
			
			try {
				Queue<MetaBlock> nextQueue = useSpatialRelation(currentBlock,relationKeywords.get(index),degrees.get(index));
				
				blocks = recursivlySearchForBlock(nextQueue,relationKeywords,degrees,index+1);
			} catch (Exception e) {
				//no blocks found in the given direction
			}
		}
		
		return blocks;
	}

	public Queue<MetaBlock> useSpatialRelation (MetaBlock current, String spatialRelation,int degree) throws Exception{
		Queue<MetaBlock> out = new LinkedList<MetaBlock>();
		
		//Get corresponding 
		switch(spatialRelation){
			case "LEFT":
				out.addAll(current.left);
				break;
			case "RIGHT":
				out.addAll(current.right);
				break;
			case "FRONT":
				out.addAll(current.front);
				break;
			case "BEHIND":
				out.addAll(current.behind);
				break;
			//currently not able to sort by the distances between lists, later cna re calc distances to determine the closest blocks.
//			case "ADJACENT":
//				out.addAll(current.left);
//				out.addAll(current.right);
//				out.addAll(current.front);
//				out.addAll(current.behind);
//				break;
			default:
				throw new Exception("Unrecoginized spatial relation");
		}
		//Figure get to the ith block away, where i is the degree.
		int i = degree;
		while(i > 1){
			out.poll();
			i--;
			if(out.isEmpty()){
				throw new Exception("Invalid degree: " + degree);
			}
		}
		
		
		if(out.isEmpty()){
			throw new Exception("No blocks found");
		}
		
		
		return out;
	}

}
