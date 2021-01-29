package edu.cmpy.assessment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchPost extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		String title=req.getParameter("title");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String dburl="jdbc:mysql://localhost:3306/assessment_db";
			Connection con=DriverManager.getConnection(dburl,"root","root");
			String s1="select * from create_table where title=?";
			PreparedStatement pstmt=con.prepareStatement(s1);
			pstmt.setString(1, title);
			
			
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				out.println("<h1>title:  "+rs.getString(1)+"</h1>");
				out.println("<h1>text:  "+rs.getString(2)+"</h1>");

		
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
