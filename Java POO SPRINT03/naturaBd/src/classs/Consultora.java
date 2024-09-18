package classs;

import java.sql.Date;

public class Consultora {
	private int Cod_consultora;
	private String Nome_consultora;
	private String Email_consultora;
	private String Telefone_consultora;
	private Date Data_inicio;
	private Date Data_fim;

	public Consultora(int cod_consultora, String nome_consultora, String email_consultora, String telefone_consultora,
			Date data_inicio, Date data_fim) {

		Cod_consultora = cod_consultora;
		Nome_consultora = nome_consultora;
		Email_consultora = email_consultora;
		Telefone_consultora = telefone_consultora;
		Data_inicio = data_inicio;
		Data_fim = data_fim;
	}
	
	public Consultora() {};

	public int getCod_consultora() {
		return Cod_consultora;
	}

	public void setCod_consultora(int cod_consultora) {
		Cod_consultora = cod_consultora;
	}

	public String getNome_consultora() {
		return Nome_consultora;
	}

	public void setNome_consultora(String nome_consultora) {
		Nome_consultora = nome_consultora;
	}

	public String getEmail_consultora() {
		return Email_consultora;
	}

	public void setEmail_consultora(String email_consultora) {
		Email_consultora = email_consultora;
	}

	public String getTelefone_consultora() {
		return Telefone_consultora;
	}

	public void setTelefone_consultora(String telefone_consultora) {
		Telefone_consultora = telefone_consultora;
	}

	public Date getData_inicio() {
		return Data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		Data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return Data_fim;
	}

	public void setData_fim(Date data_fim) {
		Data_fim = data_fim;
	}
}
