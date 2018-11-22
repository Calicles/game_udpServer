package services;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XML_Parser {
	
	public void getDOM(String url) {
		try {
			File inputFile= new File(url);
			DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder= dbFactory.newDocumentBuilder();
			Document document= dBuilder.parse(inputFile);
			document.getDocumentElement().normalize();
			/**Exemples
			 * NodeList nListe= document.getElementsByTagName("exemples");
			 * Node node= nListe.item(numero);
			 * Element elem= (Element) node;
			 * possible elem.getAttribute("exemple");
			 * elem.item(int);
			 * elem.getTextContent();
			 */
		}catch(IOException ioe) {System.out.println(ioe);
		}catch(ParserConfigurationException pce){System.out.println(pce);
		}catch(SAXException se) {System.out.println(se);}
	}

}
