package greetingserver;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
 
public class JAXBUserAuthUnmarshall {
        String username;
        String password;
        String Id;
        String To;
        String From;
	public boolean UnMarshall(String msg,String id) throws IOException{
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
                username=ua.getUsername();
                password=ua.getPassword();
                Id=ua.getId();
                From=ua.getFrom();
                To=ua.getTo();
                MySQLAccessUserAuth msaua=new MySQLAccessUserAuth();
                Valid=msaua.ConnectCheck(ua.getUsername(), ua.getPassword());
                String IdByClient=ua.getId();
                if((Valid==true)&&(id.equals(IdByClient)))
                {
                    System.out.println("Valid User");
                    return true;
                }
                else
                {
                    System.out.println("Not a valid user");
                    return false;
                }
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
 return false;
	}
        public void SessionDetails(String Ip) throws IOException
        {
            StoreClientDetails scd= new StoreClientDetails();
            scd.StoreIp(username, Id, Ip);
        }
        public String GetData() throws IOException
        {
            return username;
        }
}