# SWE-437-Quotes-Server

// run down of each class, how to run the cli. 


//will add a line to seperate * Ahmad
# Changelog:
  
  # 2.9.18
  1. added checkQuoteInDatabase() in the QuoteCHecker.java class. this allows us to verify that the quote does not already exist in the XML file.
  2.	Added an additional option to the QuoteCLI.java class to allow the user to enter a quote to be added to the List/XML. this option takes in the user input and passes it to the QuoteChecker.java to ensure it is valid. before actually writing it to the file.
3. Added Reloadlist() to the QuoteCMD.java class because we wrote to the XML file we needed to update the list so that when the user is prompted again the list is always up to date.

  # 2.8.18
  1. created QuoteChecker.java which allows us to verify that the input provided by the user for a new quote to be added is valid before writing it into the XML file.
  2. added checkSpecialCharacters(Quote q) to the QuoteChecker.java class, this method that uses a Regex to confirm that the input String matches the regex that does not allow for Special characters. it is then added to the list.

  # 2.7.18
  1. created XmlWriter.java to allow us to write the new quote to the existing XML file, after reading in the quotes.xml file.

Assignment 3

SWE 437

Spring 2018


Team Members: Ahmad Aram, Khang Chau Vo and Ranjit Singh

Source Code: https://github.com/davey108/SWE-437-Quotes-Server


# Maintainability Assessment: 

To begin, the CLI for the quotes server is well documented and is not too difficult to understand even at a first glance. The comments do provide some insight as to what each method does in terms of functionality. Variable naming convention allows whomever is reading the program should be able to understand what each variables purpose is as, as no method is fairly convoluted in terms of functionality. This allows anyone who may want to modify this application to be able to do it with ease.
 
Screenshots





