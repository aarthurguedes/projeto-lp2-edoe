package eDoe;

import enums.Classe;
import util.Validador;
import java.util.HashMap;
import java.util.Map;

/**
* Representacao de um usuario, que possui nome, email, celular, classe e id. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class Usuario implements Comparable <Usuario> {
	
	/**
	* Identificacao do usuario.
	*/
	private String id;
	/**
	* Nome do usuario.
	*/
	private String nome;
	/**
	* Email do usuario.
	*/
	private String email;
	/**
	* Celular do usuario.
	*/
	private String celular;
	/**
	* Status do usuario.
	*/
	private String status;
	/**
	* Classe do usuario.
	*/
	private Classe classe;
	/**
	 * Mapa de itens do usuario.
	 */
	private Map<Integer, Item> itens;
    /**
    * Inteiro representando a posição na qual o usuario foi cadastrado.
    */
	private int idOrdem;
    /**
	* Objeto validador.
	*/
	private Validador validador = new Validador();

	/**
	* Constroi o usuario a partir do seu id, nome, email, celular e classe.
	*
	* @param id a identificacao do usuario
	* @param nome o nome do usuario
	* @param email o email do usuario
	* @param celular o numero do celular do usuario
	* @param classe a classe do usuario
	*/
	public Usuario(String id, String nome, String email, String celular, Classe classe, String status, int idOrdem) {
		validador.validarString(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		validador.validarString(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
		validador.validarString(email, "Entrada invalida: email nao pode ser vazio ou nulo.");
		validador.validarString(celular, "Entrada invalida: celular nao pode ser vazio ou nulo.");
		validador.validarString(classe.getClasse(), "Entrada invalida: classe nao pode ser vazia ou nula.");

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = status;
		this.idOrdem = idOrdem;
		this.itens = new HashMap<>();
	}
	
	/**
	 * @return o id do usuario
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return o nome do usuario
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome o novo nome do usuario
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return o email do usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email o novo email do usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}	
	
	/**
	 * @return o celular do usuario
	 */
	public String getCelular() {
		return celular;
	}
	
	/**
	 * @return a classe do usuario
	 */
	public String getClasse() {
		return classe.getClasse();
	}
	
	/**
	 * @return a ordem do cadastro do usuario
	 */
	public int getIdOrdem() {
		return idOrdem;
	}

	/**
	 * @param celular o novo numero de celular do usuario
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public Map<Integer, Item> getItens() {
		return this.itens;
	}
	
	/**
	 * Cadastra um item para o usuario.
	 * @param id representa a identificacao do item
	 * @param descricao representa a descricao do item
	 * @param quantidade representa a quantidade daquele item
	 * @param tags representa as tags do item
	 */
	public void cadastrarItem(int id, Descritor descricao, int quantidade, String tags) {
		validador.validarInteiro(id, "Entrada invalida: id do item nao pode ser negativo.");
		validador.validarString(descricao.getDescritor(), "Entrada invalida: descricao nao pode ser vazia ou nula.");
		validador.validarInteiro(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");

		Item item = new Item(id, descricao, quantidade, tags);
		itens.put(id, item);
	}

	/**
	 * Metodo responsavel por verificar a existencia de um item no map de itens do doador.
	 * @param id representa a identificacao do item
	 * @return o boolean que representa a existencia (ou nao) do item
	 */
	public boolean containsItem(int id) {
		if (!this.itens.containsKey(id)) {
			return false;
		}
		return true;
	}

	/**
	 * Metodo responsavel por exibir um item.
	 * @param id representa a identificacao do item
	 * @return string que representa o item
	 */
	public String exibirItem(int id) {
		validador.validarInteiro(id, "Entrada invalida: id do item nao pode ser negativo.");

		if (!itens.containsKey(id)) {
			throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
		}

		return itens.get(id).toString();
	}

	/**
	 * Metodo responsavel por atualizar um item
	 * @param id representa a identificacao do item
	 * @param quantidade representa a quantidade daquele item
	 * @param tags representa as tags do item
	 * @return string que representa o tem atualizado
	 */
	public String atualizarItem(int id, int quantidade, String tags) {
		validador.validarInteiro(id, "Entrada invalida: id do item nao pode ser negativo.");

		if (!itens.containsKey(id)) {
			throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
		}

		if (quantidade > 0) {
			itens.get(id).setQuantidade(quantidade);
		}
		if (tags != null && !tags.trim().equals("")) {
			itens.get(id).setTags(tags);
		}

		return this.itens.get(id).toString();
	}

	/**
	 * Metodo responsavel por remover um item
	 * @param id representa a identificacao do item
	 */
	public void removerItem(int id) {
		validador.validarInteiro(id, "Entrada invalida: id do item nao pode ser negativo.");

		if (!itens.containsKey(id)) {
			throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
		}

		this.itens.remove(id);
	}


	/**
	 * Método usado para comparar os usuarios com base no idOrdem.
	 *
	 * @param usuario Objeto usuario para comparação
	 * @return inteiro usado para comparação.
	 */
    public int compareTo(Usuario usuario) {
        return this.idOrdem - usuario.idOrdem;
    }

	/**
	* Retorna a String que representa o usuario. Formato: Nome/Id, email, celular, status.
	* 
	* @return a representacao em String do usuario.
	*/
	@Override
	public String toString() {
		return this.nome + "/" + this.id + ", " + this.email + ", " + this.celular + ", status: " + this.status;

	}
	
	/**
	* Retorna o inteiro que representa a posicao do Usuario na memoria.
	* 
	* @return a representacao numerica do Usuario.  
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	* Retorna o boolean que representa se dois usuarios sao iguais, ou seja, se possuem o mesmo id.
	* 
	* @param obj o objeto que representa o outro usuario
	* @return o valor boolean da igualdade (ou nao) entre dois clientes. 
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
