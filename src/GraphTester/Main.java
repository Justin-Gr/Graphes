package GraphTester;

import java.util.Scanner;

import GraphStructure.Graph;
import GraphStructure.ListEdges;
import ToolBox.GraphLoader;

public class Main {

	public static void main(String[] args) {
		try {
			
			Scanner s = new Scanner(System.in);
			long startTime, endTime;
			
			System.out.println("==========QUESTION 2==========\n");
			
			System.out.println("Construction d'un graphe G1 des communes de plus de 5000 habitants, rayon de liaison : 50 km");
			startTime = System.nanoTime();
			Graph g_5000_50 = GraphLoader.loadXLSX("assets/CommunesFrance.xlsx", 5000, 50);
			endTime = System.nanoTime();
			System.out.println("Fin de la construction, duree : " + (endTime-startTime)/1e9 + "s\n");
			
			System.out.println("Construction d'un graphe G2 des communes de plus de 1000 habitants, rayon de liaison : 20 km");
			System.out.println("Cette operation peut prendre jusqu'a 1 minute");
			startTime = System.nanoTime();
			Graph g_1000_20 = GraphLoader.loadXLSX("assets/CommunesFrance.xlsx", 1000, 20);
			endTime = System.nanoTime();
			System.out.println("Fin de la construction, duree : " + (endTime-startTime)/1e9 + "s\n");
			
			
			System.out.println("Appuyez sur entree pour continuer...");
			s.nextLine();
			System.out.println();
			
			System.out.println("==========QUESTION 4==========\n");
			
			int idA = 0;
			int idB = g_5000_50.getVerticesNb() - 1;
			
			System.out.println("Application de l'algorithme A* sur le graphe G1 :");
			startTime = System.nanoTime();
			ListEdges cheminG1 = Algorithmes.aEtoile(g_5000_50, idA, idB);
			endTime = System.nanoTime();
	
			System.out.println("Fin de A*, duree : " + (endTime-startTime)/1e6 + "ms");
			System.out.println("Distance entre " + g_5000_50.getVertex(idA).getName() + " et " + g_5000_50.getVertex(idB).getName() + " : " + cheminG1.calcSum(0) + " km\n");
			
			System.out.println("Application de l'algorithme de Dijkstra sur le graphe G1 :");
			startTime = System.nanoTime();
			double[] distG1 = Algorithmes.dijkstra(g_5000_50, idA);
			endTime = System.nanoTime();
			
			double tempsDijkstraG1 = (endTime - startTime)/1e6;
	
			System.out.println("Fin de Dijsktra, duree : " + tempsDijkstraG1 + "ms");
			System.out.println("Distance entre " + g_5000_50.getVertex(idA).getName() + " et " + g_5000_50.getVertex(idB).getName() + " : " + distG1[idB] + " km\n");
			
			
			System.out.println("Appuyez sur entree pour continuer...");
			s.nextLine();
			System.out.println();
			
			
			idA = 0;
			idB = g_1000_20.getVerticesNb() - 1;
			
			System.out.println("Application de l'algorithme A* sur le graphe G2 :");
			startTime = System.nanoTime();
			ListEdges cheminG2 = Algorithmes.aEtoile(g_1000_20, idA, idB);
			endTime = System.nanoTime();
	
			System.out.println("Fin de A*, duree : " + (endTime-startTime)/1e6 + "ms");
			System.out.println("Distance entre " + g_1000_20.getVertex(idA).getName() + " et " + g_1000_20.getVertex(idB).getName() + " : " + cheminG2.calcSum(0) + " km\n");
			
			System.out.println("Application de l'algorithme de Dijkstra sur le graphe G2 :");
			startTime = System.nanoTime();
			double[] distG2 = Algorithmes.dijkstra(g_1000_20, idA);
			endTime = System.nanoTime();
			
			double tempsDijkstraG2 = (endTime - startTime)/1e6;
	
			System.out.println("Fin de Dijsktra, duree : " + tempsDijkstraG2 + "ms");
			System.out.println("Distance entre " + g_1000_20.getVertex(idA).getName() + " et " + g_1000_20.getVertex(idB).getName() + " : " + distG2[idB] + " km\n");
			
			
			System.out.println("Appuyez sur entree pour continuer...");
			s.nextLine();
			System.out.println();
			
			System.out.println("==========QUESTION 5==========\n");
			
			idA = 0;
			idB = g_5000_50.getVerticesNb() - 1;
			
			System.out.println("Application de l'algorithme de Dijkstra avec tas de Fibonacci sur le graphe G1 :");
			startTime = System.nanoTime();
			double[] distG1Fibo = Algorithmes.dijkstraFibonacci(g_5000_50, idA);
			endTime = System.nanoTime();
			
			double tempsDijkstraFiboG1 = (endTime - startTime)/1e6;
	
			System.out.println("Fin de Dijsktra avec tas de Fibonacci, duree : " + tempsDijkstraFiboG1 + "ms");
			System.out.println("Distance entre " + g_5000_50.getVertex(idA).getName() + " et " + g_5000_50.getVertex(idB).getName() + " : " + distG1Fibo[idB] + " km\n");
			
			idA = 0;
			idB = g_1000_20.getVerticesNb() - 1;
			
			System.out.println("Application de l'algorithme de Dijkstra avec tas de Fibonacci sur le graphe G2 :");
			startTime = System.nanoTime();
			double[] distG2Fibo = Algorithmes.dijkstraFibonacci(g_1000_20, idA);
			endTime = System.nanoTime();
			
			double tempsDijkstraFiboG2 = (endTime - startTime)/1e6;
	
			System.out.println("Fin de Dijsktra avec tas de Fibonacci, duree : " + tempsDijkstraFiboG2 + "ms");
			System.out.println("Distance entre " + g_1000_20.getVertex(idA).getName() + " et " + g_1000_20.getVertex(idB).getName() + " : " + distG2Fibo[idB] + " km\n");
			
			
			System.out.println("Comparaisons sur G1 :");
			System.out.println("Dijkstra classique : " + tempsDijkstraG1 + "ms");
			System.out.println("Dijkstra avec tas de Fibonacci : " + tempsDijkstraFiboG1 + "ms\n");
			
			System.out.println("Comparaisons sur G2 :");
			System.out.println("Dijkstra classique : " + tempsDijkstraG2 + "ms");
			System.out.println("Dijkstra avec tas de Fibonacci : " + tempsDijkstraFiboG2 + "ms\n");
	
			
			System.out.println("Appuyez sur entree pour continuer...");
			s.nextLine();
			System.out.println();
			
			System.out.println("==========QUESTION 6==========\n");
			
			System.out.println("Chargement du graphe G3 de toutes les communes, rayon de liaison : 20 km");
			startTime = System.nanoTime();
			Graph g_0_20 = GraphLoader.loadFile("assets/CommunesFrance_0_20.gsb");
			endTime = System.nanoTime();
			System.out.println("Fin du chargement, duree : " + (endTime-startTime)/1e9 + "s\n");
			
			System.out.println("Application de l'algorithme VRP1 sur le graphe G3 :");
			startTime = System.nanoTime();
			Algorithmes.VRP1(g_0_20, 200000);
			endTime = System.nanoTime();
			System.out.println("Fin de VRP1, duree : " + (endTime-startTime)/1e6 + "ms\n");
			
			
			System.out.println("Appuyez sur entree pour continuer...");
			s.nextLine();
			System.out.println();
			
			System.out.println("==========QUESTION 7==========\n");
			
			System.out.println("Application de l'algorithme VRP2 sur le graphe G3 avec un seuil de 200 000 habitants :");
			startTime = System.nanoTime();
			ListEdges trajet_200k = Algorithmes.VRP2(g_0_20, 200000);
			endTime = System.nanoTime();
			System.out.println("Fin de VRP2, duree : " + (endTime-startTime)/1e6 + "ms");
			double longueur_200k = trajet_200k.calcSum(0);
			System.out.println("Longueur du trajet trouve : " + longueur_200k + " km\n");
			
			System.out.println("Application de l'algorithme VRP2 sur le graphe G3 avec un seuil de 150 000 habitants :");
			startTime = System.nanoTime();
			ListEdges trajet_150k = Algorithmes.VRP2(g_0_20, 150000);
			endTime = System.nanoTime();
			System.out.println("Fin de VRP2, duree : " + (endTime-startTime)/1e6 + "ms");
			double longueur_150k = trajet_150k.calcSum(0);
			System.out.println("Longueur du trajet trouve : " + longueur_150k + " km\n");
			
			System.out.println("Application de l'algorithme VRP2 sur le graphe G3 avec un seuil de 100 000 habitants :");
			startTime = System.nanoTime();
			ListEdges trajet_100k = Algorithmes.VRP2(g_0_20, 100000);
			endTime = System.nanoTime();
			System.out.println("Fin de VRP2, duree : " + (endTime-startTime)/1e6 + "ms");
			double longueur_100k = trajet_100k.calcSum(0);
			System.out.println("Longueur du trajet trouve : " + longueur_100k + " km\n");
			
			
			System.out.println("Appuyez sur entree pour fermer le programme...");
			s.nextLine();
			System.out.println();
			
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
