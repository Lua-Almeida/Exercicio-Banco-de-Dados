package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {

	static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Connection conectar() throws SQLException{
        String servidor = "localhost";
        String porta = "3306";
        String database = "JavaBD";
        return DriverManager.getConnection("jdbc:mysql://"+servidor+":"+porta+"/"+database+""
                + "?useTimezone=true&serverTimezone=UTC", "root","");
    }
}
