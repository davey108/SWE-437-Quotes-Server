package quotes;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuoteTest {

	//All return values for the asserts are 1 or 0 atm since when its checking the actual result it gets what looks like an Object Reference****Ahmad
	@Test
	public void testAuthor() {
		QuoteList testQuote = new QuoteList();//test quote object
		assertEquals(1,testQuote.search("Richard Nixon", 0));//pass in Richard Nixon and confirm that it is found in the search"
		assertNotEquals(1,testQuote.search("Random Sam", 0));//passing in an author that is not in the list and expect a return of false
	}
	@Test
	public void testQuote(){
		QuoteList testQuote = new QuoteList();
		String quote = "Eschew obfuscation!";
		assertEquals(1,testQuote.search(quote, 1));//passing in a quote that does exist and expecting a return of true
		assertNotEquals(1,testQuote.search("The sky is blue", 1));//passing in a quote that is not in the list and expecting it to fail
	}
	@Test
	public void testAuthorQuote(){
		QuoteList testQuote = new QuoteList();
		String validQuote = "Eschew obfuscation!";//valid quote that is located in the list
		String invalidQuote = "random quotes are fun";//invalid quote that is not in the list
		String validAuthor ="John Keats";//valid author to be used in the search
		String invalidAuthor ="Bill Gates";//invalid author that is not in the list
		
		assertEquals(1,testQuote.search(validQuote, 2));//passing a valid quote and expecting it to be found
		
		assertNotEquals(1,testQuote.search(invalidQuote, 2));//passing a invalid quote to expect it to fail as its not in the list
		
		assertEquals(1,testQuote.search(validAuthor, 2));//passing it a valid author and expecting a true
		
		assertNotEquals(1,testQuote.search(invalidAuthor, 2));//passing an invalid author and expecting it to return a true
		
	}
	@Test
	public void test4(){
		QuoteList testQuote = new QuoteList();
		String validQuote = "I know that you believe you understand what you think I said, but I am not sure you realize that what you heard is not what I meant.";
		String validAuthor = "Ramsey Clark";
		
		assertEquals(1,testQuote.search(validAuthor,2));
		
		assertNotEquals(1,testQuote.search("Mickey Mouse", 2));
		
		assertEquals(1,testQuote.search(validQuote, 2));
		assertNotEquals(1,testQuote.search("i love testing!", 2));
		
	}
	

}
