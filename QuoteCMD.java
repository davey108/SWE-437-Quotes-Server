package quotes;
import java.util.Scanner;

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
          if(isValid){
            XmlWriter writer = new XmlWriter();
            writer.fileWriter(userQuote);
          }
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
    System.out.println("Quit:                                 q  ");
    System.out.println("-----------------------------------------");
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
    return new Quote(userAuthorText,userQuoteText);  
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
}