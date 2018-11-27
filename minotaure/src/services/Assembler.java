package services;

import java.io.IOException;
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
	
	private Map<String, String> injections;	//gestion d'un cache
	private Map<String, String> methods;
	
	public Assembler() throws SAXException, IOException, ParserConfigurationException {
		injections= new HashMap<>();
		methods= new HashMap<>();
		parse();
	}
	
	public void parse() throws SAXException, IOException, ParserConfigurationException{
		SAXParserFactory factory= SAXParserFactory.newInstance();
		SAXParser parser= factory.newSAXParser();
		parser.parse("ressources/config/conf.xml", new XMLHandler());
	/*	saxReader.setContentHandler(new DefaultHandler() {
			public void startElement(String uri, String name, String qualif, Attributes att) 
			throws SAXException {
				String application= null, inject= null, method= null;
				if(name.equals("injection")) {
					for(int i=0; i<att.getLength(); i++) {
						if(att.getLocalName(i).equals("apllication")) {
							application= att.getValue(i);
						}
						if(att.getLocalName(i).equals("inject")) {
							inject= att.getValue(i);
						}
					}
				}
				injections.put(application, inject);
				methods.put(inject, method);
			}
		}); */
	}
	/*
	private Object injection(String applicationName, String injectionName) throws ClassNotFoundException {
		Object object= null;
		Class<?> appli= Class.forName(applicationName);
		Class<?> injection= Class.forName(injectionName);
		
		
		java.lang.reflect.Method m= appli.getDeclaredMethod("setService", ServiceI.class);
		object= appli.newInstance();
		m.invoke(object, injection.newInstance());
		return object;
	}
	
	public Object newInstance(Class<?> application) throws ClassNotFoundException {
		return newInstance(application.getName());
	}
	
	private Object newInstance(String applicationName) throws ClassNotFoundException {
		String injectionName= injections.get(applicationName);
		String methodName= methods.get(injectionName);
		return injection(applicationName, injectionName);
	}
	*/
	class XMLHandler extends DefaultHandler {
		
		private String node= null;
		
		@Override
		public void startDocument() {
			System.out.println("Start");
		}
		
		@Override
		public void characters(char[] data, int start, int end) {
			System.out.println("***************");
			String str= new String(data, start, end);
			System.out.println("donnée du noeud : "+node+" :  "+str);
		}
		
		@Override
		public void startElement(String nameSpaceURI, String lName, String qName,
				Attributes attr) throws SAXException {
			System.out.println("----------------------------------");
			System.out.println("qName: "+qName);
			node= qName;
			if(attr != null) {
				for(int i=0; i<attr.getLength(); i++) {
					String aName= attr.getLocalName(i);
					
					System.out.println("attribute: "+aName+"; value: "+attr.getValue(i));
				}
			}
			
		}
		
		@Override
		public void endDocument() throws SAXException {
			System.out.println("fin");
		}
	}
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		new Assembler();
	}
}
				