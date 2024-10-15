
package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

public class OracleUsuarioDAO implements UsuarioDAO {


	Connection conexao = ConnectionFactory.getInstance().getConnection();
	@Override
	public boolean validarUsuario(Usuario usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			stmt = conexao.prepareStatement("SELECT * FROM TB_USUARIO WHERE EMAIL = ? AND SENHA = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			rs = stmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void inserirUsuario(Usuario usuario) {
		String sql = "insert into tb_usuario(email, senha) values (?, ?)";
		try {
			
	
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, usuario.getEmail());
		stmt.setString(2, usuario.getSenha());
		stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public OracleUsuarioDAO getUsuarioDAO() {
		return null;
	}
}
