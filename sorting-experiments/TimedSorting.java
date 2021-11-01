import java.util.function.Function;
import java.util.Comparator;

/** Used as part of a comparative study of different sorting algorithms. */
public class TimedSorting <T extends Comparable<T>> {

  /** Algorithm type */
  public static enum Algo {
    INSERTION, QUICK, MERGE, COUNTING, RADIX
  }

  /** start time in nanoseconds */
  private long startNS;
  /** end time in nanoseconds */
  private long endNS;

  /** number of operations/iterations in sorting process.
  machine-independent measure of runtime. */
  private long operations;

  /** Outfile to which results will be written */
  String outfile;

  /** Maximum value in the data file (gotten from the file) */
  Integer maxValue = 0;

  /** Max number of digits across all data (calculated), for Radix */
  Integer digits = 0;

  /** Contains all values in the file up to max size. New arrays are created
  from this array, and then sorted with one of the algorithms. */
  T[] maxSizedArray = null;

  /** The range of sizes to use in experiments. There should be 10 of them.
  For example, if the file contains 1,000,000 elements, then perhaps
  sizes = {100,1000,5000,10000,25000,75000,100000,250000,750000,1000000}
  */
  Integer[] sizes = null;

  /** Constructor. Initializes metrics.
  @param inputFile name of file containing data to sort
  @param outputFile name of file to which results will be written
  @param sizes array of increasing sizes to run to get data.
  */
  public TimedSorting(String inputFile, String outputFile, Integer[] sizes) {

    // Prepare data by copying into an array of largest value in sizes.
    // Along the way, record the max value to determine the number of digits.
    String[] dataInfo = DataIO.getDataType(inputFile);
    if (null==dataInfo) {
      System.out.println("ERROR: dataType in file unknown. Abandon ship!");
      return;
    }
    // Get more information about the data to be used in calls to other algos
    int size = sizes[sizes.length-1];
    if ("Integer".equals(dataInfo[0])) {
      maxSizedArray = (T[]) DataIO.readIntegers(inputFile,size);
      maxValue = Integer.valueOf(dataInfo[1]);
      digits = calculateDigits(maxValue);
      System.out.print("Integers ");
    } else {
      maxSizedArray = (T[]) DataIO.readSimples(inputFile,size);
      digits = Integer.valueOf(dataInfo[1]);
      System.out.print("Simples ");
    }

    // Confirm output file is okay to write to.
    boolean exists = DataIO.checkOutputFile(outputFile);
    if (exists) {
      System.out.println("Output file exists. Data will be appended.");
    } else {
      System.out.println("Something wrong with output file. Abandon ship!");
    }
    outfile = outputFile;
    this.sizes = sizes;
  }

  /** This is used to experiment with either QUICK or INSERTION sort.
  @param algo Quick or Insertion
  @param orderBy Comparator to establish ordering of elements
  */
  public void experiment(Algo algo, Comparator<T> orderBy) {

    // This must be either quick or insertion
    if ((algo != Algo.QUICK) && (algo != Algo.INSERTION)) {
      // should be throwing an exception ...
      return;
    }

    // Create a function call for either Quick or Insertion
    Function<T[],Integer> sortCall;
    if (algo == Algo.QUICK) {
      sortCall = (array) -> SortQuick.sort(array,orderBy);
      // This is probably where you want to write to the file
      // or save a string to write to the file
      System.out.print("Quick ");
    } else {
      sortCall = (array) -> SortInsertion.sort(array,orderBy);
      System.out.print("Insertion ");
    }

    // ready to run experiment -- pass function call
    runExperiment(sortCall);
  }

  /** This is used to experiment with MERGE sort
  @param algo should be MERGE
  @param orderBy Comparator determines ordering of values
  @param param sizeLimit for using Insertion sort with Merge
  */
  public void experiment(Algo algo, Comparator<T> orderBy, Integer param) {

    // this has to be merge sort
    if (algo != Algo.MERGE) {
      // should probably be throwing an error
      System.out.println("ERROR: algorightm unknown. Abandoning ship!");
      return;
    }

    //  Create a function call that can be used to test different sized arrays
    Function<T[],Integer> sortCall;
    sortCall = (array) -> SortMerge.sort(array,orderBy,param);
    System.out.print("Merge ");
    runExperiment(sortCall);
  }

  /** This is used to experiment with COUNTING sort
  @param algo should be MERGE
  @param fn Used to extract value used for indexing the histogram
  */
  public void experiment(Algo algo, Function<T,Integer> fn) {

    // This has to be counting sort
    if (algo != Algo.COUNTING) {
      System.out.println("ERROR: algorightm unknown. Abandoning ship!");
      return;
    }

    // Create a function call that can be used to test different sized arrays
    Function<T[],Integer> sortCall;
    sortCall = (array)->SortCounting.sort(array, maxValue, fn);
    System.out.print("Counting ");
    runExperiment(sortCall);
  }

  /** This is used to experiment with RADIX sort
  @param algo should be RADIX
  @param param true if using Integers, false if using Simples
  */
  public void experiment(Algo algo, Boolean param) {
    // This has to be Radix Sort
    if (algo != Algo.RADIX) {
      System.out.println("ERROR: algorightm unknown. Abandoning ship!");
      return;
    }
    // Create a function call that can be used to test different sized arrays
    Function<T[],Integer> sortCall;
    sortCall = (array)->SortRadix.sort(array, digits, param);
    System.out.print("Radix ");
    runExperiment(sortCall);
  } // end experiment(algo,param)

  /** Run a series of experiments on different sizes of arrays.
  @param sortCall is an algorithm function call
  */
  private void runExperiment(Function<T[],Integer> sortCall) {

    // For each of the array sizes specified ...
    for(int i=0; i<sizes.length; i++) {

      // Make a copy of the unsorted array in appropriate size sizes[i]
      @SuppressWarnings("unchecked")
      T[] array = (T[]) new Comparable[sizes[i]];
      for (int j=0; j<sizes[i]; j++) {
        array[j] = maxSizedArray[j];
      }

      // Time the call to the sorting function
      startNS = System.nanoTime();
      operations = sortCall.apply(array);
      endNS = System.nanoTime();

      // save results to file - just printing for now
      System.out.println("\nPost Sorting");
      for (T el : array) {
        System.out.print(el+" ");
      }
      System.out.println();

      // this for debugging purposes to check timer.
      // but this is the information you want to write to the file
      System.out.println("start="+startNS+". end="+endNS+". elapsed="+(endNS-startNS)/1000);

    }
  } // end runExperiment()

  /** Getter for number of operations. */
  public long operations() {
    return operations;
  }

  /** Getter for elapsed time in microsecs based on stored start and end time, which are in ns */
  public long elapsedTimeUS() {
    return (endNS - startNS) / 1000;
  }

  /** Determine number of digits in value -- used to call Radix sort**/
  private Integer calculateDigits(Integer value) {
    Integer d = 1;
    value = (Integer) (value / 10);
    while (value < 0) {
      value = (Integer) (value / 10);
      d++;
    }
    return d;
  }

}
