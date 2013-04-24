package com.java.TotalBeginner;

public class Book {

	String title;
	String author;
	Person person; //Class(upper case), field (lower case)
	
	// "private" and "public" are known as Access Modifiers
	//"public" access from any class
	//"private" access only from this class
	// no modifier access only from this package

	public Book(String string) { //"String" is the Java class "String". "string" is the parameter
		// TODO Auto-generated constructor stub (this is a special comment that eclipse adds for a task (shown on task tab)
		this.title = string;
		this.author = "unknown author";
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setPerson(Person p2) {
		this.person = p2;
	}

	public Person getPerson() {
		return this.person; //getting the value of the person field with the getPerson()
	}
	
	public String toString() {
		String available;
		if (this.getPerson() == null) {
			available = "Available"; //local variable available = "Available"
		}
		else {
			available = "Checked out to " +
			this.getPerson().getName(); //available= "Checked out to Elvis"
		}
		return this.getTitle() + " by " + this.getAuthor() +
				"; " + available;
	}

}
