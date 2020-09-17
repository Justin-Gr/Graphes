package GraphTester;

import java.util.LinkedList;
import java.util.Queue;

import GraphStructure.Graph;
import GraphStructure.Vertex;

public abstract class Parcours {
	public static void ParcoursLargeur(Graph g, Vertex a) {
		boolean[] marque = new boolean[g.getVerticesNb()];
		int[] traite = new int[g.getVerticesNb()];
		Queue<Vertex> f = new LinkedList<Vertex>();
		
		// Initialisation
		for(Vertex v : g.getListVertices()) {
			marque[v.getId()] = false;
			traite[v.getId()] = 0;
		}
		marque[a.getId()] = true;
		f.add(a);
		
		
		while(!f.isEmpty()) {
			Vertex v = f.peek();
			Vertex[] neighbors = g.getNeighbors(a);
		}
	}
}
