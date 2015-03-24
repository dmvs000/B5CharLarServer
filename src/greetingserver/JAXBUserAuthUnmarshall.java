package greetingserver;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
 
public class JAXBUserAuthUnmarshall {
	public void UnMarshall(String msg,String id) throws IOException{
            //String message3="";
            boolean Valid = false;
	 try {
 
		//File file = new File("C:\\Users\\SHANKAR\\Desktop\\Test\\xml.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(UserAuth.class);
                StringReader reader = new StringReader(msg);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		//UserAuth ua = (UserAuth) jaxbUnmarshaller.unmarshal(file);
                UserAuth ua = (UserAuth) jaxbUnmarshaller.unmarshal(reader);
                //message3 = message2.getNewString();
                System.out.println(ua.getTo());
                System.out.println(ua.getFrom());
                System.out.println(ua.getUsername());
                System.out.println(ua.getPassword());
                System.out.println(ua.getId());
                MySQLAccessUserAuth msaua=new MySQLAccessUserAuth();
                Valid=msaua.ConnectCheck(ua.getUsername(), ua.getPassword());
                String IdByClient=ua.getId();
                if((Valid==true)&&(id.equals(IdByClient)))
                {
                    System.out.println("Valid User");
                    
                }
                else
                {
                    System.out.println("Not a valid user");
                }
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
 
	}
}