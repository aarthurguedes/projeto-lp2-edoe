package controllers;

import eDoe.Item; 
import eDoe.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EdoeController {
	
    private ItemController itemController;
    private UsuarioController usuarioController;

    public EdoeController() {
        this.itemController = new ItemController();
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
        this.itemController.adicionarDescritor(descricao);
    }

    public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
        return this.itemController.cadastrarItem(this.usuarioController.getUsuario(idDoador),descricao, quantidade, tags);
    }

    public String exibeItem(int idItem, String idDoador) {
        return this.itemController.exibirItem(idItem, this.usuarioController.getUsuario(idDoador));
    }

    public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
        return this.itemController.atualizarItem(idItem, this.usuarioController.getUsuario(idDoador), quantidade, tags);
    }

    public void removeItemParaDoacao(int idItem, String idDoador) {
        this.itemController.removerItem(idItem, this.usuarioController.getUsuario(idDoador));
    }

    public String listaDescritorDeItensParaDoacao() {
        return this.itemController.listarDescritorDeItensParaDoacao(this.usuarioController.getUsuarios());
    }
    
    private List<Item> getTodosItensCadastrados() {
        List<Item> itensCadastrados = new ArrayList<>();

        for (Usuario usuario : this.usuarioController.getUsuarios().values()) {
            for (Item item : usuario.getItens().values()) {
                itensCadastrados.add(item);
            }
        }

        return itensCadastrados;
    }

    public String listaItensParaDoacao() {
        return this.itemController.listarItensParaDoacao(this.getTodosItensCadastrados());
    }

    public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
        return this.itemController.pesquisarItemParaDoacaoPorDescricao(descricao, this.getTodosItensCadastrados());
    }
}
