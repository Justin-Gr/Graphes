package GraphTester;

import java.io.FileNotFoundException;

import ToolBox.GSB_Loader;
import GraphStructure.Graph;
import GraphStructure.Vertex;

public class TestMain {
	
	public static void main(String[] args) {
		try {
//			Graph g = GSB_Loader.loadFile("assets/GrapheStructureNonOriente.gsb");
//			Graph g = GSB_Loader.loadFile("assets/CommunesFrance_1000_20_ppv.gsb");
//			Graph g = GSB_Loader.loadFile("assets/CommunesFrance_5000_50.gsb");
//			g.printListAdjacent();
			
//			long startTime = System.nanoTime();
//			g.aEtoile(g.getVertex(0), g.getVertex(4));
//			long endTime = System.nanoTime();
			
//			System.out.println("Début de parcours");
//			Parcours.ParcoursLargeur(g, g.getVertex(0));
//			System.out.println("Fin de Dijkstra, time : " + (endTime-startTime)/1e9 + " s");
			
			GSB_Loader.loadXLSX("assets/CommunesFrance.xlsx", 300000.0, 400.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
