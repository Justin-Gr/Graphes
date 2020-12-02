package GraphStructure;

import java.util.Arrays;

public class Edge {
	private final int id;
	private int indexInitialVertex;
	private int indexFinalVertex;
	private final int valuesNb;
	private double[] values;
	
	
	public Edge(int id, int indexInitialVertex, int indexFinalVertex, int valuesNb) {
		this.id = id;
		this.indexInitialVertex = indexInitialVertex;
		this.indexFinalVertex = indexFinalVertex;
		this.valuesNb = valuesNb;
		this.values = new double[valuesNb];
	}
	
	public Edge reverse() {
		Edge opposite = new Edge(-id, indexFinalVertex, indexInitialVertex, valuesNb);
		opposite.values = values;
		return opposite;
	}
	

	// GETTERS
	
	public int getId() {
		return id;
	}
	
	public int getIndexInitialVertex() {
		return indexInitialVertex;
	}
	
	public int getIndexFinalVertex() {
		return indexFinalVertex;
	}
	
	public int getNbValues() {
		return valuesNb;
	}
	
	public double[] getValues() {
		return values;
	}
	public double getValue(int i) {
		return values[i];
	}
	
	
	// SETTERS
	
	public void setIndexInitialVertex(int indexInitialVertex) {
		this.indexInitialVertex = indexInitialVertex;
	}
	
	public void setIndexFinalVertex(int indexFinalVertex) {
		this.indexFinalVertex = indexFinalVertex;
	}
	
	public void setValues(double[] values) {
		this.values = values;
	}
	
	public void setValue(int i, double value) {
		this.values[i] = value;
	}
	
	public String toString() {
		return("( " + indexInitialVertex + " -> " + indexFinalVertex + " ) valeurs : " + Arrays.toString(getValues()));
	}
}
