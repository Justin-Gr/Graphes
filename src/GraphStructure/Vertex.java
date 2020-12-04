package GraphStructure;

public class Vertex {
	
	private final int id;
	private String name;
	private final int valuesNb;
	private double[] values;
	
	public Vertex(int id, String name, int valuesNb) {
		this.id = id;
		this.name = name;
		this.valuesNb = valuesNb;
		this.values = new double[valuesNb];
	}
	
	public Vertex(int id, String name, double population, double lng, double lat) {
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
		return "id : " + id + " name : " + name + " pop : " + getValue(0) + " lng : " + getValue(1) + " lat : " + getValue(2);
	}
	
}
