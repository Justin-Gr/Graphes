package GraphStructure;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class ListEdges extends ArrayList<Edge> {
	
	private double value;
	
	public ListEdges() {
		this.value = 0;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public double calcSum(int i) {
		this.value = 0;
		this.forEach((edge) -> value += edge.getValue(i));
		return this.value;
	}
	
}
