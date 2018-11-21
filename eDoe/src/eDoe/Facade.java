package eDoe;

import controllers.UsuarioController;
import easyaccept.EasyAccept;

public class Facade {
	
	UsuarioController uc;
	
	public Facade() {
		this.uc = new UsuarioController();
	}

    public static void main(String[] args) {
        args = new String[] {"eDoe.Facade", "acceptance_tests/use_case_1.txt"};
        EasyAccept.main(args);
    }
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return this.uc.cadastrarDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return this.uc.pesquisarUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return this.uc.pesquisarUsuarioPorNome(nome);
	} 
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return this.uc.atualizarUsuario(id, nome, email, celular);
	} 
	
	public void removeUsuario(String id) {
		this.uc.removerUsuario(id);
	}
}
