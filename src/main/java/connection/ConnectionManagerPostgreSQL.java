package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerPostgreSQL implements ConnectionManager{
    private static final ConnectionManagerPostgreSQL INSTANCE = new ConnectionManagerPostgreSQL();
    private ConnectionManagerPostgreSQL() {
        try {
            Class.forName("org.postgresql.Driver");
            connection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/umalog", "postgres", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static synchronized ConnectionManagerPostgreSQL getInstance(){
        return INSTANCE;
    }

    private Connection connection;

    public Connection getConnection(){
        return connection;
    }
}
