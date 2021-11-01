import java.util.Comparator;
import java.util.function.Function;

/** A simple class with a string (alpha) and number (numeric)
*/
public class Simple implements Comparable<Simple> {
	private String alpha = null;
	private Integer numeric = 0;

	/**Default Constructor **/
	public Simple() {
	}

	/** Constructor with values */
	public Simple(String a, Integer n) {
		alpha = a;
		numeric = n;
	}

	/** Getters and setters */
	public String alpha() { return alpha; }
	public void alpha(String a) { alpha = a; }
	public Integer numeric() { return numeric; }
	public void numeric(Integer n) { numeric = n; }

	/** Pretty print the contents */
	@Override
	public String toString() {
		return "{"+alpha+","+numeric+"}";
	}

	/** Default comparison of the numbers */
	@Override
	public int compareTo(Simple other) {
		return numeric.compareTo(other.numeric);
	}

	/** Two objects equal each other if both components are equal*/
	@Override
	public boolean equals(Object object) {
		// If same object, at same memory location, they are equal
		if (this==object) return true;

		// Make sure the right type before casting.
		if (!(object instanceof Simple)) return false;

		// Cast and check that both things match
		Simple other = (Simple) object;
		return (alpha.equals(other.alpha) && numeric==other.numeric);

	}

	/** Using the alpha component to establish order */
	public static Comparator<Simple> byAlpha = new Comparator<Simple>() {
		@Override
		public int compare(Simple s1, Simple s2) {
			return s1.alpha().compareTo(s2.alpha());
		}
	};

	/** Using the numeric component to establish order */
	public static Comparator<Simple> byNumeric = new Comparator<Simple>() {
		@Override
		public int compare(Simple s1, Simple s2) {
			return s1.numeric().compareTo(s2.numeric());
		}
	};

	/** Lambda function to get an index value from a string.
	It uses the first letter and maps it to an index in range 0 to 25.
	*/
	static Function<Simple,Integer> getAlpha = obj -> Character.getNumericValue(obj.alpha().charAt(0)) - Character.getNumericValue('a');

	/** Lambda function to get the value of the numeric from the Simple object.
	*/
	static Function<Simple,Integer> getNumeric = obj -> obj.numeric();

}
