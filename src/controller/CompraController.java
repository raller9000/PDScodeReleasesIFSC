package controller;

import model.Carrinho;
import model.Produto;
import model.Usuario;
import model.NotaFiscal;

public class CompraController {

    private Carrinho carrinho = new Carrinho();

    public boolean adicionarPorId(int id) {
        ProdutoController produtoCtrl = new ProdutoController();
        Produto produto = produtoCtrl.buscarPorId(id);
        if (produto == null || produto.getEstoque() <= 0) {
            return false;
        }
        getCarrinho().adicionarProduto(produto);
        produto.setEstoque(produto.getEstoque() - 1);
        return true;
    }

    public void adicionar(Produto p) {
        carrinho.adicionarProduto(p);
    }

    public void remover(Produto p) {
        carrinho.removerProduto(p);
    }

    public double verTotal() {
        return carrinho.calcularTotal();
    }

    public String finalizar(Usuario u) {
        NotaFiscal nf = new NotaFiscal(u, carrinho);
        return nf.gerarNota();
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }
}
