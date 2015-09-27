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
						if(line != "*"){
							dictionary.put(line.hashCode(), line);
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
		//--------Main Screen-------
		System.out.print("Welcome to WordLadders");
		while(flag){
		System.out.print("Command me");
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
				System.out.println(firstWord + secondWord);
				//put word ladder fxn here?
			}
			else{
				System.out.println("Give me two words buddy.");
			}
			
			
		}
	}
	}
}
