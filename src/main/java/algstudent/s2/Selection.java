package algstudent.s2;

import util.Util;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection extends Vector {
	public Selection(int nElements) {
		super(nElements);
	}
	
	@Override
	public void sort() {
		/*
		 * Quite simmilar to bubble but in this case it finds directly 
		 * the minimum number of the rest of the array and substitutes it. 
		 * Then it changes to the next number
		 */
		int pos;
		for(int i = 0; i < elements.length;i++) {
			pos = Util.findPosMin(elements, i); //This command has O(n)
			// It iterates over the vector to find its minimum number.
			Util.interchange(elements, i, pos);
		}
	}  
	
	@Override
	public String getName() {
		return "Selection";
	} 
} 
