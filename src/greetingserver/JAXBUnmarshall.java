package greetingserver;
import java.io.File;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
 
public class JAXBUnmarshall {
    String To;
            String From;
            String Body;
            String Lang;
            String Id;
	public void UnMarshall(String msg) {
            
 
	 try {
 
		//File file = new File("C:\\Users\\SHANKAR\\Desktop\\Test\\xml.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
                StringReader reader = new StringReader(msg);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		//Message message = (Message) jaxbUnmarshaller.unmarshal(file);
                Message message = (Message) jaxbUnmarshaller.unmarshal(reader);
                //message3 = message2.getNewString();
                System.out.println(message.getTo());
                System.out.println(message.getFrom());
                System.out.println(message.getBody());
                System.out.println(message.getLang());
                System.out.println(message.getId());
                To=message.getTo();
                From=message.getFrom();
                Body=message.getBody();
                Lang=message.getLang();
                Id=message.getId();
		//System.out.println(message3);
 
	  } catch (JAXBException e) {
                System.out.println("Unmarshalling Exception at JAXBUnmarshall Server Side");
		e.printStackTrace();
	  }
 
	}
        public String getTo()
        {
                return To;
        }
        public String getFrom()
        {
            return From;
        }
        public String getBody()
        {
            return Body;
        }
}