import java.util.Random;
import java.util.function.Function;
import java.util.Comparator;

/** Framework to test sorting algorithms. */
public class Main {

	/************** Comparator for Integer ******/
	public static Comparator<Integer> defaultInts = new Comparator<Integer>() {
		@Override
		public int compare(Integer n1, Integer n2) {
			return n1 - n2;
		}
	};

	/** Lambda function to get the Integer of an Integer, hence the name */
	static Function<Integer,Integer> identity = obj -> obj;

	/**********************************************************************/
	/**********************************************************************/
	/**********************************************************************/

	public static void main(String[] args) {

		// TRY COUNTING SORT
		Integer[] numbers = { 5, 4, 1, 3, 0, 2, 1, 7, 8, 6 };
		System.out.print("pre-sort COUNTING ");
		for (Integer n : numbers) {
			System.out.print(n+" ");
		}
		System.out.println();


		SortCounting.sort(numbers, 10, identity);

		System.out.print("post-sort ");
		for (Integer n : numbers) {
			System.out.print(n+" ");
		}
		System.out.println();

		// --------------------------------------------
		// TRY MERGE SORT
		Integer[] numbers2 = { 5, 4, 1, 3, 0, 2, 1, 7, 8, 6 };
		System.out.print("pre-sort MERGE ");
		for (Integer n : numbers2) {
			System.out.print(n+" ");
		}
		System.out.println();

		SortMerge.sort(numbers2, defaultInts);

		System.out.print("post-sort ");
		for (Integer n : numbers2) {
			System.out.print(n+" ");
		}
		System.out.println();

		// --------------------------------------------
		// TRY INSERTION SORT
		Integer[] numbers3 = { 5, 4, 1, 3, 0, 2, 1, 7, 8, 6 };
		System.out.print("pre-sort INSERTION ");
		for (Integer n : numbers3) {
			System.out.print(n+" ");
		}
		System.out.println();

		SortMerge.sort(numbers3, defaultInts);

		System.out.print("post-sort ");
		for (Integer n : numbers3) {
			System.out.print(n+" ");
		}
		System.out.println();
	} // end main
} // end class main
