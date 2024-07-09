package nos;

import dados.ItemVeiculo;

public class NoArvVeiculo {

	private ItemVeiculo info;
	private NoArvVeiculo esq, dir;

	public NoArvVeiculo(ItemVeiculo elem) {
		this.info = elem;
		this.esq = null;
		this.dir = null;
	}

	public NoArvVeiculo getEsq() {
		return this.esq;
	}

	public NoArvVeiculo getDir() {
		return this.dir;
	}

	public ItemVeiculo getInfo() {
		return this.info;
	}

	public void setEsq(NoArvVeiculo no) {
		this.esq = no;
	}

	public void setDir(NoArvVeiculo no) {
		this.dir = no;
	}

	public void setInfo(ItemVeiculo elem) {
		this.info = elem;
	}
}
