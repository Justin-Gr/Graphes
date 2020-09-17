package ToolBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GraphStructure.*;

public abstract class GSB_Loader {
	public static Graph loadFile(String filename) throws FileNotFoundException {
		String graphName;
		boolean directed;
		int verticesNb, verticesValueNb, edgesNb, edgesValueNb;
		
		FileInputStream fis = new FileInputStream(filename);
        Scanner scanner = new Scanner(fis);
        
        graphName = scanner.nextLine().split(" ")[1];
        
        if(scanner.nextLine().split(" ")[1] == "oui") {
        	directed = true;
        } else {
        	directed = false;
        }
        
        verticesNb = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        verticesValueNb = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        edgesNb = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        edgesValueNb = Integer.parseInt(scanner.nextLine().split(" ")[1]);
               
        Graph g = new Graph(graphName, directed, verticesNb, verticesValueNb, edgesNb, edgesValueNb);
        
        String line;
        
        for(int i = 0; i < verticesNb; i++) {
        	line = scanner.nextLine();
        	String[] params = line.split(" ");
        	int id = Integer.parseInt(params[0]);
        	String vertexName = params[1];
        	
        	Vertex v = new Vertex(id, vertexName, verticesValueNb);
        	
        	for(int j = 0; j < verticesValueNb; j++) {
        		v.setValue(j, Integer.parseInt(params[2+j]));
        	}
        	
        	g.addVertex(v);
        }
     
        scanner.close();
		
		return null;
	}
}
