package GraphTester;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
		marque[a.getId()] = true;
		pile.add(a);
		
		Vertex v = a;
		while(!pile.empty()) {
			List<Vertex> neighbors = g.getNeighbors(v);
			for (Vertex n : neighbors) {
				if (!marque[n.getId()]) {
					marque[n.getId()] = true;
					pile.add(n);
				}
			}
			v = pile.pop();
			traite[v.getId()] = p;
			p++;
		}
	}
}
