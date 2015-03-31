/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greetingserver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author SHANKAR
 */
public class MySQLAccessChangeStatus {
    public boolean ConnectCheck(String username,String status) throws IOException
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
			 PreparedStatement ps=con.prepareStatement("update friendstatus set status = ? where username = ?");
			 ps.setString(2,username);
                         ps.setString(1,status);
			 int updateCount=ps.executeUpdate();
                         if(updateCount>0)
                         {
                             System.out.println("Successfully changed the status of friendstatus table");
                         }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
                return false;
	}
}
