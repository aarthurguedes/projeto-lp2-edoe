package eDoe;

import util.Util;
import util.Validador;

import java.util.ArrayList;
import java.util.List;

/**
* Representacao de um item que possui identificacao, descricao, quantidade e tags. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class Item {
	
	private int id;
	private Descritor descricao;
	private int quantidade;
	private String tags;
	private Validador validador = new Validador();
	private String idDoador;

	/**
	 * Constroi um item.
	 *
	 * @param id representa a identificacao do item
	 * @param descricao representa a descricao do item
	 * @param quantidade representa a quantidade daquele item
	 * @param tags representa as tags do item
	 */
	public Item(int id, Descritor descricao, int quantidade, String tags, String idDoador) {
		validador.validarInteiro(id, "Entrada invalida: id do item nao pode ser negativo.");
		validador.validarString(descricao.getDescricao(), "Entrada invalida: descricao nao pode ser vazia ou nula.");
		validador.validarInteiro(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");

		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tags = tags;
		this.idDoador = idDoador;
	}

	/**
	 * @return retorna o valor atual da identificacao do item
	 */
	public int getId() {
		return id;
	}

	public String getIdDoador() {
		return this.idDoador;
	}

	/**
	 * @return valor atual da descricao do item
	 */
	public String getDescricao() {
		return descricao.getDescricao();
	}

	/**
	 * @param descricao representa a nova descricao do item
	 */
	public void setDescricao(String descricao) {
		this.descricao.setDescricao(Util.formatString(descricao));
	}

	/**
	 * @return valor atualizado da quantidade de itens
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade representa a nova quantidade do item
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return o valor atualizado de tags
	 */
	public String getTags() {
		return tags;
	}

	public Descritor getDescritor() {
		return this.descricao;
	}

	/**
	 * @param tags representa as novas tags do item
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * Metodo responsavel por criar uma lista com as tags de um item
	 *
	 * @param tags representa as tags do item
	 * @return lista com as tags
	 */
	private List<String> listaTags(String tags) {
		List<String> listaTags = new ArrayList<String>();
		
		for (String s : tags.split(",")) {
			listaTags.add(s);
		}
		
		return listaTags;
	}

	/**
	 * Retorna a String que representa o item. Formato: Id - descricao, tags, quantidade.
	 *
	 * @return a representacao em String do usuario.
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao.getDescricao() + ", tags: " + listaTags(this.tags) + ", quantidade: " + this.quantidade;
	}

	/**
	 * Retorna o inteiro que representa a posicao do Item na memoria.
	 *
	 * @return a representacao numerica do Item.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	/**
	 * Retorna o boolean que representa se dois itens sao iguais, ou seja, se possuem a mesma descricao e tags.
	 *
	 * @param obj o objeto que representa o outro item
	 * @return o valor boolean da igualdade (ou nao) entre dois itens.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}
}
