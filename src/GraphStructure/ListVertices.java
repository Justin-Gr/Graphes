package GraphStructure;

import java.util.ArrayList;
import java.util.List;

public class ListVertices {

	private List<Vertex> list = new ArrayList<Vertex>();
	private double value;
	
	public ListVertices(List<Vertex> list) {
		this.list = list;
		this.value = 0;
	}

	public void add(Vertex vertex) {
		this.list.add(vertex);
	}
	
	public Vertex get(int i) {
		return list.get(i);
	}
	
	public void set(int i, Vertex vertex) {
		this.list.set(i, vertex);
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double calcSum(int i) {
		value = 0;
		for (Vertex v : list) {
			value += v.getValue(i);
		}
		return value;
	}
	
}
