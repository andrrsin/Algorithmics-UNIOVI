package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the DIRECT INSERTION */
public class Insertion extends Vector {
	public Insertion(int nElements) {
		super(nElements);
	}

	@Override
	public void sort() {
		int j;
		int pivot;
		/*
		 * Separates the "vector" in two and taking the rightmost value as pivot it
		 * starts comparing with the rest. The moment it finds the element at its left 
		 * is smaller it swaps. After that when the element at the left is bigger it 
		 * changes to that element and starts again.
		 * 
		 */
		for (int i = 1; i < elements.length; i++) {
			j = i - 1;
			pivot = elements[i];
			while ( j >= 0 && pivot < elements[j]) {
				elements[j+1] = elements[j];
				j--;
			}
			elements[j+1] = pivot;
		}
	}

	@Override
	public String getName() {
		return "Insertion";
	}
}
