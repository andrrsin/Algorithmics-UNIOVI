package algstudent.s4;

public class RunMapColoring {

	public static void main(String[] args) {
		MapColoring mc = new MapColoring();
		mc.printColors();
		mc.printCountries();
		
		mc.greedyColoring();
		System.out.println("---------------------------------------------");
		mc.printCountries();

	}

}
