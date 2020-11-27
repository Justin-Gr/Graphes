package GraphTester;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import GraphStructure.Graph;
import GraphStructure.Vertex;

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
			double[] distances = g.dijkstraFibonacci(ville);
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
