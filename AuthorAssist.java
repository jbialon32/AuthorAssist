import java.util.Scanner;

/**
 ******************************************************
 * 
 *	Tools made to help authors easily edit their text
 *	
 * @author James
 * @version 1.0
 * Date: 2/15/2018
 * 
 ******************************************************
 */

public class AuthorAssist {

	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		
		char userChoice;
		int numHolder;
		
		String userText = getUserText(scnr);
		
		do {
			
			userChoice = printMenu(scnr);
			
			switch (userChoice) {
			
			case 'c':
				numHolder = numNonWhitespace(userText);
				System.out.println();
				System.out.println("There are " + numHolder + " characters in your text.");
				continue;
				
			case 'w':
				numHolder = numWords(userText);
				System.out.println();
				System.out.println("There are " + numHolder + " words in your text.");
				continue;
				
			case 'f':
				wordFinder(userText, scnr);
				continue;
				
			case 'r':
				userText = replaceExclamation(userText);
				System.out.println();
				System.out.println("New text: ");
				System.out.println(userText);
				continue;
				
			case 's':
				userText = shortenSpaces(userText);
				System.out.println();
				System.out.println("New text: ");
				System.out.println(userText);
				continue;
				
			}
			
		} while (userChoice != 'q');
		
		scnr.close();
		
