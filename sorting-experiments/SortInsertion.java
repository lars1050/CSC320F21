import java.util.Comparator;

/** Algorithm to sort a list of objects by continually Sorting
/* current element into the sorted list to its left.
*/
public class SortInsertion <T> {

  /** This is the typical call to insertion. It calls the other
  * version that operates on a subarray (used with Merge Sort)
  * @param array of Objects to be sorted
  * @param order Comparator used to determine ordering of objects
  * @return value returned from call to other version of sort
  */
  public static <T> int sort(T[] array, Comparator<T> order) {
    // operate on the entirety of the array
    return SortInsertion.sort(array, 1, array.length-1, order);
  }

  /** This version operates on a subarray. It is used with merge sort.
  * @param array of objects to be sorted
  * @param p index of the first element in the subarray.
  * @param r index of the last element in the subarray.
  * @param order Comparator used to determine ordering of elements.
  * @return number of operations
  */
  public static <T> int sort(T[] array, int p, int r, Comparator<T> order) {
    // reset ops (counting comparisons) since this is a new sort 
    int ops = 0;

    // at each iteration, insert element i into the subarray from p to i-1
    for (int i=p+1; i<=r; i++) {
      // store the value trying to insert
      T key = array[i];
      // start to the left of that element
      int j = i-1;
      ++ops; // add one for the comparison in the while loop
      // keep looking left until you find the place to insert.
      while (j >= p && order.compare(array[j],key)>0) {
        ++ops;
        // scooch over one to make room for element to insert
        array[j+1] = array[j];
        j--;
      }
      // insert element into its sorted location
      array[j+1] = key;
    }
    return ops;
  }
}
