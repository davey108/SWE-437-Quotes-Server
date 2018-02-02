package quotes;
import java.util.Scanner;

public class QuoteCMD{
  private Scanner inputTaker;
  private String fileName;
  private QuoteList list;
  public static void main(String [] args){
     String path = "quotes\\";
     String fname = "quotes.xml";
     QuoteCMD engine = new QuoteCMD(path,fname);
     engine.askSelection();
   }
  public QuoteCMD(String path, String fname){
    inputTaker = new Scanner(System.in);
    fileName = path + fname;
    QuoteSaxParser parser = new QuoteSaxParser(fileName);
    list = parser.getQuoteList();
  }
  
  public void askSelection(){
    System.out.println("Welcome to the quote generator");
    String searchBy;
    QuoteList temp;
    while(true){
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
        case("q"):
          System.out.println("Goodbye!");
          inputTaker.close();
          System.exit(0);
        default:
          System.out.println("You did not enter a valid input!");
          
        
      }
    }
  }
  
  public void printMenu(){
    System.out.println("-----------------------------------------");
    System.out.println("Get a random quote:                   rq ");
    System.out.println("Search by quote text:                 sq ");
    System.out.println("Search by author:                     sa ");
    System.out.println("Search by author and quote:           s  "); 
    System.out.println("Quit:                                 q  ");
    System.out.println("-----------------------------------------");
  }
  
  public String askSearch(){
    System.out.print("Search by: ");
    String searchString = inputTaker.nextLine();
    return searchString.trim();   
  }
  
  public void printQuoteList(QuoteList quoteList){
    for(int i = 0; i < quoteList.getSize(); i++){     
      printQuoteFormat(quoteList.getQuote(i));      
    }  
  }
  public void printQuoteFormat(Quote quote){
    System.out.println(quote.getQuoteText());
    System.out.println("- " + quote.getAuthor());
  }
}