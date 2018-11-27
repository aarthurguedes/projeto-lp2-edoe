package controllers;

/**
* Representacao do controlador geral do eDoe. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/

public class EDoeController {
	
	/**
	 * Objeto do controlador de usuarios
	 */
	private UsuarioController uc;
	
	/**
	 * Objeto do controlador de itens
	 */
	private ItemController ic;
	
	/**
	 * Constroi o eDoe controller
	 */
	public EDoeController() {
		this.uc = new UsuarioController();
		this.ic = new ItemController();
	}
	
	/**
	 * Metodo responsavel por chamar o metodo de cadastrar um doador
	 * @param id representa o id do usuario
	 * @param nome representa o nome do usuario
	 * @param email representa o email do usuario
	 * @param celular representa o celular do usuario
	 * @param classe representa a classe do usuario
	 * @return String do id do usuario
	 */
	public String adicionarDoador(String id, String nome, String email, String celular, String classe) {
		return this.uc.cadastrarDoador(id, nome, email, celular, classe);
	}
	
	/**
	 * Metodo responsavel por invocar o metodo de pesquisar um usuario pelo id
	 * @param id representa a identificacao do usuario
	 * @return String do usuario que foi pesquisado
	 */
	public String pesquisarUsuarioPorId(String id) {
		return this.uc.pesquisarUsuarioPorId(id);
	}
	
	/**
	 * Metodo responsavel por invocar o metodo de pesquisar um usuario pelo nome
	 * @param nome representa o nome do usuario
	 * @return String do usuario que foi pesquisado
	 */
	public String pesquisarUsuarioPorNome(String nome) {
		return this.uc.pesquisarUsuarioPorNome(nome);
	} 
	
	/**
	 * Metodo responsavel por invocar o metodo de atualizar um usuario
	 * @param id representa a novo identificacao do usuario
	 * @param nome representa o novo nome do usuario
	 * @param email representa o novo email do usuario
	 * @param celular representa o novo celular do usuario
	 * @return String do usuario atualizado
	 */
	public String atualizarUsuario(String id, String nome, String email, String celular) {
		return this.uc.atualizarUsuario(id, nome, email, celular);
	} 
	
	/**
	 * Meotodo responsavel por invocar o metodo de remover um usuario
	 * @param id representa a identificacao do usuario
	 */
	public void removerUsuario(String id) {
		this.uc.removerUsuario(id);
	}

	/**
	 * Metodo responsavel por invocar o metodo de ler os receptores
	 * @param caminho represnta o caminho do arquivo .csv
	 */
	public void lerReceptores(String caminho) {
	   this.uc.lerReceptores(caminho);
    }
	
	/**
	 * Metodo responsavel por invocar o metodo de adicionar descritor
	 * @param descricao representa a descricao
	 */
	public void adicionarDescritor(String descricao) {
		this.ic.adicionaDescritor(descricao);
	}
	 /**
	  * Metodo responsavel por invocar o metodo de adicionar um item
	  * @param idDoador representa o doador desse item
	  * @param descricao representa a descricao do item
	  * @param quantidade representa a quantidade do item
	  * @param tags representa as tags do item
	  * @return inteiro que representa o id do item
	  */
	public int adicionarItem(String idDoador, String descricao, int quantidade, String tags) {
		return this.ic.cadastrarItem(idDoador, uc.getUsuarios(), descricao, quantidade, tags);
	}
	
	/**
	 * Metodo responsavel invocar o metodo de exibir um item
	 * @param idItem representa a identificacao do item
	 * @param idDoador representa a identificacao do doador daquele item
	 * @return String que representa o item a ser exibido
	 */
	public String exibirItem(int idItem, String idDoador) {
		return this.ic.exibirItem(idItem, idDoador, uc.getUsuarios());
	}
	
	/**
	 * Meotodo responsavel por invocar o metodo de atualizar um item
	 * @param idItem representa a identificacao do item
	 * @param idDoador representa a identificacao do doador
	 * @param quantidade representa a nova quantidade do item
	 * @param tags representa as novas tags do item
	 * @return String que representa o item atualizado
	 */
	public String atualizarItem(int idItem, String idDoador, int quantidade, String tags) {
		return this.ic.atualizarItem(idItem, idDoador, uc.getUsuarios(), quantidade, tags);
	}
	
	/**
	 * Meotodo responsavel por invocar o metodo que remove um item
	 * @param idItem representa a identificacao do item
	 * @param idDoador representa a identificacao do doador
	 */
	public void removerItem(int idItem, String idDoador) {
		this.ic.removerItem(idDoador, uc.getUsuarios(), idItem);
	}
}
