package quotes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* This class check if the entering quotes has special character, recognize by regex
 * and check if it is a duplicate quote that is already in the database
 * Author: Ranjit Singh & Khang Chau Vo
 */
public class QuoteChecker {

    /* to check if there is any special character or numbers in
     * quote or author name
     * @return true if special character is found within the quote text or author text
     * @return false if no special character(s) is found within the text
     */   
    public static boolean checkSpecialCharacters(Quote q){

        //check if the author name or quote is null
        if( q.getAuthor().trim().isEmpty() || q.getQuoteText().trim().isEmpty()){
            System.out.println("Author name or quote is Empty");
            return true;
        }

        //create a regex pattern of a-z and comma and stop and space
        //this regex if for quote only
        Pattern p = Pattern.compile("[^A-Za-z., ]");

        //Create a matcher for quote text
        Matcher quoteMatcher = p.matcher(q.getQuoteText());
        boolean quoteCheck =quoteMatcher.find();

        //create a regex pattern of a-z and space and stop
        //this regex if for author only
        Pattern pattern = Pattern.compile("[^A-Za-z. ]");

        //Create a matcher for author of quote
        Matcher authorMatch = pattern.matcher(q.getAuthor());
        boolean authorCheck = authorMatch.find();

        //if there is special character or numbers in the author name
        if (authorCheck == true){
            System.out.println("Numbers or special characters found in the (author) name. Please try again.");
            return true;
        }
        //similarly if special characters are found in the quote
        else if(quoteCheck == true){
            System.out.println("Numbers or special characters found in the (quote) entered. Please try again.");
            return true;
        }
        else{
            return false;
        }
    }
    
    /* Check if a quote is already in the database of quotes
     * @return true if the quote is already in the database
     * @return false if the quote is not in the database
     */
    public static boolean checkQuoteInDatabase(Quote q, QuoteList list){
      String quoteText = q.getQuoteText();
      QuoteList resultSearch = list.search(quoteText,1);
      if(resultSearch.getSize() == 0){
        return false;
      }
      return true;
    }
}
