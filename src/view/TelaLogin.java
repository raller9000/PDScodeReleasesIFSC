package view;

import javax.swing.*;
import java.awt.event.*;
import controller.LoginController;
import model.Usuario;

public class TelaLogin extends JFrame {
    private JTextField tfNome;
    private JTextField tfCpf;
    private JButton btnEntrar;
    private JButton btnCadastrar;
    private LoginController loginCtrl = new LoginController();

    public TelaLogin() {
        setTitle("Login - Supermercado");
        setLayout(null);
        setSize(360, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblBem = new JLabel("Bem-vindo ao Supermercado");
        lblBem.setBounds(70, 10, 220, 25);
        add(lblBem);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 50, 80, 25);
        add(lblNome);

        tfNome = new JTextField();
        tfNome.setBounds(100, 50, 200, 25);
        add(tfNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 85, 80, 25);
        add(lblCpf);

        tfCpf = new JTextField();
        tfCpf.setBounds(100, 85, 200, 25);
        add(tfCpf);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(60, 130, 100, 30);
        add(btnEntrar);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(180, 130, 120, 30);
        add(btnCadastrar);

        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = tfNome.getText().trim();
                String cpf = tfCpf.getText().trim();

                if (nome.isEmpty() || cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha nome e CPF");
                    return;
                }

                Usuario u = loginCtrl.fazerLogin(nome, cpf);
                if (u == null) {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado ou dados incorretos");
                    return;
                }

                if (u.isAdmin()) {
                    TelaMain adm = new TelaMain(u);
                    adm.setVisible(true);
                    dispose();
                } else {
                    LoginTelaWindow caixa = new LoginTelaWindow(u);
                    caixa.setVisible(true);
                    dispose();
                }
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCadastro cadastro = new TelaCadastro();
                cadastro.setVisible(true);
                dispose();
            }
        });
    }
}
