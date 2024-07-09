package dados;

public class ItemLocatario {

	private int chave;
	private String nome;
	private int idade;
	private String vencimentoCnh;
	private boolean locado;

	public ItemLocatario(int ch, String nome, int idade, String vencimentoCnh) {
		this.chave = ch;
		this.nome = nome;
		this.idade = idade;
		this.vencimentoCnh = vencimentoCnh;
		this.locado = false;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ItemLocatario(int ch) {
		this.chave = ch;
	}

	public void setChave(int ch) {
		this.chave = ch;
	}

	public int getChave() {
		return this.chave;
	}

	public String getVencimentoCnh() {
		return this.vencimentoCnh;
	}

	public void setVencimentoCnh(String vencimentoCnh) {
		this.vencimentoCnh = vencimentoCnh;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public boolean getLocado() {
		return this.locado;
	}

	public void setLocado(boolean locado) {
		this.locado = locado;
	}

}
