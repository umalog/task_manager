package connection;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPoolPostgreSql implements ConnectionManager {
    private Connection conn;

    private static final Logger logger = Logger.getLogger(ConnectionPoolPostgreSql.class);

    private static PoolingDataSource<PoolableConnection> pds;

    static {

        try {
            Class.forName("org.h2.Driver");
            ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                    "jdbc:postgresql://localhost:5432/umalog", "postgres", "");
            PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
                    connectionFactory, null);
            ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
            poolableConnectionFactory.setPool(connectionPool);
            pds = new PoolingDataSource<>(connectionPool);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    private static DataSource setupDataSource() throws ClassNotFoundException {

        Class.forName("org.h2.Driver");
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                "jdbc:postgresql://localhost:5432/umalog", "postgres", "");
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
                connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        return new PoolingDataSource<>(connectionPool);
    }


    @Override
    public Connection getConnection() {
        try {
            return pds.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}