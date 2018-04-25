package ser422.lab2.gschober;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;

public class XMLWriter {
	
   public static void NewEntryToXML(String filename,Map<String,String> data)throws IOException{

	   try{
		File inputFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		Element root = doc.getDocumentElement();		
		
		Element user = doc.createElement("user");
		
		Element username = doc.createElement("username");
		username.appendChild(doc.createTextNode(data.get("firstname")+data.get("lastname")));
		user.appendChild(username);
		
		Element firstname = doc.createElement("firstname");
		firstname.appendChild(doc.createTextNode(data.get("firstname")));
		user.appendChild(firstname);

		Element lastname = doc.createElement("lastname");
		lastname.appendChild(doc.createTextNode(data.get("lastname")));
		user.appendChild(lastname);

		Element sourceeditor = doc.createElement("sourceeditor");
		sourceeditor.appendChild(doc.createTextNode(data.get("sourceeditor")));
		user.appendChild(sourceeditor);

		Element days = doc.createElement("days");
		days.appendChild(doc.createTextNode(data.get("days")));
		user.appendChild(days);

		Element languages = doc.createElement("languages");
		languages.appendChild(doc.createTextNode(data.get("languages")));
		user.appendChild(languages);
		
		root.appendChild(user);
		
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(inputFile);
        transformer.transform(source, result);	
                
	   }catch(Exception e){
		   
		   e.printStackTrace();

	   }		
	}
   

}

