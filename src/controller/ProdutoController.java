package controller;

import dao.ProdutoDAO;
import model.Produto;
import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public void cadastrarProduto(String nome, double preco, int estoque) {
        Produto p = new Produto(0, nome, preco, estoque);
        produtoDAO.inserir(p);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listar();
    }

    public Produto buscarPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }

    public void atualizarEstoque(int id, int novo) {
        Produto p = produtoDAO.buscarPorId(id);
        if (p != null) {
            p.setEstoque(novo);
        }
    }
}
