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
public class MySQLAccessRequestRoster
{
	public void ConnectCheck(String username) throws IOException
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
			 PreparedStatement ps=con.prepareStatement("select * from friends where username=?");
			 ps.setString(1,username);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next())
			 {
                                System.out.println(rs.getString("friendname"));        
			 }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
                System.out.println("MySQLAccessRequestRoster Closed");
	}
}