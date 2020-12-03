package ToolBox;

import java.io.FileWriter;
import java.io.IOException;

import GraphStructure.Edge;
import GraphStructure.Graph;
import GraphStructure.Vertex;

public class GraphExport {
	
	
	public static void exportGSB(Graph g, String filename) {
		
		try {
			FileWriter f = new FileWriter(filename);
			
			if (g.isDirected()) {
				f.write("Nom: " + g.getName() + "\n");
				f.write("Oriente: Oui\n");
				f.write("NbSommets: " + g.getVerticesNb() + "\n");
				f.write("NbValSommet: " + g.getVertex(0).getValuesNb() + "\n");
				f.write("NbAretes: " + g.getEdgesNb() + "\n");
				f.write("NbValArete: " + g.getEdge(0).getNbValues() + "\n");
			} else {
				f.write("Nom: " + g.getName() + "\n");
				f.write("Oriente: Non\n");
				f.write("NbSommets: " + g.getVerticesNb() + "\n");
				f.write("NbValSommet: " + g.getVertex(0).getValuesNb() + "\n");
				f.write("NbAretes: " + g.getEdgesNb() / 2 + "\n");
				f.write("NbValArete: " + g.getEdge(0).getNbValues() + "\n");
			}
			
			f.write("--- Liste des sommets\n");
			for (Vertex v : g.getListVertices()) {
				f.write(String.valueOf(v.getId()));
				f.write(" " + v.getName());
				
				for (int i = 0; i < v.getValuesNb(); i++) {
					f.write(" " + v.getValue(i));
				}
				
				f.write("\n");
			}
			
			boolean findIdZero = false;
			f.write("--- Liste des aretes\n");
			for (Edge e : g.getListEdges()) {
				if (e.getId() >= 0) {
					if (e.getId() == 0) {
						if (findIdZero) {
							continue;
						} else {
							findIdZero = true;
						}
					}
					
					f.write(String.valueOf(e.getIndexInitialVertex()));
					f.write(" " + String.valueOf(e.getIndexFinalVertex()));
					
					for (int i = 0; i < e.getNbValues(); i++) {
						f.write(" " + e.getValue(i));
					}
					
					f.write("\n");
				}
			}
			
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
