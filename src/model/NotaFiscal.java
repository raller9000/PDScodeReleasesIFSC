package model;

public class NotaFiscal {
    private Usuario usuario;
    private Carrinho carrinho;

    public NotaFiscal(Usuario usuario, Carrinho carrinho) {
        this.usuario = usuario;
        this.carrinho = carrinho;
    }

    public String gerarNota() {
        String texto = "===== NOTA FISCAL =====\n";
        texto += "Cliente: " + usuario.getNome() + " | CPF: " + usuario.getCpf() + "\n";
        texto += "Produtos:\n";
        for (Produto p : carrinho.getProdutos()) {
            texto += "- " + p.getNome() + " R$" + p.getPreco() + "\n";
        }
        texto += "Total: R$" + carrinho.calcularTotal() + "\n";
        return texto;
    }
}
