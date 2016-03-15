import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ActualizarCarpeta extends HttpServlet {
     
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("Iniciando CrearCarpeta...");
  }

  public void destroy() {
    System.out.println("No hay nada que hacer...");
  }

  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
    HttpSession session = req.getSession(true);

    
    String strNombre = req.getParameter("Nombre");
	String strNombrea= req.getParameter("Nombrea");
    String strFecha = req.getParameter("fecha");
    String strEtiqueta1 = req.getParameter("etiqueta1");
    String strEtiqueta2 = req.getParameter("etiqueta2");  
    String strEtiqueta3 = req.getParameter("etiqueta3");  

	  

    devolverPaginaHTML(resp, strNombre, strNombrea, strFecha, strEtiqueta1, strEtiqueta2,strEtiqueta3);
	}
  
    
  public void devolverPaginaHTML(HttpServletResponse resp, String Nombre, String Nombrea, String Fecha, String Etiqueta1, String Etiqueta2, String Etiqueta3) throws IOException {
			  resp.setContentType("text/html");

    PrintWriter out = null;
    try {
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("Se ha producido una excepcion");    
    }
	
		File file = new File("Carpetas.txt");
		Scanner scanner = new Scanner(file);
		String line = null;
        
        int carp;
        carp = 0;
      

		while (scanner.hasNext()) {
			line = scanner.nextLine();
			Scanner lineSc = new Scanner(line);
			lineSc.useDelimiter("\t");
			try {
				String texto = lineSc.next();
				String Nombrec = lineSc.next();
				String Fech = lineSc.next();
				String Etiquet1 = lineSc.next();
				String Etiquet2 = lineSc.next();
				String Etiquet3 = lineSc.next();
				
				if (Nombre.equals(Nombrec)){
					carp = 1;
					break;
				}
			} catch (NoSuchElementException ex) {
				System.out.println("Error en BuscarCarpeta " + ex);
			}

		}
		if (carp==1){
			    
			FileWriter fileWriter = new FileWriter("Carpetas.txt", true);
			PrintWriter toFile = new PrintWriter(fileWriter);
 
			toFile.println("> Carpeta: " + Nombrea + "\t" + Fecha + "\t" + Etiqueta1 + "\t" + Etiqueta2 + "\t" + Etiqueta3);
			fileWriter.close();
				
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>Carpeta Actualizada:</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY TEXT=\"white\" BACKGROUND=\"Fondo2.jpg\" BGPROPERTIES=\"fixed\" style=\"background-repeat: no-repeat; background-size:cover\" leftMargin  topMargin  marginheight  marginwidth>");
			out.println("<P><font size=\"5\" color=\"black\" face=\"Arial\"> <B>Nombre de Carpeta: </B><font color=\"black\">" + Nombrea + "</font></FONT>");
			out.println("<BR><font size=\"5\" color=\"black\" face=\"Arial\"> <B>Fecha ultima actualizacion: </B><font color=\"black\">" + Fecha + "</font></FONT>");
			out.println("<BR><font size=\"5\" color=\"black\" face=\"Arial\"> <B>Etiquetas: </B><font color=\"black\">" + Etiqueta1 + " / " + Etiqueta2 + " / " + Etiqueta3 + " / " + "</font></FONT>");
			out.println("</BODY>");
			out.println("<BR><a href=\"ActualizarCarpeta.html\">Actualizar mas carpetas</a>");
			out.println("<BR><a href=\"Inicio\">Ir al menu</a>");
			out.println("</html>");
			out.close();
		}else{
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>Error</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY TEXT=\"black\" BACKGROUND=\"Fondo2.jpg\" BGPROPERTIES=\"fixed\" style=\"background-repeat: no-repeat; background-size:cover\" leftMargin  topMargin  marginheight  marginwidth>");
			out.println("<P><font size=\"5\" color=\"white\" face=\"Arial\"> <B>Nombre de Carpeta: </B><font color=\"black\">" + "La carpeta seleccionada no existe en la base de datos" + "</font></FONT>");
			out.println("</BODY>");
			out.println("<BR><a href=\"ActualizarCarpeta.html\">Actualizar otra carpeta</a>");
			out.println("<BR><a href=\"Inicio\">Ir al menu</a>");
			out.println("</html>");			
			out.close();
		}

		out.flush();
		out.close();
	}	
     
  public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
  }
}