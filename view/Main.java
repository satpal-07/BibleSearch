package view;
import java.util.Scanner;
import controller.Reader;

/**
 * The Main class contains the main method and shows
 * what is displayed on the Text User Interface,
 * and allows the user to interact with it 
 * 
 * @author Satpal Singh
 * @version 09 Dec 2014
 */

public class Main {

	/**
	 * Message displayed when the program is run, 
	 * displays information for the user so that they are able
	 * to naviagate around the system
	 */
	public static void welcomeMessage() {
		
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("To search for word type \"search word\". \nExample: search lord ");
		System.out.println("To look-up for verse, Please type \"show\" followed by provide book name, chapter number followed by verse number to search. \nExample: show Job 3 or Job 3:2 or show Job 3:2-5.");
		System.out.println("Type exit to exit the program");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");

	}
	
	/**
	 * Main Method of the program 
	 */
	public static void main(String[] args) {
		System.out.println("Reading the bible txt files...");
		// Constructs a new reader
		Reader reader = new Reader();
		// Constructs a new scanner
		Scanner in = new Scanner(System.in);

		while (true) {
			// Runs the welcome message method, displaying the above info
			Main.welcomeMessage();
			String input = in.next();

			// when the user inputs exit
			if (input.equalsIgnoreCase("exit")) {
				// exits and terminates the program 
				break;
			// when the user inputs search 	
			} else if (input.equalsIgnoreCase("search")) {
				// attains the search word, e.g. for search God,  it obtains God
				input = in.next();
				// Skips the rest to avoid exception 
				in.nextLine();
				// Reader searches for the word and stores in result array 
				String result[] = reader.searchWord(input);
				// Gives further options to the user, after searching for the word 
				if (!result[1].isEmpty()) {
					System.out.println("Press 1 for the verse, press 2 for location and press 3 for the frequency.");
					input = in.nextLine();

					// Catches the error if the user inputs non-numerical values
					try {
						int option = Integer.parseInt(input);
						if(option >= 0 && option < 4){
						switch (option){
						  case 1:  System.out.println(result[0]);
		                     break;
						  case 2:  System.out.println(result[1]);
		                     break;
						  case 3:  System.out.println(result[2]);
		                     break;
						}
						// Alerts the user if they input an invalid option number 
						}else{
							System.out
							.println("Invalid option entered!");
						}
					} catch (NumberFormatException e) {
						System.out
								.println("Please, provide numeric value as option!");
					}	
				// Alerts the user when the certain word cannot be found 	
				} else {
					System.out.println("Sorry no such word found!");
				}
			// Searches for the verses when user inputs 'show' followed by the book name and chapter
			} else if (input.equalsIgnoreCase("show")) {
				String title = in.next();
				String numbers[] = in.nextLine().trim().split("[^0-9]"); // Get all the digit values, and store this into an array 
				int chapter = 0;
				int verse = 0;
				int upto = 0;
				int i = 0;

				try {
					// if the numbers length is greater than 1 and contains chapter number...
					// then assign values to verse and upto so it will get all the verses in the chapter  
					if (i < numbers.length) {
						chapter = Integer.parseInt(numbers[i]); // converts the first number
						// Change the value to 1 to start from verse 1
						verse = 1;  
						// Change the value to 1000 to show all the verse
						upto = 1000; 	
						i++; // increment i by 1
					}
					// if the numbers length is greater than 2 and contains verse number...
					// then assign verse value to upto so it will only get one specific verse 
					if (i < numbers.length) {
						verse = Integer.parseInt(numbers[i]); // converts the second number
						// changes the upto value to the verse number, so it shows that specific verse
						upto = verse;
						i++;
					}
					// if the numbers length is greater than 3 and contains upto number...
					// then get the upto value to get the list asked by user
					if (i < numbers.length) {
						upto = Integer.parseInt(numbers[i]); // converts the third number
						i++;
					}
					// Show the verses
					System.out.println(reader.searchVerse(title, chapter,verse, upto)); 
					
				} catch (NumberFormatException e) {
					System.out.println("Please, provide numeric value as chapter and verse!");
				}
			} else {
				System.out.println("Please type in correct option!");
			}
		}
		// prints out program terminated, when the user exits
		System.out.println("Program Terminated!");
		// closes the scanner 
		in.close();
	}
}
