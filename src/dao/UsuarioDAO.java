package dao;

import java.sql.*;
import model.Usuario;

public class UsuarioDAO {

    public void inserir(Usuario u) {
        String sql = "INSERT INTO usuarios (nome, cpf, email, senha, admin) VALUES (?,?,?,?,?)";
        try (Connection c = FabricaConexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getCpf());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getSenha());
            ps.setBoolean(5, u.isAdmin());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Usuario buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        try (Connection c = FabricaConexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getBoolean("admin")
                );
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
}
    