package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classs.Consultora;

public class ConsultoraDAO {
	private Connection connection;
	Scanner entrada = new Scanner(System.in);

	public ConsultoraDAO(Connection connection) {
		this.connection = connection;
	}
	//create ok read ok update !ok delete ok

	//create
	public void createTableConsultora() {
		String criarTable = "CREATE TABLE consultora (" + "Cod_consultora INT PRIMARY KEY, "
				+ "Nome_consultora VARCHAR2(255), " + "Email_consultora VARCHAR2(255), "
				+ "Telefone_consultora VARCHAR2(15), "+ "Situacao_consultora VARCHAR2(7), " + "Data_inicio DATE, " + "Data_fim DATE)";
		String criarSequencia = "CREATE SEQUENCE consultora_seq START WITH 1 INCREMENT BY 1";
		try (Statement stmt = connection.createStatement()) {
			stmt.execute(criarTable);
			// criar sequencia pra id nao repetir
			stmt.execute(criarSequencia);

			System.out.println("Tabela criada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//cria e da insert na nova consultora
	public void addConsultora(Consultora consultora) {
		String sql = "INSERT INTO consultora (Cod_consultora, Nome_consultora, Email_consultora, Telefone_consultora, Data_inicio, Data_fim) "
				+ "VALUES (consultora_seq.NEXTVAL, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, consultora.getNome_consultora());
			stmt.setString(2, consultora.getEmail_consultora());
			stmt.setString(3, consultora.getTelefone_consultora());
			stmt.setDate(4, consultora.getData_inicio());
			stmt.setDate(5, consultora.getData_fim());

			stmt.execute();
			System.out.println("Consultora adicionada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//read
	public List<Consultora> listarConsultoras() {

		List<Consultora> Consultoras = new ArrayList<Consultora>();

		try {
			String sql = "SELECT * FROM CONSULTORA";
			// prepara pra fazer a consulta
			PreparedStatement stmt = connection.prepareStatement(sql);
			// executa a consulta
			ResultSet rs = stmt.executeQuery();
			// rs = dados que retornaram pela consulta
			while (rs.next()) {
				Consultora consultora = new Consultora();
				consultora.setCod_consultora(rs.getInt(1));
				consultora.setNome_consultora(rs.getString(2));
				consultora.setEmail_consultora(rs.getString(3));
				consultora.setTelefone_consultora(rs.getString(4));
				consultora.setData_inicio(rs.getDate(5));
				consultora.setData_fim(rs.getDate(6));
				Consultoras.add(consultora);

			}
		
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return Consultoras;

	}

	//uptade
	public void update(int id, String nome, String email, String tel, Date data) {
		String sql = "UPDATE consultora SET Nome_consultora = ?, Email_consultora = ?, Telefone_consultora = ?, Data_fim = ? WHERE Cod_consultora = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, nome);
			stmt.setString(2, email);
			stmt.setString(3, tel);
			stmt.setDate(4, data);
			stmt.setInt(5, id);

			stmt.executeUpdate();
			System.out.println("Consultora atualizada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	

	//apaga somente alguma consultora (pelo id)
	public void apagarConsultora(int codConsultora) {
		String sql = "DELETE FROM consultora WHERE Cod_consultora = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, codConsultora);
			stmt.executeUpdate();

			System.out.println("Consultora excluída com sucesso.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//apaga a table
	public void dropTable() {
		String droptable = "DROP TABLE consultora";
		String dropSequencia = "DROP SEQUENCE consultora_seq";
		try {
			Statement stmt = connection.createStatement();
			stmt.execute(droptable);
			stmt.execute(dropSequencia);
			System.out.println("tabela excluida");
		} catch (SQLException e) {
			System.out.println("Erro ao excluir tabela" + e.getMessage());
			e.printStackTrace();
		}
	}
}