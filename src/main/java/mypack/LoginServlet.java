package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mySqlConnection.ConnectionProvider;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		

		try {

			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			con = ConnectionProvider.getConnection();

			PreparedStatement ps = con.prepareStatement("select *from user where email = ? and password = ?");

			ps.setString(1, email);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("WelcomeServlet");
				rd.forward(request, response);

			} else {
				out.println("invalid username and password");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
