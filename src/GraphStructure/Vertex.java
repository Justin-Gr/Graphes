package GraphStructure;

public class Vertex {
	
	private final int id;
	private String name;
	private int degree;
	private int degreePos;
	private int degreeNeg;
	private int color;
	private final int valuesNb;
	private double[] values;
	
	public Vertex(int id, String name, int valuesNb) {
		this.id = id;
		this.name = name;
		this.valuesNb = valuesNb;
		this.values = new double[valuesNb];
	}
	
	public Vertex(int id, String name, double lat, double lng, double population) {
		this(id, name, 3);
		this.values[0] = population;
		this.values[1] = lng;
		this.values[2] = lat;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getDegree() {
		return degree;
	}
	
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public int getDegreePos() {
		return degreePos;
	}
	
	public void setDegreePos(int degreePos) {
		this.degreePos = degreePos;
	}
	
	public int getDegreeNeg() {
		return degreeNeg;
	}
	
	public void setDegreeNeg(int degreeNeg) {
		this.degreeNeg = degreeNeg;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getValuesNb() {
		return valuesNb;
	}
	
	public double getValue(int i) {
		return values[i];
	}
	
	public void setValue(int i, double value) {
		this.values[i] = value;
	}
	
	public String toString() {
		return "id : " + id + " name : " + name + " lng : " + getValue(1) + " lat : " + getValue(2);
	}
	
}
