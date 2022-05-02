package algstudent.s7;

import java.util.ArrayList;

public class BranchAndBoundAvg  extends BranchAndBound{

	public BranchAndBoundAvg(NodeAvg node) {
		rootNode = node;
	}
	
	public void branchAndBound(Node rootNode) { 
		ds.insert(rootNode); //First node to be explored
	
		pruneLimit = rootNode.initialValuePruneLimit();

		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();	
			
			ArrayList<Node> children = node.expand(); 
			
			for (Node child : children)
				if (child.isSolution()) {
					int cost = child.getHeuristicValue();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
					} 
				}
				else
					if (child.getHeuristicValue() < pruneLimit) {
						ds.insert(child);
					}
		} //while
		bestNode.getManager().createResults();
	}

	
}
