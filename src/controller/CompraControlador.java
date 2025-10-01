package controller;

import model.Carrinho;
import model.Produto;
import model.Usuario;
import model.NotaFiscal;

public class CompraControlador {

    private Carrinho carrinho = new Carrinho();

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
