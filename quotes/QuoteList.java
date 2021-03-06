package quotes;

import java.util.ArrayList;
import java.util.Random;

/**
 * List of all the quotes.
 * @author Mongkoldech Rajapakdee & Jeff Offutt
 *       Date: Nov 2009
 * Last updated: 3/3/2018
 * Lastest changes: added in a new search mode (3) to search for quotes by keywords
 */
public class QuoteList
{
   private ArrayList<Quote> quoteArray;

   // These constants are used in the servlet
   /* package */ static final int SearchAuthorVal = 0;
   /* package */ static final int SearchTextVal   = 1;
   /* package */ static final int SearchBothVal   = 2;
   /* package */ static final int SearchKeyVal   = 3; /*added on 3/3/2018 by Ranjit. To Search by keyword*/

   // For returning a random quote
   private Random randomGen;

   // Default constructor creates a new list and initializes the random seed
   public QuoteList()
   {
      this.quoteArray = new ArrayList<Quote>();
      // make it really random!
      randomGen = new Random(System.currentTimeMillis());
   }

   // Called when a quote is found, added to the array
   public void setQuote (Quote q)
   {
      quoteArray.add (q);
   }

   // Current size of the quote list
   public int getSize()
   {
      return quoteArray.size();
   }

   // Returns the ith quote from the list
   public Quote getQuote (int i)
   {
      return (Quote) quoteArray.get (i);
   }

   /**
    * Search the quotes in the list, based on searchString
    * @param searchString String input for search
    * @param mode search in the author, quotr, or both
    * @return quotes.QuoteList containing the search results (may be multiple quotes)
    */
   public QuoteList search (String searchString, int mode)
   {
      QuoteList returnQuote = new QuoteList();
      Quote quote;
      for (int i = 0; i < quoteArray.size(); i++)
      {
         quote = quoteArray.get (i);
         if (mode == SearchAuthorVal && quote.getAuthor().toLowerCase().indexOf (searchString.toLowerCase()) != -1)
         {  // Found a matching author, save it
            // System.out.println ("Matched Author ");
            returnQuote.setQuote (quote);
         } else if (mode == SearchTextVal && quote.getQuoteText().toLowerCase().indexOf (searchString.toLowerCase()) != -1)
         {  // Found a matching quote, save it
            // System.out.println ("Matched Text ");
            returnQuote.setQuote (quote);
         } else if ((mode == SearchBothVal) &&
                    (quote.getAuthor().toLowerCase().indexOf (searchString.toLowerCase()) != -1 ||
                     quote.getQuoteText().toLowerCase().indexOf (searchString.toLowerCase()) != -1))
         {  // Found a matching author or quote, save it
            // System.out.println ("Matched Both ");
            returnQuote.setQuote (quote);
         }
         /*added on 3/3/2018 by Ranjit. To Search by keyword*/
         else if((mode == SearchKeyVal) && quote.getCategory().toLowerCase().indexOf (searchString.toLowerCase()) != -1){
            returnQuote.setQuote (quote);
         }
      }
      return returnQuote;
   }

   //Function to get list of Keywords for the quotes without duplication
   /*added on 3/3/2018 by Ranjit.*/
   public ArrayList<String> getKeys(){

      //String array list ot return
      ArrayList<String> keywordsList = new ArrayList<>();
      Quote quote;   //variable to get quote

      //loop over the quotes List
      for (int i = 0; i < quoteArray.size(); i++){

         quote = quoteArray.get(i); //getting each quote

         //checking if the keyword is not in String list
         if( !keywordsList.contains(quote.getCategory())){
            //add keyword to the sting list
            keywordsList.add(quote.getCategory());
         }
      }
      return keywordsList;
   }

   /**
    * Return a random quote object from the list.
    * @return a random quotes.Quote
    */
   public Quote getRandomQuote ()
   {
     // the issue before is every run has same quote, so to avoid that, regenerate random quotes everytime!
     this.randomGen = getNewRandom();
      return quoteArray.get (randomGen.nextInt (quoteArray.size()));
   }
   
   /**
    * Generate a new random machine based on time
    * @return a new random machine
    */
   private Random getNewRandom(){
     return new Random(System.currentTimeMillis());   
   }
}
