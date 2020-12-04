package GraphStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Graph {

	private String name;
	private boolean directed;
	private int verticesNb;
	private int edgesNb;
	private ListVertices listVertices;
	private ListEdges listEdges;
	private List<LinkedList<Edge>> listAdjacent;

	public Graph(String name, boolean directed) {
		this.name = name;
		this.directed = directed;
		this.verticesNb = 0;
		this.edgesNb = 0;
		this.listVertices = new ListVertices();
		this.listEdges = new ListEdges();
		this.listAdjacent = new ArrayList<LinkedList<Edge>>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}

	public int getVerticesNb() {
		return verticesNb;
	}

	public void setVerticesNb(int verticesNb) {
		this.verticesNb = verticesNb;
	}

	public int getEdgesNb() {
		return edgesNb;
	}

	public void setEdgesNb(int edgesNb) {
		this.edgesNb = edgesNb;
	}

	public Vertex getVertex(int i) {
		return listVertices.get(i);
	}

	public void addVertex(Vertex vertex) {
		this.listVertices.add(vertex);
		
		this.listAdjacent.add(vertex.getId(), new LinkedList<Edge>());
		this.verticesNb++;
	}

	public Edge getEdge(int i) {
		return listEdges.get(i);
	}

	public void addEdge(Edge edge) {
		this.edgesNb++;
		this.listEdges.add(edge);

		int idInitial = edge.getIndexInitialVertex();
		int idFinal = edge.getIndexFinalVertex();

		LinkedList<Edge> successorsA = listAdjacent.get(idInitial);

		if (!successorsA.contains(edge)) {
			successorsA.add(edge);
		}

		if (!this.directed) {
			LinkedList<Edge> successorsB = listAdjacent.get(idFinal);
			Edge opposite = edge.reverse();
			if (!successorsB.contains(opposite)) {
				successorsB.add(opposite);
				this.edgesNb++;
				this.listEdges.add(opposite);
			}
		}
	}

	public LinkedList<Edge> getListAdjacent(int i) {
		return this.listAdjacent.get(i);
	}

	public void setListAdjacent(int i, LinkedList<Edge> listAdjacent) {
		this.listAdjacent.set(i, listAdjacent);
	}

	public ListVertices getListVertices() {
		return this.listVertices;
	}
	
	public ListEdges getListEdges() {
		return this.listEdges;
	}

	public ListEdges getListEdges() {
		return this.listEdges;
	}
	
	public List<Vertex> getNeighbors(Vertex vertex) {
		LinkedList<Edge> list = listAdjacent.get(vertex.getId());

		return list.stream().map(Edge::getIndexFinalVertex).map(this::getVertex).collect(toList());
	}

	public void printListAdjacent() {
		for (int i = 0; i < this.listAdjacent.size(); i++) {
			System.out.print("Les successeurs du sommet n�" + i + " : [ ");
			for (int j = 0; j < this.listAdjacent.get(i).size(); j++) {
				System.out.print(this.listAdjacent.get(i).get(j) + " ");
			}
			System.out.println("]");
		}
	}

	/**
	 * Calcule et retourne la distance entre deux sommets
	 * Les sommets doivent poss�der une valeur de latitude et de longitude
	 * 
	 * @param a sommet 1
	 * @param b sommet 2
	 * @return la distance entre a et b
	 */
	public static double calcDist(Vertex a, Vertex b) {
		if(a.equals(b)) {
			return 0.0;
		}
		double latA = Math.toRadians(a.getValue(2));
		double latB = Math.toRadians(b.getValue(2));
		double deltaLng = Math.toRadians(b.getValue(1) - a.getValue(1));

		// System.out.println("Latitude a = " + latA + " Latitude b = " + latB);
		// System.out.println("Delta lng = " + deltaLng);
		double rayonTerre = 6378.137;

		double dist = rayonTerre
				* Math.acos(Math.sin(latA) * Math.sin(latB) + Math.cos(latA) * Math.cos(latB) * Math.cos(deltaLng));
		return dist;
	}
	
	public int getVertexByName(String name) {
		for(Vertex v : listVertices) {
			if (v.getName().equals(name)) {
				return v.getId();
			}
		}
		return -1;
	}
}
