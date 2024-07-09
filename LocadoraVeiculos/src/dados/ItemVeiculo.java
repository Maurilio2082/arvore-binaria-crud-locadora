package dados;

public class ItemVeiculo {

	private int chave;
	private String nome;
	private String marca;
	private String cor;
	private int anoFabricacao;
	private int quilometragem;
	private boolean locado;
	private ItemLocatario locatario;

	public ItemVeiculo(int ch, String nome, String marca, String cor, int anoFabricacao, int quilometragem) {
		this.chave = ch;
		this.nome = nome;
		this.marca = marca;
		this.cor = cor;
		this.anoFabricacao = anoFabricacao;
		this.quilometragem = quilometragem;
		this.locado = false;
		this.locatario = null;

	}

	public ItemVeiculo(int ch) {
		this.chave = ch;
	}

	public void setChave(int ch) {
		this.chave = ch;
	}

	public int getChave() {
		return this.chave;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getAnoFabricacao() {
		return this.anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public int getQuilometragem() {
		return this.quilometragem;
	}

	public void setQuilometragem(int quilometragem) {
		this.quilometragem = quilometragem;
	}

	public boolean getLocado() {
		return this.locado;
	}

	public void setLocado(boolean locado) {
		this.locado = locado;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String modelo) {
		this.nome = modelo;
	}

	public ItemLocatario getLocatario() {
		return this.locatario;
	}

	public void setLocatario(ItemLocatario locatario) {
		this.locatario = locatario;
	}

}
