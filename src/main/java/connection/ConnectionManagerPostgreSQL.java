package connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerPostgreSQL implements ConnectionManager{

    private static final ConnectionManagerPostgreSQL INSTANCE = new ConnectionManagerPostgreSQL();

    private static final Logger logger = Logger.getLogger(ConnectionManagerPostgreSQL.class);


    private ConnectionManagerPostgreSQL() {
        try {
            Class.forName("org.postgresql.Driver");
            connection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/umalog", "postgres", "");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
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
