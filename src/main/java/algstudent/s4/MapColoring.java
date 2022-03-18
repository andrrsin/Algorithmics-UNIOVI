package algstudent.s4;

import java.util.ArrayList;

public class MapColoring {
	private String[] colors;
	private Country[] countries;
	private static final String COLORS_PATH = "src/main/java/algstudent/s4/colors.txt";
	private static final String COUNTRIES_PATH = "src/main/java/algstudent/s4/borders.txt";

	public MapColoring(String colorsPath, String countriesPath) {
		setColors(Parser.loadColors(colorsPath));
		setCountries(Parser.loadCountries(countriesPath));
	}
	
	public MapColoring() {
		setColors(Parser.loadColors(COLORS_PATH));
		setCountries(Parser.loadCountries(COUNTRIES_PATH));
	}
	
	public void printColors() {
		System.out.println("Colors: ");
		for(String color: colors)
			System.out.print(color+", ");
		System.out.println();
	}

	public Country[] getCountries() {
		return countries;
	}

	public void setCountries(Country[] countries) {
		this.countries = countries;
	}

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}
	public void printCountries() {
		for(Country country:countries)
			System.out.println(country.toString());
	}
	public void greedyColoring() {
		countries[0].setColor(colors[0]);
		ArrayList<String> used = new ArrayList<String>();
		used.add(colors[0]);
		String color = "";
		for(int i = 1;i<countries.length;i++) {
			color = nextColor(countries[i],used);
			used.add(color);
			countries[i].setColor(color);
			
		}
			
	}

	private String nextColor(Country country, ArrayList<String> used) {
		for(String color:colors) {
			if(!used.contains(color)) {
				return color;
			}
		}
		return "";
	}

}
