import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class Logout extends HttpServlet{

		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("logging out...");

		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("logout successful");
		
		System.out.println("redirecting to login page");
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		out.print("logout");
		out.flush();
		out.close();
		

    }

}