package arvore;

import dados.ItemVeiculo;
import nos.NoArvVeiculo;

public class ArvoreVeiculo {

	private NoArvVeiculo raiz;
	private int quantNos;

	public ArvoreVeiculo() {
		this.quantNos = 0;
		this.raiz = null;
	}

	public boolean eVazia() {
		return (this.raiz == null);
	}

	public NoArvVeiculo getRaiz() {
		return this.raiz;
	}

	public int getQuantNos() {
		return this.quantNos;
	}

	// inserir um novo nó na arvore. Sempre insere em um atributo que seja igual a
	// null
	public boolean inserir(ItemVeiculo elem) {
		if (pesquisar(elem.getChave())) {
			return false;
		} else {
			this.raiz = inserir(elem, this.raiz);
			this.quantNos++;
			return true;
		}
	}

	public NoArvVeiculo inserir(ItemVeiculo elem, NoArvVeiculo no) {
		if (no == null) {
			NoArvVeiculo novo = new NoArvVeiculo(elem);
			return novo;
		} else {
			if (elem.getChave() < no.getInfo().getChave()) {
				no.setEsq(inserir(elem, no.getEsq()));
				return no;
			} else {
				no.setDir(inserir(elem, no.getDir()));
				return no;
			}
		}
	}

	public ItemVeiculo buscarVeiculo(int chave) {
		NoArvVeiculo no = pesquisar(chave, this.raiz);
		if (no != null) {
			return no.getInfo();
		}
		return null;
	}

	// Pesquisa se um determinado valor está na árvore
	public boolean pesquisar(int chave) {
		if (pesquisar(chave, this.raiz) != null) {
			return true;
		} else {
			return false;
		}
	}

	private NoArvVeiculo pesquisar(int chave, NoArvVeiculo no) {
		if (no != null) {
			if (chave < no.getInfo().getChave()) {
				no = pesquisar(chave, no.getEsq());
			} else {
				if (chave > no.getInfo().getChave()) {
					no = pesquisar(chave, no.getDir());
				}
			}
		}
		return no;
	}

	// remove um determinado nó procurando pela chave. O nó pode estar em qualquer
	// posição na árvore
	public boolean remover(int chave) {
		if (pesquisar(chave, this.raiz) != null && !buscarVeiculo(chave).getLocado()) {
			this.raiz = remover(chave, this.raiz);
			this.quantNos--;
			return true;
		} else {

			return false;
		}
	}

	public NoArvVeiculo remover(int chave, NoArvVeiculo arv) {
		if (chave < arv.getInfo().getChave()) {
			arv.setEsq(remover(chave, arv.getEsq()));
		} else {
			if (chave > arv.getInfo().getChave()) {
				arv.setDir(remover(chave, arv.getDir()));
			} else {
				if (arv.getDir() == null) {
					return arv.getEsq();
				} else {
					if (arv.getEsq() == null) {
						return arv.getDir();
					} else {
						arv.setEsq(Arrumar(arv, arv.getEsq()));
					}
				}
			}
		}
		return arv;
	}

	private NoArvVeiculo Arrumar(NoArvVeiculo arv, NoArvVeiculo maior) {
		if (maior.getDir() != null) {
			maior.setDir(Arrumar(arv, maior.getDir()));
		} else {
			arv.setInfo(maior.getInfo());
			maior = maior.getEsq();
		}
		return maior;
	}

	// caminhamento pré-fixado
	public ItemVeiculo[] CamPreFixado() {
		int[] n = new int[1];
		n[0] = 0;
		ItemVeiculo[] vet = new ItemVeiculo[this.quantNos];
		return (FazCamPreFixado(this.raiz, vet, n));
	}

	private ItemVeiculo[] FazCamPreFixado(NoArvVeiculo arv, ItemVeiculo[] vet, int[] n) {
		if (arv != null) {
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamPreFixado(arv.getEsq(), vet, n);
			vet = FazCamPreFixado(arv.getDir(), vet, n);
		}
		return vet;
	}

	// caminhamento para listar Veiculos Alugados
	public ItemVeiculo[] veiculosAlugados() {
		int[] n = new int[1];
		n[0] = 0;
		ItemVeiculo[] vet = new ItemVeiculo[this.quantNos];
		return (listarVeiculosAlugados(this.raiz, vet, n));
	}

	private ItemVeiculo[] listarVeiculosAlugados(NoArvVeiculo arv, ItemVeiculo[] vet, int[] n) {
		if (arv != null) {
			if (arv.getInfo().getLocado() == true) { // visita alterada
				vet[n[0]] = arv.getInfo();
				n[0]++;
			}
			vet = listarVeiculosAlugados(arv.getEsq(), vet, n);
			vet = listarVeiculosAlugados(arv.getDir(), vet, n);
		}
		return vet;
	}

	// caminhamento para listar Veiculos Disponiveis
	public ItemVeiculo[] veiculosDisponiveis() {
		int[] n = new int[1];
		n[0] = 0;
		ItemVeiculo[] vet = new ItemVeiculo[this.quantNos];
		return (listarVeiculosDisponiveis(this.raiz, vet, n));
	}

	private ItemVeiculo[] listarVeiculosDisponiveis(NoArvVeiculo arv, ItemVeiculo[] vet, int[] n) {
		if (arv != null) {
			if (arv.getInfo().getLocado() == false) { // visita alterada
				vet[n[0]] = arv.getInfo();
				n[0]++;
			}
			vet = listarVeiculosDisponiveis(arv.getEsq(), vet, n);
			vet = listarVeiculosDisponiveis(arv.getDir(), vet, n);
		}
		return vet;
	}

	public ItemVeiculo[] quilometragem(int kmConsulta) {
		int[] n = new int[1];
		n[0] = 0;
		ItemVeiculo[] vet = new ItemVeiculo[this.quantNos];
		return (consultarQuilometragem(this.raiz, vet, n, kmConsulta));
	}

	private ItemVeiculo[] consultarQuilometragem(NoArvVeiculo arv, ItemVeiculo[] vet, int[] n, int kmConsulta) {
		if (arv != null) {
			if (arv.getInfo().getQuilometragem() >= kmConsulta) {
				vet[n[0]] = arv.getInfo();
				n[0]++;
			}
			vet = consultarQuilometragem(arv.getEsq(), vet, n, kmConsulta);
			vet = consultarQuilometragem(arv.getDir(), vet, n, kmConsulta);
		}
		return vet;
	}
}
