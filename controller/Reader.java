package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import model.*;

/**
 * Reader 
 * 
 * @author Satpal Singh
 * @version 09 Dec 2014
 */

public class Reader {

	/**
	 * Creates a new book object
	 */
	Book book;
	/**
	 * Creates a new bible ArrayList of type 'Book'
	 */
	ArrayList<Book> bible;
	/**
	 * Creates a new search instance 
	 */
	private Search search;

	/**
	 * Readers constructor
	 */
	public Reader() {
		// Constructs a new bible ArrayList as a Book type 
		bible = new ArrayList<Book>();
		try {
			// Creates a File object for accessing the specified file folder.
			String bibleFolder = "src/bible_files";
			// Read each file within the specified folder 
			File dir = new File(bibleFolder);
			// Gets the current time
			long startTime = (new Date()).getTime();
			for (File bibleFile : dir.listFiles()) {
				// Get the name of the txt file to use it as title of book
				String title = bibleFile.getName().substring(0,
						bibleFile.getName().indexOf('.'));
				// Constructs a new String Builder 
				StringBuilder chapterAndVerse = new StringBuilder();
				// A Scanner object for handling file input.
				Scanner scan = new Scanner(bibleFile);
				// Instantiate book object
				book = new Book(); 
				// Read the the bible book file line by line.
				String line = scan.nextLine();
				book.setTitle(title + " - " + line);
				
				// While another line exists... 
				while (scan.hasNextLine()) {	
					 //Read a line from the bible text file then add it to string builder as a chapter
					line = scan.nextLine();
					// Check if the line is not empty
					if (!line.isEmpty()) {
						chapterAndVerse.append(line);
						chapterAndVerse.append("\n");
						// Do while loop scans the next lines and appends them onto the string builder 
						do {
							line = scan.nextLine();
							chapterAndVerse.append(line);
							chapterAndVerse.append("\n");
							// until there are no more lines in the text file 
						} while (!line.isEmpty() && scan.hasNext());
						// Adds on the chapter to the book 
						book.addChapter(chapterAndVerse.toString());
						chapterAndVerse = new StringBuilder();
					}
				}
				scan.close();
				bible.add(book);
			}
			// Gets the current time... and calculate the time taken
			long endTime = (new Date()).getTime();
			long elapsedTime = endTime - startTime;
			// printout the time taken
			System.out.println("The txt Files have been read in " + elapsedTime
					/ 1000.0 + " sec(s).");
		} catch (FileNotFoundException ioe) {
			System.out.println(ioe.toString());
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		search = new Search(bible);

	}

	/**
	 * Search method to search specific word Returns time taken for search, the
	 * title of the book, chapter and verse number where the word occurs as a
	 * string.
	 * 
	 * @return Returns time taken, title, chapter/verse number as a String array 
	 */
	public String[] searchWord(String word) {
		return search.searchWord(word);
	}

	/**
	 * Search verse method to search specific verse(s) Prints out the time taken
	 * for search Returns the verse(s) as a string.
	 * 
	 * @return Returns time taken, and the verse as a String array 
	 */
	public String searchVerse(String title, int chapter, int verse, int upto) {
		return search.searchVerse(title, chapter, verse, upto);
	}

}
