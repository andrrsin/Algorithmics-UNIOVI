package algstudent.s4;

import java.util.ArrayList;

public class Country  {

	private ArrayList<Country> frontier;
	private String color;
	private String name;

	public Country(String name, ArrayList<Country> countries) {
		
		this.name = name;
		if(countries != null)
			this.frontier = countries;
		else
			this.frontier = new ArrayList<Country>();
	}

	public ArrayList<Country> getFrontier() {
		return frontier;
	}

	public void setFrontier(ArrayList<Country> frontier) {
		this.frontier = frontier;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		String aux = "[Country: " + getName();
		aux+= " - Color: " + getColor();
		aux += " - Frontiers with: ";
		for(var country:frontier)
			aux += country.getName() + ", ";
		aux+=" ]";
		return aux;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
