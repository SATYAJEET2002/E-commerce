package modeldescription;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Addtocart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String itemName = request.getParameter("itemName");
        double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));


        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","4066");
            // Insert data into the database
           
			String query = "INSERT INTO cart (itemName, itemPrice, quantity) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, itemName);
            pstmt.setDouble(2, itemPrice);
            pstmt.setInt(3, quantity);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0)
            {
                out.println("<h2>Item added to cart successfully!</h2>");
            } 
            else 
            {
                out.println("<h2>Failed to add item to cart!</h2>");
            }

            con.close();
        } 
        catch (Exception e) 
        {
            out.println(e);
        }

        request.getRequestDispatcher("cart.jsp").forward(request, response);

        out.close();
    }

}
