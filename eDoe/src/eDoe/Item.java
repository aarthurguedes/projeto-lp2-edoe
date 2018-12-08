package eDoe;

import util.Util;
import util.Validador;

import java.io.Serializable;
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
public class Item implements Serializable {

	public static final long serialVersionUID = 1147231641906472989L;
	private int id;
	private Descritor descricao;
	private int quantidade;
	private String tags;
	private String idUsuario;
	private int pontuacaoMatch;

	/**
	 * Constroi um item a partir de sua descricao, quantidade, tags e do id do seu doador.
	 *
	 * @param id representa a identificacao do item
	 * @param descricao representa a descricao do item
	 * @param quantidade representa a quantidade daquele item
	 * @param tags representa as tags do item
	 */
	public Item(int id, Descritor descricao, int quantidade, String tags, String idUsuario) {
		Validador.validarInteiro(id, "Entrada invalida: id do item nao pode ser negativo.");
		Validador.validarString(descricao.getDescricao(), "Entrada invalida: descricao nao pode ser vazia ou nula.");
		Validador.validarInteiro(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");

		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tags = tags;
		this.idUsuario = idUsuario;
		this.pontuacaoMatch = 0;
	}

	/**
	 * Metodo responsavel por retornar o valor atual da identificacao do item
	 * @return retorna o valor atual da identificacao do item
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metodo responsavel por retornar o valor atual da identificacao do usuario.
	 * @return identificacao atual do usuario
	 */
	public String getIdUsuario() {
		return this.idUsuario;
	}

	/**
	 * Metodo responsavel por retornar o valor atual de descricao.
	 * @return valor atual da descricao do item
	 */
	public String getDescricao() {
		return descricao.getDescricao();
	}

	/**
	 * Metodo responsavel por atualizar a descricao.
	 * @param descricao representa a nova descricao do item
	 */
	public void setDescricao(String descricao) {
		this.descricao.setDescricao(Util.formatString(descricao));
	}

	/**
	 * Metodo responsavel por exibir o valor atual de quantidade.
	 * @return valor atualizado da quantidade de itens
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Metodo responsavel por atualizar o valor de quantidade.
	 * @param quantidade representa a nova quantidade do item
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Metodo responsavel por exibibr o valor atual de tags.
	 * @return o valor atualizado de tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * Metodo responsavel por retornar um objeto do tipo descritor.
	 * @return objeto do tipo descritor
	 */
	public Descritor getDescritor() {
		return this.descricao;
	}

	/**
	 * Metodo responsavel por atualizar o valor de tags.
	 * @param tags representa as novas tags do item
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public int getPontuacaoMatch() {
		return this.pontuacaoMatch;
	}

	public void setPontuacaoMatch(int pontuacaoMatch) {
		this.pontuacaoMatch = pontuacaoMatch;
	}

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
