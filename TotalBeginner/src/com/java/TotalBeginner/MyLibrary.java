package com.java.TotalBeginner;

import java.util.ArrayList;

public class MyLibrary {

	//remove the access modifiers so classes inside the package can access
	String name;
	ArrayList<Book> books;
	ArrayList<Person> people;

	public MyLibrary(String name) {
		//this.name = "the name field of the current object". 
		//"name" is the String parameter of constructor
		this.name = name;
		
		//putting the () at the end of the ArrayList constructor because we need to include 
		//these when we call methods or constructors that have no parameters
		//make sure to only include a variable once
		books = new ArrayList<Book>(); 									   
		people = new ArrayList<Person>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	//these next 2 methods are simple because they use 
	//built in methods that are used from the ArrayList class
	public void addBook(Book b1) {
		this.books.add(b1); //add(object) method from ArrayList
		
	}

	public void removeBook(Book b1) {
		this.books.remove(b1); //remove(object)
		
	}
	
	public void addPerson(Person p1){
		this.people.add(p1);
	}
	
	public void removePerson(Person p1) {
		this.people.remove(p1);

	}

	//if this evaluates to boolean true, meaning if this person has 0 books set to him
	//how to read this whole statement: if there's nothing in the person field,
	//then set the person of b1 to p1 and return true, otherwise return false
	public boolean checkOut(Book b1, Person p1) {
		
		//below code, calculating how many books this person already has out
		int booksOut = this.getBooksForPerson(p1).size(); //this sets the local variable booksOut, to the number of books p1 has out. so, size() is the number of books in the list
		
		//below code, only does checkout if person is under maximum book limit
		if ((b1.getPerson() == null) && //null means nothing is in the field or variable, means "no object"
				(booksOut < p1.getMaximumBooks())){ 
			b1.setPerson(p1);         
			return true;
		}
		else {
			return false;
		}
	}

	//how to read this whole statement: if the person in b1 is not null (meaning there's a person object in there)
	//then set it to null and return true
	//otherwise this book not already checked out, so we can't check it in, so we return false
	public boolean checkIn(Book b1) {
		if (b1.getPerson() != null) {
			b1.setPerson(null); //removes object reference from person field
			return true;
		}
		else {
			return false;
		}
	}

	//below code= list of books checked out by p1
	public ArrayList<Book> getBooksForPerson(Person p1) {
		//this is just going to hold the result of this method
		ArrayList<Book> result = new ArrayList<Book>();
		
		//the way to read this: "for each Book aBook in the list this.getBooks()..."
		//what this does is, for book abook(abook is the local variable)
		//in the list, return the if (book abook : this.getBooks())
//             "for each loop" --use to process each item on a list
//          1. Put first Book in this.getBooks() in aBook.
//			2. Do the block of code
//			3. Put next book in aBook.
//			4. Do the block of Code.
//			5. repeat to the end of the list
		//you need the aBook.getPerson() != null part because
			//if book not checked out, getPerson() returns null.
			// Trying to execute getName() method on null object --->NullPointerException
		for (Book aBook : this.getBooks()) { 
			if ((aBook.getPerson() != null) && // condition 1=  there must be a person of the person field of the book
					(aBook.getPerson().getName() // condition 2= the name of the person = the name of the person p1
							.equals(p1.getName()))) //in Java, a String is an object. Use ".equals" method to check equality of OBJECTS including strings.
													 //Use "==" only for primitive types (e.g. int, boolean, char, double, etc.).
			{
				
				result.add(aBook);
			}
		
		}
		return result; //make sure the "return" statement is not in the "for loop" block
	}

	//this is similar to the getBooksForPerson
	// but there is only a single check.
	public ArrayList<Book> getAvailableBooks() {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book aBook : this.getBooks()) {
			if (aBook.getPerson() == null){
				result.add(aBook);
			}
		}
		return result;
	}

	public ArrayList<Book> GetUnavailableBooks(Person p1) {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book aBook : this.getBooks()) {
			if (aBook.getPerson() != null){
				result.add(aBook);
			}
		}
		return result;
	}
	
	public String toString() {
		return this.getName() + ": " +
		this.getBooks().size() + " books; " +
		this.getPeople().size() + " people.";
	}
	
	//takes string array called args, this allows java program to read parameters from cmd line
	public static void main(String[] args) {
		// create a new MyLibrary
		MyLibrary testLibrary = new MyLibrary("Test Drive Library");
		Book b1 = new Book("War and Peace");
		Book b2 = new Book("Great Expectation");
		b1.setAuthor("Tolstoy");
		b2.setAuthor("Dickens");
		Person jim = new Person();
		Person sue = new Person();
		jim.setName("Jim");
		sue.setName("Sue");
		
//	***	the below set of code is used to add the objects to the MyLibrary books and people fields
		testLibrary.addBook(b1); 
		testLibrary.addBook(b2);
		testLibrary.addPerson(jim);
		testLibrary.addPerson(sue);
		
		//    Shortcut to print console:
//		1. enter "your stuff" to be printed
//		2. Highlight with mouse
//		3. CTRL-Space, up-arrow, Enter.
//		4. Eclipse surrounds with System.outprintln.
		System.out.println("Just created new library");
		testLibrary.printStatus(); //just going to print out the current status of the library
		
		System.out.println("Check out War and Peace to Sue");
		testLibrary.checkOut(b1, sue);
		testLibrary.printStatus();
		
		System.out.println("Do some more stuff");
		testLibrary.checkIn(b1);
		testLibrary.checkOut(b2, jim);
		testLibrary.printStatus();
		
	}

	private void printStatus() {
		System.out
				.println("Status Report of MyLibrary \n" + this.toString());
		for (Book thisBook : this.getBooks()) {
			//when we put an object into the argument area of the System.out.println command
			//that command invoked the toString method on the object.
			// its like: this book toString
			//or System.out.println(thisBooke); is the same as System.out.println(thisBook.toString());
			System.out.println(thisBook); 
		}
		
		//we're gonna loop through all the person objects
		//for each one, we're gonna see how many books each person has checked out
		for (Person p : this.getPeople()) {
			int count = this.getBooksForPerson(p).size();
			System.out.println(p + " (has " + count + " of my books)"); 
		}
		
		System.out.println("Books Available: "
				+ this.getAvailableBooks().size());
		System.out.println("---End of Status Report---");
	}
}