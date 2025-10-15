package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class LoginController {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario fazerLogin(String cpf, String senha) {
        Usuario u = usuarioDAO.buscarPorCpf(cpf);
        if (u != null) {
            if (u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}
