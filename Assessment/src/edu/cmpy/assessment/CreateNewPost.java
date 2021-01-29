package edu.cmpy.assessment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CreateNewPost extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		String title=req.getParameter("title");
		String text=req.getParameter("text");
		
	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dburl="jdbc:mysql://localhost:3306/assessment_db";
			Connection con=DriverManager.getConnection(dburl,"root","root");
			
			String query="insert into create_table values(?,?)";
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, text);
			int count=pstmt.executeUpdate();
			out.println(count+"rows affected");
			if(count!=0)
			{
				out.print("<h1>data inserted</h1>");
			}
			
		
		} 
		catch (ClassNotFoundException e)
		{
			out.println(e.getMessage());
		} 
		catch (SQLException e)
		{
			out.println(e.getMessage());
		}


	}
}
