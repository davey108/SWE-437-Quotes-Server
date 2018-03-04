package quotes;

import java.util.Scanner;


/* This class runs the command line interface for the quote server
 * @author Khang Chau Vo
 * Last Updated: 2/8/2018
 */
public class QuoteCMD{
  private Scanner inputTaker;
  private String fileName;
  private QuoteList list;
  private QuoteSaxParser parser;
  public static void main(String [] args){
    // was reading 1 directory above so needed path to move it to 1 below
    String path = "quotes\\";
    String fname = "quotes.xml";
    QuoteCMD engine = new QuoteCMD(path+fname);
    engine.askSelection();
    
  }
  /* Constructor
   * @param path the full math of the file
   * @param fname the name of the file including the extension
   */
  public QuoteCMD(String fileName){
    this.fileName = fileName;
    inputTaker = new Scanner(System.in);
    parser = new QuoteSaxParser(fileName);
    list = parser.getQuoteList();
  }
  /* Reload the quote list*/
  private void reloadList(String fileName){
    parser = new QuoteSaxParser(fileName);
    list = parser.getQuoteList();
  }

  /* This is the main driver that takes in the user's input
   * and call appropriate action and display it to the screen */
  public void askSelection(){
    System.out.println("Welcome to the quote generator");
    String searchBy;
    QuoteList temp;
    while(true){
      reloadList(fileName);
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      printQuoteFormat((list.getRandomQuote()));
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("Please enter which option you want to select");
      printMenu();
      System.out.print("->");
      String input = inputTaker.nextLine().trim();
      switch(input){
        // break for rq since a random quote will be generate no matter what
        case("rq"):
          break;
        case("sq"):
          searchBy = askSearch();
          temp = list.search(searchBy,1);
          printQuoteList(temp);
          break;
        case("sa"):
          searchBy = askSearch();
          temp = list.search(searchBy,0);
          printQuoteList(temp);
          break;
        case("s"):
          searchBy = askSearch();
          temp = list.search(searchBy,2);
          printQuoteList(temp);
          break;
        case("aq"):
          Quote userQuote = askInputQuote();
          boolean isValid = isUserQuoteValid(userQuote,list);
          if(isValid == true){
            XmlWriter writer = new XmlWriter();
            writer.fileWriter(userQuote);
          }
          break;
        case("sk"):
          searchBy = askForKeywords();
          processKeyWord(searchBy);
          break;
        case("q"):
          System.out.println("Goodbye!");
          inputTaker.close();
          System.exit(0);
        default:
          System.out.println("You did not enter a valid input!");
      }
    }
  }
  /* Display the menu to the users*/
  public void printMenu(){
    System.out.println("-----------------------------------------");
    System.out.println("Get a random quote:                   rq ");
    System.out.println("Search by quote text:                 sq ");
    System.out.println("Search by author:                     sa ");
    System.out.println("Search by author and quote:           s  ");
    System.out.println("Add in your custom quote:             aq ");
    System.out.println("Search by keywords:                   sk ");
    System.out.println("Quit:                                 q  ");
    System.out.println("-----------------------------------------");
  }
  /**
   * Return the list of quotes
   * @return
   * 		The list of quotes
   */
  public QuoteList getQuoteList(){
    return this.list;
  }
  /* Ask the user to input the key they want to
   * search by
   * @return the key the user wants to search
   */
  public String askSearch(){
    System.out.print("Search by: ");
    String searchString = inputTaker.nextLine();
    return searchString.trim();
  }
  /* Print out a list of quotes */
  public void printQuoteList(QuoteList quoteList){
    if(quoteList.getSize() == 0)
      System.out.println("No search results found");
    for(int i = 0; i < quoteList.getSize(); i++)
      printQuoteFormat(quoteList.getQuote(i));
  }
  /* Prints a single quote in a nicely formatted fashion
   * by printing out the quote, follow by its author
   */
  public void printQuoteFormat(Quote quote){
    System.out.println(quote.getQuoteText());
    System.out.println("- " + quote.getAuthor());
  }
  /* Ask the users for 2 strings: Author and quote text
   * and return a quote object comprises of those 2 items
   * @return a quote object with the inputted author and quote text
   */
  private Quote askInputQuote(){
    System.out.println("You can enter your quote below, please do not include any special characters (e.g: @, #, $, %, etc) or leave the field empty");
    System.out.print("Enter your quote: ");
    String userQuoteText = inputTaker.nextLine().trim();
    System.out.print("Enter the author name of the quote: ");
    String userAuthorText = inputTaker.nextLine().trim();
    // initially blank keyword, unless we add in new option allowing user to tag a quote with a keyword
    return new Quote(userAuthorText,userQuoteText,"");
  }

  /* Given a quote, validate the quote
   * @return true if the quote is valid
   * @return false if the quote is invalid (has special character, repeat)
   */
  public boolean isUserQuoteValid(Quote q, QuoteList list){
    boolean containsSpecialChars = QuoteChecker.checkSpecialCharacters(q);
    if(containsSpecialChars == true){
      System.out.println("Quote entered contains a special character(s) or is empty in one of the required field(s)");
      return false;
    }
    boolean isRepeat = QuoteChecker.checkQuoteInDatabase(q,list);
    if(isRepeat == true){
      System.out.println("The quote or author is already in the database");
      return false;
    }
    return true;
  }
  /**
   * Given a list of keywords, print out those keywords
   */
  public void printKeyWords(String [] keywords){
    if(keywords == null){
      System.out.print("");
    }
    else{
      int len = keywords.length;
      for(int i = 0; i < len; i++){
        // empty is treated as nothing
        if(keywords[i].equals(" ")){
          System.out.print("");
        }
        else{
          System.out.print(keywords[i]);
        }
        // print separation for if length is not last one
        if(i < len - 1){
          System.out.print(", ");
        }
      }
    }
  }
  /**
   * Given a list of keywords and a keyword to search by, return a list of the result of the keysearch
   * @return
   * 		The list of quotes associated with that keyword
   * @throws
   * 		NoKeyWordException if no such keyword exist in list of keywords
   */
  public QuoteList searchKey(String searchKey, String [] keywords) throws NoKeyWordException{
    // transform space to be the same as empty search
    if(searchKey.equals(" ")){
      searchKey = "";
    }
    boolean containsKey = false;
    for(String key : keywords){
      if(key.equals(searchKey)){
        containsKey = true;
        break;
      }
    }
    if(!containsKey)
      throw new NoKeyWordException();
    if(searchKey.equals("") || searchKey.equals(" "))
      System.out.print("Search result for \"\": ");
    else
      System.out.print("Search result for " + searchKey + ": ");
    // search for the quote
    return list.search(searchKey, 3);
  }
/**
 * Ask for the keyword to search for
 * @return
 * 		The user search string
 */
  public String askForKeywords(){
    System.out.print("Enter the keyword that you want to search by: ");
    String input = inputTaker.nextLine();
    return input.trim();
  }
  /**
   * Given the user keyword to search for, search the list of quotes for that keywords and print out those quotes
   * @param keyword
   * 		The keyword to search for quotes
   * @return
   * 		Return empty string if the list is empty, else return null
   */
  public String processKeyWord(String keyword){
	  printKeyWords(list.getKeys().toArray(new String[0]));
	  System.out.println();
	  try{
		  QuoteList searchResult = searchKey(keyword,list.getKeys().toArray(new String[0]));
		  System.out.println();
		  printQuoteList(searchResult);
	  }
	  catch(NoKeyWordException e){
		  System.out.println("No quotes found for the keyword: " + keyword);
		  return "";
	  }
	  return null;
  }

}
