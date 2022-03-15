package algstudent.s4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Parser {

	public static Country[] loadCountries(String countriesPath) {
		BufferedReader reader;
		LinkedList<Country> aux = new LinkedList<Country>();
		try {
			reader = new BufferedReader(new FileReader(countriesPath));
			String line;
			String[] data;
			while (reader.ready()) {
				line = reader.readLine();
				data = line.split(":");
				aux.add(new Country(data[0],null));
			}
			
			while (reader.ready()) {
				line = reader.readLine();
				data = line.split(":");
				for(String name:data[1].split(",")) {
					for(Country country:aux) {
						if(name.trim().equals(country.getName())) {
							
						}
					}
				}
			}
			
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return (String[]) aux.toArray();
	}

	public static String[] loadColors(String colorsPath) {
		ArrayList<String> aux = new ArrayList<String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(colorsPath));

			while (reader.ready())
				aux.add(reader.readLine());
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return (String[]) aux.toArray();
	}

}
