package com.java.TotalBeginner;

//Class contains
//	1. Fields for data
// 	2. Constructors to create objects
//	3. Methods, e.g., getters, setters
//	Members= fields and methods of a class
public class Person {
	
	//fields
	private String name; // name of the person
	private int maximumBooks; //most books the person can check out
	
	//constructors
	public Person(){
		name = "unknown name";
		maximumBooks = 3;
	}
	
	//methods
	public String getName(){
		return name;
	}
	//parameters can have any name
	public void setName(String anyName) { //parameter name (parameter = variable that holds value passed to the method)
		name = anyName;
	}

	public int getMaximumBooks() {
		return maximumBooks;
	}
	//parameter name is maxixmumBooks, but it means something else.  Its just the name of the parameter.  Just happens to be the same
	public void setMaximumBooks(int maximumBooks) {
		this.maximumBooks = maximumBooks; //this = the current object
		//this.'maximumBooks' = class field
		//"this." this = the current object.  in this situation it is optional.
	}
	
	public String toString(){
		return this.getName() + " (" + this.getMaximumBooks() + 
		" books)"; //keyword "this" refers to the current object. so "this.getName()" just returns the name of the current Person object
		// could have used "this.name" instead of "this.getName()".
			//Using method "hides" our fields and gives us the option to change fields in the future.
				//for example, in the future we decided to split the "name" field into two fields: "firstName" and "lastName".  Would only need to change getName() to: "return firstName + "" + lastName;"
					//as long as we used getName() and not the "name"field, all other methods would still work.
	}
	
}
