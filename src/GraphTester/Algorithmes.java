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
import ToolBox.GraphLoader;

public abstract class Algorithmes {
	
	/**
	 * Affiche le parcours en largeur d'un graphe donn�
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param a le sommet de d�part
	 */
	public static void ParcoursLargeur(Graph g, Vertex a) {
		boolean[] marque = new boolean[g.getVerticesNb()]; // garde trace des sommets visit�s
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
			// On prend le sommet en t�te de la file et on parcours ses voisins
			Vertex v = f.peek();
			List<Vertex> neighbors = g.getNeighbors(v);
			for (Vertex n : neighbors) {
				// Si le voisin n'a pas d�j� �t� visit� on le marque comme tel et on l'ajoute dans la file
				if (!marque[n.getId()]) {
					marque[n.getId()] = true;
					f.add(n);
				}
			}
			// on garde en m�moire l'ordre de passage pour le sommet actuel et on affiche son id
			traite[v.getId()] = p++;
			System.out.println(v.getId());
			// on retire le sommet de la file
			f.remove();
		}
	}

	/**
	 * Affiche le parcours en profondeur d'un graphe donn�
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param a le sommet de d�part
	 */
	public static void ParcoursProfondeur(Graph g, Vertex a) {
		boolean[] marque = new boolean[g.getVerticesNb()]; // garde trace des sommets visit�s
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
			// On r�cup�re et retire le sommet en haut de la pile
			Vertex v = pile.pop();

			// Si le sommet n'a pas �t� visit� on le marque comme tel, on garde en m�moire son ordre de passage
			// et on l'affiche dans la console
			if (!marque[v.getId()]) {
				marque[v.getId()] = true;
				traite[v.getId()] = p++;
				System.out.println(v.getId());
			}

			// On ajoute les voisins du sommet actuel dans la pile, � condition qu'ils n'aient pas d�j� �t� visit�s
			List<Vertex> neighbors = g.getNeighbors(v);
			for (Vertex n : neighbors) {
				if (!marque[n.getId()]) {
					pile.push(n);
				}
			}
		}
	}

	/**
	 * Applique l'algorithme de Dijkstra classique sur le graphe donn�
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param s le sommet de d�part
	 * @return le tableau des distances minimales de chaque sommet par rapport au point de d�part
	 */
	public static double[] dijkstra(Graph g, int s) {
		int verticesNb = g.getVerticesNb();

		// On utilise une hashmap qui associe l'id d'un vertex au vertex lui m�me
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
		while (!z.isEmpty()) { // v�rification en temps constant
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
			z.remove(idMin); // op�ration en temps constant

			// Pour chaque voisin de x, on regarde s'il est dans l'ensemble Z, et si oui on met � jour sa valeur si c'est n�cessaire
			for (Edge e : g.getListAdjacent(x.getId())) {
				int neighborId = e.getIndexFinalVertex();
				if (z.containsKey(neighborId)) { // op�ration en temps constant
					if (dist[x.getId()] + e.getValue(0) < dist[neighborId]) {
						dist[neighborId] = dist[x.getId()] + e.getValue(0);
					}
				}
			}
		}
		return dist;
	}

	/**
	 * Applique l'algorithme A* sur le graphe donn�
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param s le sommet de d�part
	 * @param d le sommet d'arriv�e
	 * @return la liste des ar�tes qui constituent le chemin le plus court de s � d
	 */
	public static ListEdges aEtoile(Graph g, int s, int d) throws Exception {
		int verticesNb = g.getVerticesNb();
		ListEdges chemin = new ListEdges();

		// On utilise une hashmap qui associe l'id d'un vertex au vertex lui m�me
		Map<Integer, Vertex> z = new HashMap<Integer, Vertex>();
		for (Vertex v : g.getListVertices()) {
			if (v.getId() != s) {
				z.put(v.getId(), v);
			}
		}

		double[] dist = new double[verticesNb];
		double[] estimation = new double[verticesNb];
		int[] predecessor = new int[verticesNb];

		// Initialisation des distances et pr�d�cesseurs
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

			// on cherche le sommet x de Z dont la valeur estim�e est minimale
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
					Edge e = new Edge(0, previous, index, 1);
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
	 * Applique l'algorithme de Dijkstra sur le graphe donn�.
	 * Utilise pour cela un tas de Fibonacci.
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param s le sommet de d�part
	 * @return le tableau des distances minimales de chaque sommet par rapport au point de d�part
	 */
	public static double[] dijkstraFibonacci(Graph g, int s) {
		int verticesNb = g.getVerticesNb();

		double[] dist = new double[verticesNb];
		boolean[] marque = new boolean[verticesNb];

		// La hashmap va nous permettre d'acc�der aux sommets stock�s dans le tas de Fibonacci pour pouvoir les modifier plus tard.
		// On associe pour cela un id de vertex � "l'entr�e" du tas de Fibonacci qui lui correspond.
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
			// On r�cup�re et supprime du tas le sommet de valeur minimale
			int vertexId = fiboHeap.dequeueMin().getValue().getId();
			vertexEntries.remove(vertexId);
			marque[vertexId] = true; // on le marque comme visit�

			// On parcourt les voisins du sommet
			for (Edge e : g.getListAdjacent(vertexId)) {
				int neighborId = e.getIndexFinalVertex();

				if (marque[neighborId] == false) { // Si le voisin n'a pas �t� visit�
					double newValue = dist[vertexId] + e.getValue(0);
					if (newValue < dist[neighborId]) { // Si on doit mettre � jour sa valeur

						// Soit le voisin n'�tait pas dans le tas de Fibonacci et on l'ajoute avec sa valeur modifi�e,
						// soit le voisin �tait d�j� dans le tas de Fibonacci et on ne fait que modifier sa valeur
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
	 * Probl�me de la question 6
	 * D�termine la commune dont la moyenne de ses distances avec les villes de plus de n habitants est minimale
	 * 
	 * @param g le graphe sur lequel appliquer l'algorithme
	 * @param n le seuil minimal pour consid�rer une ville comme "grande"
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

		// On fait la moyenne des diff�rents tableaux des distances de chaque ville
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

		// On cherche la commune dont la distance moyenne � chaque ville est minimale
		int indexMin = -1;
		double moyenneMin = Double.MAX_VALUE;
		for (int i = 0; i < nbCommunes; i++) {
			if (moyennes[i] < moyenneMin && moyennes[i] != Double.MAX_VALUE) {
				indexMin = i;
				moyenneMin = moyennes[i];
			}
		}

		// Affichage du r�sultat
		if (moyenneMin != Double.MAX_VALUE) {
			System.out.println(g.getVertex(indexMin).getName() + " a la plus petite moyenne des distances de : "
					+ (int) moyenneMin + " km");
		} else {
			System.out.println(
					"Le graphe n'est pas suffisamment connexe pour permettre l'acc�s � toutes les grandes villes");
		}
	}

	public static ListEdges VRP2(Graph g, int n) throws Exception {
		List<Integer> tableIndex = new ArrayList<Integer>();
		
		// on extrait le graphe des grandes villes sans aucune arete
		Graph gVilles = GraphLoader.genererSousGrapheGrandesVilles(g, n, tableIndex);
		System.out.println(gVilles.getListVertices());

		int nbVilles = gVilles.getVerticesNb();
		boolean[] marque = new boolean[nbVilles];
		int nbMarque = 0;

		int edgeCounter = 0;
		int idCurrent = 0;

		// on part de la premiere grande ville et on la relie � sa plus proche voisine 
		while (nbMarque < nbVilles - 1) {
			double distPPV = Double.MAX_VALUE;
			int idPPV = -1;

			for (int i = 0; i < nbVilles; i++) {
				if (idCurrent != i && !marque[i]) {
					double dist = Graph.calcDist(gVilles.getVertex(idCurrent), gVilles.getVertex(i));
					if (dist < distPPV) {
						distPPV = dist;
						idPPV = i;
					}
				}
			}
			Edge e = new Edge(edgeCounter++, idCurrent, idPPV, 1);
			e.setValue(0, distPPV);
			gVilles.addEdge(e);
			marque[idCurrent] = true;
			nbMarque++;
			idCurrent = idPPV;
		}
		
		// on ferme la boucle en reliant la derniere grande ville a la premiere
		double dist = Graph.calcDist(gVilles.getVertex(idCurrent), gVilles.getVertex(0));
		Edge lastEdge = new Edge(edgeCounter++, idCurrent, 0, 1);
		lastEdge.setValue(0, dist);
		gVilles.addEdge(lastEdge);
		
		
		ListEdges trajet = new ListEdges();
		
		// pour chaque arete reliant deux grandes villes, on trouve le chemin passant par les petites communes interm�diaires avec A*
		for(Edge edge : gVilles.getListEdges()) {
			int idInitial = edge.getIndexInitialVertex();
			int idFinal = edge.getIndexFinalVertex();
			try {
				ListEdges chemin = Algorithmes.aEtoile(g, tableIndex.get(idInitial), tableIndex.get(idFinal));
				for(Edge e : chemin) {
					// on ajoute les aretes intermediaires au trajet total
					trajet.add(e);
					System.out.println("( " + g.getVertex(e.getIndexInitialVertex()).getName() + " - " + g.getVertex(e.getIndexFinalVertex()).getName() + " ) ");
				}
				System.out.println();
			} catch (Exception ex) {
				throw(ex);
			}
		}
		
		return trajet;
	}
}
