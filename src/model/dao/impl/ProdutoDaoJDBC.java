package model.dao.impl;

import db.ConnectionFactory;
import model.dao.ProdutoDao;
import model.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn;

    public ProdutoDaoJDBC() throws SQLException {
        this.conn = ConnectionFactory.getConnection();
    }

    @Override
    public void inserir(Produto p) {
        try {
            String sql = "INSERT INTO produtos (nome, preco, descricao) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setString(3, p.getDescricao());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Produto p) {
        try {
            String sql = "UPDATE produtos SET nome = ?, preco = ?, descricao = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setString(3, p.getDescricao());
            ps.setInt(4, p.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    @Override
    public void deletar(Integer ID) {
        try {
            String sql = "DELETE FROM produtos WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    @Override
    public Produto procurarPorID(Integer ID) {
        try {
            String sql = "SELECT * FROM produtos WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("descricao")
                );
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Produto> ListarProdutos() {
        List<Produto> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produtos";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                lista.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("descricao")
                ));
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        return lista;
    }
}
