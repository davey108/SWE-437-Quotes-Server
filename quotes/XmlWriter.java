package quotes;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/* This class writes new quote to the XML file
 * Author: Ranjit Singh
 * 
 * Changed addNewElement() on 3/3/2018 by Ranjit. To add category element to the quote
 */
public class XmlWriter {


    //Initialize default constructor
    public XmlWriter( ) {

    }

    //function to write new Quote in XML file
    public void fileWriter(Quote q){

        //File path
        String path = "quotes\\quotes.xml";

        //check of the file is found
        if(path == null){
            System.out.println("File path not found.");
        }

        try {
            //Xml parser
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path);

            //add new <quote> in <quote-list> using the following function
            addNewElement(doc,q);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);

            System.out.println("Quote is successfully added");


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    //function to add new <quote> in <quote-list>
    private void addNewElement(Document doc, Quote q){

        //get <quotes-list> tag
        Element quotesList = doc.getDocumentElement();

        //Create and Add new quote child
        Element quote = doc.createElement("quote");
        quotesList.appendChild(quote);

        //Create and Add quote-text
        Element quoteText = doc.createElement("quote-text");
        quoteText.appendChild(doc.createTextNode(q.getQuoteText()));
        quote.appendChild(quoteText);

        //Create and Add author
        Element author = doc.createElement("author");
        author.appendChild(doc.createTextNode(q.getAuthor()));
        quote.appendChild(author);
        
        //Create and Add category
        Element category = doc.createElement("category");
        author.appendChild(doc.createTextNode(q.getCategory()));
        quote.appendChild(category);
    }
}
