package GraphStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Graph {
	
	private String name;
	private boolean directed;
	private int verticesNb;
	private int verticesValuesNb;
	private int edgesNb;
	private int edgesValuesNb;
	private ListVertices listVertices;
	private ListEdges listEdges;
	private List<LinkedList<Integer>> listAdjacent;
	
	public Graph(String name, boolean directed, int verticesNb, int verticesValuesNb, int edgesNb, int edgesValuesNb) {
		this.name = name;
		this.directed = directed;
		this.verticesNb = verticesNb;
		this.verticesValuesNb = verticesValuesNb;
		this.edgesNb = edgesNb;
		this.edgesValuesNb = edgesValuesNb;
		this.listVertices = new ListVertices();
		this.listEdges = new ListEdges();
		this.listAdjacent  = new ArrayList<LinkedList<Integer>>();
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
	
	public int getVerticesValuesNb() {
		return verticesValuesNb;
	}

	public void setVerticesValuesNb(int verticesValuesNb) {
		this.verticesValuesNb = verticesValuesNb;
	}

	public int getEdgesNb() {
		return edgesNb;
	}

	public void setEdgesNb(int edgesNb) {
		this.edgesNb = edgesNb;
	}

	public int getEdgesValuesNb() {
		return edgesValuesNb;
	}

	public void setEdgesValuesNb(int edgesValuesNb) {
		this.edgesValuesNb = edgesValuesNb;
	}
	
	public Vertex getVertex(int i) {
		return listVertices.get(i);
	}

	public void addVertex(Vertex vertex) {
		if (!listVertices.contains(vertex)) {
			this.listVertices.add(vertex);
			
			this.listAdjacent.add(vertex.getId(), new LinkedList<Integer>());
		}
	}

	public Edge getEdge(int i) {
		return listEdges.get(i);
	}

	public void addEdge(Edge edge) {
		if (!listEdges.contains(edge)) {
			this.listEdges.add(edge);
			
			int idInitial = edge.getIndexInitialVertex();
			int idFinal = edge.getIndexFinalVertex();
			
			LinkedList<Integer> successorsA = listAdjacent.get(idInitial);
			
			if(!successorsA.contains(idFinal)) {
				successorsA.add(idFinal);
			}
			
			if(!this.directed) {
				LinkedList<Integer> successorsB = listAdjacent.get(idFinal);
				
				if(!successorsB.contains(idInitial)) {
					successorsB.add(idInitial);
				}
			}
		}
	}

	public LinkedList<Integer> getListAdjacent(int i) {
		return listAdjacent.get(i);
	}

	public void setListAdjacent(int i, LinkedList<Integer> listAdjacent) {
		this.listAdjacent.set(i, listAdjacent);
	}
	
	public ListVertices getListVertices() {
		return this.listVertices;
	}
	
	public List<Vertex> getNeighbors(Vertex vertex) {
		LinkedList<Integer> list = listAdjacent.get(vertex.getId());
			
		return list.stream()
				.map(this::getVertex)
				.collect(toList());
	}
	
}
