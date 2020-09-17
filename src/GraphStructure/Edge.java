package GraphStructure;

public class Edge {
	private final int id;
	private int indexInitialVertex;
	private int indexFinalVertex;
	private String nameInitialVertex;
	private String nameFinalVertex;
	private final int valuesNb;
	private double[] values;
	
	
	public Edge(int id, int indexInitialVertex, int indexFinalVertex, String nameInitialVertex, String nameFinalVertex,
			int valuesNb, double[] values) {
		super();
		this.id = id;
		this.indexInitialVertex = indexInitialVertex;
		this.indexFinalVertex = indexFinalVertex;
		this.nameInitialVertex = nameInitialVertex;
		this.nameFinalVertex = nameFinalVertex;
		this.valuesNb = valuesNb;
		this.values = values;
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
	
	public String getNameInitialVertex() {
		return nameInitialVertex;
	}
	
	public String getNameFinalVertex() {
		return nameFinalVertex;
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
	
	public void setNameInitialVertex(String nameInitialVertex) {
		this.nameInitialVertex = nameInitialVertex;
	}
	
	public void setNameFinalVertex(String nameFinalVertex) {
		this.nameFinalVertex = nameFinalVertex;
	}
	
	public void setValues(double[] values) {
		this.values = values;
	}
	
	public void setValue(int i, double value) {
		this.values[i] = value;
	}
}
