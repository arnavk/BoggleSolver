N x M Boggle solver implemented in Java. 
Feel free to edit the code. I probably made a bunch of bad design decisions.
Would be good if you add why it was a bad design.


How to use:
 - look in Main.java for example
 - Call Solver.java's findWords function. It will return a HashSet<String> of words found

Algorithm goes like this:
 - Implemented a state machine using the concept of tries (wikipedia it!)
 - insert all dictionary words in a multi-way trie
 - checks all possible words in a matrix using recursion. it's basically
   a brute-force pathfinding algorithm
 - uses char in matrix[i][j] as a transition input to go from one state
   to another.
 - used state machines to allow O(1) check if something is a word in
   dictionary. O(1) not O(|word|).

Fully functional backend. Will implement UI into an Android app later on. 
Will refactor code and optimize later on and test some edge cases.
