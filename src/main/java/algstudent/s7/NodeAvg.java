package algstudent.s7;

import java.util.ArrayList;
import java.util.UUID;

import algstudent.s6.ImageAverager;

public class NodeAvg extends Node{

	private ImageAverager manager;
	
	private double prev_zncc;
	
	public NodeAvg(ImageAverager imgAvg) {
		this.setManager(imgAvg);
	}
	
	public NodeAvg(ImageAverager imgAvg,int depth,UUID parentID, double prev_zncc) {
		this.setManager(imgAvg);
		this.depth = depth;
		this.parentID = parentID;
		
		this.prev_zncc = prev_zncc;
		
		calculateHeuristicValue();
	}
	
	
	@Override
	public void calculateHeuristicValue() {
		if(prune()) {
			heuristicValue=Integer.MAX_VALUE;
		}else {
			heuristicValue = (int) (manager.zncc*100);
		}
		
	}

	private boolean prune() {
		return manager.zncc < prev_zncc;
	}

	@Override
	public ArrayList<Node> expand() {
		
		ArrayList<Node> res = new ArrayList<Node>();
		
		for (int k=0; k<3; k++) {
			ImageAverager avg = new ImageAverager(manager,depth,k);
			NodeAvg temp=new NodeAvg(avg, depth+1, this.getID(), manager.zncc());
			res.add(temp);
		}
		
		return res;
	}
	
	

	@Override
	public boolean isSolution() {
		return depth == manager.getnImgs();
	}

	public ImageAverager getManager() {
		return manager;
	}

	public void setManager(ImageAverager manager) {
		this.manager = manager;
	}

}
