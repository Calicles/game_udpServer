package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XML_Parser {
	
	Document doc;
	
	public void getDOM(String url) {
		try {
			File inputFile= new File(url);
			FileInputStream stream= new FileInputStream(inputFile);
			DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder= dbFactory.newDocumentBuilder();
			doc= dBuilder.parse(stream);
			doc.getDocumentElement().normalize();
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
	
	public String getValueByTagName(String tagName) {
		return doc.getElementById(tagName).getNodeValue();
	}
	
	public static void main(String args[]) {
		String test;
		XML_Parser parser= new XML_Parser();
		parser.getDOM("ressources/config/conf.xml");
		System.out.println(parser.getValueByTagName("small"));
	}

}
