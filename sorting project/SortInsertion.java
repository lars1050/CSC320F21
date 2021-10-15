public class SortInsertion <T> {

  public static int sort(T[] array, Comparator<T> order) {

    int ops = 0;

    for (int i=1; i<array.length; i++) {
      T key = array[i];
      int j = i-1;
      ++ops; // add one for the comparison in the while loop
      while (j >= 0 && order.compare(A[j],key)>0) {
        ++ops;
        A[j+1] = A[j];
        j--;
      }
      A[j+1] = key;
    }
    return ops;
  }
}
