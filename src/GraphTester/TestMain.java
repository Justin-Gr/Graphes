package GraphTester;

import java.io.FileNotFoundException;
import java.util.Arrays;

import ToolBox.GSB_Loader;
import GraphStructure.Graph;

public class TestMain {
	
	public static void main(String[] args) {
		try {
//			Graph g = GSB_Loader.loadFile("assets/GrapheStructureNonOriente.gsb");
//			Graph g = GSB_Loader.loadFile("assets/CommunesFrance_1000_20_ppv.gsb");
			Graph g = GSB_Loader.loadFile("assets/CommunesFrance_5000_50.gsb");
//			g.printListAdjacent();
			
			long startTime = System.nanoTime();
			double[] dist1 = g.dijkstraFibonacci(g.getVertex(0));
			long endTime = System.nanoTime();

			System.out.println(Arrays.toString(dist1));
			System.out.println("Fin de Dijkstra am�lior�, dur�e : " + (endTime-startTime)/1e9 + "s");

			startTime = System.nanoTime();
			double[] dist2 = g.dijkstra(g.getVertex(0));
			endTime = System.nanoTime();
			
			System.out.println(Arrays.toString(dist2));
			System.out.println("Fin de Dijkstra, dur�e : " + (endTime-startTime)/1e9 + "s");
			
			// V�rification
			boolean test = true;
			for (int i = 0; i < g.getVerticesNb(); i++) {
				if (dist1[i] != dist2[i]) {
					test = false;
				}
			}
			System.out.println("R�sultats identiques : " + test);
			
//			System.out.println("D�but de parcours");
//			Parcours.ParcoursLargeur(g, g.getVertex(0));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
