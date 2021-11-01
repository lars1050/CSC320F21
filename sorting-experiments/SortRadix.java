import java.lang.Math;
import java.util.function.Function;
import java.util.Comparator;

/** Radix Sort algorithm used to sort a list of objects.
* In its current form, it can only handle Integers and sorting by the
* alpha component of Simple class objects.
*/
public class SortRadix {

  /** Track the number of iterations required to sort the list */
  private static int ops = 0;

  /** Creates lambda function to use "d" digit for sorting.
  @param digit indicates which char in the alpha string to capture
  @return function that will convert that char from give alpha to an int
	*/
  private static Function<Simple,Integer> getAlphaHashFunction(int digit) {
    // determine ASCII value of char at position "digit" and normalize
		// to be in range of 0 and 25
		return (simple) -> Character.getNumericValue(simple.alpha().charAt(digit)) - Character.getNumericValue('a');
	}

	/** Get the digit at the 10^position place.
	For example, if position==0, then getting the 10^0 (ones) digit.
	For example, if position==2, then getting the 10^2 (hundreds) digit.
	@param number to extract digit from
	@param position position of the digit
	*/
	private static int getDigit(Integer number, int position) {
    if (0 != position) {
      number = number / (int) Math.pow(10,position);
		}
		return number % 10;
	}

	/** Create a lambda function that uses getDigit function to map a position to a digit.
	@param pos position of digit to extract from Simple.numeric
	@return lambda function maps Simple.numeric to the digit at pos
	*/
	public static Function<Simple,Integer> getNumericHashFunction(int pos) {
		return (simple) -> getDigit(simple.numeric(),pos);
	}

  /** Create a lambda function that calls the getDigit function,
  with the position provided.
  @param pos position of digit to extract from Simple.numeric
  @return lambda function maps Simple.numeric to the digit at pos
  */
  public static Function<Integer,Integer> getIntegerHashFunction(int pos) {
    return (number) -> getDigit(number,pos);
  }

  public static <T> int sort(T[] array, int digits, boolean byInteger) {

    ops = 0;

    // IMPORTANT: You will need to get the number of operations for each loop
		// This means you have to get it from Counting sort in each loop and
		// add to total.
		if (byInteger) {  // Definitely Integers
			for (int d = 0; d<digits; d++) {
        ops += SortCounting.sort((Integer[]) array,9,getIntegerHashFunction(d));
      }
		} else {
      // This is not working due to some casting issues.
      // I will work on it some more to see if I can get this working.
			/*
      // We will assume that it is the Alpha part of Numeric
			for (int d = 0; d<digits; d++) { // Definitely Simples
        ops += SortCounting.sort((Simple[])array,26,getAlphaHashFunction(d));
      }
      */
		}
    return ops;
	} // end sort
} // end class
