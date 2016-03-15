import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class saveDocument extends HttpServlet {

    static Scanner in = new Scanner(System.in);

	private String attached;
    private String title;
    private String d_type;
    private String date;
    private String version;
    private String area;
    private String author;
    private String code;
    private String comments;


    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //attached document
		attached = request.getParameter("attached");
        title = request.getParameter("title");
        d_type = request.getParameter("d_type");
        date = request.getParameter("date");
        version = request.getParameter("version");
        area = request.getParameter("area");
		author = request.getParameter("author");
		code = request.getParameter("code");
		comments = request.getParameter("comments");


//Rewrite 
        File file = new File("documents.txt"); 
        Scanner inFile = new Scanner(file); //to read it
        String line;
        ArrayList<String> text = new ArrayList<String>(); 
    
        while (inFile.hasNext()) {
            line = inFile.nextLine();
            text.add(line);
        }
        FileWriter fileWriter = new FileWriter("documents.txt", true);
        PrintWriter toFile = new PrintWriter(fileWriter); //to write in the .txt
		toFile.println(attached + "\t" + title + "\t" + d_type + "\t" + date + "\t" + version + "\t" + area + "\t" + author + "\t" + code + "\t" + comments);

        fileWriter.close();

// Generate the page
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>MotorSport Wiki</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<TABLE width='100%'' align='center'>");
        out.println("<TR><TD colspan=4><image src='logo.jpg' width='100%'/></TD></TR>");
		out.println("<TR><TD></TD></TR>");
		out.println("<div align='right'><p>Your document has been successfully uploaded</p>");
        out.println("</TABLE>");
        out.println("</BODY>");
        out.println("</HTML>");
        out.close();
    }
}