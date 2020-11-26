package ToolBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import GraphStructure.*;

public abstract class GSB_Loader {
	public static Graph loadFile(String filename) throws FileNotFoundException {
		String graphName;
		boolean directed;
		int verticesNb, verticesValueNb, edgesNb, edgesValueNb;

		FileInputStream fis = new FileInputStream(filename);
		Scanner scanner = new Scanner(fis);

		graphName = scanner.nextLine().split(" ")[1];

		if (scanner.nextLine().split(" ")[1] == "oui") {
			directed = true;
		} else {
			directed = false;
		}

		verticesNb = Integer.parseInt(scanner.nextLine().split(" ")[1]);
		verticesValueNb = Integer.parseInt(scanner.nextLine().split(" ")[1]);
		edgesNb = Integer.parseInt(scanner.nextLine().split(" ")[1]);
		edgesValueNb = Integer.parseInt(scanner.nextLine().split(" ")[1]);

		scanner.nextLine();

		Graph g = new Graph(graphName, directed, 0, verticesValueNb, 0, edgesValueNb);

		String line;

		// Generate all vertices
		for (int i = 0; i < verticesNb; i++) {
			line = scanner.nextLine();
			String[] params = line.split(" ");
			int id = Integer.parseInt(params[0]);
			String vertexName = params[1];

			Vertex v = new Vertex(id, vertexName, verticesValueNb);

			for (int j = 0; j < verticesValueNb; j++) {
				v.setValue(j, Integer.parseInt(params[2 + j]));
			}

			g.addVertex(v);
		}

		scanner.nextLine();

		// Generate all edges
		for (int i = 0; i < edgesNb; i++) {
			line = scanner.nextLine();
			String[] params = line.split(" ");
			int initialId = Integer.parseInt(params[0]);
			int finalId = Integer.parseInt(params[1]);

			Edge e = new Edge(i, initialId, finalId, edgesValueNb);

			for (int j = 0; j < edgesValueNb; j++) {
				e.setValue(j, Double.parseDouble(params[2 + j]));
			}

			g.addEdge(e);
		}

		scanner.close();

		return g;
	}

	@SuppressWarnings("deprecation")
	public static Graph loadXLSX(String filename, double popLimit, double distLimit) {
		Graph g = new Graph("Carte", false, 0, 0, 0, 1);
		try {
			File file = new File(filename); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			itr.next();
			int id = 0;
			while (itr.hasNext()) {
				Row row = itr.next();
				Cell cell = row.getCell(3);
				if (cell.getNumericCellValue() >= popLimit) {
					Vertex v = new Vertex(id++, row.getCell(1).getStringCellValue(), 0, row.getCell(5).getNumericCellValue(), row.getCell(4).getNumericCellValue());
					// System.out.println(v);
					g.addVertex(v);
				}
			}
			
			ListVertices list = g.getListVertices();
			int idEdge = 0;
			
			for(int i = 0; i < list.size(); i++) {
				for(int j = i + 1; j < list.size(); j++) {
					Vertex a = list.get(i);
					Vertex b = list.get(j);
				
					double dist = Graph.calcDist(a, b);
					if(dist < distLimit) {
						Edge e = new Edge(idEdge++, a.getId(), b.getId(), 1);
						e.setValue(0, dist);
						// System.out.println(e);
						g.addEdge(e);
					}
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return g;
	}
}
