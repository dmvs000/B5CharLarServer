/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author NANI
 */
public class createparserxml {
  public static void main(String args[]) {
       String arr[]={"vivek","online","busy","shankar","offline","battery died"};
int i=0,j=0;
int len=0;
len=arr.length;
      try {
         DocumentBuilderFactory dbFactory =
         DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = 
            dbFactory.newDocumentBuilder();
         Document doc = dBuilder.newDocument();
         // root element
         Element rootElement = doc.createElement("roster");
         doc.appendChild(rootElement);

         //  friendinfo element
for(j=0;j<len;j++){
    if(j%3==0){

    Element friendinfo = doc.createElement("friendinfo");
         rootElement.appendChild(friendinfo);

         // setting attribute to element
         
for(i=j;i<j+3;i++){
         // name,presence,status elements
         if(i%3==0){
         Element name = doc.createElement("name");
         
         name.appendChild(
         doc.createTextNode(arr[i]));
         friendinfo.appendChild(name);
         }
         if(i%3==1){
         Element presence = doc.createElement("presence");
         
         presence.appendChild(
         doc.createTextNode(arr[i]));
         friendinfo.appendChild(presence);
         }
         if(i%3==2){
         Element status = doc.createElement("status");
         
         status.appendChild(
         doc.createTextNode(arr[i]));
         friendinfo.appendChild(status);
}
}
    }}
         // write the content into xml file
         TransformerFactory transformerFactory =
         TransformerFactory.newInstance();
         Transformer transformer =
         transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         StreamResult result =
         new StreamResult(new File("C:\\Users\\NANI\\Desktop\\input.xml"));
         transformer.transform(source, result);
         // Output to console for testing
         StreamResult consoleResult =
         new StreamResult(System.out);
         transformer.transform(source, consoleResult);
      } catch (ParserConfigurationException | DOMException | TransformerException e) {
         e.printStackTrace();
      }
   }  
}
