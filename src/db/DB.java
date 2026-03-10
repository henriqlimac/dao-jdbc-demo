package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties properties = loadProperties();

                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException error) {
                throw new DbException(error.getMessage());
            }
        }

        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException error) {
                throw new DbException(error.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties properties = new Properties();

            properties.load(fis);
            return properties;
        } catch (IOException error) {
            throw new DbException(error.getMessage());
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException error) {
                throw new DbException(error.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException error) {
                throw new DbException(error.getMessage());
            }
        }
    }
}
