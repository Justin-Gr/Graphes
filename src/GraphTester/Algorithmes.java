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
import GraphStructure.ListEdges;
import GraphStructure.Vertex;
import ToolBox.FibonacciHeap;
import ToolBox.FibonacciHeap.Entry;

public abstract class Algorithmes {
	
	/**
	 * Affiche le parcours en largeur d'un graphe donné
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param a le sommet de départ
	 */
	public static void ParcoursLargeur(Graph g, Vertex a) {
		boolean[] marque = new boolean[g.getVerticesNb()]; // garde trace des sommets visités
		int[] traite = new int[g.getVerticesNb()]; // garde trace de l'ordre de passage de chaque sommet
		int p = 1;
		
		Queue<Vertex> f = new LinkedList<Vertex>();

		// Initialisation
		for (Vertex v : g.getListVertices()) {
			marque[v.getId()] = false;
			traite[v.getId()] = 0;
		}
		marque[a.getId()] = true;
		f.add(a);

		// Tant que la file n'est pas vide
		while (!f.isEmpty()) {
			// On prend le sommet en tête de la file et on parcours ses voisins
			Vertex v = f.peek();
			List<Vertex> neighbors = g.getNeighbors(v);
			for (Vertex n : neighbors) {
				// Si le voisin n'a pas déjà été visité on le marque comme tel et on l'ajoute dans la file
				if (!marque[n.getId()]) {
					marque[n.getId()] = true;
					f.add(n);
				}
			}
			// on garde en mémoire l'ordre de passage pour le sommet actuel et on affiche son id
			traite[v.getId()] = p++;
			System.out.println(v.getId());
			// on retire le sommet de la file
			f.remove();
		}
	}

	/**
	 * Affiche le parcours en profondeur d'un graphe donné
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param a le sommet de départ
	 */
	public static void ParcoursProfondeur(Graph g, Vertex a) {
		boolean[] marque = new boolean[g.getVerticesNb()]; // garde trace des sommets visités
		int[] traite = new int[g.getVerticesNb()]; // garde trace de l'ordre de passage de chaque sommet
		int p = 1;
		
		Stack<Vertex> pile = new Stack<Vertex>();

		// Initialisation
		for (Vertex v : g.getListVertices()) {
			marque[v.getId()] = false;
			traite[v.getId()] = 0;
		}
		pile.add(a);

		// Tant que la pile n'est pas vide
		while (!pile.empty()) {
			// On récupère et retire le sommet en haut de la pile
			Vertex v = pile.pop();

			// Si le sommet n'a pas été visité on le marque comme tel, on garde en mémoire son ordre de passage
			// et on l'affiche dans la console
			if (!marque[v.getId()]) {
				marque[v.getId()] = true;
				traite[v.getId()] = p++;
				System.out.println(v.getId());
			}

			// On ajoute les voisins du sommet actuel dans la pile, à condition qu'ils n'aient pas déjà été visités
			List<Vertex> neighbors = g.getNeighbors(v);
			for (Vertex n : neighbors) {
				if (!marque[n.getId()]) {
					pile.push(n);
				}
			}
		}
	}