		System.out.println();
		System.out.println("Goodbye.");
		
		
	}
	
	/**
	 ****************************************************** 
	 * 
	 * 	Outputs a menu of valid options and takes choice
	 * 	from user until valid option is selected.
	 * 
	 * @param Scanner
	 * @return Users choice
	 * 
	 ****************************************************** 
	 */
	
	public static char printMenu(Scanner scnr) {
		
		char[] validList = {'c', 'w', 'f', 'r', 's', 'q'};
		char usrChoice;
		String usrChoiceStr;
		char goodChoice = '!';
		
		boolean valid = false;
		
		int i;
		
		// Menu information
		System.out.println();
		System.out.println("MENU");
		System.out.println("---------------------------------------------");
		System.out.println("c - Number of non-whitespace characters");
		System.out.println("w - Number of words");
		System.out.println("f - Find text");
		System.out.println("r - Replace all !'s");
		System.out.println("s - Shorten spaces");
		System.out.println("q - Quit");
		System.out.println("---------------------------------------------");
		System.out.println();
		System.out.println("Choose an option: ");
		
		// Run loop until valid choice is made
		while (valid == false) {
			
			// Get users choice
			usrChoiceStr = scnr.nextLine();
			usrChoice = usrChoiceStr.toLowerCase().charAt(0);
			
			// Check against array of valid choices
			for (i=0; i < validList.length; ++i) {
				
				// If choice is found assign it to the return value and end loop
				if (usrChoice == validList[i]) {
					
					goodChoice = usrChoice;
					
					valid = true;
					continue;
				}
				
			}
		
		}
		
		return goodChoice;
		
	}
	
	/** 
	 ******************************************************
	 *
	 *	Replaces '!' with '.' in users input
	 *
	 * @param usrStr
	 * @return string with '.' instead of '!'
	 * 
	 ****************************************************** 
	 */
	
	public static String replaceExclamation(String usrStr) {
		
		String usrStrCopy = "";
		int i;
		
		// Iterates through users string
		for (i=0; i < usrStr.length(); ++i) {
			
			// Checks if its an '!'
			if (usrStr.charAt(i) == '!') {
				
				// If so replace with '.' in copy
				usrStrCopy = usrStrCopy + '.';
				
			} else {
				
				// Otherwise copy it with no change
				usrStrCopy = usrStrCopy + usrStr.charAt(i);
				
			}
			
		}
		
		return usrStrCopy;
		
	}
	
	/**
	 ****************************************************** 
	 * 
	 * 	Gets input to search for a certain word in users
	 * 	phrase and returns total count
	 * 
	 * @param usrStr, Scanner
	 * @return
	 * 
	 ****************************************************** 
	 */
	
	public static void wordFinder(String usrStr, Scanner scnr) {
		
		String userEntry;
		String[] usrCopy;
		int numCount = 0;
		
		int i;
		
		// Copies users string and removes extra whitespace while splitting into an array
		usrCopy = usrStr.split("\\s+");
		
		// Remove pesky punctuation in array of words
		
		for (i=0; i < usrCopy.length; ++i) {
			
			usrCopy[i] = removePunctuation(usrCopy[i]);
			
		}
		
		// Gets word to search for from user
		
		System.out.println();
		System.out.println("Enter a word to search for: ");
		userEntry = scnr.nextLine();
		
		System.out.println();
		System.out.println("Searching for " + userEntry);
		
		// Iterates through array of words
		for (i=0; i < usrCopy.length; ++i) {
			
			// Matches current element with users entry
			if (userEntry.equals(usrCopy[i])) {
				
				// Adds to the match counter
				++numCount;
				
			}
			
		}
		
		// Communicates results
		if (numCount == 0) {
			
			System.out.println();
			System.out.println("No matches for " + userEntry + " found.");
			
		} else {
			
			System.out.println();
			System.out.println(userEntry + " appears " + numCount + " times.");
			
		}
		
		return;
		
	}

	/**
	 ****************************************************** 
	 *
	 *	Removes all whitespace from users string
	 *	and returns length without spaces
	 * 
	 * @param usrStr
	 * @return Users string length without spaces
	 * 
	 ****************************************************** 
	 */

	public static int numNonWhitespace(String usrStr) {
		
		String stringNoSpace = "";
		int i;
		
		// Iterates through the users string
		for (i=0; i < usrStr.length(); ++i) {
			
			// Checks if character in string is a space
			if (Character.isWhitespace(usrStr.charAt(i))) {
				continue;
			
			// If not a space adds its to a new string
			} else {
				
				stringNoSpace = stringNoSpace + usrStr.charAt(i);
				
			}
			
		}
		
		return stringNoSpace.length();
		
		}
	
	/**
	 ****************************************************** 
	 * 
	 * 	Removes any extra spacing in users entered text
	 * 
	 * @param usrStr
	 * @return users string without extra space
	 * 
	 ******************************************************  
	 */
	
	public static String shortenSpaces(String usrStr) {
		
		String[] usrCopyArray = usrStr.split("\\s+");
		String usrCopy = "";
		int i;
		
		for (i=0; i < usrCopyArray.length; ++i) {
			
			usrCopy = usrCopy + usrCopyArray[i] + ' ';
			
		}
		
		return usrCopy;
		
	}
	
	/**
	 ****************************************************** 
	 *
	 *	Returns word count of users input without extra
	 *	spaces
	 * 
	 * @param usrStr
	 * @return Word count
	 * 
	 ****************************************************** 
	 */
	
	public static int numWords(String usrStr) {
		
		return usrStr.split("\\s+").length;
		
	}

	/**
	 ******************************************************
	 * 
	 * 	Gets users input for text to edit and confirms
	 * 	with user that input is correct before returning
	 * 	said input.
	 * 
	 * @param Scanner
	 * @return userString (users input)
	 *
	 ******************************************************
	 */
	
	public static String getUserText(Scanner scnr) {
		String userString;
		char yesNo;

		// Ask for users input
		do {
			System.out.println("Enter your text here: ");
			userString = scnr.nextLine();
			
			// Confirms users input is correct
			System.out.println();
			System.out.println("You entered: " + userString);
			System.out.println("Is this correct(Y/N):");
			
			do {
				yesNo = scnr.nextLine().toLowerCase().charAt(0);				
			// If confirmation entry not valid ask yes or no again
			} while (yesNo != 'y' || yesNo == 'n');
		
		// If user answer no asks for input again
		} while (yesNo != 'y');
		
		return userString;
		
	}
	
	/**
	 ****************************************************** 
	 * 
	 * 	Searches string for non alphabet characters at
	 * 	beginning and end then removes them for easier
	 * 	precise word matching.
	 * 
	 * @param phrase
	 * @return word without punctuation at start or end
	 * 
	 ****************************************************** 
	 */
	
	public static String removePunctuation(String phrase) {
		
		String goodString;
		
		int i;

		// Checks for non-alphabetical characters at start of text
		if (Character.isAlphabetic(phrase.charAt(0)) == false) {
				
			goodString = "";
				
			// Makes a copy without beginning punctuation
			for (i=1; i < phrase.length(); ++i) {
					
				goodString = goodString + phrase.charAt(i);
					
			}
			
			phrase = goodString;
								
		}
			
		// Checks for non-alphabetical characters at end of text
		if (Character.isAlphabetic(phrase.charAt((phrase.length() - 1))) == false) {
				
			goodString = "";
				
			// Makes a copy without end punctuation
			for (i=0; i < (phrase.length() - 1); ++i) {
					
				goodString = goodString + phrase.charAt(i);
					
			}
				
			// Assigns it too the list position
			phrase = goodString;
				
		}
		
		return phrase;
		
	}
		
		
	}

