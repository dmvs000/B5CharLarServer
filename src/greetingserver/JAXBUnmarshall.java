package greetingserver;
import java.io.File;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
 
public class JAXBUnmarshall {
	public void UnMarshall(String msg) {
            String message3="";
 
	 try {
 
		File file = new File("C:\\Users\\SHANKAR\\Desktop\\Test\\xml.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
                StringReader reader = new StringReader(msg);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Message message = (Message) jaxbUnmarshaller.unmarshal(file);
                Message message2 = (Message) jaxbUnmarshaller.unmarshal(reader);
                //message3 = message2.getNewString();
                System.out.println(message.getTo());
                System.out.println(message.getFrom());
                System.out.println(message.getBody());
                System.out.println(message.getLang());
		System.out.println(message3);
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
 
	}
}