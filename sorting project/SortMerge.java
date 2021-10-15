import java.util.Comparator;

public class SortMerge {

  /** Records number of comparisons and iterations. */
  private static int ops = 0;

  /** Sorting algorithm using given comparator.
  @param A values to be sorted (in place)
  @param comparator for ordering <T> type elements
  @return number of operations
  */
  public static <T> int sort(T[] A, Comparator<T> comparator) {
    ops = 0;
    sort(A, 0, A.length-1, comparator);
    return ops;
  }

  /** The recursive function (Mergesort) following book's logic.
	@param p index of first element of subarray to sort.
	@param r index of last element of subarray to sort.
	*/
	private static <T> void sort(T[] A, int p, int r, Comparator<T> comparator) {
		if (p<r) {
			// 1 or more elements to sort
			// establish middle index
			int q = (p+r)/2;
			// sort left half
			sort(A,p,q,comparator);
			// sort right half
			sort(A,q+1,r,comparator);
			// merge left and right half
			merge(A,p,q,r,comparator);
			// now array[p] to array[r] are in sorted order
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
