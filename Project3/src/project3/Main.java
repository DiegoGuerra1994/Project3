import java.util.*;
import java.io.*;

public class Main {
	
	
	private class Node{
		private Node parent;
		private String word;
		
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
		Set<String> usedWords = new HashSet<String>();
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
					usedWords.add(firstWord);
					usedWords.add(secondWord);
					String newWord = firstWord;
					
					for(int letterPos = 0; letterPos < 5; letterPos++){
						for(int character = 0; character < 26; character++){
							char a = newWord.charAt(letterPos);
							newWord.replace(a, 'q');
							if(dic.get(newWord.hashCode()) != null){
								System.out.println(newWord);
							}
							
						}
					}
					
					
					System.out.println(firstWord + secondWord);
				}
				
				
				//put word ladder fxn here?
			}
			else{
				System.out.println("Give me two words buddy.");
			}
			
			
		}
	}
	}
}
