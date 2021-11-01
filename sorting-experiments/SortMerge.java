import java.util.Comparator;

/** Merge sorting algorithm that uses a divide-and-conquer strategy */
public class SortMerge {

  /** Used as the size limit to determine if combining with Insertion sort.
  If the value is 0, it means do not use Insertion sort. */
  private static int limit = 0;

  /** Records number of comparisons and iterations. */
  private static int ops = 0;

  /** Entry point into the algorithm. It calls the recursive function.
  @param A array of objects (generic) to be sorted.
  @param comparator used to establish order
  @param insertLimit indicates size of subarray in which to call Insertion sort
  @return ops (number of comparisons)
  */
  public static <T> int sort(T[] A, Comparator<T> comparator, int insertLimit) {
    limit = insertLimit;
    ops = 0;
    // Call the recursive sort to operate on the entire array.
    sort(A, 0, A.length-1, comparator);
    // ops is updated in the recursive calls.
    return ops;
  }

  /** Sorting algorithm using given comparator.
  @param A values to be sorted (in place)
  @param comparator for ordering <T> type elements
  @return number of operations
  */
  public static <T> int sort(T[] A, Comparator<T> comparator) {
    return SortMerge.sort(A,comparator,0);
  }

  /** The recursive function (Mergesort) following book's logic.
  @param p index of first element of subarray to sort.
  @param r index of last element of subarray to sort.
  */
  private static <T> void sort(T[] A, int p, int r, Comparator<T> comparator) {
    if (p < r) {
      // if not using or not ready for insertion sort, proceed as usual
      if ((limit == 0) || (r - p > limit)) {
        // calculate center of the subarray (between p and r)
        int q = (p + r) / 2;
        // sort left of middle
        sort(A, p, q, comparator);
        // sort right of middle
        sort(A, q + 1, r, comparator);
        // merge the 2 halves
        merge(A, p, q, r, comparator);
      } else {
        // use insertion sort to sort the subarray from p to r
        // add the operations from that part to those from this part
        ops += SortInsertion.sort(A, p, r, comparator);
      }
    }
  }

	/** Given a subarray with left and right halves sorted, merge left and right into sorted order.
	@param p index of first element in subarray
	@param q middle index of subarray (marks end of left half)
	@param r index of last element of subarray
	*/
	private static <T> void merge(T[] A, int p, int q, int r, Comparator<T> comparator) {
		// Instantiate new arrays to hold copies of the left and right halves.
		@SuppressWarnings("unchecked")
		T[] left = (T[]) new Object[q-p+1];
		@SuppressWarnings("unchecked")
		T[] right = (T[]) new Object[r-q];

		// Copy elements from array into left and right halves.
		int iLeft = 0;
		for (int i=p; i<=q; i++) {
			left[iLeft] = A[i];
			iLeft++;
      ++ops;
		}
		int iRight = 0;
		for (int i=q+1; i<=r; i++) {
			right[iRight] = A[i];
			iRight++;
      ++ops;
		}

		// index into the left and right subarrays to mark next element to merge.
		iRight = 0;
		iLeft = 0;

		// iterate over the subarray from A[p] to A[r]
		for (int i=p; i<=r; i++) {

			ops++;	// record number of ops (equating a forloop to single op)

			// Determine which list holds next min and move back into the array
			if (comparator.compare(left[iLeft],right[iRight])<0) {
				// the front of the left side is smaller, it goes next.
				A[i] = left[iLeft];
				iLeft++;
        if (iLeft >= left.length) {
          // Left is empty. Fill with the rest of right array
          for (int j=i+1; j<=r; j++) {
            ++ops;
            A[j] = right[iRight];
            ++iRight;
          }
          break;
        }
			} else {
				// the front of right side is smaller.
				A[i] = right[iRight];
				iRight++;
        if (iRight >= right.length) {
          // Right is empty. Fill with the rest of the left array
          for (int j=i+1; j<=r; j++) {
            ++ops;
            A[j] = left[iLeft];
            ++iLeft;
          }
          break;
        }
			} // end of the if-else to determine smallest element to add
		} // end of for-loop for merging back into array
	} // end merge
} // end class
