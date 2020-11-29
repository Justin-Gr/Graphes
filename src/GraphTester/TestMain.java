package GraphTester;
import ToolBox.GraphLoader;

import java.util.Arrays;

import GraphStructure.Graph;

public class TestMain {
	
	public static void main(String[] args) {
		try {
			long startTime, endTime;
			
			System.out.println("Génération du graphe...");
			startTime = System.nanoTime();
//			Graph g = GraphLoader.loadFile("assets/GrapheStructureNonOriente.gsb");
//			Graph g = GraphLoader.loadFile("assets/CommunesFrance_1000_20_ppv.gsb");
//			Graph g = GraphLoader.loadFile("assets/CommunesFrance_5000_50.gsb");
			Graph g = GraphLoader.loadXLSX("assets/CommunesFrance.xlsx", 0, 10);
//			g.printListAdjacent();
			endTime = System.nanoTime();
			
			System.out.println("Graphe généré avec succès, durée : " + (endTime-startTime)/1e9 + "s");
			System.out.println();
			
			int idA = 9759;
			int idB = 28152;
		
			System.out.println("A* :");
			startTime = System.nanoTime();
			double[] dist1 = Algorithmes.aEtoile(g, idA, idB);
			endTime = System.nanoTime();
			
			// System.out.println(Arrays.toString(dist1));
			System.out.println("Distance " + g.getVertex(idA).getName() + "-" + g.getVertex(idB).getName() + " : " + dist1[idB] + " km");
			System.out.println("Fin de aEtoile, durée : " + (endTime-startTime)/1e9 + "s");
			System.out.println();
			
			
			System.out.println("Dijkstra Fibo :");
			startTime = System.nanoTime();
			double[] dist2 = Algorithmes.dijkstraFibonacci(g, idA);
			endTime = System.nanoTime();

//			System.out.println(Arrays.toString(dist2));
			System.out.println("Distance " + g.getVertex(idA).getName() + "-" + g.getVertex(idB).getName() + " : " + dist2[idB] + " km");
			System.out.println("Fin de Dijkstra Fibonacci, durée : " + (endTime-startTime)/1e9 + "s");
			System.out.println();
			
			
			System.out.println("Dijkstra :");
			startTime = System.nanoTime();
			double[] dist3 = Algorithmes.dijkstra(g, idA);
			endTime = System.nanoTime();

//			System.out.println(Arrays.toString(dist2));
			System.out.println("Distance " + g.getVertex(idA).getName() + "-" + g.getVertex(idB).getName() + " : " + dist3[idB] + " km");
			System.out.println("Fin de Dijkstra, durée : " + (endTime-startTime)/1e9 + "s");
			
			// Vérification
			boolean test = true;
			for (int i = 0; i < g.getVerticesNb(); i++) {
				if (dist2[i] != dist3[i]) {
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
