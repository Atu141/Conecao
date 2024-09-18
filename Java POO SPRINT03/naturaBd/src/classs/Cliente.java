package classs;

public class Cliente {

	private int Cod_Cliente;
	private String nome_cliente;
	private String email_cliente;
	private String telefone_cliente;
	private String endereco_cliente;
	private int cod_consultora;

	public Cliente(int cod_Cliente, String nome_cliente, String email_cliente, String telefone_cliente,
			String endereco_cliente, int cod_consultora) {
		super();
		Cod_Cliente = cod_Cliente;
		this.nome_cliente = nome_cliente;
		this.email_cliente = email_cliente;
		this.telefone_cliente = telefone_cliente;
		this.endereco_cliente = endereco_cliente;
		this.cod_consultora = cod_consultora;
	}

	public Cliente() {
	}

	public int getCod_Cliente() {
		return Cod_Cliente;
	}

	public void setCod_Cliente(int cod_Cliente) {
		Cod_Cliente = cod_Cliente;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public String getEmail_cliente() {
		return email_cliente;
	}

	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}

	public String getTelefone_cliente() {
		return telefone_cliente;
	}

	public void setTelefone_cliente(String telefone_cliente) {
		this.telefone_cliente = telefone_cliente;
	}

	public String getEndereco_cliente() {
		return endereco_cliente;
	}

	public void setEndereco_cliente(String endereco_cliente) {
		this.endereco_cliente = endereco_cliente;
	}

	public int getCod_consultora() {
		return cod_consultora;
	}

	public void setCod_consultora(int cod) {
		this.cod_consultora = cod;
	}

}
