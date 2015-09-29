import java.util.*;
import java.io.*;

public class Main {
	
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
				System.out.println(firstWord + secondWord);
				System.out.println(dic.get((Integer)firstWord.hashCode()));
				//put word ladder fxn here?
			}
			else{
				System.out.println("Give me two words buddy.");
			}
			
			
		}
	}
	}
}
