package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {

    public void inserir(Produto p) {
        String sql = "INSERT INTO produtos (nome, preco, estoque) VALUES (?,?,?)";
        try (Connection c = FabricaConexao.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setInt(3, p.getEstoque());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection c = FabricaConexao.getConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("estoque"));
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }

    public Produto buscarPorId(int id) {
        for (Produto p : listar()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

}
