package omg;

import java.util.*;
import java.io.*;

public class Shiritori {
	static boolean win = false;
	static boolean lose = false;
	static File file = new File("src/lib/american-words.95");
	static List<String> list = new ArrayList<String>();
	static List<String> used = new ArrayList<String>();

	public static void main(String[] args) {
		// import/build dictionary of words

		Scanner sc = new Scanner(System.in);
		try {
			buildDictionary();

			// get user input from console with menu face
			System.out.println("Welcome to the Word Chain Game!");
			System.out.println("You know the rules :)");
			System.out.println("Please enter a word");

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
						System.out.println(list.get(i));
						prev = list.get(i);
						list.remove(i);
						break;
					}
				}
				System.out.println(list.size());
				if (list.size() < 1 || list == null) {
					win = true;
				}

				// replay with having to start with last letter of previous word

				playAgain(prev);

				// win case/lose case
				if (win == true) {
					System.out.println("You cheated");
					sc.close();
					System.exit(0);
				}
			} else {
				lose = true;
				System.out.println("You suck");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

	}

	private static void buildDictionary() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String s;
		while ((s = br.readLine()) != null) {
			list.add(s);
		}
		getListSize(list); // 2946 words in this bank
		br.close();
	}

	private static void playAgain(String prev) {
		System.out.println("Youre previous word was " + prev);
		System.out.println("Provide a word that starts with " + prev.charAt(prev.length() - 1));
		play(prev);
	}

	private static void play(String prev) {
		// TODO Auto-generated method stub

	}

	private static void getListSize(List<String> list) {
		System.out.println(list.size());
	}

}
