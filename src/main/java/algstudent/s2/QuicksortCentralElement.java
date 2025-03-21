﻿package algstudent.s2;

import util.Util;

/* This program can be used to sort n elements with 
 * the best algorithm of this lab. It is the QUICKSORT */
public class QuicksortCentralElement extends Vector {
	
	public QuicksortCentralElement(int nElements) {
		super(nElements);
	}
	
	private void quickSort(int left, int right) {
		if(left < right) {
			int i  = left;
			int j = right-1;
			int center = elements[(right-left)/2];
			
			/*
			 * the moment the subarray is less than 3 they are ordered.
			 */
			if(left-right >= 3) {
				
				Util.interchange(elements, right, center);//hide the pivot
				do {
					while(elements[i] <= center && i <right)
						i++;
					while(elements[j] >= center && j > left)
						j--;
					if(i<j)
						Util.interchange(elements, i, j);
				}while(i<j);
				Util.interchange(elements, i, right);
				quickSort(left,i-1);
				quickSort(i+1,right);
				
			}
		}
	}

	@Override
	public void sort() {
		quickSort(0, elements.length-1);		
	}
	
	@Override
	public String getName() {
		return "Quicksort - Central element";
	} 
} 
