import java.util.Comparator;
import java.lang.System;
import java.util.Random;

/** Quick Sort for sorting a list of any type object. **/
public class SortQuick {

  /** Track number of comparisons / iterations. */
  private static int ops = 0;

  /** Random number generator for choosing a random pivot */
  private static Random rand;

  /** Entry point of Quicksort -- it calls the recursive method.
  * @param array array of objects needed to be sorted
  * @param order comparator for ordering elements of <T> type
  * @return number of comparisons
  */
  public static <T> int sort(T[] array, Comparator<T> order) {
    ops = 0;
    // seed the random number generator
    rand = new Random(System.currentTimeMillis());
    // Start the recursion on the full array
    quicksort(array, 0, array.length - 1, order);

    // ops were updated in quicksort()
    return ops;
  } //end sort()

  /**
  * Quicksort recursive function
  * @param array of objects for sorting
  * @param p start of subarray to be sorted at this recursive call
  * @param r end of subarray to be sorted
  */
  public static <T> void quicksort(T[] array, int p, int r, Comparator<T> order) {
    if (p < r) {
      // partition will divide the subarray into 2 "piles"
      // on either side of the element at index q
      int q = partition(array, p, r, order);
      // left and right of q in the subarray now need to be sorted.
      quicksort(array, p, q - 1, order);
      quicksort(array, q + 1, r, order);
    }
  } // end recursive quicksort()

  /** Partition iterates over array to "sort" elements into 2 piles.
  * The left pile are elements less than the value at A[r]
  * The right pile are elements greather than the value at A[r]
  * This version chooses 3 random elements and uses the middle value as
  * the pivot (i.e. value used to "sort" into 2 piles).
  */
  public static <T> int partition(T[] array, int p, int r,Comparator<T> order) {

    // Used in the swap process
    T temp;

    // helper function to find middle value of 3 randoms between p and r
    int midIndex = getRandomIndex(array, p, r, order);
    // swap this value with the last in the subarray to use as pivot
    temp = array[r];
    array[r] = array[midIndex];
    array[midIndex] = temp;

    // All values between index p and i are less than the pivot.
    // Since we don't know any yet, set i to the one before p
    int i = p - 1;

    // iterate over subarray to "sort" into piles of < and > the pivot
    // PEI, elements from p to j-1 have been sorted into piles
    for (int j = p; j < r; j++) {
      ops++;
      //comparing the current element in iteration to our pivot point
      if (order.compare(array[j], array[r]) < 0) {
        // advance the marker for values less than pivot
        i++;
        // place the element at j in the "< pile"
        temp = array[j];
        array[j] = array[i];
        array[i] = temp;
      }
    }
    // permanently place pivot (it will never again be compared to any other)
    i++; // advance marker for less than (equal to) pile from p to i
    temp = array[r];
    array[r] = array[i];
    array[i] = temp;
    // successfully sorted into piles. return location of pivot
    return i;
  } // end partition

  /** helper function to find the median among 3 randomly selected values
  * in the range p to r. This will be used as the "pivot".
  */
  private static <T> int getRandomIndex(T[] array, int p, int r, Comparator<T> order) {

    // if there are not enough elements to choose from, arbitrarily choose r
    if ((r-p+1) <= 3) {
      return r;
    }

    // need 3 random indices in the range of p to r
    int index1 = rand.nextInt(r - p + 1) + p;
    int index2 = rand.nextInt(r - p + 1) + p;
    int index3 = rand.nextInt(r - p + 1) + p;

    // Create an array to perform mini-sort on the 3 values
    int[] indices = {index1, index2, index3};

    // establish the INDEX of the min and place at index 0
    ++ops;
    if (order.compare(array[index1],array[index2]) < 0) {
      indices[0] = index2;
      indices[1] = index1;
    }
    ++ops;
    if (order.compare(array[index3], array[indices[0]]) < 0) {
      // index 3 is the min (else what is at 0 is the min)
      indices[2] = indices[0];
      indices[0] = index3;
    }
    // since min is at index 0, establish the min of last 2 (i.e. the middle)
    ++ops;
    if (order.compare(array[indices[1]],array[indices[2]]) < 0) {
      return indices[1];
    } else {
      return indices[2];
    }
  } // end getRandomIndex

} // end class
