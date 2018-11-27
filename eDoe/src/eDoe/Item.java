package eDoe;

import java.util.ArrayList;
import java.util.List;

import validacao.ValidadorBase;
/**
* Representacao de um item que possui identificacao, descricao, quantidade e tags. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class Item {
	
	/**
	 * Identificacao do item
	 */
	private int id;
	
	/**
	 * Descricao do item
	 */
	private String descricao;
	
	/**
	 * Quantidade daquele item
	 */
	private int quantidade;
	
	/**
	 * Tags dos itens
	 */
	private String tags;
	
	/**
	 * Objeto de validacao
	 */
	private ValidadorBase vb = new ValidadorBase();
	
	/**
	 * Constroi um item
	 * @param id representa a identificacao do item
	 * @param descricao representa a descricao do item
	 * @param quantidade representa a quantidade daquele item
	 * @param tags representa as tags do item
	 */
	public Item(int id, String descricao, int quantidade, String tags) {
		vb.validaItem(id, descricao, quantidade, tags);
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tags = tags;
	}
	
	/**
	 * @return retorna o valor atual da identificacao do item
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id representa a nova identificacao do item
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return valor atual da descricao do item
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao representa a nova descricao do item
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	/**
	 * @param tags representa as novas tags do item
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	/**
	 * Metodo responsavel por criar uma lista com as tags de um item
	 * @param tags representa as tags do item
	 * @return lista com as tags
	 */
	private List<String> listaTags(String tags) {
		List<String> listaTags = new ArrayList<String>();
		for (String s: tags.split(",")) {
			listaTags.add(s);
		}
		return listaTags;
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * PAREI OS COMENTÁRIOS AQUI, TERMINAR DEPOIS
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + ", tags: " + listaTags(this.tags) + ", quantidade: " + this.quantidade;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
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
