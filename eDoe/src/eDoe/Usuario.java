package eDoe;

import enums.Classe;
import util.Validador;

import java.io.Serializable;
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
public class Usuario implements Comparable<Usuario>, Serializable {

	public static final long serialVersionUID = 3536817023363986264L;
	private String id;
	private String nome;
	private String email;
	private String celular;
	private String status;
	private Classe classe;
	private Map<Integer, Item> itens;
	private int posicao;

	/**
	 * Constroi o usuario a partir do seu id, nome, email, celular e classe.
	 *
	 * @param id      a identificacao do usuario
	 * @param nome    o nome do usuario
	 * @param email   o email do usuario
	 * @param celular o numero do celular do usuario
	 * @param classe  a classe do usuario
	 */
	public Usuario(String id, String nome, String email, String celular, Classe classe, String status, int idOrdem) {
		Validador.validarString(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		Validador.validarString(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
		Validador.validarString(email, "Entrada invalida: email nao pode ser vazio ou nulo.");
		Validador.validarString(celular, "Entrada invalida: celular nao pode ser vazio ou nulo.");
		Validador.validarString(classe.getClasse(), "Entrada invalida: classe nao pode ser vazia ou nula.");

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = status;
		this.posicao = idOrdem;
		this.itens = new HashMap<>();
	}

	/**
	 * Metodo responsavel por retornar o atual valor do id.
	 * 
	 * @return o id atual do usuario
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo responsavel por retornar o valor atual de nome.
	 * 
	 * @return o atual nome do usuario
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo responsavel por atualizar o nome do usuario.
	 * 
	 * @param nome o novo nome do usuario
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo responsavel por retornar o atual valor do email do usuario.
	 * 
	 * @return o email do usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo responsavel por atualizar o valor do email.
	 * 
	 * @param email o novo email do usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo responsavel por retornar o valor atual do celular do usuario.
	 * 
	 * @return o celular do usuario
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Metodo responsavel por retornar a classe do usuario.
	 * 
	 * @return a classe do usuario
	 */
	public String getClasse() {
		return classe.getClasse();
	}

	/**
	 * Metodo responsavel por retornar o valor atual do status do usuario.
	 * 
	 * @return o atual status do usuario.
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Metodo responsavel por retornar o valor atual da posicao que o usuario foi
	 * cadastrado
	 * 
	 * @return a ordem do cadastro do usuario
	 */
	public int getIdOrdem() {
		return posicao;
	}

	/**
	 * Metodo responsavel por atualizar o valor do celular do usuario.
	 * 
	 * @param celular o novo numero de celular do usuario
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Metodo responsavel por retornar um objeto do tipo Item.
	 * 
	 * @param idItem representa a identificacao do item que sera retornado
	 * @return objeto do tipo Item
	 */
	public Item getItem(int idItem) {
		return this.itens.get(idItem);
	}

	/**
	 * Metodo responsavel por retornar o valor atual do map de itens.
	 * 
	 * @return map de itens
	 */
	public Map<Integer, Item> getItens() {
		return this.itens;
	}

	/**
	 * Cadastra um item para o usuario.
	 * 
	 * @param id         representa a identificacao do item
	 * @param descricao  representa a descricao do item
	 * @param quantidade representa a quantidade daquele item
	 * @param tags       representa as tags do item
	 */
	public void cadastrarItem(int id, Descritor descricao, int quantidade, String tags) {
		Validador.validarInteiro(id, "Entrada invalida: id do item nao pode ser negativo.");
		Validador.validarString(descricao.getDescricao(), "Entrada invalida: descricao nao pode ser vazia ou nula.");
		Validador.validarInteiro(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");

		Item item = new Item(id, descricao, quantidade, tags, this.nome + "/" + this.id);
		itens.put(id, item);
	}

	/**
	 * Metodo responsavel por verificar a existencia de um item no map de itens do
	 * doador.
	 * 
	 * @param id representa a identificacao do item
	 * @return o boolean que representa a existencia (ou nao) do item
	 */
	public boolean containsItem(int id) {
		if (!this.itens.containsKey(id)) {
			return false;
		}
		return true;
	}

	private void validarExistenciaItem(int idItem) {
		if (!itens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
	}

	/**
	 * Metodo responsavel por exibir um item.
	 * 
	 * @param id representa a identificacao do item
	 * @return string que representa o item
	 */
	public String exibirItem(int id) {
		Validador.validarInteiro(id, "Entrada invalida: id do item nao pode ser negativo.");
		this.validarExistenciaItem(id);

		return itens.get(id).toString();
	}

	/**
	 * Metodo responsavel por atualizar um item
	 * 
	 * @param id         representa a identificacao do item
	 * @param quantidade representa a quantidade daquele item
	 * @param tags       representa as tags do item
	 * @return string que representa o tem atualizado
	 */
	public String atualizarItem(int id, int quantidade, String tags) {
		Validador.validarInteiro(id, "Entrada invalida: id do item nao pode ser negativo.");
		this.validarExistenciaItem(id);

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
	 * 
	 * @param id representa a identificacao do item
	 */
	public void removerItem(int id) {
		Validador.validarInteiro(id, "Entrada invalida: id do item nao pode ser negativo.");
		this.validarExistenciaItem(id);
		this.itens.remove(id);
	}
	
	/*
	private void validarDoacao(int idItemNecessario, int idItemDoado, String data) {
		Validador.validarInteiro(idItemNecessario, "Entrada invalida: id do item nao pode ser negativo.");
		Validador.validarInteiro(idItemDoado, "Entrada invalida: id do item nao pode ser negativo.");
		this.validarExistenciaItem(idItemNecessario);
		this.validarExistenciaItem(idItemDoado);

		if (!itens.get(idItemNecessario).getDescricao().equals(itens.get(idItemDoado).getDescricao())) {
			throw new IllegalArgumentException("Os itens nao tem descricoes iguais.");
		}
 
		Validador.validarString(data, "Entrada invalida: data nao pode ser vazia ou nula.");
	}

	private int getQtdDoacao(int idItemNecessario, int idItemDoado) {
		int qtdItemNecessario = itens.get(idItemNecessario).getQuantidade();
		int qtdItemDoado = itens.get(idItemDoado).getQuantidade();
		int qtdDoacao = 0;
		
		if (qtdItemDoado > qtdItemNecessario) {
			qtdDoacao = qtdItemNecessario;
			itens.get(idItemDoado).setQuantidade(itens.get(idItemDoado).getQuantidade() - qtdItemNecessario);
		} else if (qtdItemDoado == qtdItemNecessario) {
			qtdDoacao = qtdItemDoado;
		} else if (qtdItemNecessario > qtdItemDoado) {
			qtdDoacao = qtdItemDoado;
			itens.get(idItemNecessario).setQuantidade(itens.get(idItemNecessario).getQuantidade() - qtdItemDoado); 
		}
		
		return qtdDoacao;	
	}
	*/
	
	public void removeItensQtd0() {
		int idParaSerRemovido = 0;
		for (Item item : this.itens.values()) {
			if (item.getQuantidade() == 0) {
				idParaSerRemovido = item.getId();
			}
		} 
		itens.remove(idParaSerRemovido);
	}

	/**
	 * Metodo usado para comparar os usuarios com base no idOrdem.
	 *
	 * @param usuario Objeto usuario para comparacao
	 * @return inteiro usado para comparacao.
	 */
	public int compareTo(Usuario usuario) {
		return this.posicao - usuario.posicao;
	}
 
	/**
	 * Retorna a String que representa o usuario. Formato: Nome/Id, email, celular,
	 * status.
	 * 
	 * @return a representacao em String do usuario.
	 */
	@Override
	public String toString() {
		return this.nome + "/" + this.id + ", " + this.email + ", " + this.celular + ", status: " + this.status;

	}

	/**
	 * Metodo responsavel por retornar o nome e o id. Formatacao: nome/id
	 * 
	 * @return string que representa o nome e a identificacao do usuario
	 */
	public String getNomeEId() {
		return this.nome + "/" + this.id;
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
	 * Retorna o boolean que representa se dois usuarios sao iguais, ou seja, se
	 * possuem o mesmo id.
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
