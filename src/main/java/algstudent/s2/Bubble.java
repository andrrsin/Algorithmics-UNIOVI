package algstudent.s2;

import util.Util;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the BUBBLE or DIRECT EXCHANGE */
public class Bubble extends Vector {
	public Bubble(int nElements) {
		super(nElements);
	}

	@Override
	public void sort() {
		/*
		 * It iterates from left to right through all the elements 
		 * but the used before, if the element at its left is bigger 
		 * they swap
		 */
		for(int i = 1;i <elements.length;i++) {
			for(int j = elements.length-1; j>=i;j--) {
				if(elements[j-1]>elements[j])
					Util.interchange(elements, j-1, j);
			}
		}
	}  
	
	@Override
	public String getName() {
		return "Bubble";
	} 
} 

