package controllers;

public class EDoeController {
	
	private UsuarioController uc;
	
	private ItemController ic;
	
	public EDoeController() {
		this.uc = new UsuarioController();
		this.ic = new ItemController();
	}

	public String adicionarDoador(String id, String nome, String email, String celular, String classe) {
		return this.uc.cadastrarDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisarUsuarioPorId(String id) {
		return this.uc.pesquisarUsuarioPorId(id);
	}
	
	public String pesquisarUsuarioPorNome(String nome) {
		return this.uc.pesquisarUsuarioPorNome(nome);
	} 
	
	public String atualizarUsuario(String id, String nome, String email, String celular) {
		return this.uc.atualizarUsuario(id, nome, email, celular);
	} 
	
	public void removerUsuario(String id) {
		this.uc.removerUsuario(id);
	}
	
	public void lerReceptores(String caminho) {
	   this.uc.lerReceptores(caminho);
    }
	
	public void adicionarDescritor(String descricao) {
		this.ic.adicionaDescritor(descricao);
	}
	 
	public int adicionarItem(String idDoador, String descricao, int quantidade, String tags) {
		return this.ic.cadastrarItem(idDoador, uc.getUsuarios(), descricao, quantidade, tags);
	}
	
	public String exibirItem(int idItem, String idDoador) {
		return this.ic.exibirItem(idItem, idDoador, uc.getUsuarios());
	}
	
	public String atualizarItem(int idItem, String idDoador, int quantidade, String tags) {
		return this.ic.atualizarItem(idItem, idDoador, uc.getUsuarios(), quantidade, tags);
	}
	
	public void removerItem(int idItem, String idDoador) {
		this.ic.removerItem(idDoador, uc.getUsuarios(), idItem);
	}
}
