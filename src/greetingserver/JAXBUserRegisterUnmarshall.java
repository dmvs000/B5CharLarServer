package greetingserver;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
 
public class JAXBUserRegisterUnmarshall {
        String fullname;
        String lastname;
        String dob;
        String email;
        String password;
	public boolean UnMarshall(String msg) throws IOException{
            //String message3="";
            boolean Valid = false;
	 try {
 
		//File file = new File("C:\\Users\\SHANKAR\\Desktop\\Test\\xml.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(UserRegister.class);
                StringReader reader = new StringReader(msg);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		//UserAuth ua = (UserAuth) jaxbUnmarshaller.unmarshal(file);
                UserRegister ua = (UserRegister) jaxbUnmarshaller.unmarshal(reader);
                //message3 = message2.getNewString();
                System.out.println(ua.getFullName());
                System.out.println(ua.getLastName());
                System.out.println(ua.getDOB());
                System.out.println(ua.getEmail());
                System.out.println(ua.getPassword());
                fullname=ua.getFullName();
                password=ua.getPassword();
                lastname=ua.getLastName();
                dob=ua.getDOB();
                email=ua.getEmail();
                MySQLAccessUserRegister msaur=new MySQLAccessUserRegister();
                Valid=msaur.ConnectRegister(fullname, lastname, dob, email, password);
                //String IdByClient=ua.getId();
                if(Valid==true)
                    return true;
                else
                    return false;
                            
                
 	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
 return false;
	}
        
}