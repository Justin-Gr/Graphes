package GraphStructure;

import java.util.ArrayList;
import java.util.List;

public class ListEdges {
	private List<Edge> list = new ArrayList<Edge>();
	private double value;
	
	public ListEdges(List<Edge> list) {
		this.list = list;
		this.value = 0;
	}
	
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
	
	public Edge get(int i) {
		return list.get(i);
	}
	
	public double getValue() {
		return this.value;
	}
	
	// SETTERS
	
	public void set(int i, Edge e) {
		this.list.set(i, e);
	}
}
