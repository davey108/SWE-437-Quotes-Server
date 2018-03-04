package quotes;


/**
 * Quote data object.
 * @author Mongkoldech Rajapakdee & Jeff offutt
 *         Date: Nov 2009
 * A quote has two parts, an author and a quoteText.
 * This bean class provides getters and setters for both, plus a toString()
 */
public class Quote
{
   private String author;
   private String quoteText;
   private String category;

   // Default constructor does nothing
   public Quote ()
   {
   }

   // Constructor that assigns both strings
   public Quote (String author, String quoteText, String category)
   {
      this.author = author;
      this.quoteText = quoteText;
      this.category = category;
   }

   // Getter and setter for author
   public String getAuthor ()
   {
      return author;
   }
   public void setAuthor (String author)
   {
      this.author = author;
   }
   
   // Getter and setter for quoteText
   public String getQuoteText ()
   {
      return quoteText;
   }
   public void setQuoteText (String quoteText)
   {
      this.quoteText = quoteText;
   }
   
   // Getter and setter for category
   public String getCategory(){
	   return category;
   }
   public void setCategory(String category){
	   this.category = category;
   }
   
   @Override
   public String toString ()
   {
      return "Quote {" + "author='" + author + '\'' + ", quoteText='" + quoteText + '\'' + '}';
   }
}
