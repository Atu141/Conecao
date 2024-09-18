package classs;

public class Produto {

	private int cod_Produto;
	private String nome_Produto;
	private String desc_Produto;
	private double preco_Produto;
	private int estoque;

	

	public Produto(int cod_Produto, String nome_Produto, String desc_Produto, double preco_Produto, int estoque) {
		super();
		this.cod_Produto = cod_Produto;
		this.nome_Produto = nome_Produto;
		this.desc_Produto = desc_Produto;
		this.preco_Produto = preco_Produto;
		this.estoque  =  estoque;
	}

	public Produto() {
	}

	public int getCod_Produto() {
		return cod_Produto;
	}

	public void setCod_Produto(int cod_Produto) {
		this.cod_Produto = cod_Produto;
	}

	public String getNome_Produto() {
		return nome_Produto;
	}

	public void setNome_Produto(String nome_Produto) {
		this.nome_Produto = nome_Produto;
	}

	public String getDesc_Produto() {
		return desc_Produto;
	}

	public void setDesc_Produto(String desc_Produto) {
		this.desc_Produto = desc_Produto;
	}

	public double getPreco_Produto() {
		return preco_Produto;
	}

	public void setPreco_Produto(double preco_Produto) {
		this.preco_Produto = preco_Produto;
	}
	
	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

}
