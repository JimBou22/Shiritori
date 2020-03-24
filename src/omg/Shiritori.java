package omg;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.*;

public class Shiritori {
	static boolean win = false;
	static File file = new File("src/lib/american-words.95");
	static File file2 = new File("src/lib/ReallyshortListofWords.txt");
	static List<String> list = new ArrayList<String>();
	static List<String> used = new ArrayList<String>();
	private static int difficulty;

	public static void main(String[] args) {
		Scanner scc = new Scanner(System.in);
		System.out.println("Welcome to the Word Chain Game!");
		System.out.println("You know the rules :)");
		System.out.println("Select Difficulty: 1 or 2");
		difficulty = Integer.parseInt(scc.nextLine());
		try {
			// import/build dictionary of words
			buildDictionary();
			playFirst();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			scc.close();
		}

	}

	private static void playFirst() {
		// get user input from console with menu face

		System.out.println("Please enter a word");

		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		// check if word
		// a. Exists as a word previously used
		// b. Has an answer retort in the dictionary provided.

		if (!used.contains(input)) {
			used.add(input);
			Collections.shuffle(list);

			String prev = "";
			for (int i = 0; i < list.size(); i++) {
				char last = input.charAt(input.length() - 1);
				if (list.get(i).charAt(0) == last) {
					System.out.println("My word is " + list.get(i));
					prev = list.get(i);
					list.remove(i);
					break;
				}
			}
			getListSize(list);
			checkWin(prev);

			// replay with having to start with last letter of previous word

			playAgain(prev);


		} else {
			sc.close();
			lose();
		}
	}



	private static void buildDictionary() throws FileNotFoundException, IOException {
		BufferedReader br;
		if(difficulty == 1) {
			br = new BufferedReader(new FileReader(file));
		} else {
			br = new BufferedReader(new FileReader(file2));
		}
		
		String s;
		while ((s = br.readLine()) != null) {
			list.add(s);
		}
		getListSize(list); // 2946 words in this bank
		br.close();
	}

	private static void playAgain(String prev) {
		System.out.println("-------------------------------------------------");
		System.out.println("Your previous word was " + prev);
		System.out.println("Provide a word that starts with " + lastChar(prev));
		System.out.println();
		play(prev);
	}

	private static void play(String prev) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter another word");
		String input = sc.nextLine();

		// check if word
		// a. Exists as a word previously used
		// b. Has an answer retort in the dictionary provided.
		if(input.charAt(0) != lastChar(prev)) {
			lose();
		}
		
		if(input.equalsIgnoreCase("win")) {
			Shiritori s = new Shiritori();
			s.winNow(null);
		}
		
		if (!used.contains(input)) {
			used.add(input);
			Collections.shuffle(list);

			String prev1 = "";
			for (int i = 0; i < list.size(); i++) {
				char last = input.charAt(input.length() - 1);
				if (list.get(i).charAt(0) == last) {
					System.out.println("My word is " + list.get(i));
					prev1 = list.get(i);
					list.remove(i);
					break;
				}
			}
			getListSize(list);
			// win case/lose case
			checkWin(prev1);
			playAgain(prev1);

			
		} else {
			sc.close();
			lose();
			
		}
	}

	private static void lose() {
		System.out.println("You lose");
		System.exit(0);
	}

	private static void getListSize(List<String> list) {
		System.out.println(list.size() + " words left in the bank");
	}
	
	private static char lastChar(String prev) {
		return prev.charAt(prev.length() - 1);
	}
	private static void checkWin(String prev) {
		if (list.size() < 1 || list == null || prev.equals("")) {
			win = true;
		}// win case/lose case
		if (win == true) {
			System.out.println("You cheated. No Winning Allowed!");
			System.exit(0);
		}
	}
	
	public void winNow(JFrame frame) {
		JOptionPane.showMessageDialog(frame, "You cheated");
		System.exit(0);
	}

}
