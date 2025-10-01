package view;

import javax.swing.*;
import java.awt.event.*;
import controlador.ProdutoControlador;
import controlador.CompraControlador;
import model.Produto;
import model.Usuario;
import java.util.List;
import java.util.Vector;

public class LoginTelaWindow extends JFrame {

    private Usuario usuario;
    private JTextField tfIdProduto;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnFinalizar;
    private JList<String> listaCarrinho;
    private DefaultListModel<String> listModel;
    private ProdutoControlador produtoCtrl = new ProdutoControlador();
    private CompraControlador compraCtrl = new CompraControlador();

    public LoginTelaWindow(Usuario u) {
        this.usuario = u;
        setTitle("Caixa - Cliente: " + u.getNome());
        setLayout(null);
        setSize(520, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lbl = new JLabel("Caixa - adicione produtos pelo ID");
        lbl.setBounds(140, 10, 300, 25);
        add(lbl);

        JLabel lId = new JLabel("ID Produto:");
        lId.setBounds(20, 50, 80, 25);
        add(lId);

        tfIdProduto = new JTextField();
        tfIdProduto.setBounds(100, 50, 120, 25);
        add(tfIdProduto);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(240, 50, 110, 25);
        add(btnAdicionar);

        btnRemover = new JButton("Remover Selecionado");
        btnRemover.setBounds(360, 50, 140, 25);
        add(btnRemover);

        listModel = new DefaultListModel<>();
        listaCarrinho = new JList<>(listModel);
        JScrollPane sp = new JScrollPane(listaCarrinho);
        sp.setBounds(20, 90, 480, 200);
        add(sp);

        btnFinalizar = new JButton("Finalizar Compra");
        btnFinalizar.setBounds(180, 305, 160, 30);
        add(btnFinalizar);

        JButton btnListar = new JButton("Mostrar Produtos");
        btnListar.setBounds(20, 305, 140, 30);
        add(btnListar);

        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(tfIdProduto.getText().trim());
                    boolean ok = compraCtrl.adicionarPorId(id);
                    if (!ok) {
                        JOptionPane.showMessageDialog(null, "Produto não existe ou sem estoque");
                    } else {
                        Produto p = produtoCtrl.buscarPorId(id);
                        // show the added product in cart
                        listModel.addElement(p.getId() + " - " + p.getNome() + " R$" + p.getPreco());
                    }
                    tfIdProduto.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido");
                }
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idx = listaCarrinho.getSelectedIndex();
                if (idx >= 0) {
                    String linha = listModel.get(idx);
                    try {
                        String idStr = linha.split(" - ")[0];
                        int id = Integer.parseInt(idStr);
                        Produto encontrado = null;
                        for (Produto p : compraCtrl.getCarrinho().getProdutos()) {
                            if (p.getId() == id) {
                                encontrado = p;
                                break;
                            }
                        }
                        if (encontrado != null) {
                            compraCtrl.remover(encontrado);
                            listModel.remove(idx);
                            JOptionPane.showMessageDialog(null, "Removido do carrinho (nota: estoque não é restaurado nesta versão)");
                        } else {
                            JOptionPane.showMessageDialog(null, "Produto não encontrado no carrinho");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao remover");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um item na lista");
                }
            }
        });

        btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (compraCtrl.getCarrinho().getProdutos().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Carrinho vazio");
                    return;
                }
                String nota = compraCtrl.finalizar(usuario);
                JTextArea ta = new JTextArea(nota);
                ta.setEditable(false);
                JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Nota Fiscal", JOptionPane.INFORMATION_MESSAGE);
                listModel.clear();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Produto> lista = produtoCtrl.listarProdutos();
                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (Produto p : lista) {
                    sb.append(p.toString()).append("\n");
                }
                JTextArea ta = new JTextArea(sb.toString());
                ta.setEditable(false);
                JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Produtos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(430, 10, 80, 25);
        add(btnLogout);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaLogin login = new TelaLogin();
                login.setVisible(true);
                dispose();
            }
        });
    }
}
