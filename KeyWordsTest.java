import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;


import quotes.QuoteCMD;

public class KeyWordsTest {
	QuoteCMD cmd;
	String [] keywords;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
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
	
	/**
	 * Test if the method will print out the keywords correctly
	 */
	@Test
	public void testPrintKeyWords3Keys(){
		setKeyContents(new String[]{"Inspirational","Random","Knowledge"});
		cmd.printKeyWords(keywords);
		assertKeys("Inspirational, Random, Knowledge");	
	}
	/**
	 * Test if the method will print out empty when given null key word list
	 */
	@Test
	public void testPrintNullKeys(){
		setKeyContents(null);
		cmd.printKeyWords(keywords);
		assertKeys("");
	}
	/**
	 * Test if the method will print out blank keywords (or even empty keyword)
	 */
	@Test
	public void testPrintEmptyKeys(){
		setKeyContents(new String []{""});
		cmd.printKeyWords(keywords);
		assertKeys("");
		setKeyContents(new String []{" "});
		cmd.printKeyWords(keywords);
		// treat empty still the same as nothing
		assertKeys("");
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
	private void assertKeys(String expected){
		assertEquals(expected,outContent.toString());
	}
	
}
