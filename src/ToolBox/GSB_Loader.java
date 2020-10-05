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
               
        scanner.nextLine();
        
        Graph g = new Graph(graphName, directed, verticesNb, verticesValueNb, edgesNb, edgesValueNb);
        
        String line;
        
        // Generate all vertices
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
        
        scanner.nextLine();
        
        // Generate all edges
        for(int i = 0; i < edgesNb; i++) {
        	line = scanner.nextLine();
        	String[] params = line.split(" ");
        	int initialId = Integer.parseInt(params[0]);
        	int finalId = Integer.parseInt(params[1]);
        	        	
        	Edge e = new Edge(i, initialId, finalId, edgesValueNb);
        			
        	for(int j = 0; j < edgesValueNb; j++) {
        		e.setValue(j, Double.parseDouble(params[2+j]));
        	}
        	        	
        	g.addEdge(e);
        }
     
        scanner.close();        
		
		return g;
	}
}
