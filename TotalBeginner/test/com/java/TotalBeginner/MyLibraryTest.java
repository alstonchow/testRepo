package com.java.TotalBeginner;

import java.util.ArrayList;

import junit.framework.TestCase;

public class MyLibraryTest extends TestCase {

	private Book b1;
	private Book b2;
	private Person p1;
	private Person p2;
	private MyLibrary ml;

	//test constructor
	//all test methods must start with the word "test"
	public void testMyLibrary(){
		MyLibrary ml = new MyLibrary("Test");
		
		assertEquals("Test", ml.name);
		
		//boolean= evaluates to either true or false
		//instanceof = (A instanceof B) returns true if a is of type b
		assertTrue(ml.books instanceof ArrayList);
		assertTrue(ml.people instanceof ArrayList);
	
	}
	
	public void setup(){
		//'b1 = new Book("Book 1");' was formally 'Book b1 = new Book("Book 1");'
		//but since it was a local variable and not a field that can be used by the other test methods
		//we need to change it to a field so other test methods can use it.
		//highlight the area and go to Refactor> Convert local variable to field...
		
		b1 = new Book("Book 1"); 
		b2 = new Book("Book 1");
		
		p1 = new Person(); 
		p2 = new Person();
		p1.setName("Fred");
		p2.setName("Sue");
		
		ml = new MyLibrary("Test");
	}
	
	public void testAddBook(){
		//create test objects by running method setup()
		setup();
		
		//test initial size is 0
		//size() method returns the total number of elements in the list
		assertEquals(0, ml.getBooks().size());
		
		ml.addBook(b1); //used quick fix to add these to MyLibrary.java
		ml.addBook(b2);
		
		assertEquals(2, ml.getBooks().size());
		assertEquals(0, ml.getBooks().indexOf(b1));
		assertEquals(1, ml.getBooks().indexOf(b2));		
		
		//ml.removeBooks() will change the indexOf number of the books
		ml.removeBook(b1);
		assertEquals(1, ml.getBooks().size());
		assertEquals(0, ml.getBooks().indexOf(b2));

		ml.removeBook(b2);
		assertEquals(0, ml.getBooks().size());
		
	}
	
	//this will check out a book to the person only if
	//it isn't checked out to someone else
	public void testCheckOut() {
		//set up objects
		setup();
		
		addItems();
		
		//Will display String message if unit test fails.  Makes it clear what failed
		//the below code means we are checking out book b1 to person p1
		 //checkOut method returns boolean, so we can use checkOut
		//anywhere we need a boolean value.
		//Also, method is executed.
		assertTrue("Book did not check out correctly", ml.checkOut(b1,p1));
			
		//we are using name to compare Person objects
		//instead of just comparing the objects (which can be tricky in Java).
		//we are assuming that everyone on our list will have a unique name
		assertEquals("Fred", b1.getPerson().getName()); //we need to use b1.setPerson(p1), but ONLY if the person field is null(empty)
		
		//assertFalse succeeds when boolean value returns false
		assertFalse("Book was already checked out", ml.checkOut(b1,p2));
		
		//the below method tries checking in a book
		//the reason we don't need a person as a parameter. 
			//when we check in a book, we don't need to know who it was checked out to.
			//the checkedIn method only needs to know the book.
		assertTrue("Book check in failed", ml.checkIn(b1));
		
		//now we'll try to check in a book that wasn't checked out and make sure it fails
		assertFalse("Book was already checked in", ml.checkIn(b1)); //<-- should return false
		
		//the below code should succeed (because checkIn returns false)
		assertFalse("Book was never checked out", ml.checkIn(b2));
		
		//additional test for maximum books
		setup(); 
		//to be safe we need to add setup() 
		//again so that nothing changed from what happened above.
		
		p1.setMaximumBooks(1); //just used 1 so its easy to check checking out 2 books
		
		// we need these to redo our lists
		addItems();
		
		
		//Reminder: checkOut method returns true if successful, false if not.
		//so we can use assertTrue, assertFalse to test success of checkOut.
		//the methods below are: making sure checking out a book works, 
		//checking out a 2nd book fails
		assertTrue("First book did not check out", ml.checkOut(b2, p1));
		assertFalse("Second book should not have checked out", ml.checkOut(b1, p1));
		
		}

	//created this so that way we don't have to keep retyping these. 
	//select all> Refactor> Extract Method...
	private void addItems() {
		ml.addBook(b1);
		ml.addBook(b2);
		ml.addPerson(p1);
		ml.addPerson(p2);
	}
	
	public void testGetBooksForPerson() {
		setup();
		addItems();
		
		//basically just saying that size should be 0 so 
		//there shouldn't be anything in the getBooksforPerson(p1)
		assertEquals(0, ml.getBooksForPerson(p1).size()); 		//<--1. start with 0 books
		
		//now we're going to check out book b1 to person p1
		ml.checkOut(b1, p1); 									//<--2. check out first book
																		
		ArrayList<Book> testBooks = ml.getBooksForPerson(p1);
		assertEquals(1, testBooks.size()); 						//<--3. 1 book in list, b1 at index 0
		assertEquals(0, testBooks.indexOf(b1));
		
		ml.checkOut(b2, p1);
		testBooks = ml.getBooksForPerson(p1);
		assertEquals(2, testBooks.size()); 						//<--4. 2 books in list, b2 at index 1
		assertEquals(1, testBooks.indexOf(b2));
	}
	
	public void testGetAvailableBooks() {
		setup();
		addItems();
		ArrayList<Book> testBooks = ml.getAvailableBooks();
		assertEquals(2, testBooks.size()); //2 available books to start
		assertEquals(1, testBooks.indexOf(b2)); //book b2 in second position (index = 1)
		
		ml.checkOut(b1, p1); 
		testBooks = ml.getAvailableBooks();
		assertEquals(1, testBooks.size()); //1 book available
		assertEquals(0, testBooks.indexOf(b2));
		
		ml.checkOut(b2, p1);
		testBooks = ml.getAvailableBooks(); //0 books available
		assertEquals(0, testBooks.size());
	}
	
	public void testGetUnavailableBooks() {
		setup(); //setup test objects
		addItems();
		assertEquals(0, ml.GetUnavailableBooks(p1).size()); //0 books unavailable 		
		
		
		ml.checkOut(b1, p1); //then check out 1 book									
												
		//below code means 1 book is unavailable
		ArrayList<Book> testBooks = ml.GetUnavailableBooks(p1); 
		assertEquals(1, testBooks.size()); 						
		assertEquals(0, testBooks.indexOf(b1));
		
		//below code means 2 books are unavailable
		ml.checkOut(b2, p1);
		testBooks = ml.GetUnavailableBooks(p1);
		assertEquals(2, testBooks.size()); 						
		assertEquals(1, testBooks.indexOf(b2));
	}
	
	public void testToString() {
		setup();
		addItems();
		assertEquals("Test: 2 books; 2 people.", ml.toString());
	}

}
