package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
    private static final String CONFIG_FILE = "src\\resources\\db.properties";

    public static Connection getConnection() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(CONFIG_FILE));

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }
}
