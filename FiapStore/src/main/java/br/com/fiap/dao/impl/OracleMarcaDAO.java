package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.dao.MarcaDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.Marca;

public class OracleMarcaDAO implements MarcaDAO {
    private Connection conexao;

    @Override
    public void cadastrar(Marca marca) throws DBException {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "INSERT INTO TB_Marca (NOME_Marca, PAIS_ORIGEM, DESCRICAO) VALUES (?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, marca.getNome());
            stmt.setString(2, marca.getPaisOrigem());
            stmt.setString(3, marca.getDescricao());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conexao != null)
                    conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(Marca marca) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "UPDATE TB_Marca SET NOME_Marca = ?, PAIS_ORIGEM = ?, DESCRICAO = ? WHERE ID_Marca = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, marca.getNome());
            stmt.setString(2, marca.getPaisOrigem());
            stmt.setString(3, marca.getDescricao());
            stmt.setInt(4, marca.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conexao != null)
                    conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remover(int id) throws DBException {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "DELETE FROM TB_Marca WHERE ID_Marca = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conexao != null)
                    conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Marca buscar(int id) {
        Marca marca = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "SELECT * FROM TB_Marca WHERE ID_Marca = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int codigo = rs.getInt("ID_Marca");
                String nome = rs.getString("NOME_Marca");
                String paisOrigem = rs.getString("PAIS_ORIGEM");
                String descricao = rs.getString("DESCRICAO");

                marca = new Marca(nome, paisOrigem, codigo, descricao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (rs != null)
                    rs.close();
                if (conexao != null)
                    conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return marca;
    }

    @Override
    public List<Marca> listar() {
        List<Marca> lista = new ArrayList<Marca>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "SELECT * FROM TB_Marca";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("ID_Marca");
                String nome = rs.getString("NOME_Marca");
                String paisOrigem = rs.getString("PAIS_ORIGEM");
                String descricao = rs.getString("DESCRICAO");

                Marca marca = new Marca(nome, paisOrigem, codigo, descricao);
                lista.add(marca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (rs != null)
                    rs.close();
                if (conexao != null)
                    conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

}