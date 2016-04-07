import java.io.*;
import java.util.*;
 
import javax.servlet.*;
import javax.servlet.http.*;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import java.sql.*;

public class UploadServlet extends HttpServlet {
   
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 100 * 1024;
   private int maxMemSize = 8 * 1024;
   private File file;

   Connection con;
			

   public void init( ){
      // Get the file location where it would be stored.
	ServletContext servletContext = getServletContext();
	String relativeWebPath = "img";
	String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
		
	filePath = absoluteDiskPath+"/";
	//System.out.println("path: "+filePath);
	
	try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("creating connection....");
			con = DriverManager.getConnection("jdbc:odbc:TMSwiki");
			System.out.println("connection created");
		}catch (Exception e){
			System.out.println("db connection failed");
		}
   }
   public void doPost(HttpServletRequest request, 
               HttpServletResponse response)
              throws ServletException, java.io.IOException {

	  isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
	  
      if( !isMultipart ){
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      factory.setSizeThreshold(maxMemSize);
      factory.setRepository(new File("img"));

      ServletFileUpload upload = new ServletFileUpload(factory);
      upload.setSizeMax( maxFileSize );

      try{ 
		  List fileItems = upload.parseRequest(request);
		  Iterator i = fileItems.iterator();

		  String nameOfFile="";
		  while ( i.hasNext () ) 
		  {
			 FileItem fi = (FileItem)i.next();
			 if ( !fi.isFormField () )	
			 {
				// Get the uploaded file parameters
				String fieldName = fi.getFieldName();
				String fileName = fi.getName();
				nameOfFile= fileName;
				
				String contentType = fi.getContentType();
				boolean isInMemory = fi.isInMemory();
				long sizeInBytes = fi.getSize();
				// Write the file
				if( fileName.lastIndexOf("\\") >= 0 ){
				   file = new File( filePath + 
				   fileName.substring( fileName.lastIndexOf("\\"))) ;
				}else{
				   file = new File( filePath + 
				   fileName.substring(fileName.lastIndexOf("\\")+1)) ;
				}
				fi.write( file ) ;
				out.println("Uploaded Filename: " + fileName + "<br>");
			 }
		}

		//database: change imagenURL to filename
		System.out.println("file name: "+nameOfFile);
		String name=request.getParameter("username");

		String filePath="img/"+nameOfFile;
		HttpSession session = request.getSession();
		String id=session.getAttribute("username").toString();
		String query = "UPDATE Usuarios SET imagenURL='"+filePath+"' WHERE ID="+id;
		
		System.out.println("SQL: "+query);
		
		Statement stmt = con.createStatement();
		
		System.out.println("executing Update img Statement");
		int rs = stmt.executeUpdate(query);
		System.out.println("img update executed");
 

        } catch (SQLException ex) {
            //ex.printStackTrace();
            System.out.println("SQL Error: " + ex.getMessage());
        } catch (IOException ex) {
            //ex.printStackTrace();
            //System.out.println("IO Error: " + ex.getMessage());
        }catch(Exception ex) {
       System.out.println(ex);
   }
}
	
		
	public void destroy(){
		try{
			con.close();
		}catch (Exception e){
			System.out.println("con . close failed");
		}
	}
}