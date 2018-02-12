# SWE-437-Quotes-Server


# To Run the Quotes Server CLI:
1. Download/fork/clone the github with all the java classes for this assignment.
2. Compile the classes on your local machine(there should be no compilation errors).
3. Run the <code>QuotesCMD.java</code> file to begin the use of the interface.


# Written Class Descriptions:
<code>QuotesCMD.java</code> - The main driver for the interface allowing the user to enjoy a random quotes, search for quotes or authors, and add in their own inspiring quote

<code>QuoteChecker.java</code> - This class checks for the validity of a quote. A quote is valid if it:</br >
* Does not have special characters (e.g: @, ', #, $, %, etc.)</br >
* Must not already in the database</br >
* Must not be an empty line</br >

<code>XmlWriter.java</code> - After passing the quote validification, this class writes the quote to the <code>quotes.xml</code> file.

******************************************************
# Changelog:
  
  # 2.9.18
  1. Added <code>checkQuoteInDatabase()</code> in the <code>QuoteChecker.java</code>. This class allows verification of quotes inputted by the users against quotes that were already in the database.
  2. Added an add quote option to the <code>QuoteCMD.java</code> class to allow the user to enter a quote to be added to the quotes.xml. This option takes in the user input and passes it to the QuoteChecker.java to ensure it is valid before writing the quote to quotes.xml.
  3. Added <code>reloadList()</code> to the <code>QuoteCMD.java</code> class to reload the list of quotes since before, quoteList was only instantiated once in the constructor, so it couldn't get the latest update on the user's inputted quote. The method <code>reloadList()</code> allows the list of quotes to be constantly updated

  # 2.8.18
  1. Created <code>QuoteChecker.java</code> which verify that the quote provided by the user is valid before writing it into the XML file.
  2. Added <code>checkSpecialCharacters(Quote q)</code> to the <code>QuoteChecker.java</code>, this method uses regex to confirm that the input string matches the regex that does not allow for Special characters. it is then added to the list.
  3. The rules for a valid quote are:</br >
&nbsp;&nbsp;- No special characters allow (e.g: @, ', #, $, %, etc.)</br >
&nbsp;&nbsp;- The provided quote must not be already in the database</br >
&nbsp;&nbsp;- The quote cannot be an empty line</br >

  # 2.7.18
  1. Created <code>XmlWriter.java</code> to write the new quotes to the existing XML database file.

*********************************************************************************************

# Maintainability Assessment: 

To begin, the CLI for the quotes server is well documented and is not too difficult to understand even at a first glance. The comments do provide some insight as to what each method does in terms of functionality. Variable naming convention allows whomever is reading the program should be able to understand what each variables purpose is as, as no method is fairly convoluted in terms of functionality. This allows anyone who may want to modify this application to be able to do it with ease.

# Integrity Check
The integrity of the new data that is being added to the XML/Quotes database is being run through a Regex in the <code>QuoteChecker.java</code> class. This class allows us to check the formatting of the quote to ensure it does not include any special characters and only contains letters and spaces for both the author and the quote fields which follows the trend for the rest of the quotes in the file. When the user enters an invalid quote, a message stating which field contained invalid characters and allows them to try again. it also ensures that there are no duplicate quotes in the file with the <code>checkQuoteInDatabase()</code> method.
