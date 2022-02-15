package util;


import java.io.*;

public abstract class FileUtil {

	public static int[][] loadMatrix(String fileName) {

		String line;
		String[] row = null;
		int[][] matrix = null;
		
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			
				line = file.readLine();
				int n = Integer.valueOf(line);
				matrix = new int[n][n];
				for(int i =0 ; i < matrix.length;i++) {
					line = file.readLine();
					row = line.split("\t");
					for(int j = 0; j <matrix.length;j++) {
						matrix[i][j]=Integer.valueOf(row[j]);
					}
					
					
				}
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
		return matrix;
	}

	

//	public static void saveToFile(String fileName, List<Client> orderList) {
//		try {
//			BufferedWriter file = new BufferedWriter(new FileWriter("files/" + fileName + ".dat"));
//			file.write("");
//			for (Client client : orderList) {
//				file.append(client.toString());
//				file.newLine();
//			}
//			file.close();
//		}
//
//		catch (FileNotFoundException fnfe) {
//			System.out.println("The file could not be saved.");
//		} catch (IOException ioe) {
//			new RuntimeException("I/O Error.");
//		}
//	}


}
