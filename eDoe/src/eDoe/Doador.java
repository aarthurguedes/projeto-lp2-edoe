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
	
	public void cadastrarItem(String descricao, int quantidade, String tags) {
		vb.validaItem(this.numItem, descricao, quantidade, tags);
		
		Item item = new Item(this.numItem, descricao, quantidade, tags);
		itens.put(this.numItem, item);
		this.numItem++;
	}

	public boolean containsItem(Integer id) {
		if (!this.itens.containsKey(id)) {
			return false;
		}
		return true;
	}
	
	public String exibirItens(int id) {
		return "";
	}
	
	public String atualizarItem(int id) {
		return "";
	}
	
	public void removerItem(int id) {
		
	}
}