	/**
	 * Applique l'algorithme de Dijkstra classique sur le graphe donné
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param s le sommet de départ
	 * @return le tableau des distances minimales de chaque sommet par rapport au point de départ
	 */
	public static double[] dijkstra(Graph g, int s) {
		int verticesNb = g.getVerticesNb();

		// On utilise une hashmap qui associe l'id d'un vertex au vertex lui même
		Map<Integer, Vertex> z = new HashMap<Integer, Vertex>();
		for (Vertex v : g.getListVertices()) {
			if (v.getId() != s) {
				z.put(v.getId(), v);
			}
		}

		double[] dist = new double[verticesNb];

		// Initialisation
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[s] = 0;
		for (Edge e : g.getListAdjacent(s)) {
			dist[e.getIndexFinalVertex()] = e.getValue(0);
		}

		// Tant que l'ensemble Z n'est pas vide
		while (!z.isEmpty()) { // vérification en temps constant
			double distMin = Double.MAX_VALUE;
			int idMin = -1;

			// on cherche le sommet x de Z dont la valeur est minimale
			for (int i : z.keySet()) {
				if (dist[i] < distMin) {
					idMin = i;
					distMin = dist[i];
				}
			}

			// Si tous les sommets ont une valeur infinie on sort de la boucle
			if (idMin == -1) {
				break;
			}

			Vertex x = g.getVertex(idMin);
			z.remove(idMin); // opération en temps constant

			// Pour chaque voisin de x, on regarde s'il est dans l'ensemble Z, et si oui on met à jour sa valeur si c'est nécessaire
			for (Edge e : g.getListAdjacent(x.getId())) {
				int neighborId = e.getIndexFinalVertex();
				if (z.containsKey(neighborId)) { // opération en temps constant
					if (dist[x.getId()] + e.getValue(0) < dist[neighborId]) {
						dist[neighborId] = dist[x.getId()] + e.getValue(0);
					}
				}
			}
		}
		return dist;
	}

