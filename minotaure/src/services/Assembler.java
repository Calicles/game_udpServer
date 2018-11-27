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
	private Map<String, String> injections;
	private Map<String, String> images;
	private Map<String, int[]> param;
	
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
			
		Class<?> injectClass= Class.forName(injections.get(id));
		Constructor<?> injectConstruct= injectClass.getConstructor();
		Object inject= injectConstruct.newInstance();
			
		Class<?> idClass= Class.forName(id_class.get(id));
			
		if(images.containsKey(id)) {
			int[] p= param.get(id);
			String img= images.get(id);
			
			Constructor<?> id_construct= idClass.getConstructor(img.getClass(), p.getClass());
			res= id_construct.newInstance(img, p);
		}else {
			Constructor<?> id_construct= idClass.getConstructor(injectClass.getInterfaces());
			res= id_construct.newInstance(inject);
		}
		return res;
	}
	
	
	class XMLHandler extends DefaultHandler {
		
		@Override
		public void startElement(String nameSpaceURI, String lName, String qName,
				Attributes attr) throws SAXException {
		
			if(qName.equals("character")) {
				register(attr, "type", "class");
			}else if(qName.equals("injection")) {
				register(attr, "for", "class");
			}else if(qName.equals("images")) {
				register(attr, "key", "src");
			}else if(qName.equals("position")) {
				register(attr, "key", "x", "y");
			}
			
		}
	}
	public void register(Attributes attr, String att1, String att2, String att3) {
		String id=null; 
		int[] tab= new int[2];
		
		for(int i= 0; i<attr.getLength(); i++) {
			if(attr.getLocalName(i).equals(att1)) {
				id= attr.getValue(id);
			}else if(attr.getLocalName(i).equals(att2)) {
				tab[0]= Integer.parseInt(attr.getValue(i));
			}else if(attr.getLocalName(i).equals(att3)) {
				tab[1]= Integer.parseInt(attr.getValue(id));
			}
		}
		param.put(id, tab);
	}
	public void register(Attributes attr, String att1, String att2) {
		String id= null, classe= null;
		for(int i=0; i< attr.getLength(); i++) {
			if(attr.getLocalName(i).equals(att1)) {
				id= attr.getValue(i);
			}else if(attr.getLocalName(i).equals(att2)) {
				classe= attr.getValue(id);
			}
		}
		injections.put(id, classe);
	}
		
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		new Assembler();
	}
	
}
				