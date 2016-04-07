import java.sql.*;
class basicJDBC {
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        // 1. Connect
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection connection = DriverManager.getConnection("jdbc:odbc:basic");
        
        // 2. Query
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("Select titulo, autor from datos");
        
        // 3. Result
        while(result.next()) {
            System.out.print(  result.getString("titulo")+", ");
            System.out.println(result.getString("autor"));
        }
        
        connection.close();
    }
} 