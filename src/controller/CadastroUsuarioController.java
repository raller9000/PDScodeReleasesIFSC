package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class CadastroUsuarioController {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void cadastrar(String nome, String cpf, String email, String senha, boolean admin) {
        Usuario novo = new Usuario(0, nome, cpf, email, senha, admin);
        usuarioDAO.inserir(novo);
    }
}
