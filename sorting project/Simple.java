public class Simple implements Comparable<Simple> {
	private String alpha = null;
	private Integer numeric = 0;

	public Simple() {
	}

	public Simple(String a, Integer n) {
		alpha = a;
		numeric = n;
	}

	public String alpha() { return alpha; }
	public void alpha(String a) { alpha = a; }
	public Integer numeric() { return numeric; }
	public void numeric(Integer n) { numeric = n; }

	@Override
	public String toString() {
		return "{"+alpha+","+numeric+"}";
	}

	@Override
	public int compareTo(Simple other) {
		return numeric.compareTo(other.numeric);
	}

	@Override
	public boolean equals(Object object) {
		if (this==object) return true;
		if (!(object instanceof Simple)) return false;

		Simple other = (Simple) object;
		return (alpha.equals(other.alpha) && numeric==other.numeric);

	}

	public static Comparator<Simple> byAlpha = new Comparator<Simple>() {
		@Override
		public int compare(Simple s1, Simple s2) {
			return s1.alpha().compareTo(s2.alpha());
		}
	};

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
