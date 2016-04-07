import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MostrarComent extends HttpServlet {
 
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("Iniciando MostrarComent...");
  }
        
  public void destroy() {
    System.out.println("No hay nada que hacer...");
  }
  
  
  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    devolverPaginaHTML(resp);
  }
    
  public void devolverPaginaHTML(HttpServletResponse resp) 
      throws IOException {
    resp.setContentType("text/html");
    PrintWriter out = null;
    try {
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("Se ha producido una excepcion");    
    }
        
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Valores recogidos en el formulario</TITLE>");
    out.println("</HEAD>");
    out.println("<BODY background =Imagenes/fondo.jpg>");
    File file = new File("comentarios.txt");
    Scanner scanner = new Scanner(file);

    String line = null;
    String strout = "";
	out.println(strout);
	strout +="<P align=\"center\" font size=24 bgcolor=white>";
	strout +="<B>";
	strout +="Comentarios de apoyo sobre los documentos";
	strout +="</B>";
	strout +="</font>";
	strout +="</P>";
	strout += "<P ALIGN=\"left\">";
	strout +="<B>";
	strout +="COMENTARIOS";
	strout +="</B>";
	strout +="</P>";
    strout += "<TABLE BGCOLOR=\"WHITE\" border=3 >";
    strout += "<TR>";
    strout += "<TH>";
    strout += "Codigo";
    strout += "</TH>";
    strout += "<TH>";
    strout += "Usuario";
    strout += "</TH>";
    strout += "<TH>";
    strout += "Comentario";
    strout += "</TH>";
    strout += "</TR>";
    while (scanner.hasNext()) {
        line = scanner.nextLine();
        Scanner lineSc = new Scanner(line);
        lineSc.useDelimiter("\t");
        try {
            String codigo = lineSc.next();
            String usuario = lineSc.next();
            String comentario = lineSc.next();
            strout += "<TR>";
            strout += "<TD>";
            strout += codigo;
            strout += "</TD>";
            strout += "<TD>";
            strout += usuario;
            strout += "</TD>";
            strout += "<TD>";
            strout += comentario;
            strout += "</TD>";
        } catch (NoSuchElementException ex) {
            System.out.println("Error en MostrarComent " + ex);
        }

    }
    strout += "</TABLE>";
	out.println(strout);
	
	
    out.println("<BR><a href=\"Inicio.html\">Ir al menu</a>");
	strout += "</P>";
    out.println("</BODY>");
    out.println("</HTML>");

    out.flush();
    out.close();
  }
     
  public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
  } 
}