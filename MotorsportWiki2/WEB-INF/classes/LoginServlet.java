import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class LoginServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String name=request.getParameter("username");
        String pass=request.getParameter("password");
        
        if(validate(name,pass)){
            
            //Log user into the system. Redirect to main page.
            response.setContentType("text");
            PrintWriter out = response.getWriter();
            
            out.print("succeeded");
            out.flush();
            out.close();
            
        }else{
            //login failed
            
            response.setContentType("text");
            PrintWriter out = response.getWriter();
            
            out.print("failed");
            out.flush();
            out.close();

        }
        
        
    }
    
    public static boolean validate(String name, String pass){
        
        boolean status=false;
        
        //logic to reference database and check username
        if(name!=null && name!="" && pass!=null && pass!=""){
            status=true;
        }
        return status;
    }
}