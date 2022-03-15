package algstudent.s4;

import java.util.HashMap;
import java.util.List;

public class MapColoring {
	private String[] colors;
	private Country[] countries;

	public MapColoring(String colorsPath, String countriesPath) {
		colors = Parser.loadColors(colorsPath);
		countries = Parser.loadCountries(countriesPath);
	}

}
