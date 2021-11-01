import java.io.*;
import java.util.*;

/** DataIO manages the making of data files, reading of data files,
as well as the writing to the output file (NOT IMPLEMENTED).
The TimedSorting class can take a single data file and create multiple sized
arrays that can be used in a series of experiments.
**/
public class DataIO {

  // Used to specify characteristic of data file
  public enum Config {
    RANDOM, // random values from 0 to maxValue, or strings with alphaLength
    SORTED, // in order from 0 to maxValue; or aaaaa, aaaab, aaaac, ...
    REVERSE, // in reverse order maxValue to 0; or zzzzz, zzzzy, zzzzx
    LARGE   // super big numbers or 10-char strings
  };

  // Initial seed value
  // If you want to test with the same random values, use the same seed
	public static long seed = 100;

  public static Random rng = new Random(seed);

  // range of values when creating random integers from 0 to maxValue
  public static int maxValue = 10000000;

	// Together these determine range of values when making LARGE number arrays.
	// Numbers start at minLarge and go up to minLarge+maxDelta.
	public static int minLarge = 100000000;
	public static int maxDelta =  99999999;

	// fixed size of the string (for alpha) when creating random strings
  public static int alphaLength = 5;

  /** Creating a file with size elements.
  @param filename file will be overwritten
  @param size total number of Simple elements added to file
  @param config characteristic of values created
  */
  public static void makeInteger(String filename, int size, Config config) {

		rng = new Random(seed);

    // Try-with-resources.
    // Java will close this resource regardless of how it exits the try-catch
    try (FileWriter writer = new FileWriter(filename)) {

      // First add the data type and the maximum value
      writer.write("Integer "+maxValue+" ");

      // Fill it in with the requested data configuration
      if (Config.RANDOM==config) {
        for (int i=0; i<size; i++) {
          writer.write(rng.nextInt(maxValue)+" ");
        }
      } else if (Config.SORTED==config) {
        for (int i=0; i<size; i++) {
          writer.write(i+" ");
        }
      } else if (Config.REVERSE==config) {
        for (int i=0; i<size; i++) {
          writer.write((size-i)+" ");
        }
      } else if (Config.LARGE==config) {
        for (int i=0; i<size; i++) {
          // in range 100,000,000 to 199,999,998
          writer.write((minLarge+rng.nextInt(maxDelta))+" ");
        }
      }
    }  catch (IOException e) {
      System.out.println("Error opening file "+filename);
      e.printStackTrace();
      return;
    } // end try-catch
  } // end integermaker

  /*-----------------------------------------------------------------*/
  //              MAKE SIMPLE
  /*-----------------------------------------------------------------*/

  /** Generate a random string with size characters
  @param length of every string
  @return random string
  */
  public static String randomString(int length) {
    int a = 97;         // ascii value of a
    String alpha = "";  // string to be returned
    // create each letter and concatenate to string alpha
    for (int k=0; k<length; k++) {
      // random number corresponding to a letter. 0 is a, 1 is b, ...
      // add it to the value of a to get its ASCII value, then
      // get corresponding char
      char r = (char) (rng.nextInt(26)+a);
      // concatenate to string by converting char to String
      alpha += String.valueOf(r);
    }
    return alpha;
  }

  /** Generate next string in order. Essentially, this is counting with 26 digits (as opposed to 10). 0 is "a", 1 is "b", ... , 25 is "z".
  It modifies ascii by 1 and returns the string.
  @param ascii current "string" to increment or decrement
  @param reverse true if decrement instead of increment
  @return next string converted from ascii
  */
  public static String nextString(int[] ascii, boolean reverse) {
    if (!reverse) {
      // the last digit is the one that we add or subtract 1
      int i = ascii.length-1;
      // the carry if there is overflow in that digit (starts the adding of 1)
      int carry = 1;
      // as long as the 1 is carrying to the next digit ...
      // assuming it will not overflow
      while (1==carry && i<ascii.length) {
        ascii[i] = ascii[i]+carry;
        // if it overflows
        if (ascii[i]==26) {
          carry = 1;
          ascii[i] = 0;
        } else {
          carry = 0;
        }
        i--;
      }// end while
    } else {
      int i = ascii.length-1;
      // same as above except might need to borrow instead of carry
      int borrow = 1;
      while (1==borrow && i<ascii.length) {
        ascii[i] = ascii[i] - borrow;
        if (ascii[i] < 0) {
          ascii[i] = 25;
        } else {
          borrow = 0;
        }
        i--;
      } // end while
    }
    // convert ascii to its corresponding string.
    int a = 97;
    String alpha = "";
    for (int value : ascii) {
      // value in range of 0 to 25, add to "a" to get ascii value
      char r = (char) (value+a);
      // concatenate to string by converting char to String
      alpha += String.valueOf(r);
    }
    return alpha;
  }

