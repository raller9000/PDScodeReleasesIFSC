package view;

import javax.swing.*;
import java.awt.event.*;
import controller.CadastroUsuarioController;

public class TelaCadastro extends JFrame {
    private JTextField tfNome, tfCpf, tfEmail;
    private JPasswordField pfSenha;
    private JCheckBox cbAdmin;
    private JButton btnSalvar;
    private CadastroUsuarioController ctrl = new CadastroUsuarioController();

    public TelaCadastro() {
        setTitle("Cadastro de Usuário");
        setLayout(null);
        setSize(380, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lbl = new JLabel("Cadastro");
        lbl.setBounds(150, 10, 80, 25);
        add(lbl);

        JLabel ln = new JLabel("Nome:");
        ln.setBounds(20, 40, 80, 25);
        add(ln);
        tfNome = new JTextField();
        tfNome.setBounds(100, 40, 240, 25);
        add(tfNome);

        JLabel lc = new JLabel("CPF:");
        lc.setBounds(20, 75, 80, 25);
        add(lc);
        tfCpf = new JTextField();
        tfCpf.setBounds(100, 75, 240, 25);
        add(tfCpf);

        JLabel le = new JLabel("Email:");
        le.setBounds(20, 110, 80, 25);
        add(le);
        tfEmail = new JTextField();
        tfEmail.setBounds(100, 110, 240, 25);
        add(tfEmail);

        JLabel ls = new JLabel("Senha:");
        ls.setBounds(20, 145, 80, 25);
        add(ls);
        pfSenha = new JPasswordField();
        pfSenha.setBounds(100, 145, 240, 25);
        add(pfSenha);

        cbAdmin = new JCheckBox("É administrador?");
        cbAdmin.setBounds(100, 180, 200, 25);
        add(cbAdmin);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120, 215, 100, 30);
        add(btnSalvar);

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = tfNome.getText().trim();
                String cpf = tfCpf.getText().trim();
                String email = tfEmail.getText().trim();
                String senha = new String(pfSenha.getPassword()).trim();
                boolean admin = cbAdmin.isSelected();

                if (nome.isEmpty() || cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome e CPF são obrigatórios");
                    return;
                }

                ctrl.cadastrar(nome, cpf, email, senha, admin);
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");

                TelaLogin login = new TelaLogin();
                login.setVisible(true);
                dispose();
            }
        });
    }
}
