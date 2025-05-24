package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class DB {
    public static void start() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            Statement stmt = conn.createStatement();
            BufferedReader reader = new BufferedReader(new FileReader("src\\resources\\database.sql"));

            StringBuilder sql = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty() && !linha.startsWith("--")) {
                    sql.append(linha);
                    if (linha.endsWith(";")) {
                        stmt.execute(sql.toString());
                        sql.setLength(0);
                    }
                }
            }

            System.out.println("Banco de dados inicializado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
