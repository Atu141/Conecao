package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classs.Cliente;

public class ClienteDAO {

	private Connection connection;

	public ClienteDAO(Connection connection) {
		this.connection = connection;
	}
//cria table
	public void createTableCliente() {
		String criarTable = "CREATE TABLE Cliente (\r\n" + "    Cod_Cliente NUMBER PRIMARY KEY,\r\n"
				+ "    Nome_Cliente VARCHAR2(50),\r\n" + "    Email_Cliente VARCHAR2(100),\r\n"
				+ "    Telefone_Cliente VARCHAR2(11),\r\n" + "    Endereco VARCHAR2(50),\r\n"
				+ "    Consultora_Cod_Consultora NUMBER\r\n" + ")";
		String criarSeq = "CREATE SEQUENCE cliente_seq START WITH 1 INCREMENT BY 1";

		try {
			Statement stmt = connection.createStatement();
			stmt.execute(criarTable);
			System.out.println("Table criada com sucesso");
			stmt.execute(criarSeq);
			System.out.println("Sequencia criada com sucesso");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
//dropa cliente de sua table
	public void dropTableCliente() {
		String dropTable = "DROP TABLE cliente";
		String dropSeq = "DROP SEQUENCE cliente_seq";

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
	//le a table inteira
	public void readTable() {
		String readTable = "SELECT * FROM cliente";
		try {
			PreparedStatement stmt = connection.prepareStatement(readTable);
			System.out.println("Table lida");
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//atualiza cliente pelo id
	public void atualizarCliente(int id, String Nome, String Email, String Telefone, String Endereco,
			int ConsultoraId) {
		String atualizarCliente = "UPDATE cliente SET Nome_cliente = ?, Email_Cliente = ?, Telefone_Cliente = ?, Endereco =?, Consultora_Cod_Consultora = ? WHERE cod_cliente = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(atualizarCliente);
			stmt.setString(1, Nome);
			stmt.setString(2, Email);
			stmt.setString(3, Telefone);
			stmt.setString(4, Endereco);
			stmt.setInt(5, ConsultoraId);
			stmt.setInt(6, id);
			stmt.executeUpdate();
			System.out.println("Cliente atualizado com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//retorna lista de clientes da table
	public List<Cliente> listarCliente() {
		List<Cliente> arraysClientes = new ArrayList<Cliente>();
		try {
			String listagemSql = "SELECT * FROM Cliente";
			PreparedStatement stmt = connection.prepareStatement(listagemSql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCod_Cliente(rs.getInt(1));
				cliente.setNome_cliente(rs.getString(2));
				cliente.setEmail_cliente(rs.getString(3));
				cliente.setTelefone_cliente(rs.getString(4));
				cliente.setEndereco_cliente(rs.getString(5));
				cliente.setCod_consultora(rs.getInt(6));
				arraysClientes.add(cliente);

			}
			stmt.execute();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arraysClientes;
	}
//insert em cliente
	public void insertTableCliente(Cliente cliente) {
		String insert = "INSERT INTO Cliente (Cod_cliente, " + "Nome_cliente, " + "Email_cliente,"
				+ "Telefone_cliente, " + "Endereco, " + "Consultora_Cod_Consultora)"
				+ " VALUES(cliente_seq.NEXTVAL,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setString(1, cliente.getNome_cliente());
			stmt.setString(2, cliente.getEmail_cliente());
			stmt.setString(3, cliente.getTelefone_cliente());
			stmt.setString(4, cliente.getEndereco_cliente());
			stmt.setInt(5, cliente.getCod_consultora());
			stmt.execute();
			System.out.println("cliente adicionado (sql)");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
//deletar cliente da table
	public void deleteCliente(int id) {
		String dropCliente = "DELETE FROM Cliente WHERE Cod_Cliente = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(dropCliente);
			stmt.setInt(1, id);
			int funcionou = stmt.executeUpdate();
			if (funcionou > 0) {
				System.out.println("Cliente deletado");
			} else {
				System.out.println("Nenhum cliente encontrado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
