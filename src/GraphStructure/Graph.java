package GraphStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	
	private boolean directed;
	private int verticesNb;
	private int edgesNb;
	private ListVertices listVertices;
	private ListEdges listEdges;
	private List<LinkedList<Integer>> listAdjacent = new ArrayList<LinkedList<Integer>>();
	
	public Graph(boolean directed, ListVertices listVertices, ListEdges listEdges, List<LinkedList<Integer>> listAdjacent) {
		this.directed = directed;
		this.listVertices = listVertices;
		this.listEdges = listEdges;
		this.listAdjacent = listAdjacent;
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

	public void setVertex(int i, Vertex vertex) {
		this.listVertices.set(i, vertex);
	}

	public Edge getEdges(int i) {
		return listEdges.get(i);
	}

	public void setEdges(int i, Edge edge) {
		this.listEdges.set(i, edge);
	}

	public LinkedList<Integer> getListAdjacent(int i) {
		return listAdjacent.get(i);
	}

	public void setListAdjacent(int i, LinkedList<Integer> listAdjacent) {
		this.listAdjacent.set(i, listAdjacent);
	}
	
}
