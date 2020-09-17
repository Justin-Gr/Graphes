package GraphStructure;

import java.util.ArrayList;

public class ListEdges {
	private ArrayList<Edge> list;
	private double value;
	
	
	public void addEdge(Edge e) {
		this.list.add(e);
	}
	
	public double calcSum(int i) {
		this.value = 0;
		for(Edge e : this.list) {
			this.value += e.getValue(i);
		}
		return this.value;
	}
	
	// GETTERS
	
	public Edge getEdge(int i) {
		return list.get(i);
	}
	
	public double getValue() {
		return this.value;
	}
	
	// SETTERS
	
	public void setEdge(int i, Edge e) {
		this.list.set(i, e);
	}
}
