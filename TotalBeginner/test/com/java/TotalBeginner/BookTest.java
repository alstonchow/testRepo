//this Book class will have
	//title (e.g. "Great Expectations")
	//author (e.g. "Charles Dickens")
	//Person (an object of type Person) who has the book

package com.java.TotalBeginner;

import junit.framework.TestCase;

public class BookTest extends TestCase {

	//this test will test the constructor
	public void testBook() {
		Book b1 = new Book("Great Expectations"); //Constructor has a String parameter used to set the title field
		assertEquals("Great Expectations", b1.title);
		assertEquals("unknown author", b1.author);
	}
	
	//Below, we've created a relationship between the Book class and Person class (lesson 8)
	// Book class depends on Person class
	//one-to-one relationship (1 book to 1 person)
	
	public void testGetPerson(){
		Book b2 = new Book("War and Peace");
		Person p2 = new Person();
		p2.setName("Elvis");
		
		// method to say book is loaned to this person
		b2.setPerson(p2);
		
		// get the name of the person who has the book
//		Person testPerson = b2.getPerson();  <-- don't really need testPerson()
//		String testName = testPerson.getName();<--we just used it to invoke getName()
		//below we will write these 2 lines in one sentence.
		
		String testName = b2.getPerson().getName(); //this line is saying "Use the b2.getPerson() method to get a Person object..."(b2.getPerson()).
												   //"...then run the getName() method using the Person object" (getName();).
		
		assertEquals("Elvis", testName);
	}
	
	public void testToString() {
		Book b2 = new Book("War and Peace");
		Person p2 = new Person();
		p2.setName("Elvis");
		
		assertEquals("War and Peace by unknown author; Available", b2.toString());
		
		b2.setPerson(p2);
		assertEquals("War and Peace by unknown author; Checked out to Elvis", b2.toString());
		
	}

}
