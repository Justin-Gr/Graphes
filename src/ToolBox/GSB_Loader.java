package ToolBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GraphStructure.*;

public abstract class GSB_Loader {
	public static Graph loadFile(String filename) throws FileNotFoundException {
		String graphName, line;
		boolean directed;
		int vertexNb, vertexValueNb, edgeNb, edgeValueNb;
		
		FileInputStream fis = new FileInputStream(filename);
        Scanner scanner = new Scanner(fis);
                
        while(scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
     
        scanner.close();
		
		return null;
	}
}
