package quotes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class QuoteTest {

	//All return values for the asserts are 1 or 0 atm since when its checking the actual result it gets what looks like an Object Reference****Ahmad
	@Test
	public   void testAuthor() {
		//make new list that is a reference to compare that is returned by search
		QuoteList referenceList = new QuoteList();//test quote that will be our reference list
		QuoteList testList = new QuoteList();//list that we will be using to test against reference
		
		Quote q = new Quote();//temporary quote added to the 
		q.setAuthor("Richard Nixon");//setting the author of temp quote
		q.setQuoteText("Hello World");//setting the text of the temp quote
		referenceList.setQuote(q);//adding the quote to the refernce list to test Search()		
		
		System.out.println(referenceList.getSize());//test comments will delete
		testList = referenceList.search("Richard Nixon", 0);//set our temp list to the reference List after a search is done
		System.out.println(testList.getSize());//test comments will delete
		
		
		
		assertEquals(referenceList.getQuote(0),testList.getQuote(0));//pass in Richard Nixon and confirm that it is found in the search"
		testList = referenceList.search("Happy Cat", 0);//searching for a nonexistent Author thus keeping the size of testList to 0
		assertNotEquals(referenceList.getSize(),testList.getSize());//passing in an author that is not in the list and expect a return of false as the list will be empty
	}
	@Test
	public void testQuote(){
		QuoteList testQuote = new QuoteList();
		QuoteList referenceList = new QuoteList();
		Quote q = new Quote();
		String quote = "Eschew obfuscation!";
		String author = "Don Cunningham";
		
		q.setQuoteText(quote);//set the sample quote text
		q.setAuthor(author);
		referenceList.setQuote(q);//add the quote to the reference list
		
		testQuote = referenceList.search(quote, 1);//search for a quote that is existing in the reference List
		assertEquals(referenceList.getQuote(0),testQuote.getQuote(0));//passing in a quote that does exist and expecting a return of true
		
		
		//begin search and compare for a quote that isnt in the reference list
		testQuote = referenceList.search("the Sky is Blue",1);//this will yield an empty list as its not in the reference List
		assertNotEquals(referenceList.getSize(),testQuote.getSize());//passing in a quote that is not in the list and expecting it to fail as it will be empty
	}
	@Test
	public void testAuthorQuote(){
		QuoteList testQuote = new QuoteList();
		QuoteList referenceList = new QuoteList();
		//begin variables for valid and invalid quotes
		String validQuote = "Eschew obfuscation!";//valid quote that is located in the list
		String validAuthor ="John Keats";//valid author to be used in the search

		String invalidQuote = "random quotes are fun";//invalid quote that is not in the list
		String invalidAuthor ="Bill Gates";//invalid author that is not in the list
		
		//begin addition of valid quote and author to referenceList
		Quote q = new Quote();//create a new quote to add to the reference list
		q.setAuthor(validAuthor);//set the author to a valid author
		q.setQuoteText(validQuote);//set the quote to valid text
		referenceList.setQuote(q);//add the reference quote to the List
		
		//begin comparisons if the quote is in the list
		testQuote = referenceList.search(validQuote, 2);//passing a valid quote and having that store in the testQuote list if found
		assertEquals(referenceList.getQuote(0),testQuote.getQuote(0));//ensure that both quotes are the same in each list
		
		//begin comparison if the author is in the list
		testQuote = referenceList.search(validAuthor, 2);//search the list just using the valid author		
		assertEquals(referenceList.getQuote(0),testQuote.getQuote(0));//compare the quotes based on the authors
		
		//begin comparison if the quote isnt in the list
		testQuote = referenceList.search(invalidQuote, 2);//setting testQuote to the resulting list if the quote isnt valid hence size =0
		assertNotEquals(referenceList.getSize(),testQuote.getSize());//compare the size of the lists since the testQuote will be empty since no match found
		
		//begin comparison if the author isnt in the list
		testQuote = referenceList.search(invalidAuthor, 2);
		assertNotEquals(referenceList.getSize(),testQuote.getSize());//compare the size of the list since the testQuote will be empty since no author was matched
	}
	
	public void test4(){//THIS METHOD ISNT DONE YET!
		QuoteList testQuote = new QuoteList();
		String validQuote = "I know that you believe you understand what you think I said, but I am not sure you realize that what you heard is not what I meant.";
		String validAuthor = "Ramsey Clark";
		
		assertEquals(1,testQuote.search(validAuthor,2));
		
		assertNotEquals(1,testQuote.search("Mickey Mouse", 2));
		
		assertEquals(1,testQuote.search(validQuote, 2));
		assertNotEquals(1,testQuote.search("i love testing!", 2));
		
	}
	/*TESTER MAIN TO ENSURE VALUES ARE ADDED
	 * public static void main(String args[]){
		testAuthor();
	}*/

}
