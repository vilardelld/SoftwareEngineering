import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class CheckSession extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		System.out.println("CheckSession got the username: "+username);
        if(username==null){
			System.out.println("redirecting to login page");
			response.setContentType("text");
            PrintWriter out = response.getWriter();
            out.print("");
            out.flush();
            out.close();
		
		}
		else{
		//let them stay on the page
			//System.out.println("stay here");

		    response.setContentType("text");
            PrintWriter out = response.getWriter();
            out.print(username);
            out.flush();
            out.close();
		}
        
    }

}