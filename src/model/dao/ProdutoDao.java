package model.dao;

import model.entities.Produto;

import java.util.List;

public interface ProdutoDao {

    void inserir(Produto p);
    void atualizar(Produto p);
    void deletar(Integer ID);
    Produto procurarPorID(Integer ID);
    List<Produto> ListarProdutos();
}
