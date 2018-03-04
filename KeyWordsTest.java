
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

import exception.NoKeyWordException;
import quotes.Quote;
import quotes.QuoteCMD;
import quotes.QuoteList;
/**
 * This class test the keyword search functionality within QuoteCMD.java
 * @author Khang Chau Vo
 * Last updated: 3/3/2018
 */

public class KeyWordsTest {
	QuoteCMD cmd;
	String [] keywords;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private String searchKey;
	@Before
	public void setUp(){
		String fname = "quotes.xml";
		cmd = new QuoteCMD(fname);
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	@After
	public void cleanUp() {
	    System.setOut(System.out);
	    System.setErr(System.err);
	}
	
	/*
	 * User story 1
	 */
	
	/**
	 * Test if the method will print out the keywords correctly
	 */
	@Test
	public void testPrintKeyWords3Keys(){
		setKeyContents(new String[]{"Inspirational","Random","Knowledge"});
		cmd.printKeyWords(keywords);
		assertContents("Inspirational, Random, Knowledge");	
	}
	/**
	 * Test if the method will print out empty when given null key word list
	 */
	@Test
	public void testPrintNullKeys(){
		setKeyContents(null);
		cmd.printKeyWords(keywords);
		assertContents("");
	}
	/**
	 * Test if the method will print out blank keywords (or even empty keyword)
	 */
	@Test
	public void testPrintEmptyKeys(){
		setKeyContents(new String []{""});
		cmd.printKeyWords(keywords);
		assertContents("");
		setKeyContents(new String []{" "});
		cmd.printKeyWords(keywords);
		// treat empty still the same as nothing
		assertContents("");
	}
	
	/**
	 * Factorization for key words printing tests (user story 1)
	 */
	/**
	 * Given a list of keys, set the keywords array to those contents
	 * @param keys
	 */
	private void setKeyContents(String [] keys){
		if(keys == null){
			keywords = null;
			return;
		}
		keywords = new String[keys.length];			
		for(int i = 0; i < keys.length; i++){
			keywords[i] = keys[i];
		}
	}
	/**
	 * Given an expected content, assert if the console print out is equal
	 */
	private void assertContents(String expected){
		assertEquals(expected,outContent.toString());
	}
	
	/*
	 * User story 2
	 */
	
	/**
	 * Test for a keyword input and that keyword exist in the list of keywords
	 * 
	 */
	@Test 
	public void testFullWordSearch() throws NoKeyWordException{
		setKeyContents(new String[]{"Inspiration","Scary","Knowledge",""});
		setSearchKey("Scary");
		cmd.searchKey(searchKey,keywords);
		assertContents("Search result for Scary: ");
	}
	/**
	 * Test for a keyword input that does not exist in the list of keywords
	 */
	@Test(expected = NoKeyWordException.class)
	public void testNonExistKey() throws NoKeyWordException{
		setKeyContents(new String[]{"Love","Random","Final Fantasy",""});
		setSearchKey("Hello");
		cmd.searchKey(searchKey, keywords);
	}
	/**
	 * Test for a keyword input that is blank (empty) which is the default keyword to all quotes
	 * Default key should be display even though it is empty
	 * Treat space as empty
	 */
	@Test
	public void testEmptyKeySearch() throws NoKeyWordException{
		setKeyContents(new String[]{"Ranjit","","Ocean"});
		setSearchKey(" ");
		cmd.searchKey(searchKey, keywords);
		assertContents("Search result for \"\": ");
	}
	/**
	 * Refactorizing for user story 2
	 */
	private void setSearchKey(String searchKey){
		this.searchKey = searchKey;
	}
	
	/*
	 * User story 3
	 */
	/**
	 * Given a legit key word, see if the list of quotes returned associated with that keyword is matching
	 * @throws NoKeyWordException 
	 */
	@Test 
	public void testSearchExistenceKeyWord() throws NoKeyWordException{
		setSearchKey("Believe");
		setKeyContents(cmd.getQuoteList().getKeys().toArray(new String[0]));
		QuoteList resultSearch = cmd.searchKey(searchKey, keywords);
		// there should only be 1 element in the list
		assertEquals(1,resultSearch.getSize());
		Quote firstResult = resultSearch.getQuote(0);
		String quoteTextExpected = "I know that you believe you understand what you think I said, "
				+ "but I am not sure you realize that what you heard is not what I meant.";
		assertEquals(quoteTextExpected,firstResult.getQuoteText());
		String authorExpected = "Richard Nixon";
		assertEquals(authorExpected,firstResult.getAuthor());		
	}
	
	/**
	 * Given a legit key word, check if the list of quote return contains all the required quotes
	 * @throws NoKeyWordException
	 */
	@Test
	public void testSearchKeyMultipleQuotes() throws NoKeyWordException{
		setSearchKey("Random");
		setKeyContents(cmd.getQuoteList().getKeys().toArray(new String[0]));
		QuoteList resultSearch = cmd.searchKey(searchKey, keywords);
		// there should be 4 elements
		assertEquals(4,resultSearch.getSize());
		Quote firstResult = resultSearch.getQuote(0);
		Quote secondResult = resultSearch.getQuote(1);
		Quote thirdResult = resultSearch.getQuote(2);
		Quote fourthResult = resultSearch.getQuote(3);
		String quoteExpectedFirst = "Eschew obfuscation!";
		assertEquals(quoteExpectedFirst,firstResult.getQuoteText());
		String authorExpectedFirst = "Don Cunningham";
		assertEquals(authorExpectedFirst,firstResult.getAuthor());
		
		String quoteExpectedSecond = "The only man who behaves sensibly is my tailor; he "
				+ "takes my measure anew every time he sees me, "
				+ "whilst all the rest go on with their old measurements, expecting them to fit me.";
		assertEquals(quoteExpectedSecond,secondResult.getQuoteText());
		String authorExpectedSecond = "George Bernard Shaw";
		assertEquals(authorExpectedSecond,secondResult.getAuthor());
		
		String quoteExpectedThird = "We often do not know how to make those decisions "
				+ "[about a system design] until we can play with the system.";
		assertEquals(quoteExpectedThird,thirdResult.getQuoteText());
		String authorExpectedThird = "David Parnas \"Software Aspects of SDI\"";
		assertEquals(authorExpectedThird,thirdResult.getAuthor());
		
		String quoteExpectedFourth = "Our group is the best.";
		assertEquals(quoteExpectedFourth,fourthResult.getQuoteText());
		String authorExpectedFourth = "G. Awesome";
		assertEquals(authorExpectedFourth,fourthResult.getAuthor());		
	}
	/**
	 * Given a nonexistence keyword, check if the result will catch the error
	 * @throws NoKeyWordException 
	 */
	@Test
	public void testNonRealKey() throws NoKeyWordException{
		setSearchKey("");
		setKeyContents(cmd.getQuoteList().getKeys().toArray(new String[0]));
		String result = cmd.processKeyWord(searchKey);
		assertEquals("",result);
	}
	
}
