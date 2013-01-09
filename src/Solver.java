import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solver {
	private boolean q_is_qu_flag;
	private Trie st_machine;
	private HashSet<String> words_;
	int count;
	
	public Solver() throws IOException {
		q_is_qu_flag = true;
		st_machine = new Trie();
		words_ = new HashSet<String>();
		
		// insert dictionary words
		FileInputStream fis = new FileInputStream("wordlist.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
		String word;
		while ((word = br.readLine()) != null) {
			st_machine.insert(word);
		}
		br.close();
		count = 0;
	}
	
	private char[][] clone2DMatrix(char matrix[][]) {
		char neo_matrix[][] = new char[matrix.length][matrix[0].length];
		for (int i=0; i < matrix.length; i++) {
			for (int j=0; j < matrix[0].length; j++) {
				neo_matrix[i][j] = matrix[i][j];
			}
		}
		count++;
		return neo_matrix;
	}
	
	private void check(char matrix[][], int i, int j) {
		if (i < 0 || i >= matrix.length || 
			j < 0 || j >= matrix[0].length)
			return;
		
		// check if visited
		if (matrix[i][j] == '0')
			return;
		
		if (!st_machine.transition_forward(matrix[i][j]))
			return;

		// special case 'q' becomes 'qu'
		// additional transition forward
		if (matrix[i][j] == 'q' && q_is_qu_flag) {
			if (!st_machine.transition_forward('u'))
				return;
		}
		
		char neo_matrix[][] = clone2DMatrix(matrix);
		neo_matrix[i][j] = '0';
		
		if (st_machine.isWord())
			words_.add(st_machine.path());
		
		check(neo_matrix, i-1, j-1);
		check(neo_matrix,   i, j-1);
		check(neo_matrix, i+1, j-1);
		check(neo_matrix, i-1,   j);
		check(neo_matrix, i+1,   j);
		check(neo_matrix, i-1, j+1);
		check(neo_matrix,   i, j+1);
		check(neo_matrix, i+1, j+1);
		
		st_machine.transition_backward();
		
		// pop 'q' after popping 'u'
		if (matrix[i][j] == 'q' && q_is_qu_flag)
			st_machine.transition_backward();
	}
	
	// Returns dictionary words found in the 2d array
	// 'q' is treated as 'qu' by default
	// call the overloaded method if you want 'q' to be treated as is
	public HashSet<String> findWords(char matrix[][]) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				check(matrix, i, j);
			}
		}
		return words_;
	}
	
	// First param: 2D array of characters
	// Second param: Boolean value. If true, 'q' is treated as 'qu' else 'q' is treated as is.
	// Returns dictionary words found in the 2d array
	public HashSet<String> findWords(char matrix[][], boolean qIsQu) {
		q_is_qu_flag = qIsQu;
		return findWords(matrix);
	}
}