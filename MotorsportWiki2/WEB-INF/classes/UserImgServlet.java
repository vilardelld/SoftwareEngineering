import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserImgServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String name=request.getParameter("username");
        response.setContentType("text");
        PrintWriter out = response.getWriter();
        
        if(name.equalsIgnoreCase("sam")){
            
            //Return img of Sam
            //response.setContentType("image/jpg");
            //File img = new File("sam.jpg");
            //writeImg(img, response);
            out.write("sam.jpg");
            
        }else if (name.equalsIgnoreCase("bernie")){
            
            //Return img of Bernie Sanders
            //response.setContentType("image/jpg");
            //File img = new File("/bernie.jpeg");
            //writeImg(img, response);
            out.write("bernie.jpeg");
            
        } else{
            //no image
            
            out.print("no image");
            out.flush();
            out.close();
            
        }
        
    }
    
    public void writeImg(File img,HttpServletResponse response){
        
        ServletOutputStream outStream = null;
        //PrintWriter out = response.getWriter();
        
        DataInputStream inStream=null;
        byte[] arrayOfBytes;
        try
        {
            outStream=response.getOutputStream();
            inStream = new DataInputStream(new FileInputStream(img));
            arrayOfBytes = new byte[(int) img.length()];

            inStream.readFully(arrayOfBytes);
            
            //String imageDataString = Base64.encodeBase64URLSafeString(imageByteArray);
            //out.write(ImageDataString);
            outStream.write(arrayOfBytes);
            outStream.close();
            

        }
        catch (Exception e)
        {
            arrayOfBytes = null;
        }
        finally
        {
            try{
            inStream.close();
            }
            catch (Exception f){
             //failed to close;
            }
        }
        

    }
    
}