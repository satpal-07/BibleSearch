package model;
/**
 * A class to represent the Book, where chapters can be added and returned 
 * 
 * @author Satpal Singh
 * @version 09 Dec 2014
 */

public class Book {

	/**
	 * Maximum number of chapters a book can hold
	 */
	private static final int MAX_CHAPTER = 200;
	/**
	 * Title of the book
	 */
	private String title;
	/**
	 * Chapters of the book
	 */
	private Chapter[] chapter;
	/**A count of the number of chapters in the book
	 * 
	 */
	private int chapterCount;

	/**
	 * Constructs a Book with the following default values
	 * <li> title is set to null (doesn't currently refer to an object)
	 * <li> chapter uses the default constructor of Chapter, thus the actual value is defined by that class.
	 * <li> chapter count is 0
	 */
	public Book() {
		title = null;
		chapter = new Chapter[MAX_CHAPTER];
		chapterCount = 0;
	}

	/**
	 * Used to update the title of the book
	 * @param title
	 * 			The new value for title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method to add a chapter to the book
	 */
	public void addChapter(String chapter) {
		String[] lines = chapter.split("\n");
		// Check if the chapter is real chapter, more than one line

		if (lines.length > 1) {
			if(lines[0].startsWith("CHAPTER")||lines[0].startsWith("PSALM")){
				this.chapter[chapterCount] = new Chapter(lines[0]);
				// if the verse does not start right after the first line
				if (!lines[1].substring(0, 1).matches("\\d")) {
					// add the description to the chapter
					this.chapter[chapterCount].concatChapterName(lines[1]);
					// start adding the verse from the line after
					for (int i = 2; i < lines.length; i++) {
						this.chapter[chapterCount].addVerse(lines[i]);
					}
				} else {
					for (int i = 1; i < lines.length; i++) {
						this.chapter[chapterCount].addVerse(lines[i]);
					}
				}
				chapterCount++;
			}else{
				if (lines[1].substring(0, 1).matches("\\d")) {
					// start adding the verse from the line after
					for (int i = 1; i < lines.length; i++) {
						this.chapter[chapterCount].addVerse(lines[i]);
					}
				}
				if(lines[1].substring(0, 3).equals("169")){
					chapterCount++;
				}
			}
		}else if(lines[0].contains("PSALM 119")){
			this.chapter[chapterCount] = new Chapter(lines[0]);
		}
	}

	/**
	 * Used to query the title of the book
	 * @return Returns book title 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Used to obtain the chapter by the provided chapter number
	 * @return Specified chapter
	 */
	public Chapter getChapter(int number) {
		// Check the chapter number provided is less than total chapters
		// and greater than zero to avoid throwing exceptions
		if (number < chapter.length && number >=0) {
			// return the specified chapter number
			return chapter[number];
		}
		return null;
	}

	/**
	 * Used to query the number of chapters
	 * @return Returns the number of chapters the book holds.
	 */
	public int getChapterCount() {
		return chapterCount;
	}

	/**
	 * @return The contents of the book as a string.
	 */
	public String toString() {
		// Constructs a new string Builder
		StringBuilder sb = new StringBuilder();
		// Appends the title string to the character sequence
		sb.append(title);
		// Appends a new line to the character sequence
		sb.append("\n");

		// Increments through the chapter count to add all chapters of the book
		for (int i = 0; i < chapterCount; i++) {
			//Appends the chapter title to the character sequence
			sb.append(chapter[i].toString());
			// Appends a new line to the character sequence
			sb.append("\n");
			// Appends the verse contained in the chapter to the character sequence
			sb.append(chapter[i].verseToString());
			// Appends a new line to the character sequence
			sb.append("\n");
		}
		return sb.toString();
	}

}
