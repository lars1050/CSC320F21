import java.util.function.Function;

public class SortCounting {

  /** Method call from outside class to sort input array.
  @param array array that will contain sorted elements upon fn return.
  @param max maximum value used to size the histogram (i.e. C array)
  @param fn hash function applied to T object to determine index
  */
  public static <T> int sort(T[] array, Integer max, Function<T,Integer> fn) {
    // keep track of iterations
    int iterations = 0;

    // Make a copy so that the original array has the final sorted values
    @SuppressWarnings("unchecked")
		T[] copy = (T[]) new Object[array.length];
		for (int i=0; i<array.length; i++) {
      ++iterations;
			copy[i] = array[i];
		}

    // Make a histogram
    @SuppressWarnings("unchecked")
    Integer[] histogram = new Integer[max+1];
    for (int i=0; i<histogram.length; i++) {
      histogram[i] = 0;
      ++iterations;
    }

    // Fill histogram with frequency of each value.
    for (T val : array) {
      histogram[fn.apply(val)]++;
      ++iterations;
    }

    // Accumulate the sum across the histogram.
    for (int i=1; i<histogram.length; i++) {
      histogram[i] += histogram[i-1];
      ++iterations;
    }

    // Place the values in sorted order back into the array
    for (int i=copy.length-1; i>=0; i--) {
      int placeAt = histogram[ fn.apply(copy[i])] - 1;
      array[ placeAt ] = copy[i];
      histogram[ fn.apply(copy[i]) ]--;
      ++iterations;
    } // end put back in sorted order

    return iterations;

  } // end sort
}
