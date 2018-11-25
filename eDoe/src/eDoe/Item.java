package eDoe;

import validacao.ValidadorBase;


/**
* Representacao de um item, que id, descricao, quantidade e tags 
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
	 * Quantidade do item
	 */
	private int quantidade;
	
	/**
	 * Tags do item
	 */
	private String tags;
	
	/**
	 * Objeto validador
	 */
	private ValidadorBase vb = new ValidadorBase();
	
	/**
	 * Construcao de um item
	 * @param id representa a identificacao do item
	 * @param descricao representa a descricao do item
	 * @param quantidade representa a quantidade do item
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
	 * @return a atual identificacao do item
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return a atual descricao do item 
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo responsavel por alterar a descricao do item
	 * @param descricao representa a nova descricao do item
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return a atual quantidade do item
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	/**
	 * Metodo responsavel por alterar a quantidade do item
	 * @param quantidade representa a nova quantidade de item
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return a atual tags de item
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * Metodo responsavel por alterar as tags do item
	 * @param tags representa as novas tags do item
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	/**
	 * Retorna a string que representa um item, no formato:
	 * "id - descricao, tags, quantidade: "
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + ", " + this.tags.split(",") + ", quantidade: " + this.quantidade;
	}
	
	/**
	* Retorna o inteiro que representa a posicao do item na memoria.
	* 
	* @return a representacao numerica do item.  
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
	* Retorna o boolean que representa se dois itens sao iguais, ou seja, se possuem o mesmo id ou as mesmas tags
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
