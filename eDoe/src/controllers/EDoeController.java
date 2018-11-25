package controllers;

/**
* Representacao do controlador geral do eDoe 
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
	 * Constroi o controlador geral
	 */
	public EDoeController() {
		this.uc = new UsuarioController();
		this.ic = new ItemController();
	}
	
	/**
	 * Chamada do metodo responsavel por cadastrar um usuario doador
	 * @param id representa o id do usuario
	 * @param nome representa o nome do usuario
	 * @param email representa o email do usuario
	 * @param celular representa o celular do usuario
	 * @param classe representa a classe do usuario
	 * @return string referente ao id do usuario
	 */
	public String adicionarDoador(String id, String nome, String email, String celular, String classe) {
		return this.uc.cadastrarDoador(id, nome, email, celular, classe);
	}
	
	/**
	 * Chamada do metodo responsavel por pesquisar um usuario por id
	 * @param id representa a identificacao do usuario
	 * @return String com a representacao do usuario encontrado
	 */
	public String pesquisarUsuarioPorId(String id) {
		return this.uc.pesquisarUsuarioPorId(id);
	}
	
	/**
	 * Chamada do metodo responsavel por pesquisar um usuario por nome
	 * @param nome representa o nome do usuario que se procura
	 * @return String com a representacao do usuario encontrado
	 */
	public String pesquisarUsuarioPorNome(String nome) {
		return this.uc.pesquisarUsuarioPorNome(nome);
	} 
	
	/**
	 * Chamada do metodo responsavel por atualizar um usuario
	 * @param id representa a identificacao do usuario
	 * @param nome representa o nome do usuario
	 * @param email representa o email do usuario
	 * @param celular representa o celular do usuario
	 * @return String com a representacao do usuario atualizado
	 */
	public String atualizarUsuario(String id, String nome, String email, String celular) {
		return this.uc.atualizarUsuario(id, nome, email, celular);
	} 
	
	/**
	 * Chamada do metodo responsavel por remover um usuario
	 * @param id identificacao do usuario
	 */
	public void removerUsuario(String id) {
		this.uc.removerUsuario(id);
	}

	/**
	 * Chamada do metodo responsavel por ler o arquivo .csv
	 * @param caminho representa o caminho do arquivo .csv
	 */
	public void lerReceptores(String caminho) {
	   this.uc.lerReceptores(caminho);
    }
	
	/**
	 * Chamada do metodo responsavel por adicionar descricao
	 * @param descricao representa as descricoes dos itens
	 */
	public void adicionarDescritor(String descricao) {
		this.ic.adicionaDescritor(descricao);
	}
	
	/**
	 * Chamada do metodo responsavel por adicionar item a um doador
	 * @param idDoador representa a identificacao do doador que sera cadastrado o item
	 * @param descricao representa a descricao do item
	 * @param quantidade representa a quantidade de itens
	 * @param tags representa as tags do item
	 * @return um inteiro que representara a identificacao do item
	 */
	public int adicionarItem(String idDoador, String descricao, int quantidade, String tags) {
		return this.ic.cadastrarItem(uc.getDoador(idDoador),descricao, quantidade, tags);
	}
	
	/**
	 * Chamada do metodo responsavel por exibir um item de um doador
	 * @param idItem representa a identificacao do item a ser exibido
	 * @param idDoador representa a identificacao do doador daquele item
	 * @return uma String com a representacao do item 
	 */
	public String exibirItem(int idItem, String idDoador) {
		return this.ic.exibirItem(idItem, idDoador, uc.getUsuarios());
	}
	
	/**
	 * Chamada do metodo responsavel por atualizar um item
	 * @param idItem representa a identificacao do item
	 * @param idDoador representa a identificacao do doador
	 * @param quantidade representa a quantidade de itens
	 * @param tags representa as tags do item
	 * @return uma String com a representacao do item atualizado
	 */
	public String atualizarItem(int idItem, String idDoador, int quantidade, String tags) {
		return this.ic.atualizarItem(idItem, idDoador, uc.getUsuarios(), quantidade, tags);
	}
	
	/**
	 * Chamada do metodo responsavel por remover um item de um doador
	 * @param idItem representa a identificacao do item
	 * @param idDoador representa a identificacao do doador
	 */
	public void removerItem(int idItem, String idDoador) {
		this.ic.removerItem(idDoador, uc.getUsuarios(), idItem);
	}
}
