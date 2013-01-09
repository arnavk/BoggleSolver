import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		char matrix[][] = 
		{ 
			{'q', 'e',},
			{'e', 'n',}
		};
		
		Solver solver = null;
		try {
			solver = new Solver();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long time_start = System.currentTimeMillis();
		HashSet<String> words = solver.foundWords(matrix, true);
		long time_end = System.currentTimeMillis();
		System.out.println("time spent finding words: " + (time_end - time_start));
		Iterator<String> it = words.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
	}
}
