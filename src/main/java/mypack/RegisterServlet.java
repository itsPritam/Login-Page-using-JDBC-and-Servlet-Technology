package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mySqlConnection.ConnectionProvider;

public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into user values (?,?,?)");

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, pass);

			int i = ps.executeUpdate();

			if (i > 0)

				// out.println("Registration Successfull");

				response.sendRedirect("index.html");
			else
				out.println("Registration failed");
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
