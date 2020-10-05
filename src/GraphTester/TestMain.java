package GraphTester;

import java.io.FileNotFoundException;

import ToolBox.GSB_Loader;
import GraphStructure.Graph;

public class TestMain {
	
	public static void main(String[] args) {
		try {
			Graph g = GSB_Loader.loadFile("assets/GrapheStructureNonOriente.gsb");

			System.out.println("Début de parcours");
			Parcours.ParcoursLargeur(g, g.getVertex(0));
			System.out.println("Fin de parcours");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
