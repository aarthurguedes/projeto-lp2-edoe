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
	
	public int adicionarItem(String idDoador, String descricao, int quantidade, String tags) {
		return this.ic.cadastrarItem(uc.getDoador(idDoador),descricao, quantidade, tags);
	}
}
