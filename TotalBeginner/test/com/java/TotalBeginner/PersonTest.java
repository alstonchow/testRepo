package com.java.TotalBeginner; //puts PersonTest class in same package as Person class

import junit.framework.TestCase; //imports JUnit package from external class library

public class PersonTest extends TestCase { //extends = "subclass of" PersonTest inherits all TestCase methodes


	//eclipse created 3 methods for us
	public void testPerson() {
		//create new Person object
		//Test that the fields are set correctly
		Person p1 = new Person();
		assertEquals("unknown name", p1.getName()); //this means we're trying to test if "unknown name" and p1.getName() are equal
		assertEquals(3, p1.getMaximumBooks()); //used to test the maximum books
		//"assertEquals" is a Static Method= Method that belongs to the class as a whole.
			//does not belong to an instance of the class
			//Syntax is <Class>.<Method>(e.g. PersonTest.assertEquals()); PersonTest.assertEquals() also works.
			//can omit <Class>. if in the same class
	}

	public void testSetName() {
		Person p2 = new Person();
		p2.setName("Fred");
		assertEquals("Fred", p2.getName());
	}

	public void testSetMaximumBooks() {
		Person p3 = new Person();
		p3.setMaximumBooks(10);
		assertEquals(10, p3.getMaximumBooks());
	}
	//TDD = created this method before writing the method in the Person class (Total Beginner lesson 6)
		//toString() method =
			//inherited from Object class
			//returns a String representation of the object
			//normally create for every class
		//TDD = 1. think about what the method should do. 2. write a test case that will test this method
			//3. write the new method. 4. test the new method
		//advantages of TDD = unit tests document the program without the need to maintain separate written
			// documentation that can easily become out of date
			// Also, forced to create detailed specifications before coding
				//Test method serves as specifications, documentation and as a unit test
				// when method passes the test, method is complete and correct.
	public void testToString() {
		Person p4 = new Person();
		p4.setName("Fred Flintstone"); //<-- our test method
		p4.setMaximumBooks(7);
		// our toString() method specification: Return the person's name and the number of books in parenthesis. For example, "Fred Flintstone (7 books)"
		String testString = "Fred Flintstone (7 books)";//<-- what our toString method needs to do
		assertEquals(testString, p4.toString());
	}

}
