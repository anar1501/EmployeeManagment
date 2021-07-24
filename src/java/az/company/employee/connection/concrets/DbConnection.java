/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.company.employee.connection.concrets;

import az.company.employee.config.DbConfig;
import az.company.employee.config.DbConfigParse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NAZIM
 */
public class DbConnection {

    private DbConnection() {
    }

    public static Connection getConnection() throws SQLException {
        DbConfig dbConfig = DbConfigParse.getDbConfig();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
        return connection;

    }

}
