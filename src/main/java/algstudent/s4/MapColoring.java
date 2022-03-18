package algstudent.s4;



public class MapColoring {
	private String[] colors;
	private Country[] countries;
	private static final String COLORS_PATH = "/colors.txt";
	private static final String COUNTRIES_PATH = "/borders.txt";

	public MapColoring(String colorsPath, String countriesPath) {
		setColors(Parser.loadColors(colorsPath));
		setCountries(Parser.loadCountries(countriesPath));
	}
	
	public MapColoring() {
		setColors(Parser.loadColors(COLORS_PATH));
		setCountries(Parser.loadCountries(COUNTRIES_PATH));
	}
	
	public void printColors() {
		System.out.println(colors);
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

}
