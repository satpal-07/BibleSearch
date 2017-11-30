package controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.*;
/**
 * Search 
 * 
 * @author Satpal Singh
 * @version 09 Dec 2014
 */

public class Search {

	/**
	 * Creates a new Book ArrayList
	 */
	private List<Book> books = new ArrayList<Book>();

	/**
	 * Constructs a Search with the following default values
	 * <li> books is the value from the parameter 
	 */
	public Search(ArrayList<Book> books) {
		this.books = books;
	}

	/**
	 * Search method to search specific word Returns time taken for search, the
	 * title of the book, chapter and verse number where the word occurs as a
	 * string.
	 * 
	 * @return
	 */
	public String[] searchWord(String word) {
		int wordCount = 0;
		String result[] = new String[3];
		long startTime = (new Date()).getTime();

		StringBuilder verseNumber = new StringBuilder();
		StringBuilder tempVerseNumber = new StringBuilder();
		StringBuilder locationVerse = new StringBuilder();
		for (int i = 0; i < books.size(); i++) {

			Book book = books.get(i);

			for (int j = 0; j < book.getChapterCount(); j++) {

				Chapter chapter = book.getChapter(j);
				boolean wordFound = false;

				for (int k = 0; k < chapter.getVerseCount(); k++) { 

					String words[] = chapter.getVerse(k).split("\\s|[^a-zA-Z'-]");
					int countCheck = wordCount; // store the current value of wordCount
					// in countCheck
					// Search through the array of words
					for (int l = 0; l < words.length; l++) {

						if (words[l].toLowerCase().equals(word.toLowerCase())) {
							wordCount++; // Increment the count if word found
							wordFound = true; //change the word found to true
						}
					}

					/* if the count check value is changed then add the verse number to
					 * sb do this outside verse for loop to avoid repetition of verse number
					 * if the work occurs more than once.
					 **/
					if (countCheck != wordCount) {
						locationVerse.append(chapter.getVerse(k)+"\n"); //add the verse where the word located
						tempVerseNumber.append((k + 1) + " ");//add the verse number where the word located
					}
				}

				if (wordFound) {
					verseNumber.append(books.get(i).getTitle() + " ");
					verseNumber.append(chapter + " Verse(s) ");
					verseNumber.append(tempVerseNumber); // Get the verse number
					verseNumber.append("\n");

					tempVerseNumber = new StringBuilder();//reset the verse number
				}
			}
		}


		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
		System.out.println("The search is conducted in " + elapsedTime / 1000.0
				+ " sec(s).");
		result[0] = locationVerse.toString();
		result[1] = verseNumber.toString();
		result[2] = "The word " + word + " occured " + String.valueOf(wordCount) + " times." ;

		return result;
	}


	/**
	 * Search verse method to search specific verse(s)
	 * Prints out the time taken for search
	 * Returns the verse(s) as a string. 
	 * @return
	 */
	public String searchVerse(String title, int chapter, int verse, int upto) {
		//TODO
		if(title.equalsIgnoreCase("psalms") && chapter >119){
			chapter +=21;
		}

		boolean bookFound = false;
		boolean chapterFound = false;
		boolean verseFound = false;
		long startTime = (new Date()).getTime();
		StringBuilder bookNames = new StringBuilder();//used to display book names
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < books.size(); i++) {

			String bookTitle[] = books.get(i).getTitle().split("\\s");
			bookNames.append(bookTitle[0] + "\n");

			if (bookTitle[0].equalsIgnoreCase(title)) {
				bookFound = true;
				do {
					// Subtract 1 to get the right number of chapter from arraylist
					Chapter chapterTemp = books.get(i).getChapter(chapter-1);
					if (chapterTemp != null) {
						chapterFound = true;
						// Subtract 1 to get the right number of verse from arraylist
						String verseTemp = chapterTemp.getVerse(verse-1);
						if (verseTemp != null) {
							verseFound = true;
							sb.append(verseTemp);
							sb.append("\n");
							// If upto is 100 get it to verse size limit within a chapter
							if(upto==100){
								upto = chapterTemp.getVerseCount();
							}

						}
					}
					verse++;
				} while (verse <= upto);
				// Once the verse is found stop the search
				break;
			}

		}
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
		//if book is not found add valid message
		if (!bookFound) {
			sb.append("Please provide valid book name!!!\n");
			sb.append(bookNames.toString());

		} 
		//if chapter is not found add valid message
		else if (!chapterFound) {
			sb.append("Please provide valid chapter Number!!!\n");

		} 
		//if verse is not found add valid message
		else if (!verseFound) {
			sb.append("Please provide valid verse Number!!!\n");
		}

		if (bookFound && chapterFound && verseFound) {

			sb.append("The verse(s) returned in " + elapsedTime / 1000.0
					+ " sec(s).");
		} else {

			sb.append("No such Book, Chapter or Verse found!");
		}
		return sb.toString();
	}

}
