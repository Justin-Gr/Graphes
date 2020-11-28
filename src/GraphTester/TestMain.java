package GraphTester;
import ToolBox.GraphLoader;

import GraphStructure.Graph;

public class TestMain {
	
	public static void main(String[] args) {
		try {
			long startTime, endTime;
			
			startTime = System.nanoTime();
//			Graph g = GraphLoader.loadFile("assets/GrapheStructureNonOriente.gsb");
//			Graph g = GraphLoader.loadFile("assets/CommunesFrance_1000_20_ppv.gsb");
//			Graph g = GraphLoader.loadFile("assets/CommunesFrance_5000_50.gsb");
			Graph g = GraphLoader.loadXLSX("assets/CommunesFrance.xlsx", 5000, 50);
//			g.printListAdjacent();
			endTime = System.nanoTime();
			
			System.out.println("Graphe généré avec succès, durée : " + (endTime-startTime)/1e9 + "s");
			
			startTime = System.nanoTime();
			double[] dist1 = Algorithmes.dijkstra(g, 0);
			endTime = System.nanoTime();
			
//			System.out.println(Arrays.toString(dist1));
//			System.out.println("Distance des premiers noeuds: " + dist1[1] + " km");
			System.out.println("Fin de Dijkstra, durée : " + (endTime-startTime)/1e9 + "s");
			
			startTime = System.nanoTime();
			double[] dist2 = Algorithmes.dijkstraFibonacci(g, 0);
			endTime = System.nanoTime();

//			System.out.println(Arrays.toString(dist2));
//			System.out.println("Distance des premiers noeuds : " + dist2[1] + " km");
			System.out.println("Fin de Dijkstra Fibonacci, durée : " + (endTime-startTime)/1e9 + "s");
			
			// Vérification
			boolean test = true;
			for (int i = 0; i < g.getVerticesNb(); i++) {
				if (dist1[i] != dist2[i]) {
					test = false;
				}
			}
			System.out.println("Résultats identiques : " + test);
			
//			startTime = System.nanoTime();
//			Algorithmes.VRP1(g, 200000);
//			endTime = System.nanoTime();
//			
//			System.out.println("Fin de VRP1, durée : " + (endTime-startTime)/1e9 + "s");
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