  /** Creating a file with 2*size elements alternating string, number.
  @param filename file will be overwritten
  @param size total number of Simple elements added to file
  @param config characteristic of values created
  @param byAlpha when sorting, sort by alpha (true) or sort by numeric (false)
  */
  public static void makeSimple(String filename, int size, Config config, boolean byAlpha) {

    int[] current = new int[alphaLength];

    // Open the file
    try (FileWriter writer = new FileWriter(filename)) {

      writer.write("Simple "+alphaLength+" "+maxValue+" ");

      String alpha = "";
      if (Config.RANDOM==config) {
        for (int i=0; i<size; i++) {
          writer.write(randomString(5)+" "+rng.nextInt(maxValue)+" ");
        }
      } else if (Config.SORTED==config) {
        for (int i=0; i<size; i++) {
          if (byAlpha) {
            writer.write(nextString(current,false)+" "+rng.nextInt(maxValue)+" ");
          } else {
            writer.write(randomString(alphaLength)+" "+i+" ");
          }
        }
      } else if (Config.REVERSE==config) {
        for (int i=0; i<alphaLength; i++) {
          current[i] = 25;
        }
        for (int i=0; i<size; i++) {
          if (byAlpha) {
            writer.write(nextString(current,true)+" "+rng.nextInt(maxValue)+" ");
          } else {
            writer.write(randomString(alphaLength)+" "+(size-i)+" ");
          }
        }
      } else if (Config.LARGE==config) {
        current = new int[10];
        for (int i=0; i<size; i++) {
          writer.write(randomString(10)+" " + (minLarge+rng.nextInt(maxDelta))+" ");
        }
      }

    } catch (IOException e) {
      System.out.println("Error with file "+filename);
      e.printStackTrace();
      return;
    } // end try-catch
  } // end simplemaker


  /*-----------------------------------------------------------------*/
  //             READING FILES with Integers and Simples
  /*-----------------------------------------------------------------*/

  /** Reads first value of file to determine contents of file.
  @param filename (relative path) to the file containing values.
  @return String either "Integer" or "Simple"
  */
  public static String[] getDataType(String filename) {

    // Every file begins with data type Integer or Simple
    // If it is Integer, it will be followed by the max value
    // If it is a Simple, it will be followed by string length and max value
    String[] returnValues = new String[3];

    // try-with-resources.
    // Java will close the file regardless of how it exits try-catch
    try (Scanner scan = new Scanner(new File(filename))) {
      // Get the data type
      returnValues[0] = scan.next();
      if ("Integer".equals(returnValues[0])){
        // max value
        returnValues[1] = scan.next();
      } else if ("Simple".equals(returnValues[0])) {
        // string length and max value
        returnValues[1] = scan.next();
        returnValues[2] = scan.next();
      }
      return returnValues;
    } catch (IOException e) {
      System.out.println("Error opening/reading file "+filename);
      e.printStackTrace();
      return null;
    } // end try-catch
  } // end getdataType

  /** Reads the files created by DatasetMaker and puts values into an array of Integers.
  @param filename (absolute path) to the file containing values.
  @param size array size (the number of values to read from the file)
  @return Integer array with first "size" values of file
  */
  public static Integer[] readIntegers(String filename, int size) {

    // create the array to fill
    Integer[] array = new Integer[size];

    // Open the file
    try { Scanner scan = new Scanner(new File(filename));

      String temp = scan.next();  // read out data type
      temp = scan.next();         // read out max value

      // Fill all "size" elements of the array
      // NOTICE: no error checking for end of file. This will crash
      // if there are fewer items in the file than "size"
      for (int i=0; i<size; i++) {
        // The file has values INTEGER INTEGER INTEGER ...
        // read one INTEGER and add to array.
        array[i] = Integer.valueOf(scan.next());
      }
      return array;
    } catch (Exception e) {
      System.out.println("Error reading from file "+filename);
      e.printStackTrace();
      return null;
    } // end try-catch
  } // end readIntegers()

  /** Reads the files created by DatasetMaker and puts values into an array of Simples.
  @param filename (absolute path) to the file containing values.
  @param size array size (the number of values to read from the file)
  @return Simple array with "size" Simple objects
  */
  public static Simple[] readSimples(String filename, int size) {

    // Open the file
    try (Scanner scan = new Scanner(new File(filename))) {

      // create the array to fill
      Simple[] array = new Simple[size];

      String temp = scan.next();  // read out data type
      temp = scan.next();         // read out string length
      temp = scan.next();         // read out maxvalue

      // Fill all "size" elements of the array
      // NOTICE: no error checking for end of file. This will crash
      // if there are fewer items in the file than "size"
      for (int i=0; i<size; i++) {
        // The file has values ALPHA NUMERIC ALPHA NUMERIC ...
        // read an ALPHA NUMERIC pair and make a new Simple Object.
        array[i] = new Simple(scan.next(), Integer.valueOf(scan.next()));
      }
      return array;
    } catch (Exception e) {
      System.out.println("Error reading from file "+filename);
      e.printStackTrace();
      return null;
    } // end try-catch
  } // end readSimples

  /*-----------------------------------------------------------------*/
  //             RECORDING RESULTS
  /*-----------------------------------------------------------------*/

  /** Confirm output file is okay (before we launch the experiment)
  @param filename (absolute path) to the file containing values.
  @return String either "Integer" or "Simple"
  */
  public static boolean checkOutputFile(String filename) {

    // Open the file for appending (hence the true)
    try (FileWriter writer = new FileWriter(filename,true)) {
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  } // end checkOUtputFile

} // end class
