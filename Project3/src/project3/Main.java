import java.util.*;
import java.io.*;

public class Main {
	
	
	private static class Node{ //We need a Node class because we need reference to the parent for BFF
		public Node parent;
		public String word;
		public Node next;
		
		
		
		Node(){
			parent = null;
			word = "";
			next = null;
		}
		Node(Node parent, Node next, String word){
			this.parent = parent;
			this.word = word;
			this.next = next;
		}
		
	}
	
	private static ArrayList<String> findWordLadder(String firstWord, String secondWord, HashMap<Integer,String> dic){
		Set<String> usedWords = new HashSet<String>(); //stores the words that we've already found 
		char[] alphabet = new char[26]; //making the alphabet
		for(int x = 0; x< 26;x++){
			alphabet[x] = (char)(97+x);
		}
		usedWords.add(firstWord);
		Node rootNode = new Node(null, null, firstWord); //parent, next, word
		Node newNode = new Node();
		Node firstNode = new Node(rootNode, newNode, "");
		boolean firstNodeIsEmpty = true;
	outerloop:
	while(true){ //BFF implemintation  
		for(int y=0; y< 5; y++){ //
			for(int x=0; x< 26; x++){
				if(rootNode.word.equals("")){ //rootNode will only be valid if it has a word in it. If it's not valid, that is because there are no more words availabe to link
					return null;
				}
				String newWord = (rootNode.word).substring(0,y) + alphabet[x] + (rootNode.word).substring(y+1,5); //iterates through the alphabet, changing one letter at a time
				if(dic.get(newWord.hashCode()) != null && !(usedWords.contains(newWord))){ //Word is in dictionary and has not been used before
					if(newWord.equals(secondWord)){
						newNode.word = newWord;
						newNode.parent = rootNode;
						
						break outerloop;
					}
					else{
						usedWords.add(newWord);
						newNode.word = newWord;
						newNode.parent = rootNode;
						newNode.next = new Node();
						if (firstNodeIsEmpty){
							firstNode = newNode;
							firstNodeIsEmpty = false;
						}
						newNode = newNode.next;
					}
				}
			}
		}
		if(rootNode.next == null){ //end of layer, go to next layer of BFF
		rootNode = firstNode;
		
		}
		else{ //continue on the same layer
			rootNode = rootNode.next;
		}
		
		firstNode = newNode; //update the first element of the row to be the latest node
	}	
		
		
	
		ArrayList<String> stringLadder = new ArrayList<String>();
		while(newNode != null){ //Since we are at the last element of the tree, we want it to be First in, Last out
			stringLadder.add(newNode.word);
			newNode = newNode.parent;
		}
	
		return stringLadder;
		
	}
	
	
	
	
	private static HashMap<Integer,String> makeDictionary() throws IOException{
		HashMap<Integer, String> dictionary = new HashMap<Integer, String>();
		/*Build the dictionary*/
		java.io.File data_file = new File("assn3words.dat");
					java.io.Reader file_reader = new java.io.FileReader(data_file);
					java.io.BufferedReader reader = new java.io.BufferedReader(file_reader);
					String line = reader.readLine();
					while (line != null) {
						if(line.charAt(0) != '*'){
							line = line.substring(0,5);
							Integer hash = (Integer) line.hashCode();
							dictionary.put(hash, line);
						}
						line = reader.readLine();
					}
					reader.close();
		return dictionary;
		
	}
	
	
	public static void main(String[] args) throws IOException{
		HashMap<Integer, String> dic = makeDictionary();
		
		Scanner scan = new Scanner(System.in);
		boolean flag = true;
		while(flag){
		System.out.print("Command me>>");
		String input = scan.nextLine();
		
		/*First we check if it is a command*/
		if(input.charAt(0) == '/'){
			String sub = (input.substring(1, input.length()));
			if( sub.matches("quit")){
				flag = false;
			}
			else{
				System.out.println("invalid command " + input);
			}
		}
		else{
			String[] words = input.split(" ");
			String firstWord;
			String secondWord;
			if(words.length == 2){
				firstWord = words[0] ;
				secondWord = words[1];
				if(dic.get(firstWord.hashCode()) == null || dic.get(secondWord.hashCode()) == null || firstWord.equals(secondWord)){ //Word doesn't exist
					System.out.println("No word ladder can be found between " + firstWord + " and " + secondWord);
				}
				else{
					
					ArrayList<String> ladder = findWordLadder(firstWord,secondWord, dic);
					if(ladder == null){ 
						System.out.println("No word ladder can be found between " + firstWord + " and " + secondWord);
					}
					else{
						System.out.println("a " + (ladder.size()-2) + "-run word ladder exists between " + firstWord + " and " + secondWord);
						for(int x=ladder.size()-1; x>= 0; x--){
							System.out.println("\t"+ladder.get(x));
						}
					}
					
				}
			}
			else{
				System.out.println("Give me two words buddy.");
			}
			
			
		}
	}
	}
}
