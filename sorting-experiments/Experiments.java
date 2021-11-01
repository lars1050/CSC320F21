import java.util.Random;
import java.lang.System;
import java.util.function.Function;
import java.util.Comparator;

public class Experiments {

  /** Comparator for ordering Integers */
  public static Comparator<Integer> defaultInts = new Comparator<Integer>() {
    @Override
    public int compare(Integer n1, Integer n2) {
      return n1 - n2;
    }
  };

  /** Lambda function to get the Integer of an Integer, hence the name */
  static Function<Integer,Integer> identity = obj -> obj;

  /** STRUCTURE YOUR EXPERIMENTS HERE **/
  public static void main(String[] args) {
    //make_integers();  // if you have not yet made it
    //make_simples(); // if you have not made them
    run_experiment_1(); // Insertion with random, sorted, reversed Integers
    //run_experiment_2(); // Quick with sorted integers, simple, large
  }

  /** Make files with integers in it. Everything is hard-coded
  It is using the file maker functions in DataIO. */
  private static void make_integers() {

    // These are set low for initial testing purposes
    // In experiments, these should be VERY LARGE numbers
    Integer maxSize = 100;
    Integer[] sizes = {10,25,50,75,100};

    // Set the maxValue same as maxSize so that the numbers are 1,2,3,...
    DataIO.maxValue = maxSize;

    // create various data files with Integer objects (if you haven't yet)
    DataIO.makeInteger("integer-sorted.txt", maxSize, DataIO.Config.SORTED);
    DataIO.makeInteger("integer-reversed.txt", maxSize, DataIO.Config.REVERSE);

    // Create 3 versions of random files - take the average of the 3
    // Seed the random number generator
    DataIO.seed = System.currentTimeMillis();
    DataIO.makeInteger("integer-random1.txt", maxSize, DataIO.Config.RANDOM);
    // Re-seed random number generator to create different file
    DataIO.seed = System.currentTimeMillis();
    DataIO.makeInteger("integer-random2.txt", maxSize, DataIO.Config.RANDOM);
    // Re-seed random number generator to create different file
    DataIO.seed = System.currentTimeMillis();
    DataIO.makeInteger("integer-random3.txt", maxSize, DataIO.Config.RANDOM);
  }

  /** Create files of simple objects */
  private static void make_simples() {

    // This is set low for initial testing purposes
    // In experiments, this should be a VERY LARGE number
    Integer maxSize = 100;

    // Set the maxValue same as maxSize so that the numbers are 1,2,3,...
    DataIO.maxValue = maxSize;
    // Alpha components of Simple will be a string of 5 random characters
    DataIO.alphaLength = 5;

    // create various data files with Simple objects
    DataIO.makeSimple("simple-sorted.txt", maxSize, DataIO.Config.SORTED,true);
  }

  /** Set up for one experiment */
  private static void run_experiment_1() {

    /* Experiment 1 is a comparison of Insertion Sort as it operates on:
    * - random Integers (average of 3 files)
    * - sorted integers
    * - sorted in reverse Integers
    */

    // These are set low for initial testing purposes
    // In experiments, these should be VERY LARGE numbers,
    // and there should be 10 of them
    Integer[] sizes = {10,50,100};

    String[] infiles = {"integer-sorted.txt","integer-reversed.txt","integer-random1.txt","integer-random2.txt","integer-random3.txt"};

    TimedSorting ts;
    for (String file : infiles) {
      ts = new TimedSorting<Integer>(file,"experiment1.csv",sizes);
      ts.experiment(TimedSorting.Algo.INSERTION, defaultInts);
    }
  }

  private static void run_experiment_2() {

    /* Experiment 2 is a comparison of Quick Sort and Radix Sort on Simples.*/

    // These are set low for initial testing purposes
    // In experiments, these should be VERY LARGE numbers,
    // and there should be 10 of them
    Integer[] sizes = {10,25,50,75,100};

    TimedSorting simpleTimed
    = new TimedSorting<Simple>("simple-sorted.txt","experiment2.csv",sizes);

    simpleTimed.experiment(TimedSorting.Algo.QUICK, Simple.byAlpha);
    simpleTimed.experiment(TimedSorting.Algo.RADIX, false);
  }

  private static void run_experiment_3() {

    /* Experiment 3 is a comparison of Counting and Radix Sort on LARGE.*/

    // These are set low for initial testing purposes
    // In experiments, these should be VERY LARGE numbers,
    // and there should be 10 of them
    Integer[] sizes = {10,25,50,75,100};

    TimedSorting simpleTimed
    = new TimedSorting<Integer>("large-random.txt","experiment3.csv",sizes);

    simpleTimed.experiment(TimedSorting.Algo.COUNTING, defaultInts);
    simpleTimed.experiment(TimedSorting.Algo.RADIX, true);
  }
}
