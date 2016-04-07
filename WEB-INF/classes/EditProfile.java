import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EditProfile extends HttpServlet{
    
    Connection con;
    public void init(){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            System.out.println("creating connection....");
            con = DriverManager.getConnection("jdbc:odbc:TMSwiki");
            System.out.println("connection created");
        }catch (Exception e){
            System.out.println("db connection failed");
        }
        
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String newID=request.getParameter("userID");
        String nombre=request.getParameter("nombre");
        String apellido=request.getParameter("apellido");
        
		HttpSession session = request.getSession();
        String id=session.getAttribute("username").toString();
        
        try{
            if(id != null && ! id.isEmpty() ) {
                
                saveChanges(id,newID, nombre, apellido);
                System.out.println("saved changes");
				
                response.setContentType("text");
                
                PrintWriter out = response.getWriter();
                out.print("succeeded");
                out.flush();
                out.close();
                
                
            }else{
                
				System.out.println("Not saving changes: null user");
                response.setContentType("text");
                
                PrintWriter out = response.getWriter();
                
                out.print("failed");
                out.flush();
                out.close();
                
            }
        }catch(Exception e){
            System.out.println("db validation failed");
            System.out.println(e);
            
            response.setContentType("text");
            PrintWriter out = response.getWriter();
            out.print("failed");
            out.flush();
            out.close();
        }
        
        
    }

    
    private void saveChanges(String id, String newID, String nombre, String apellido) throws Exception{
                
        System.out.println("saving changes");
        
        String query = "UPDATE Usuarios SET ID='" + newID + "',Nombre='" +nombre + "',Apellidos='" +apellido + "' WHERE ID="+id;
        
		System.out.println("SQL: "+query);
		
        Statement stmt = con.createStatement();
        
        System.out.println("executing Update Statement");
        int rs = stmt.executeUpdate(query);
        System.out.println("statement executed");
        
		//LoadProfile.getProfile();
    }
    
    public void destroy(){
        try{
            con.close();
        }catch (Exception e){
            System.out.println("con . close failed");
        }
    }
}