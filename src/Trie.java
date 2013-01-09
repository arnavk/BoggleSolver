import java.util.Stack;
public class Trie {
	
	private class State {
		boolean isWord;
		State alphabet[];
		
		private State() {
			isWord = false;
			alphabet = new State[26]; 
		}
	}
	
	private State start_;
	private Stack<State> state_stack;
	private Stack<Character> path_;
	
	public Trie() {
		start_ = new State();
		state_stack = new Stack<State>();
		state_stack.push(start_);
		path_ = new Stack<Character>();
	}
	
	private int charToInt(char c) {
		return (int)Character.toUpperCase(c) - 65;
	}
	
	public void insert(State state, String word, int str_index) {
		if (str_index == word.length()) {
			state.isWord = true;
			return;
		}
		int index = charToInt(word.charAt(str_index));
		if (index < 0 || index > 25)
			return;
		if (state.alphabet[index] == null)
			state.alphabet[index] = new State(); 
		insert(state.alphabet[index], word, str_index+1);
	}
	
	public void insert(String word) {
		word = word.toUpperCase();
		this.insert(start_, word, 0);
	}
	
	public boolean transition_forward(char c) {
		int transition_input = charToInt(c);
		State curr_state = state_stack.peek();
		if (curr_state.alphabet[transition_input] == null)
			return false;
		state_stack.push(curr_state.alphabet[transition_input]);
		path_.push(c);
		return true;
	}

	public boolean transition_backward() {
		if (state_stack.size() > 1) {
			state_stack.pop();
			path_.pop();
			return true;
		}
		return false;
	}
	
	public boolean isWord() {
		return state_stack.peek().isWord;
	}
	
	public String path() {
		char path[] = new char[path_.size()];
		for (int i=0; i < path_.size(); i++)
			path[i] = path_.get(i).charValue();
		return new String(path);
	}
}