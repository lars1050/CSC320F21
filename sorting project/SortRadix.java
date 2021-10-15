public class SortRadix <T> {

  /** Creates a lambda function to use the "d" digit for sorting.
	*/
  private static Function<Simple,Integer> getAlphaHashFunction(int digit) {
    // determine ASCII value of char at position "digit" and normalize
		// to be in range of 0 and 25
		return (simple) -> Character.getNumericValue(simple.alpha().charAt(digit)) - Character.getNumericValue('a');
	}

	/** Get the digit at the 10's position place.
	For example, if position==0, then getting the 10^0 (ones) digit.
	For example, if position==2, then getting the 10^2 (hundreds) digit.
	@param number to extract digit from
	@param position position of the digit
	*/
	private static int getDigit(Integer number, int position) {
    if (0 != position)
      number = number / (position*10);
		}
		return number % 10;
	}

	/** Create a lambda function that uses the getDigit function to map a position to a digit.
	@param pos position of digit to extract from Simple.numeric
	@return lambda function maps Simple.numeric to the digit at pos
	*/
	public static Function<Simple,Integer> getNumericHashFunction(int pos) {
		return (simple) -> getDigit(simple.numeric(),pos);
	}

  public static <T> int sort(T[] array, Function<T,Integer> fn) {

    // IMPORTANT: You will need to get the number of operations for each loop
		// This means you have to get it from Counting sort in each loop and
		// add to total.
		if (orderAlpha) {
			// @TODO ***^^^^^^^^^^^^^^^^ FILL ME IN
		} else {
			// @TODO ***^^^^^^^^^^^^^^^^ FILL ME IN
		}
	} // end sort
} // end class
