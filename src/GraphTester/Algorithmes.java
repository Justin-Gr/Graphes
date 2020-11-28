package GraphTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import GraphStructure.Edge;
import GraphStructure.Graph;
import GraphStructure.Vertex;
import ToolBox.FibonacciHeap;
import ToolBox.FibonacciHeap.Entry;

public abstract class Algorithmes {
	public static void ParcoursLargeur(Graph g, Vertex a) {
		boolean[] marque = new boolean[g.getVerticesNb()];
		int[] traite = new int[g.getVerticesNb()];
		Queue<Vertex> f = new LinkedList<Vertex>();
		int p = 1;

		// Initialisation
		for(Vertex v : g.getListVertices()) {
			marque[v.getId()] = false;
			traite[v.getId()] = 0;
		}
		marque[a.getId()] = true;
		f.add(a);
		
		while(!f.isEmpty()) {
			Vertex v = f.peek();
			List<Vertex> neighbors = g.getNeighbors(v);
			for(Vertex n : neighbors) {
				if(!marque[n.getId()]) {
					marque[n.getId()] = true;
					f.add(n);
				}
			}
			traite[v.getId()] = p;
			System.out.println(v.getId());
			f.remove();
			p++;
		}
	}
	
	public static void ParcoursProfondeur(Graph g, Vertex a) {
		boolean[] marque = new boolean[g.getVerticesNb()];
		int[] traite = new int[g.getVerticesNb()];
		Stack<Vertex> pile = new Stack<Vertex>();
		int p = 1;
		
		// Initialisation
		for(Vertex v : g.getListVertices()) {
			marque[v.getId()] = false;
			traite[v.getId()] = 0;
		}
		pile.add(a);
		
		Vertex v;
		while(!pile.empty()) {			
			v = pile.pop();
			
			if (!marque[v.getId()]) {
				marque[v.getId()] = true;
				traite[v.getId()] = p++;
				System.out.println(v.getId());
			}
			
			List<Vertex> neighbors = g.getNeighbors(v);
			for (Vertex n : neighbors) {
				if (!marque[n.getId()]) {
					pile.push(n);
				}
			}
		}
	}
	
	public static double[] dijkstra(Graph g, int s) {
		
//		boolean[] marque = new boolean[this.verticesNb];
//		int nbSommetTraite = 0;
//
//		marque[s.getId()] = true;
//		nbSommetTraite++;
//		double[] dist = new double[this.verticesNb];
//
//		// Initialisation
//		Arrays.fill(dist, Double.MAX_VALUE);
//		dist[s.getId()] = 0;
//		for (Edge e : this.listAdjacent.get(s.getId())) {
//			dist[e.getIndexFinalVertex()] = e.getValue(0);
//		}
//
//		while (nbSommetTraite != this.verticesNb) {
//			double distMin = Double.MAX_VALUE;
//			int idMin = -1;
//
//			for (int i = 0; i < dist.length; i++) {
//				if (!marque[i] && dist[i] < distMin) {
//					idMin = i;
//					distMin = dist[i];
//				}
//			}
//
//			if (idMin == -1) {
//				break;
//			}
//
//			Vertex x = getVertex(idMin);
//			marque[idMin] = true;
//			nbSommetTraite++;
//
//			for (Edge e : this.listAdjacent.get(x.getId())) {
//				int i = e.getIndexFinalVertex();
//				if (!marque[i]) {
//					if (dist[x.getId()] + e.getValue(0) < dist[i]) {
//						dist[i] = dist[x.getId()] + e.getValue(0);
//					}
//				}
//			}
//		}
//		return dist;
		
		int verticesNb = g.getVerticesNb();
		
		boolean[] marque = new boolean[verticesNb];
		int nbSommetTraite = 0;

		marque[s] = true;
		nbSommetTraite++;
		double[] dist = new double[verticesNb];

		// Initialisation
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[s] = 0;
		for (Edge e : g.getListAdjacent(s)) {
			dist[e.getIndexFinalVertex()] = e.getValue(0);
		}

		while (nbSommetTraite != verticesNb) {
			double distMin = Double.MAX_VALUE;
			int idMin = -1;

			for (int i = 0; i < dist.length; i++) {
				if (!marque[i] && dist[i] < distMin) {
					idMin = i;
					distMin = dist[i];
				}
			}

			if (idMin == -1) {
				break;
			}

			Vertex x = g.getVertex(idMin);
			marque[idMin] = true;
			nbSommetTraite++;

			for (Edge e : g.getListAdjacent(x.getId())) {
				int siblingId = e.getIndexFinalVertex();
				if (!marque[siblingId]) {
					if (dist[x.getId()] + e.getValue(0) < dist[siblingId]) {
						dist[siblingId] = dist[x.getId()] + e.getValue(0);
					}
				}
			}
		}
		return dist;
	}

