package nos;

import dados.ItemLocatario;

public class NoArvLocatario {

	private ItemLocatario info;
	private NoArvLocatario esq, dir;

	public NoArvLocatario(ItemLocatario elem) {
		this.info = elem;
		this.esq = null;
		this.dir = null;
	}

	public NoArvLocatario getEsq() {
		return this.esq;
	}

	public NoArvLocatario getDir() {
		return this.dir;
	}

	public ItemLocatario getInfo() {
		return this.info;
	}

	public void setEsq(NoArvLocatario no) {
		this.esq = no;
	}

	public void setDir(NoArvLocatario no) {
		this.dir = no;
	}

	public void setInfo(ItemLocatario elem) {
		this.info = elem;
	}
}
