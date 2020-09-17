package GraphTester;

import java.io.FileNotFoundException;

import ToolBox.GSB_Loader;

public class TestMain {
	
	public static void main(String[] args) {
		System.out.println("D�but de lecture");
		try {
			GSB_Loader.loadFile("assets/GrapheStructureNonOriente.gsb");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Fin de lecture");
	}

}
