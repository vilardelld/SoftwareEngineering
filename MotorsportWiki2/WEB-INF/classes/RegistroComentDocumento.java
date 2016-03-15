import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegistroComentDocumento extends HttpServlet {
     
 
  public void init(ServletConfig config) throws ServletException {
   
    super.init(config);
    System.out.println("Iniciando RegistroComentario...");
  } 
  public void destroy() {
    System.out.println("No hay nada que hacer...");
  } 
          
  
  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    
	String strCodigo = req.getParameter("codigo");
    String strUsuario = req.getParameter("usuario");
	String strComentario = req.getParameter("comentario");
	
    FileWriter fileWriter = new FileWriter("Comentarios.txt", true);
    PrintWriter toFile = new PrintWriter(fileWriter);
    toFile.println(strCodigo+ "\t" + strUsuario + "\t" + strComentario);
    fileWriter.close();
   
    devolverPaginaHTML(resp, strCodigo, strUsuario, strComentario);
  } 
    
  public void devolverPaginaHTML(HttpServletResponse resp,
    String codigo, String usuario, String comentario) {
    
    resp.setContentType("text/html");


    PrintWriter out = null;
    try {
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("Se ha producido una excepcion");    
    }
        

    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Comment</TITLE>");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<B><FONT size=+2>Comentario </FONT></B>");
	out.println("<BR><FONT size=+1><B>Codigo: </B>" + codigo + "</FONT>");
    out.println("<BR><FONT size=+1><B>Usuario: </B>" + usuario + "</FONT>");
	out.println("<P><FONT size=+1><B>Comentario: </B>" + comentario + "</FONT>");
	out.println("</BODY>");
    out.println("<BR><a href=\"Inicio.html\">Volver a la pagina de inicio</a>");

    out.println("</HTML>");

   
    out.flush();
    out.close();
  } 
  public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
  } 
}