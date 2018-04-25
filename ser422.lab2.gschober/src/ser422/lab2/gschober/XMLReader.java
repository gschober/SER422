package ser422.lab2.gschober;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLReader {

	public static Map<String,Map<String,String>> XMLToSurveyAnswers(String filename) throws IOException{
		
		Map<String,Map<String,String>> surveyAnswers = new HashMap<String,Map<String,String>>();
		
		try{
			Map<String,String> userSet = new HashMap<String,String>();
			
			File inputFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("user");
			
			for(int i=0; i < nodeList.getLength(); i++){
				Node node = nodeList.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element elem = (Element)node;
					
					String username = elem.getElementsByTagName("username").item(0).getTextContent();
					userSet.put("firstname", elem.getElementsByTagName("firstname").item(0).getTextContent());
					userSet.put("lastname", elem.getElementsByTagName("lastname").item(0).getTextContent());
					userSet.put("days", elem.getElementsByTagName("days").item(0).getTextContent());
					userSet.put("languages", elem.getElementsByTagName("languages").item(0).getTextContent());
					userSet.put("sourceeditor", elem.getElementsByTagName("sourceeditor").item(0).getTextContent());
					surveyAnswers.put(username, userSet);
				}
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return surveyAnswers;
	}
	
   public static void DeleteUser(String filename,String username) throws IOException{
	   try{
		File inputFile = new File(filename);
       	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new FileInputStream(inputFile));

        Element element = (Element) doc.getElementsByTagName("user").item(0);

        element.getParentNode().removeChild(element);

        doc.normalize();
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(inputFile);
        transformer.transform(source, result);	

		} catch (Exception e){
			e.printStackTrace();
		}
		   
   }
}
