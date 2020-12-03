package GraphTester;

import GraphStructure.Edge;
import GraphStructure.Graph;
import GraphStructure.ListEdges;
import ToolBox.GraphLoader;

public class Main {

	public static void main(String[] args) {
		try {
			
		long startTime, endTime;
			
		System.out.println("==========QUESTION 2==========\n");
		
		System.out.println("Construction d'un graphe G1 des communes de plus de 5000 habitants, rayon de liaison : 50km");
		startTime = System.nanoTime();
		Graph g_5000_50 = GraphLoader.loadXLSX("assets/CommunesFrance.xlsx", 5000, 50);
		endTime = System.nanoTime();
		System.out.println("Fin de la construction, duree : " + (endTime-startTime)/1e9 + "s\n");
		
		System.out.println("Construction d'un graphe G2 des communes de plus de 1000 habitants, rayon de liaison : 20km");
		System.out.println("Cette opération peut prendre jusqu'à 1 minute");
		startTime = System.nanoTime();
		Graph g_1000_20 = GraphLoader.loadXLSX("assets/CommunesFrance.xlsx", 1000, 20);
		endTime = System.nanoTime();
		System.out.println("Fin de la construction, duree : " + (endTime-startTime)/1e9 + "s\n");
		
//		System.out.println("Construction d'un graphe G3 de toutes les communes, rayon de liaison : 20km");
//		System.out.println("Cette opération peut prendre jusqu'à 5 minutes");
//		startTime = System.nanoTime();
//		Graph g_0_20 = GraphLoader.loadXLSX("assets/CommunesFrance.xlsx", 0, 20);
//		endTime = System.nanoTime();
//		System.out.println("Fin de la construction, duree : " + (endTime-startTime)/1e9 + "s\n");
		
		
		System.out.println("==========QUESTION 4==========\n");
		
		int idA = 0;
		int idB = g_5000_50.getVerticesNb() - 1;
		
		System.out.println("Application de l'algorithme A* sur le graphe G1 :");
		startTime = System.nanoTime();
		ListEdges cheminG1 = Algorithmes.aEtoile(g_5000_50, idA, idB);
		endTime = System.nanoTime();

		System.out.println("Fin de A*, duree : " + (endTime-startTime)/1e6 + "ms");
		System.out.print("Chemin de " + g_5000_50.getVertex(idA).getName() + " à " + g_5000_50.getVertex(idB).getName() + " : ");
		for(Edge e : cheminG1) {
			System.out.print(e);
		}
		System.out.println();
		System.out.println("distance totale : " + cheminG1.calcSum(0) + "km\n");
		
		System.out.println("Application de l'algorithme de Dijkstra sur le graphe G1 :");
		startTime = System.nanoTime();
		double[] distG1 = Algorithmes.dijkstra(g_5000_50, idA);
		endTime = System.nanoTime();
		
		double tempsDijkstraG1 = (endTime - startTime)/1e6;

		System.out.println("Fin de Dijsktra, duree : " + tempsDijkstraG1 + "ms");
		System.out.println("distance entre " + g_5000_50.getVertex(idA).getName() + " et " + g_5000_50.getVertex(idB).getName() + " : " + distG1[idB] + "km\n");
		
		
		System.out.println();
		
		
		idA = 0;
		idB = g_1000_20.getVerticesNb() - 1;
		
		System.out.println("Application de l'algorithme A* sur le graphe G2 :");
		startTime = System.nanoTime();
		ListEdges cheminG2 = Algorithmes.aEtoile(g_1000_20, idA, idB);
		endTime = System.nanoTime();

		System.out.println("Fin de A*, duree : " + (endTime-startTime)/1e6 + "ms");
		System.out.print("Chemin de " + g_1000_20.getVertex(idA).getName() + " à " + g_1000_20.getVertex(idB).getName() + " : ");
		for(Edge e : cheminG2) {
			System.out.print(e);
		}
		System.out.println();
		System.out.println("distance totale : " + cheminG2.calcSum(0) + "km\n");
		
		System.out.println("Application de l'algorithme de Dijkstra sur le graphe G2 :");
		startTime = System.nanoTime();
		double[] distG2 = Algorithmes.dijkstra(g_1000_20, idA);
		endTime = System.nanoTime();
		
		double tempsDijkstraG2 = (endTime - startTime)/1e6;

		System.out.println("Fin de Dijsktra, duree : " + tempsDijkstraG2 + "ms");
		System.out.println("distance entre " + g_1000_20.getVertex(idA).getName() + " et " + g_1000_20.getVertex(idB).getName() + " : " + distG2[idB] + "km\n");
		
		
		System.out.println("==========QUESTION 5==========\n");
		
		idA = 0;
		idB = g_5000_50.getVerticesNb() - 1;
		
		System.out.println("Application de l'algorithme de Dijkstra avec tas de Fibonacci sur le graphe G1 :");
		startTime = System.nanoTime();
		double[] distG1Fibo = Algorithmes.dijkstraFibonacci(g_5000_50, idA);
		endTime = System.nanoTime();
		
		double tempsDijkstraFiboG1 = (endTime - startTime)/1e6;

		System.out.println("Fin de Dijsktra avec tas de Fibonacci, duree : " + tempsDijkstraFiboG1 + "ms");
		System.out.println("distance entre " + g_5000_50.getVertex(idA).getName() + " et " + g_5000_50.getVertex(idB).getName() + " : " + distG1Fibo[idB] + "km\n");
		
		idA = 0;
		idB = g_1000_20.getVerticesNb() - 1;
		
		System.out.println("Application de l'algorithme de Dijkstra avec tas de Fibonacci sur le graphe G2 :");
		startTime = System.nanoTime();
		double[] distG2Fibo = Algorithmes.dijkstraFibonacci(g_1000_20, idA);
		endTime = System.nanoTime();
		
		double tempsDijkstraFiboG2 = (endTime - startTime)/1e6;

		System.out.println("Fin de Dijsktra avec tas de Fibonacci, duree : " + tempsDijkstraFiboG2 + "ms");
		System.out.println("distance entre " + g_1000_20.getVertex(idA).getName() + " et " + g_1000_20.getVertex(idB).getName() + " : " + distG2Fibo[idB] + "km\n");
		
		
		System.out.println("Comparaisons sur G1 :");
		System.out.println("Dijkstra classique : " + tempsDijkstraG1 + "ms");
		System.out.println("Dijkstra avec tas de Fibonacci : " + tempsDijkstraFiboG1 + "ms\n");
		
		System.out.println("Comparaisons sur G2 :");
		System.out.println("Dijkstra classique : " + tempsDijkstraG2 + "ms");
		System.out.println("Dijkstra avec tas de Fibonacci : " + tempsDijkstraFiboG2 + "ms\n");
		
		
		System.out.println("==========QUESTION 6==========\n");
		
		System.out.println("Construction d'un graphe G3 de toutes les communes, rayon de liaison : 20km");
		System.out.println("Cette opération peut prendre jusqu'à 5 minutes");
		startTime = System.nanoTime();
		Graph g_0_20 = GraphLoader.loadXLSX("assets/CommunesFrance.xlsx", 0, 20);
		endTime = System.nanoTime();
		System.out.println("Fin de la construction, duree : " + (endTime-startTime)/1e9 + "s\n");
		
		System.out.println("Application de l'algorithme VRP1 sur le graphe G3 :");
		startTime = System.nanoTime();
		Algorithmes.VRP1(g_0_20, 200000);
		endTime = System.nanoTime();
		System.out.println("Fin de VRP1, duree : " + (endTime-startTime)/1e6 + "ms\n");
		
		
		System.out.println("==========QUESTION 7==========\n");
		
		System.out.println("Not implemented yet");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
