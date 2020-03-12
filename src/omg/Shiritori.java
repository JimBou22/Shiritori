package omg;

import java.util.*;
import java.io.*;

public class Shiritori{
	
	
	public static void main(String[] args) {
		//import/build dictionary of words
		File file = new File("src/lib/american-words.95");
		List<String> list = new ArrayList<String>();
		List<String> used = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			
			while ((s = br.readLine()) != null) {
				list.add(s);
			}
			
			System.out.println(list.get(0));
			System.out.println(list.size()); //2946 words in this bank
		//get user input from console with menu face
			System.out.println("Welcome to the Word Chain Game!");
			System.out.println("You know the rules :)");
			System.out.println("Please enter a word");
			
			String input = sc.nextLine();
			
		// check if word
		// a. Exists as a word previously used
		// b. Has an answer retort in the dictionary provided.	
			
			
			if(!used.contains(input)) {
				used.add(input);
			}
			
			Collections.shuffle(list);
			
			for(int i = 0; i < list.size(); i++) {
				char last = input.charAt(input.length() - 1);
				if(list.get(i).charAt(0) == last) {
					System.out.println(list.get(i));
					break;
				}
			}
			
		//replay with having to start with last letter of previous word
			
			
			
		
		
		
		
		
		
		//win case/lose case
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
}
