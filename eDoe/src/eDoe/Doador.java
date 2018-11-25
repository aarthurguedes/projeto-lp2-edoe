package eDoe;

import java.util.HashMap;
import java.util.Map;

import abstrato.Usuario;
import validacao.ValidadorBase;

/**
* Representacao de um doador, que possui nome, email, celular, classe e id. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class Doador extends Usuario{
	
	private Map<Integer, Item> itens;
	private int numItem;
	private ValidadorBase vb = new ValidadorBase();

	/**
	* Constroi o doador a partir do seu id, nome, email, celular e classe.
	*
	* @param id a identificacao do doador
	* @param nome o nome do doador
	* @param email o email do doador
	* @param celular o numero do celular do doador
	* @param classe a classe do doador
	*/
	public Doador(String id, String nome, String email, String celular, String classe, int idOrdem) {
		super(id, nome, email, celular, classe, idOrdem);
		this.itens = new HashMap<>();
		this.numItem = 1;
	}
	
	/**
	 * @return the itens
	 */
	public Map<Integer, Item> getItens() {
		return itens;
	}

	/**
	* @return o status do doador
	*/
	@Override
	public String getStatus() {
		return "doador";
	}
	
	/**
	 * Metodo responsavel por cadastrar o item no usuario
	 * @param descricao representa a descricao do item
	 * @param quantidade representa a quantidade daquele item
	 * @param tags representa as tags do item
	 */
	public void cadastrarItem(String descricao, int quantidade, String tags) {
		vb.validaItem(this.numItem, descricao, quantidade, tags);
		
		Item item = new Item(this.numItem, descricao, quantidade, tags);
		itens.put(this.numItem, item);
		this.numItem++;
	}

	/**
	 * Metodo auxiliar que verifica se o item passado como parametro existe no map de itens
	 * @param id representa a identificacao do item
	 * @return retorna true caso o item exista no map e false caso nao exista
	 */
	public boolean contemItem(Integer id) {
		if (!this.itens.containsKey(id)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Metodo responsavel por exibir um item
	 * @param id representa a identificacao do item a ser exibido
	 * @return string que representa o item
	 */
	public String exibirItem(int id) {
		vb.validaIdItem(id);
		vb.verificaExistenciaItem(itens, id);
		
		return itens.get(id).toString();
	}
	
	/**
	 * Metodo responsavel por atualizar um item
	 * @param id representa a identificacao do item
	 * @param quantidade representa a quantidade daquele item
	 * @param tags representa as tags do item
	 * @return string que representa o item atualizado
	 */
	public String atualizarItem(int id, int quantidade, String tags) {
		vb.validaIdItem(id);
		vb.verificaExistenciaItem(itens, id);
		
		if (quantidade > 0) {
    		itens.get(id).setQuantidade(quantidade);
		}
		if (tags != null && tags.trim().equals("")) {
			itens.get(id).setTags(tags);
		}
		
		return itens.get(id).toString();
	}
	
	/**
	 * Metodo responsavel por remover um item
	 * @param id representa a identificacao do item
	 */
	public void removerItem(int id) {
		vb.validaIdItem(id);
		vb.verificaExistenciaItem(itens, id);
		
		itens.remove(id);
	}
}
