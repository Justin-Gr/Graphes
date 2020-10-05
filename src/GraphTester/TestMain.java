package GraphTester;

import java.io.FileNotFoundException;

import ToolBox.GSB_Loader;

public class TestMain {
	
	public static void main(String[] args) {
		System.out.println("Début de lecture");
		try {
			GSB_Loader.loadFile("assets/CommunesFrance_5000_50.gsb");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Fin de lecture");
	}

}
