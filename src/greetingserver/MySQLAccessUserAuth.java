/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greetingserver;

/**
 *
 * @author SHANKAR
 */
import java.io.*;
import java.sql.*;
import javax.sql.*;
public class MySQLAccessUserAuth
{
	public boolean ConnectCheck(String username, String password) throws IOException
	{
		//res.setContentType("text/html");
		//PrintWriter out=res.getWriter();
		//HttpSession hs=req.getSession();
		//hs.setAttribute("username", username);
		//hs.setAttribute("password", password);
		try
		{
                        System.out.println("Connecting to Database");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/B5Charlar","root","root");
			 PreparedStatement ps=con.prepareStatement("select * from userauth where username=? and password=?");
			 ps.setString(1,username);
			 ps.setString(2,password);
			 ResultSet rs=ps.executeQuery();
			 if(rs.next())
			 {
				 System.out.println("Username and password found");
                                 return true;
			 }
			 else
			 {
                                 System.out.println("The Password or Username You Entered is Incorrect");
                                 return false;
			 }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
                return false;
	}
}