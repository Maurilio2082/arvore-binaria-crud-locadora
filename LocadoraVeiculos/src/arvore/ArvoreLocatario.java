package arvore;

import dados.ItemLocatario;
import nos.NoArvLocatario;

public class ArvoreLocatario {

	private NoArvLocatario raiz;
	private int quantNos;

	public ArvoreLocatario() {
		this.quantNos = 0;
		this.raiz = null;
	}

	public boolean eVazia() {
		return (this.raiz == null);
	}

	public NoArvLocatario getRaiz() {
		return this.raiz;
	}

	public int getQuantNos() {
		return this.quantNos;
	}

	// inserir um novo nó na arvore. Sempre insere em um atributo que seja igual a
	// null
	public boolean inserir(ItemLocatario elem) {
		if (pesquisar(elem.getChave())) {
			return false;
		} else {
			this.raiz = inserir(elem, this.raiz);
			this.quantNos++;
			return true;
		}
	}

	public NoArvLocatario inserir(ItemLocatario elem, NoArvLocatario no) {
		if (no == null) {
			NoArvLocatario novo = new NoArvLocatario(elem);
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
	//buscar objeto
	public ItemLocatario buscarLocatario(int chave) {
		NoArvLocatario no = pesquisar(chave, this.raiz);
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

	private NoArvLocatario pesquisar(int chave, NoArvLocatario no) {
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
		if (pesquisar(chave, this.raiz) != null && !buscarLocatario(chave).getLocado()) {
			this.raiz = remover(chave, this.raiz);
			this.quantNos--;
			return true;
		} else {

			return false;
		}
	}

	public NoArvLocatario remover(int chave, NoArvLocatario arv) {
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

	private NoArvLocatario Arrumar(NoArvLocatario arv, NoArvLocatario maior) {
		if (maior.getDir() != null) {
			maior.setDir(Arrumar(arv, maior.getDir()));
		} else {
			arv.setInfo(maior.getInfo());
			maior = maior.getEsq();
		}
		return maior;
	}



	// caminhamento prefixado padrão
	public ItemLocatario[] CamPreFixado() {
		int[] n = new int[1];
		n[0] = 0;
		ItemLocatario[] vet = new ItemLocatario[this.quantNos];
		return (FazCamPreFixado(this.raiz, vet, n));
	}

	private ItemLocatario[] FazCamPreFixado(NoArvLocatario arv, ItemLocatario[] vet, int[] n) {
		if (arv != null) {
			
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamPreFixado(arv.getEsq(), vet, n);
			vet = FazCamPreFixado(arv.getDir(), vet, n);
		}
		return vet;
	}

		//prefixado - vencimento cnh
		public ItemLocatario[] cnhVencida(String dataConsulta) {
			int[] n = new int[1];
			n[0] = 0;
			ItemLocatario[] vet = new ItemLocatario[this.quantNos];
			return (listaCnhVencida(this.raiz, vet, n, dataConsulta));
		}
		
		private ItemLocatario[] listaCnhVencida(NoArvLocatario arv, ItemLocatario[] vet, int[] n, String dataConsulta) {
			if (arv != null) {
				if (arv.getInfo().getVencimentoCnh().compareTo(dataConsulta) <= 0) {
					vet[n[0]] = arv.getInfo();
					n[0]++;
				}
				vet = listaCnhVencida(arv.getEsq(), vet, n, dataConsulta);
				vet = listaCnhVencida(arv.getDir(), vet, n, dataConsulta);
			}
			return vet;
		}



		//prefixado-idade
		public ItemLocatario[] idade(int idadeConsulta) {
			int[] n = new int[1];
			n[0] = 0;
			ItemLocatario[] vet = new ItemLocatario[this.quantNos];
			return (listaIdade(this.raiz, vet, n, idadeConsulta));
		}
		
		private ItemLocatario[] listaIdade(NoArvLocatario arv, ItemLocatario[] vet, int[] n, int idadeConsulta) {
			if (arv != null) {
				if (arv.getInfo().getIdade() >= idadeConsulta) {
					vet[n[0]] = arv.getInfo();
					n[0]++;
				}
				vet = listaIdade(arv.getEsq(), vet, n, idadeConsulta);
				vet = listaIdade(arv.getDir(), vet, n, idadeConsulta);
			}
			return vet;
		}
}
