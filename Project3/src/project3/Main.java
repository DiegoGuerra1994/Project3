import java.util.*;
import java.io.*;

public class Main {
	
	
	private static class Node{
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
	
	private static String[] findWordLadder(String firstWord, String secondWord, HashMap<Integer,String> dic){
		Set<String> usedWords = new HashSet<String>();
		char[] alphabet = new char[26];
		for(int x = 0; x< 26;x++){
			alphabet[x] = (char)(97+x);
		}
		usedWords.add(firstWord);
		Node rootNode = new Node(null, null, firstWord);
		Node newNode = new Node();
		Node firstNode = new Node(rootNode, newNode, "");
		boolean firstNodeIsEmpty = true;
		boolean flag = true;
		
	while(flag){
		for(int y=0; y< 5; y++){
			for(int x=0; x< 26; x++){
				int charr = (rootNode.word.charAt(y)%96+x)%26;
				String newWord = (rootNode.word).substring(0,y) + alphabet[charr] + (rootNode.word).substring(y+1,5);
				if(dic.get(newWord.hashCode()) != null && !(usedWords.contains(newWord))){
					if(newWord.equals(secondWord)){
						newNode.word = newWord;
						newNode.parent = rootNode;
						flag = false;
						y=6;
						x=200;
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
		if(rootNode.next == null){ //end of layer
		rootNode = firstNode;
		}
		else{ //continue on layer
			rootNode = rootNode.next;
		}
		firstNode = newNode;
	}	
		
		
		
		
		
		ArrayList<String> stringLadder = new ArrayList<String>();
		while(newNode != null){
			stringLadder.add(0, newNode.word);
			newNode = newNode.parent;
		}
		
		for(String x: stringLadder){
			System.out.println(x);
		}
		return null;
		
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
		String input = scan.nextLine();
		
		/*First we check if it its a commnd*/
		
		
		
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
				//dic.put((Integer)firstWord.hashCode(), firstWord);
				if(dic.get(firstWord.hashCode()) == null || dic.get(secondWord.hashCode()) == null){
					System.out.println("Not a valid word");
				}
				else{
					
					String[] ladder = findWordLadder(firstWord,secondWord, dic);
				/*	for(String x: ladder){
						System.out.println(x);
					}*/
					
				}
			}
			else{
				System.out.println("Give me two words buddy.");
			}
			
			
		}
	}
	}
}
