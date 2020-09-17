package GraphStructure;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class ListVertices extends ArrayList<Vertex> {

	private double value;
	
	public ListVertices() {
		super();
		this.value = 0;
	}
	
	public double getValue() {
		return value;
	}
	
	public double calcSum(int i) {
		this.value = 0;
		this.forEach((vertex) -> this.value += vertex.getValue(i));
		return this.value;
	}
	
}
