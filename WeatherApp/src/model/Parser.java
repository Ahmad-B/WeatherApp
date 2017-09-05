/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//RESOLVES THE WOEID OF GIVEN PLACE
public class Parser {

 public static String parseMe (String location) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{



URL xmlURL = new URL("http://where.yahooapis.com/v1/places.q('" + location + "')?appid=[dj0yJmk9T21oZzBlbTFzaGZJJmQ9WVdrOU9IcDFZbkZGTXpnbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD02Mg--]");
InputStream xml = xmlURL.openStream();
DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
DocumentBuilder db = dbf.newDocumentBuilder();
Document doc = db.parse(xml);
doc.getDocumentElement().normalize();
NodeList nList = doc.getElementsByTagName("place");
Node nNode = nList.item(0);
Element eElement = (Element) nNode;
String returnString = eElement.getElementsByTagName("woeid").item(0).getTextContent();
xml.close();
return returnString; 
 }
}