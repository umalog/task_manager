package connection;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPoolPostgreSql implements ConnectionManager {
    private Connection conn;

    public ConnectionPoolPostgreSql() {
        try {
            this.conn = setupDataSource().getConnection();
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
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
    public Connection getConnection () {
        return conn;
    }
}