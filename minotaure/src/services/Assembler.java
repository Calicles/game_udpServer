package services;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


@SuppressWarnings("deprecation")
public class Assembler {
	
	private Map<String, String> id_class;	//gestion d'un cache
	private Map<String, String[]> injections;
	private Map<String, String> subInjections;
	private Map<String, String> methods;
	private Map<String, String[]> params;
	
	public Assembler() throws SAXException, IOException, ParserConfigurationException {
		injections= new HashMap<>();
		id_class= new HashMap<>();
		parse();
	}		
	
	public void parse() throws SAXException, IOException, ParserConfigurationException{
		SAXParserFactory factory= SAXParserFactory.newInstance();
		SAXParser parser= factory.newSAXParser();
		parser.parse("ressources/config/conf.xml", new XMLHandler());
	}
	
	public Object newInstance(String id) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
	InstantiationException, IllegalAccessException, IllegalArgumentException,
	InvocationTargetException {
		
		Object res= null;
			
		//TODO
		return res;
	}
	
	
	class XMLHandler extends DefaultHandler {
		
		int indexHost=0;
		int indexCli=0;
		
		@Override
		public void startElement(String nameSpaceURI, String lName, String qName,
				Attributes attr) throws SAXException {
			

			String[] buffer= null;
			
			if(qName.equals("host")) {
				
				buffer= register(attr, "id", "class", "injection");
				id_class.put(buffer[0], buffer[1]);
				injections.put(buffer[1], new String[Integer.parseInt(buffer[2])]);
				
			}else if(qName.equals("character")) {
				
				buffer= register(attr, "key", "class", "");
				injections.get(buffer[0])[indexHost]= buffer[1];
				indexHost++;
				
			}else if(qName.equals("injection")) {
				
				buffer= register(attr, "key", "class", "method");
				subInjections.put(buffer[0], buffer[1]);
				methods.put(buffer[1], buffer[2]);
				params.put(buffer[0], new String[2]);
				
			}else if(qName.equals("images")) {
				
				register(attr, "key", "src", 0);
				
			}else if(qName.equals("position")) {
				
				register(attr, "key", "coor", 1);
			}
			
		}
	
		public String[] register(Attributes attr, String att1, String att2, String att3) {
			String res= null;
			for(int i=0; i< attr.getLength(); i++) {
				if(attr.getLocalName(i).equals(att1)) {
					res= attr.getValue(i)+";";
				}else if(attr.getLocalName(i).equals(att2)) {
					if(att3.isEmpty()) {
						res += attr.getValue(i);
					}else
						res += attr.getValue(i)+";";
				}else if(attr.getValue(i).equals(att3)) {
					res += attr.getValue(i);
				}
			}
			return res.split(";");
		}
		
		public void register(Attributes attr, String att1, String att2, int paramIndex) {
			String id= null, param= null;
			for(int i=0; i< attr.getLength(); i++) {
				if(attr.getLocalName(i).equals(att1)) {
					id= attr.getValue(i);
				}else if(attr.getLocalName(i).equals(att2)) {
					param= attr.getValue(id);
				}
			}
			params.get(id)[paramIndex]= param;
		}
	}
		
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		new Assembler();
	}
	
	
}
				