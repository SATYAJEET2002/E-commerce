package modeldescription;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String contactName=request.getParameter("contactName");
		String contactEmail=request.getParameter("contactEmail");
		String contactMessage=request.getParameter("contactMessage");

		System.out.println(name);
		System.out.println(mobile);
		System.out.println(email);
		System.out.println(password);
		System.out.println(contactName);
		System.out.println(contactEmail);
		System.out.println(contactMessage);
		
		String action = request.getParameter("action");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","4066");
			
			
			if ("register".equals(action))
			{
				String insert="insert into userdetail values(?,?,?,?)";
				PreparedStatement ps=con.prepareStatement(insert);
				ps.setString(1, name);
				ps.setString(2, mobile);
				ps.setString(3, email);
				ps.setString(4, password);
				ps.executeUpdate();
				response.sendRedirect("index.html");
			}
			else if ("login".equals(action))
			{
			    String select = "select * from userdetail where email=? and password=?";
			    PreparedStatement ps = con.prepareStatement(select);
			    ps.setString(1, email);
			    ps.setString(2, password);
			    ResultSet rs = ps.executeQuery();
			    if (rs.next()) 
			    {
			        response.sendRedirect("index.html");
			    } 
			    else 
			    {
			        out.println("Sorry your account is not registerd :( ");
			    }

			}
			else if ("index".equals(action))
			{
			    String insert1 = "insert into query values(?,?,?)";
			    PreparedStatement ps = con.prepareStatement(insert1);
			    ps.setString(1, contactName);
			    ps.setString(2, contactEmail);
			    ps.setString(3, contactMessage);
			    ps.executeUpdate();
			    response.sendRedirect("index.html");
			}
			else
			{
				System.out.println("hello");
				System.out.println(action);
			}
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
