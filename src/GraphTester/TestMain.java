package GraphTester;
import ToolBox.GraphLoader;
import GraphStructure.Edge;
import GraphStructure.Graph;
import GraphStructure.ListEdges;

public class TestMain {
	
	public static void main(String[] args) {
		try {
			long startTime, endTime;
			
			System.out.println("Generation du graphe...");
			startTime = System.nanoTime();
//			Graph g = GraphLoader.loadFile("assets/GrapheStructureNonOriente.gsb");
//			Graph g = GraphLoader.loadFile("assets/CommunesFrance_1000_20_ppv.gsb");
//			Graph g = GraphLoader.loadFile("assets/CommunesFrance_5000_50.gsb");
			Graph g = GraphLoader.loadXLSX("assets/CommunesFrance.xlsx", 50000, 200);
//			g.printListAdjacent();
			endTime = System.nanoTime();
			
			System.out.println("Graphe genere avec succes, duree : " + (endTime-startTime)/1e9 + "s");
			System.out.println();
			
			int idA = 0;
			int idB = g.getVerticesNb() - 1;
		
			System.out.println("A* :");
			startTime = System.nanoTime();
			ListEdges chemin = Algorithmes.aEtoile(g, idA, idB);
			endTime = System.nanoTime();
			
			System.out.print("Chemin de " + g.getVertex(idA).getName() + " à " + g.getVertex(idB).getName() + " : ");
			for(Edge e : chemin) {
				System.out.print(e);
			}
			System.out.println();
			System.out.println("distance totale : " + chemin.calcSum(0) + " km");
			System.out.println("Fin de aEtoile, duree : " + (endTime-startTime)/1e9 + "s");
			System.out.println();
			
			
			System.out.println("Dijkstra Fibo :");
			startTime = System.nanoTime();
			double[] dist2 = Algorithmes.dijkstraFibonacci(g, idA);
			endTime = System.nanoTime();

//			System.out.println(Arrays.toString(dist2));
			System.out.println("Distance " + g.getVertex(idA).getName() + "-" + g.getVertex(idB).getName() + " : " + dist2[idB] + " km");
			System.out.println("Fin de Dijkstra Fibonacci, duree : " + (endTime-startTime)/1e9 + "s");
			System.out.println();
			
			
			System.out.println("Dijkstra :");
			startTime = System.nanoTime();
			double[] dist3 = Algorithmes.dijkstra(g, idA);
			endTime = System.nanoTime();

//			System.out.println(Arrays.toString(dist2));
			System.out.println("Distance " + g.getVertex(idA).getName() + "-" + g.getVertex(idB).getName() + " : " + dist3[idB] + " km");
			System.out.println("Fin de Dijkstra, duree : " + (endTime-startTime)/1e9 + "s");
			
			// Vï¿½rification
			boolean test = true;
			for (int i = 0; i < g.getVerticesNb(); i++) {
				if (dist2[i] != dist3[i]) {
					test = false;
				}
			}
			System.out.println("Resultats identiques : " + test);
			
//			startTime = System.nanoTime();
//			Algorithmes.VRP1(g, 200000);
//			endTime = System.nanoTime();
//			
//			System.out.println("Fin de VRP1, durï¿½e : " + (endTime-startTime)/1e9 + "s");
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
