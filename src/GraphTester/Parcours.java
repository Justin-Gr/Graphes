package GraphTester;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import GraphStructure.Graph;
import GraphStructure.Vertex;

public abstract class Parcours {
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
			ArrayList<Vertex> neighbors = g.getNeighbors(v);
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
}
