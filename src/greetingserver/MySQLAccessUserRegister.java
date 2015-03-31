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
public class MySQLAccessUserRegister
{
	public boolean ConnectRegister(String fullname, String lastname, String dob, String email, String password) throws IOException
	{
		//res.setContentType("text/html");
		//PrintWriter out=res.getWriter();
		//HttpSession hs=req.getSession();
		//hs.setAttribute("username", username);
		//hs.setAttribute("password", password);
		try
		{
                    boolean flag1=false;
                    boolean flag2=false;
                    boolean flag3=false;
                        System.out.println("Connecting to Database for Registering User");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/B5Charlar","root","root");
			 PreparedStatement ps=con.prepareStatement("insert into userdetails values(?,?,?,?,?)");
			 ps.setString(1,fullname);
			 ps.setString(2,lastname);
                         ps.setString(3,dob);
                         ps.setString(4,email);
                         ps.setString(5,password);
                         int updateCount=ps.executeUpdate();
                         if(updateCount>0)
                         {
                             flag1=true;
                         }
                         System.out.println("Updated the details to userdetails table");
                         PreparedStatement ps2=con.prepareStatement("insert into userauth values(?,?)");
			 ps2.setString(1,email);
			 ps2.setString(2,password);
                         int updateCount1=ps2.executeUpdate();
                         if(updateCount1>0)
                         {
                             flag2=true;
                         }
                         System.out.println("Updated the details to userauth table");
                         PreparedStatement ps3=con.prepareStatement("insert into friendstatus values(?,?,?)");
                         ps3.setString(1,email);
                         ps3.setString(2,"default");
                         ps3.setString(3,"I'm using B5Charlar");
                         int updateCount2=ps3.executeUpdate();
                         if(updateCount2>0)
                         {
                             flag3=true;
                         }
                         System.out.println("Updated the details to the friendstatus");
                         if((flag1&&flag2&&flag3)==true)
                         {
                             return true;
                         }
                         else 
                         {
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