	/**
	 * Applique l'algorithme A* sur le graphe donné
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param s le sommet de départ
	 * @param d le sommet d'arrivée
	 * @return la liste des arêtes qui constituent le chemin le plus court de s à d
	 */
	public static ListEdges aEtoile(Graph g, int s, int d) throws Exception {
		int verticesNb = g.getVerticesNb();
		ListEdges chemin = new ListEdges();

		// On utilise une hashmap qui associe l'id d'un vertex au vertex lui même
		Map<Integer, Vertex> z = new HashMap<Integer, Vertex>();
		for (Vertex v : g.getListVertices()) {
			if (v.getId() != s) {
				z.put(v.getId(), v);
			}
		}

		double[] dist = new double[verticesNb];
		double[] estimation = new double[verticesNb];
		int[] predecessor = new int[verticesNb];

		// Initialisation des distances et prédécesseurs
		Arrays.fill(dist, Double.MAX_VALUE);
		Arrays.fill(predecessor, -1);
		dist[s] = 0;
		for (Edge e : g.getListAdjacent(s)) {
			dist[e.getIndexFinalVertex()] = e.getValue(0);
			predecessor[e.getIndexFinalVertex()] = s;
		}

		// Initialisation de l'estimation
		for (int i = 0; i < verticesNb; i++) {
			estimation[i] = Graph.calcDist(g.getVertex(i), g.getVertex(d));
		}

		// Tant que l'ensemble Z n'est pas vide
		while (!z.isEmpty()) {
			double distMin = Double.MAX_VALUE;
			int idMin = -1;

			// on cherche le sommet x de Z dont la valeur estimée est minimale
			for (int i : z.keySet()) {
				if (dist[i] + estimation[i] < distMin) {
					idMin = i;
					distMin = dist[i] + estimation[i];
				}
			}

			// Si la destination est inatteignable
			if (idMin == -1) {
				throw new Exception("Destination inatteignable !");
			} else if (idMin == d) { // Si la destination est atteinte
				int index = d;
				while (index != -1) {
					int previous = predecessor[index];
					if (previous == -1) {
						previous = s;
						break;
					}
					Edge e = new Edge (0, previous, index, 1);
					e.setValue(0, dist[index] - dist[previous]);
					chemin.add(e);
					index = predecessor[index];
				}
				return chemin.reverse();
			}

			Vertex x = g.getVertex(idMin);
			z.remove(idMin);

			for (Edge e : g.getListAdjacent(x.getId())) {
				int neighborId = e.getIndexFinalVertex();
				if (z.containsKey(neighborId)) {
					if (dist[x.getId()] + e.getValue(0) < dist[neighborId]) {
						dist[neighborId] = dist[x.getId()] + e.getValue(0);
						predecessor[neighborId] = x.getId();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Applique l'algorithme de Dijkstra sur le graphe donné.
	 * Utilise pour cela un tas de Fibonacci.
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param s le sommet de départ
	 * @return le tableau des distances minimales de chaque sommet par rapport au point de départ
	 */
	public static double[] dijkstraFibonacci(Graph g, int s) {
		int verticesNb = g.getVerticesNb();

		double[] dist = new double[verticesNb];
		boolean[] marque = new boolean[verticesNb];

		// La hashmap va nous permettre d'accéder aux sommets stockés dans le tas de Fibonacci pour pouvoir les modifier plus tard.
		// On associe pour cela un id de vertex à "l'entrée" du tas de Fibonacci qui lui correspond.
		FibonacciHeap<Vertex> fiboHeap = new FibonacciHeap<Vertex>();
		Map<Integer, Entry<Vertex>> vertexEntries = new HashMap<Integer, Entry<Vertex>>();

		// Initialisation
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[s] = 0;
		
		// On met s dans le tas
		Entry<Vertex> initialEntry = fiboHeap.enqueue(g.getVertex(s), 0);
		vertexEntries.put(s, initialEntry);
		
		// Tant que le tas n'est pas vide
		while (!fiboHeap.isEmpty()) {
			// On récupère et supprime du tas le sommet de valeur minimale
			int vertexId = fiboHeap.dequeueMin().getValue().getId();
			vertexEntries.remove(vertexId);
			marque[vertexId] = true; // on le marque comme visité

			// On parcourt les voisins du sommet
			for (Edge e : g.getListAdjacent(vertexId)) {
				int neighborId = e.getIndexFinalVertex();

				if (marque[neighborId] == false) { // Si le voisin n'a pas été visité
					double newValue = dist[vertexId] + e.getValue(0);
					if (newValue < dist[neighborId]) { // Si on doit mettre à jour sa valeur

						// Soit le voisin n'était pas dans le tas de Fibonacci et on l'ajoute avec sa valeur modifiée,
						// soit le voisin était déjà dans le tas de Fibonacci et on ne fait que modifier sa valeur
						if (dist[neighborId] < Double.MAX_VALUE) {
							Entry<Vertex> entry = vertexEntries.get(neighborId);
							fiboHeap.decreaseKey(entry, newValue);
						} else {
							Entry<Vertex> entry = fiboHeap.enqueue(g.getVertex(neighborId), newValue);
							vertexEntries.put(neighborId, entry);
						}
						dist[neighborId] = newValue;
						
					}
				}
			}
		}
		return dist;
	}

	/**
	 * Problème de la question 6
	 * Détermine la commune dont la moyenne de ses distances avec les villes de plus de n habitants est minimale
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param n le seuil minimal pour considérer une ville comme "grande"
	 */
	public static void VRP1(Graph g, int n) {
		int nbCommunes = g.getVerticesNb();

		// On cherche les villes de plus de n habitants
		List<Vertex> villes = new ArrayList<Vertex>();
		for (int i = 0; i < nbCommunes; i++) {
			Vertex v = g.getVertex(i);
			if (v.getValue(0) >= n) {
				villes.add(v);
			}
		}

		// On applique dijkstra pour chaque ville et on stocke les tableaux des distances obtenus
		List<double[]> distancesVilles = new ArrayList<double[]>();
		for (Vertex ville : villes) {
			double[] distances = Algorithmes.dijkstraFibonacci(g, ville.getId());
			distancesVilles.add(distances);
		}

		// On fait la moyenne des différents tableaux des distances de chaque ville
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

		// On cherche la commune dont la distance moyenne à chaque ville est minimale
		int indexMin = -1;
		double moyenneMin = Double.MAX_VALUE;
		for (int i = 0; i < nbCommunes; i++) {
			if (moyennes[i] < moyenneMin && moyennes[i] != Double.MAX_VALUE) {
				indexMin = i;
				moyenneMin = moyennes[i];
			}
		}

		// Affichage du résultat
		if (moyenneMin != Double.MAX_VALUE) {
			System.out.println(g.getVertex(indexMin).getName() + " a la plus petite moyenne des distances de : "
					+ (int) moyenneMin + "km");
		} else {
			System.out.println(
					"Le graphe n'est pas suffisamment connexe pour permettre l'accès à toutes les grandes villes");
		}
	}

	public static void VRP2(Graph g, int n) {
		// TODO
	}
}
