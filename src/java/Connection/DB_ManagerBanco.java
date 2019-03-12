package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_ManagerBanco {

    private Connection connection;

    public DB_ManagerBanco() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/libreria_puercos_banco";

            this.connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Conexion establecida a la base de datos");
        } catch (Exception e) {
            System.out.println("Error al acceder a la base de datos ");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
