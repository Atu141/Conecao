package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import classs.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void createTableProduto() {
		String sql = "CREATE TABLE Produto (\r\n" + "    Cod_Produto INTEGER PRIMARY KEY,\r\n"
				+ "    Nome_Produto VARCHAR2(50),\r\n" + "    Descricao_Produto VARCHAR2(500),\r\n"
				+ "    Preco NUMBER(8, 2)\r\n" + ", estoque NUMBER (100)) ";
		String produto_seq = "CREATE SEQUENCE produto_seq START WITH 1 INCREMENT BY 1";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			System.out.println("Tabela produto criada");
			stmt = connection.prepareStatement(produto_seq);
			stmt.execute();
			System.out.println("Sequencia produto criada");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void dropTableProduto() {
		String dropTable = "DROP TABLE produto";
		String dropSeq = "DROP SEQUENCE produto_seq";

		try {
			PreparedStatement stmt = connection.prepareStatement(dropTable);
			stmt.execute();
			System.out.println("Tabela dropada com sucesso");
			stmt = connection.prepareStatement(dropSeq);
			stmt.execute();
			System.out.println("Sequencia dropada com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// le a table inteira
	public void readTable() {
		String sql = "SELECT * FROM produto";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			System.out.println("Table lida");
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//atualiza produto pelo id
	public void atualizarProduto(String nome, String desc, double preco,int estoque, int id) {
		String atualizarProduto = "UPDATE produto SET Nome_Produto = ?, Descricao_Produto = ?, Preco = ?, estoque = ? WHERE cod_produto = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(atualizarProduto);
			stmt.setString(1, nome);
			stmt.setString(2, desc);
			stmt.setDouble(3, preco);
			stmt.setInt(4, estoque);
			stmt.setInt(5, id);

			stmt.executeUpdate();
			System.out.println("Produto atualizado com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizarProdutoVenda(int qtdComprada, int id) {
		String atualizarProduto = "UPDATE produto SET estoque = estoque - ? WHERE cod_produto = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(atualizarProduto);
			stmt.setInt(1, qtdComprada);
			stmt.setInt(2, id);

			stmt.executeUpdate();
			System.out.println("Produto atualizado no sistema!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//retorna lista de produto da table
	public List<Produto> listarProduto() {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			String listagemProduto = "SELECT * FROM Produto";
			PreparedStatement stmt = connection.prepareStatement(listagemProduto);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setCod_Produto(rs.getInt(1));
				produto.setNome_Produto(rs.getString(2));
				produto.setDesc_Produto(rs.getString(3));
				produto.setPreco_Produto(rs.getDouble(4));
				produto.setEstoque(rs.getInt(5));
				produtos.add(produto);

			}
			stmt.execute();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;
	}

//insert em produto
	public void insertProduto(Produto produto) {
		String insert = "INSERT INTO Produto (Cod_Produto, Nome_Produto, Descricao_Produto, Preco, Estoque)"
				+ "VALUES (produto_seq.NEXTVAL, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setString(1, produto.getNome_Produto());
			stmt.setString(2, produto.getDesc_Produto());
			stmt.setDouble(3, produto.getPreco_Produto());
			stmt.setInt(4, produto.getEstoque());
			stmt.execute();
			System.out.println("produto adicionado (sql)");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

//deletar produto da table
	public void deleteProduto(int id) {
		String dropProduto = "DELETE FROM produto WHERE Cod_Produto = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(dropProduto);
			stmt.setInt(1, id);
			int funcionou = stmt.executeUpdate();
			if (funcionou > 0) {
				System.out.println("Produto deletado");
			} else {
				System.out.println("Nenhum produto encontrado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
