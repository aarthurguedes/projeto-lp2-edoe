package controllers;

import easyaccept.EasyAccept;

public class Facade {
	UsuarioController usuarioController;
	
	public Facade() {
		this.usuarioController = new UsuarioController();
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return this.usuarioController.cadastrarDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return this.usuarioController.pesquisarUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return this.usuarioController.pesquisarUsuarioPorNome(nome);
	} 
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return this.usuarioController.atualizarUsuario(id, nome, email, celular);
	} 
	
	public void removeUsuario(String id) {
		this.usuarioController.removerUsuario(id);
	}

	public void lerReceptores(String caminho) {
	   this.usuarioController.lerReceptores(caminho);
    }
	
	public void adicionaDescritor(String descricao) {
		this.usuarioController.adicionarDescritor(descricao);
	}
	
	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		return this.usuarioController.cadastrarItem(idDoador,descricao, quantidade, tags);
	}
	
	public String exibeItem(int idItem, String idDoador) {
		return this.usuarioController.exibirItem(idItem, idDoador);
	}
	
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return this.usuarioController.atualizarItem(idItem, idDoador, quantidade, tags);
	}
	
	public void removeItemParaDoacao(int idItem, String idDoador) {
		this.usuarioController.removerItem(idItem, idDoador);
	}

	public String listaDescritorDeItensParaDoacao() {
		return this.usuarioController.listaDescritorDeItensParaDoacao();
	}

    public String listaItensParaDoacao() {
	    return this.usuarioController.listaItensParaDoacao();
    }
	
    public static void main(String[] args) {
        args = new String[] {"controllers.Facade", "acceptance_tests/use_case_1.txt", "acceptance_tests/use_case_2.txt", "acceptance_tests/use_case_3.txt"};
        EasyAccept.main(args);
    }
}