	public static double[] aEtoile(Graph g, int s, int d) {
		int verticesNb = g.getVerticesNb();
		
		boolean[] marque = new boolean[verticesNb];
		int nbSommetTraite = 0;
		
		marque[s] = true;
		nbSommetTraite++;
		double[] dist = new double[verticesNb];
		double[] heuristic = new double[verticesNb];

		// Initialisation
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[s] = 0;
		for (Edge e : g.getListAdjacent(s)) {
			dist[e.getIndexFinalVertex()] = e.getValue(0);
		}

		// Initialisation de l'heuristique
		for (int i = 0; i < verticesNb; i++) {
			heuristic[i] = Graph.calcDist(g.getVertex(i), g.getVertex(d));
		}

		while (nbSommetTraite != verticesNb) {
			double distMin = Double.MAX_VALUE;
			int idMin = -1;

			for (int i = 0; i < dist.length; i++) {
				if (!marque[i] && dist[i] + heuristic[i] < distMin) {
					idMin = i;
					distMin = dist[i];
				}
			}

			if (idMin == -1) {
				break;
			}

			Vertex x = g.getVertex(idMin);
			marque[idMin] = true;
			nbSommetTraite++;

			for (Edge e : g.getListAdjacent(x.getId())) {
				int i = e.getIndexFinalVertex();
				if (!marque[i]) {
					if (dist[x.getId()] + e.getValue(0) < dist[i]) {
						dist[i] = dist[x.getId()] + e.getValue(0);
					}
				}
			}
		}
		return dist;
	}
	
	public static double[] dijkstraFibonacci(Graph g, int s) {
		int verticesNb = g.getVerticesNb();
		
		double[] dist = new double[verticesNb];
		boolean[] marque = new boolean[verticesNb];
		
		FibonacciHeap<Vertex> fiboHeap = new FibonacciHeap<Vertex>();
		Map<Integer, Entry<Vertex>> vertexEntries = new HashMap<Integer, Entry<Vertex>>();
		
		
		// Initialisation
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[s] = 0;
		marque[s] = true;
		
		for (Edge e : g.getListAdjacent(s)) {
			int vertexId = e.getIndexFinalVertex();
			
			dist[vertexId] = e.getValue(0);
			Entry<Vertex> entry = fiboHeap.enqueue(g.getVertex(vertexId), e.getValue(0));
			
			vertexEntries.put(vertexId, entry);
		}
		
		while (!fiboHeap.isEmpty()) {
			int vertexId = fiboHeap.dequeueMin().getValue().getId();
			vertexEntries.remove(vertexId);
			marque[vertexId] = true;
			
			for (Edge e : g.getListAdjacent(vertexId)) {
				int siblingId = e.getIndexFinalVertex();
				
				if (marque[siblingId] == false) {					
					double newValue = dist[vertexId] + e.getValue(0);
					if (newValue < dist[siblingId]) {

						if (dist[siblingId] < Double.MAX_VALUE) {
							Entry<Vertex> entry = vertexEntries.get(siblingId);
							fiboHeap.decreaseKey(entry, newValue);
						} else {
							Entry<Vertex> entry = fiboHeap.enqueue(g.getVertex(siblingId), newValue);
							vertexEntries.put(siblingId, entry);
						}
						
						dist[siblingId] = newValue;
					}
				}
			}
		}
		
		return dist;
	}
	
	public static void VRP1(Graph g, int n) {
		int nbCommunes = g.getVerticesNb();
		
		// on cherche les villes de plus de n habitants
		List<Vertex> villes = new ArrayList<Vertex>();
		for (int i = 0; i < nbCommunes; i++) {
			Vertex v = g.getVertex(i);
			if (v.getValue(0) >= n) {
				villes.add(v);
			}
		}
		
		// on applique dijkstra pour chaque ville et on stocke les tableaux des distances obtenus
		List<double[]> distancesVilles = new ArrayList<double[]>();
		for (Vertex ville : villes) {
			double[] distances = Algorithmes.dijkstraFibonacci(g, ville.getId());
			distancesVilles.add(distances);
		}
		
		// on fait la moyenne des différents tableaux des distances de chaque ville
		double[] moyennes = new double[nbCommunes];
		for (double[] distancesVille : distancesVilles) {
			for (int i = 0; i < nbCommunes; i++) {
				if (distancesVille[i] < Double.MAX_VALUE) {
					moyennes[i] += distancesVille[i] / distancesVilles.size();
				} else {
					moyennes[i] = Double.MAX_VALUE;
				}
			}
		}
		
		// on cherche la commune dont la distance moyenne à chaque ville est minimale
		int indexMin = -1;
		double moyenneMin = Double.MAX_VALUE;
		for (int i = 0; i < nbCommunes; i++) {
			if (moyennes[i] < moyenneMin && moyennes[i] != Double.MAX_VALUE) {
				indexMin = i;
				moyenneMin = moyennes[i];
			}
		}
		
		if (moyenneMin != Double.MAX_VALUE) {
			System.out.println(g.getVertex(indexMin).getName() + " a la plus petite moyenne des distances de : " + (int) moyenneMin + "km");
		} else {
			System.out.println("Le graphe n'est pas suffisamment connexe pour permettre l'accès à toutes les grandes villes");
		}
	}
	
	public static void VRP2(Graph g, int n) {
		// TODO
	}
}
