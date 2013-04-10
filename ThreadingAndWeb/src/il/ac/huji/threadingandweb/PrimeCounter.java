package il.ac.huji.threadingandweb;

public class PrimeCounter {

	private int from, to;
	private int count;
	
	public PrimeCounter(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	private static boolean isPrime(int number) {
		if (number % 2 == 0 && number != 2) return false;
		int sqrt = (int)Math.sqrt(number);
		for (int i = 3; i <= sqrt; i += 2) {
			if (number % i == 0) return false;
		}
		return true;
	}
	
	public void count() {
		count = 0;
		for (int i = from; i <= to; ++i) {
			if (isPrime(i)) {
				++count;
			}
		}
	}
	
	public int getResult() {
		return count;
	}
	
}
