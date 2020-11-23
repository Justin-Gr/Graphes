package GraphStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ToolBox.FibonacciHeap;
import ToolBox.FibonacciHeap.Entry;

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
	private List<LinkedList<Edge>> listAdjacent;

	public Graph(String name, boolean directed, int verticesNb, int verticesValuesNb, int edgesNb, int edgesValuesNb) {
		this.name = name;
		this.directed = directed;
		this.verticesNb = verticesNb;
		this.verticesValuesNb = verticesValuesNb;
		this.edgesNb = edgesNb;
		this.edgesValuesNb = edgesValuesNb;
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

			this.listAdjacent.add(vertex.getId(), new LinkedList<Edge>());
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

			LinkedList<Edge> successorsA = listAdjacent.get(idInitial);

			if (!successorsA.contains(edge)) {
				successorsA.add(edge);
			}

			if (!this.directed) {
				LinkedList<Edge> successorsB = listAdjacent.get(idFinal);
				Edge opposite = edge.opposite();
				if (!successorsB.contains(opposite)) {
					successorsB.add(opposite);
				}
			}
		}
	}

	public LinkedList<Edge> getListAdjacent(int i) {
		return listAdjacent.get(i);
	}

	public void setListAdjacent(int i, LinkedList<Edge> listAdjacent) {
		this.listAdjacent.set(i, listAdjacent);
	}

	public ListVertices getListVertices() {
		return this.listVertices;
	}

	public List<Vertex> getNeighbors(Vertex vertex) {
		LinkedList<Edge> list = listAdjacent.get(vertex.getId());

		return list.stream().map(Edge::getIndexFinalVertex).map(this::getVertex).collect(toList());
	}

	public void printListAdjacent() {
		for (int i = 0; i < this.listAdjacent.size(); i++) {
			System.out.print("Les successeurs du sommet n°" + i + " : [ ");
			for (int j = 0; j < this.listAdjacent.get(i).size(); j++) {
				System.out.print(this.listAdjacent.get(i).get(j) + " ");
			}
			System.out.println("]");
		}
	}

	public double[] dijkstra(Vertex s) {
		ListVertices z = (ListVertices) this.listVertices.clone();
		z.remove(s);
		double[] dist = new double[this.verticesNb];

		// Initialisation
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[s.getId()] = 0;
		for (Edge e : this.listAdjacent.get(s.getId())) {
			dist[e.getIndexFinalVertex()] = e.getValue(0);
		}

		while (!z.isEmpty()) {
			double distMin = Double.MAX_VALUE;
			int idMin = -1;

			for (int i = 0; i < dist.length; i++) {
				if (z.contains(getVertex(i)) && dist[i] < distMin) {
					idMin = i;
					distMin = dist[i];
				}
			}

			if (idMin == -1) {
				break;
			}

			Vertex x = getVertex(idMin);
			z.remove(x);

			for (Edge e : this.listAdjacent.get(x.getId())) {
				int i = e.getIndexFinalVertex();
				if (z.contains(getVertex(i))) {
					if (dist[x.getId()] + e.getValue(0) < dist[i]) {
						dist[i] = dist[x.getId()] + e.getValue(0);
					}
				}
			}
		}
		return dist;
	}

	public double[] aEtoile(Vertex s, Vertex d) {
		ListVertices z = (ListVertices) this.listVertices.clone();
		z.remove(s);
		double[] dist = new double[this.verticesNb];
		double[] heuristic = new double[this.verticesNb];

		// Initialisation
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[s.getId()] = 0;
		for (Edge e : this.listAdjacent.get(s.getId())) {
			dist[e.getIndexFinalVertex()] = e.getValue(0);
		}

		// Initialisation de l'heuristique
		for (int i = 0; i < verticesNb; i++) {
			heuristic[i] = calcDist(getVertex(i), d);
		}

		while (!z.isEmpty()) {
			double distMin = Double.MAX_VALUE;
			int idMin = -1;

			for (int i = 0; i < dist.length; i++) {
				if (z.contains(getVertex(i)) && dist[i] + heuristic[i] < distMin) {
					idMin = i;
					distMin = dist[i];
				}
			}

			if (idMin == -1) {
				break;
			}

			Vertex x = getVertex(idMin);
			z.remove(x);

			for (Edge e : this.listAdjacent.get(x.getId())) {
				int i = e.getIndexFinalVertex();
				if (z.contains(getVertex(i))) {
					if (dist[x.getId()] + e.getValue(0) < dist[i]) {
						dist[i] = dist[x.getId()] + e.getValue(0);
					}
				}
			}
		}
		return dist;
	}

	public static double calcDist(Vertex a, Vertex b) {
		double latA = Math.toRadians(a.getLat());
		double latB = Math.toRadians(b.getLat());
		double deltaLng = Math.toRadians(b.getLng() - a.getLng());
		
		System.out.println("Latitude a = " + latA + " Latitude b = " + latB);
		System.out.println("Delta lng = " + deltaLng);
		double rayonTerre = 6378.137;
		
		return rayonTerre
				* Math.acos(Math.sin(latA) * Math.sin(latB) + Math.cos(latA) * Math.cos(latB) * Math.cos(deltaLng));
	}
	
	public double[] dijkstraFibonacci(Vertex s) {
		double[] dist = new double[this.verticesNb];
		
		FibonacciHeap<Vertex> z = new FibonacciHeap<Vertex>();
		Map<Integer, Entry<Vertex>> vertexEntries = new HashMap<Integer, Entry<Vertex>>();
		for (Vertex vertex : listVertices) {
			if (vertex != s) {
				Entry<Vertex> entry = z.enqueue(vertex, Double.MAX_VALUE);
				vertexEntries.put(vertex.getId(), entry);
			}
		}
		
		// Initialisation
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[s.getId()] = 0;
		
		for (Edge e : this.listAdjacent.get(s.getId())) {
			int vertexId = e.getIndexFinalVertex();
			dist[vertexId] = e.getValue(0);
			
			Entry<Vertex> entry = vertexEntries.get(vertexId);
			z.decreaseKey(entry, e.getValue(0));
		}

		while (!z.isEmpty()) {
			int vertexId = z.dequeueMin().getValue().getId();
			vertexEntries.remove(vertexId);

			for (Edge e : this.listAdjacent.get(vertexId)) {
				int i = e.getIndexFinalVertex();
				Entry<Vertex> entry = vertexEntries.get(i);
				
				if (entry != null) {
					double newValue = dist[vertexId] + e.getValue(0);
					if (newValue < dist[i]) {
						dist[i] = newValue;
						z.decreaseKey(entry, newValue);
					}
				}
			}
		}
		return dist;
	}
	
}
