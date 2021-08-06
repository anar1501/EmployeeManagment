package az.company.employee.connection.concrets;

import az.company.employee.config.DbConfig;
import az.company.employee.config.DbConfigParse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private DbConnection() {
    }

    public static Connection getConnection() throws SQLException {
        DbConfig dbConfig = DbConfigParse.getDbConfig();
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
        return connection;

    }

}
