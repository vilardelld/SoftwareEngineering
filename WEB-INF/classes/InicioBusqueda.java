import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;



@SuppressWarnings("serial")
public class InicioBusqueda extends HttpServlet {
    
	Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:TMSwiki";
            connection=DriverManager.getConnection(url); 
        } catch(Exception e) {
            e.printStackTrace();
        }
}


    public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	res.setContentType("text/html");
    PrintWriter toClient = res.getWriter();	
		
	toClient.println("	<HTML>");


	toClient.println("<HEAD>");
	toClient.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
	toClient.println("<TITLE>TECNUN MOTORSPORT</TITLE>");
	toClient.println("<script type=\"text/javascript\" src=\"funciones.js\"> </script>");
	toClient.println("</HEAD>");

	toClient.println("<BODY onload=\"Ocultar('formulario');Ocultar('botonBuscar'); Ocultar('textarea');Ocultar('select');Ocultar('select2')\" >");

	toClient.println("<ul class=\"topnav\">");
	toClient.println("<li><a href=\"#home\">TECNUN MOTORSPORT</a></li>");
	toClient.println(" <li><a href=\"#home\">Home</a></li>");
	toClient.println("<li><a href=\"InicioBusqueda\">Busquedas</a></li>");
	toClient.println("<li><a href=\"#contact\">Alumnos</a></li>");
	toClient.println("<li><a href=\"#about\">Coches</a></li>");
	toClient.println("<li class=\"icon\">");
	toClient.println(" <a href=\"javascript:void(0);\" onclick=\"myFunction()\">&#9776;</a>");
	toClient.println("</li>");
	toClient.println("</ul>");
	
	
	
	
	toClient.println("<BR>");
	
	toClient.println("<H1 align=\"centre\"> REALICE SU BUSQUEDA </H1>");
	
	  
	
	
	
	toClient.println("<P>Seleccione tipo de busqueda   </P> ");
	
	toClient.println("<P><input class=\"btn\" type=\"button\" value=\"BUSCAR POR:\" onclick=\"Mostrar('formulario')\"  ></P> ");
	
	
	
	toClient.println("<div id='formulario'> <FORM action=\"Busquedas3\" method=\"GET\"   >");
	
	toClient.println("	<P>");
	toClient.println("		 Seleccione tipo de busqueda");
	toClient.println("		<BR> ");
	toClient.println("			<INPUT type=\"radio\" id=\"tipo\" name=\"tipo\" value=\"PorCarpeta\" id=\"PorCarpeta\" onclick=\"Mostrar('select');Mostrar('botonBuscar');Ocultar('fecha');Ocultar('nombre')\">POR CARPETA");
	toClient.println("		<BR> ");
				
	toClient.println("			<INPUT type=\"radio\" id=\"tipo\" name=\"tipo\" value=\"PorFecha\" onclick=\"Mostrar('select2'); Mostrar('botonBuscar');Ocultar('carpeta');Ocultar('nombre')\">POR FECHA ");
	toClient.println("		<BR> ");
				
	toClient.println("			<INPUT type=\"radio\" id=\"tipo\" name=\"tipo\" value=\"PorNombre\" onclick=\"Mostrar('textarea'); Mostrar('botonBuscar');Ocultar('carpeta');Ocultar('fecha')\">POR NOMBRE ");
		
	toClient.println("	</div >");
	
	toClient.println("<div id='DatosBuscar'>");
	toClient.println("		 <BR>");
	toClient.println("	<TEXTAREA id='textarea' name=\" DatosBuscar \" rows=\"1\" cols=\"50\" onClick=\"this.value=\"\";Mostrar('botonBuscar');Limpiar('textarea')\" >Introduzca su busqueda </TEXTAREA> ");

	toClient.println("	</div >");
	
	
	toClient.println("	<div id='select'>	<Select name=\"Carpetas\"   > ");
	String sql = "SELECT DISTINCT Nombre FROM Carpetas";
	System.out.println(sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
				
					String carpeta=result.getString("Nombre");
				toClient.println("	<OPTION VALUE=" + carpeta + ">&nbsp" + carpeta + "</OPTION>");
				 
				} 
				}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Resulset: " + sql + " Exception: " + e);
				}
       
		toClient.println("	</SELECT>");
		toClient.println("</div>");
		
	toClient.println("	<div id='select2'>	<Select name=\"Fechas\"    > ");
	String sql2 = "SELECT DISTINCT Temporada FROM Documento";
	System.out.println(sql2);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql2);
            while(result.next()) {
				
				String temporada=result.getString("Temporada");
				toClient.println("	<OPTION VALUE=" + temporada + ">&nbsp" + temporada + "</OPTION>");
				 
				} 
				}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Resulset: " + sql2 + " Exception: " + e);
				}
       
		toClient.println("	</SELECT>");
	
	toClient.println("</div>");
	
	toClient.println("	<P>");
	toClient.println("	<div id=\"botonBuscar\" class=\"btn\">	<INPUT type=\"submit\" id=\"botonBuscar\" name=\"botonBuscar\" value=\"BUSCAR\"></div>");
	toClient.println("	</P>");
	toClient.println("</font  >");
	toClient.println("</FORM>");
	
	toClient.println("<div id=\"paginacion\" class=\"paginacion\">");
	toClient.println("<span class=\"izquierda\">&laquo; Inicio</span>");
	toClient.println("<span href=\"InicioBusqueda\" class=\"derecha\">Busqueda &raquo;</span>");
    toClient.println("</div>");
		
	toClient.println("</BODY>");
	toClient.println("</HTML>");
		
	}
		
	
	}
