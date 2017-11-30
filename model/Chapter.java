package model;
import java.util.ArrayList;

/**
 * A Chapter 
 * 
 * @author Satpal Singh
 * @version 09 Dec 2014
 */

public class Chapter {

	/**
	 *  The chapter name as a string variable
	 */
	private String chapterName;
	/**
	 * Verses stored in an ArrayList
	 */
	private ArrayList<String> verse;

	/**
	 * Constructs a Chapter with the following default values
	 * <li> chapter name is the value from the parameter 
	 * <li> verse constructs a new String ArrayList
	 */
	public Chapter(String chapter) {
		this.chapterName = chapter;
		verse = new ArrayList<String>();
	}

	/**
	 * Method to add a verse to the ArrayList
	 */
	public void addVerse(String value) {
		verse.add(value);
	}
	
	/**
	 * Method to obtain the verse count 
	 * @return Returns the number of verses
	 */
	public int getVerseCount(){
		return verse.size();
	}

	/**
	 * Used to obtain the verse by the provided verse number
	 * @return Specified verse
	 */
	public String getVerse(int number) {
		// Check the verse number provided is less than total verses
		// and greater than zero to avoid throwing exceptions
		if (number < verse.size() && number >=0 ) {
			// return the specified verse number
			return verse.get(number);
		}
		return null;
	}
	
	/**
	 * Method to obtain the chapter name 
	 * @return Returns the chapter name as a string.
	 */
	public String toString() {
		return chapterName;
	}

	/**
	 * Method to concatenate the chapter description to the chapter name
	 */
	public void concatChapterName(String add) {
		chapterName += " - " + add;
	}

	/**
	 * Method to obtain the chapters verses
	 * @return Returns the verses contained in this chapter as a string.
	 */
	public String verseToString() {
		// Constructs a new String builder 
		StringBuilder sb = new StringBuilder();
		// Increments through the verse size to add all verses
		for (int i = 0; i < verse.size(); i++) {
			sb.append(verse.get(i));
			sb.append("\n");
		}
		return sb.toString();
	}

}
