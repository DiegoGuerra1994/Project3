import java.util.*;

public class Ladders {
	 public boolean isValidInput(HashMap<Integer, String> dic, String firstWord, String secondWord){
		//Is the input the client gave us valid?
		if( dic.containsValue(firstWord) && dic.containsValue(secondWord)){
			return true;
		}
		return false;
	 }
	 public boolean inDictionary(HashMap<Integer, String> dic, String permutatedWord){
		 //does the word exist in the dictionary?
		 if( dic.containsValue(permutatedWord)){
				return true;
			}
		 return false;
	 }
	 public int ladderSize(){
		 //returns the size of the chosen word ladder
		 return 0;
	 }
	 public void wordLadders(){
		 //find wordladder
		 //send to the printing fxn
		 
	 }
	 public void printLadder(){
		 //print the wordLadder we found
	 }
}
