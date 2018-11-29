package controllers;

import easyaccept.EasyAccept;

public class Facade {
	
	EdoeController edoeController;
	
	public Facade() {
		this.edoeController = new EdoeController();
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return this.edoeController.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return this.edoeController.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return this.edoeController.pesquisaUsuarioPorNome(nome);
	} 
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return this.edoeController.atualizaUsuario(id, nome, email, celular);
	} 
	
	public void removeUsuario(String id) {
		this.edoeController.removeUsuario(id);
	}

	public void lerReceptores(String caminho) {
	   this.edoeController.lerReceptores(caminho);
    }
	
	public void adicionaDescritor(String descricao) {
		this.edoeController.adicionaDescritor(descricao);
	}
	
	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		return this.edoeController.adicionaItemParaDoacao(idDoador,descricao, quantidade, tags);
	}
	
	public String exibeItem(int idItem, String idDoador) {
		return this.edoeController.exibeItem(idItem, idDoador);
	}
	
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return this.edoeController.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}
	
	public void removeItemParaDoacao(int idItem, String idDoador) {
		this.edoeController.removeItemParaDoacao(idItem, idDoador);
	}

	public String listaDescritorDeItensParaDoacao() {
		return this.edoeController.listaDescritorDeItensParaDoacao();
	}

    public String listaItensParaDoacao() {
	    return this.edoeController.listaItensParaDoacao();
    }

    public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
        return this.edoeController.pesquisaItemParaDoacaoPorDescricao(descricao);
    }

    public int adicionaItemNecessario(String idReceptor, String descricao, int quantidade, String tags) {
        return this.edoeController.adicionaItemNecessario(idReceptor, descricao, quantidade, tags);
    }

    public String atualizaItemNecessario(int idItem, String idReceptor, int quantidade, String tags) {
        return this.edoeController.atualizaItemNecessario(idItem, idReceptor, quantidade, tags);
    }

    public void removeItemNecessario(int idItem, String idReceptor) {
        this.edoeController.removeItemNecessario(idItem, idReceptor);
    }

    public String listaItensNecessarios() {
	    return edoeController.listaItensNecessarios();
    }
	
    public static void main(String[] args) {
        args = new String[] {"controllers.Facade", "acceptance_tests/use_case_1.txt", "acceptance_tests/use_case_2.txt", "acceptance_tests/use_case_3.txt", "acceptance_tests/use_case_4.txt"};
        EasyAccept.main(args);
    }
}
