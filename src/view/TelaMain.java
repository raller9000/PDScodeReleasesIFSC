package view;

import javax.swing.*;
import java.awt.event.*;
import controller.ProdutoControlador;
import model.Produto;
import model.Usuario;
import java.util.List;

public class TelaMain extends JFrame {

    private Usuario usuario;
    private JButton btnCadastrarProduto;
    private JButton btnListar;
    private JButton btnLogout;
    private ProdutoControlador produtoCtrl = new ProdutoControlador();

    public TelaMain(Usuario u) {
        this.usuario = u;
        setTitle("Painel Admin - " + u.getNome());
        setLayout(null);
        setSize(420, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lb = new JLabel("Painel do Administrador");
        lb.setBounds(120, 10, 200, 25);
        add(lb);

        btnCadastrarProduto = new JButton("Cadastrar Produto");
        btnCadastrarProduto.setBounds(40, 50, 150, 40);
        add(btnCadastrarProduto);

        btnListar = new JButton("Listar Produtos");
        btnListar.setBounds(220, 50, 150, 40);
        add(btnListar);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(140, 110, 120, 35);
        add(btnLogout);

        btnCadastrarProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField tfNome = new JTextField();
                JTextField tfPreco = new JTextField();
                JTextField tfEstoque = new JTextField();
                Object[] fields = {
                    "Nome:", tfNome,
                    "Preco:", tfPreco,
                    "Estoque:", tfEstoque
                };
                int option = JOptionPane.showConfirmDialog(null, fields, "Cadastrar Produto", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        String nome = tfNome.getText().trim();
                        double preco = Double.parseDouble(tfPreco.getText().trim());
                        int estoque = Integer.parseInt(tfEstoque.getText().trim());
                        produtoCtrl.cadastrarProduto(nome, preco, estoque);
                        JOptionPane.showMessageDialog(null, "Produto cadastrado");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Dados inválidos");
                    }
                }
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

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaLogin login = new TelaLogin();
                login.setVisible(true);
                dispose();
            }
        });
    }
}

