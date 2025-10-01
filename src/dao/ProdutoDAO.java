package dao;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produto p) {
        String sql = "INSERT INTO produtos (nome, preco, estoque) VALUES (?,?,?)";
        try (Connection c = FabricaConexao.abrirConexao();
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
        try (Connection c = FabricaConexao.abrirConexao();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("estoque")
                );
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }
}
