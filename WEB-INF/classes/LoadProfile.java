import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoadProfile extends HttpServlet{
    
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
        
		System.out.println("Loading Profile");
        HttpSession session = request.getSession(true);
        String id=session.getAttribute("username").toString();
		
		System.out.println("session id:"+id);
        
        
        try{
            if(id != null && ! id.isEmpty() ) {
                
                String jsonString=getProfile(id);
                
                response.setContentType("text");
                
                PrintWriter out = response.getWriter();
                
                out.print(jsonString);
                out.flush();
                out.close();
                
                
            }else{
                
                response.setContentType("text");
                
                PrintWriter out = response.getWriter();
                
                out.print("failed");
                out.flush();
                out.close();
                
            }
        }catch(Exception e){
            System.out.println("db user-load failed" +e);
        }
        
        
    }
    
    public String getProfile(String id) throws Exception{
        
        //logic to reference database and check username
        System.out.println("getting info for user "+id);
        String query = "SELECT * FROM Usuarios WHERE ID="+id+";";
        
        Statement stmt = con.createStatement();
        
        System.out.println("executing Statement");
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("statement executed");
        
		String nombre="";
		String apellido="";
		
        while (rs.next()) {
            nombre = rs.getString("Nombre");
            apellido = rs.getString("Apellidos");
            
            System.out.println("From DB: nombre:"+nombre+" apellido:"+apellido);
            
        }
        
        String jsonString="{\"id\":\""+id+"\",\"nombre\":\""+nombre+"\",\"apellido\":\""+apellido+"\"}";
        
        return jsonString;
    }
    
    public void destroy(){
        try{
            con.close();
        }catch (Exception e){
            System.out.println("con . close failed");
        }
    }
}