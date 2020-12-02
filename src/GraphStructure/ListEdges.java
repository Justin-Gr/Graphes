package GraphStructure;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class ListEdges extends ArrayList<Edge> {
	
	private double value;
	
	public ListEdges() {
		super();
		this.value = 0;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public double calcSum(int i) {
		this.value = 0;
		this.forEach((edge) -> this.value += edge.getValue(i));
		return this.value;
	}
	
	public ListEdges reverse() {
		ListEdges reversed = new ListEdges();
		for (int i = this.size() - 1; i >= 0; i--) {
			reversed.add(this.get(i));
		}
		return reversed;
	}
	
}
