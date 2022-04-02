package dbConnect;

import java.sql.*;

public class Connexion {
    // private static String URL = "jdbc:sqlite:D:\\LP GL\\_STN\\RMI_app_repartie\\src\\main\\resources\\librairie.db";

    private static String URL = "jdbc:sqlite::resource:librairie.db";
    //private static String URL = "jdbc:sqlite:../../../../resources/librairie.db";
    //private static String URL = "jdbc:sqlite:D:\\LP GL\\_STN\\RMI_app_repartie\\src\\main\\resources\\librairie.db";
    //private static String URL = "jdbc:sqlite:librairie.db";

    private static String DRIVER = "org.sqlite.JDBC";

    public static Connection createConnexion() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}  