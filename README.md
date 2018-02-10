# SWE-437-Quotes-Server
This is SWE 437 Quotes Server that testing will be done on






Assignment 3

SWE 437

Spring 2018


Team Members: Ahmad Aram, Khang Chau Vo and Ranjit Singh

Source Code: https://github.com/davey108/SWE-437-Quotes-Server
 

Maintainability Assessment: 

To begin, the CLI for the quotes server is well documented and is not too difficult to understand even at a first glance. The comments do provide some insight as to what each method does in terms of functionality. Variable naming convention allows whomever is reading the program should be able to understand what each variables purpose is as, as no method is fairly convoluted in terms of functionality. This allows anyone who may want to modify this application to be able to do it with ease.

Documentation Log: 

XmlWriter.java	
1.	Entire class allows us to write our new quote to the existing XML file, after reading in the quotes.xml file

QuoteChecker.java	
1.	This class allows us to verify that the input provided by the user for a new quote to be added is valid before adding it to the XML file.
2.	In checkSpecialCharacters(Quote q) We use a Regex to confirm that the input String matches the regex conditions to not allow any special characters(!,@,#,$,%) etc. in the quote to be added into the list.
3.	This class also checks if the quote being inputted is already in the database using the checkQuoteInDatabase() by simply searching the list.

QuoteCMD.java	
1.	What was added to this class was the additional option to add a quote using "aq" when prompted. This then prompts the user to enter a quote to be added, then passes this to the XML writer to confirm it is valid before actually writing it to the file.

 
Screenshots





