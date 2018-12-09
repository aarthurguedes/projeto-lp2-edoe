package controllers;

import eDoe.Item;  
import eDoe.Usuario;
import util.Validador;

import java.util.ArrayList;
import java.util.List;

public class EdoeController {
	
    private ItemController itemController;
    private UsuarioController usuarioController;
    private DoacaoController doacaoController;

    public EdoeController() {
        this.itemController = new ItemController();
        this.usuarioController = new UsuarioController();
        this.doacaoController = new DoacaoController();
    } 

    public void iniciaSistema() {
        this.usuarioController.inicializaSistema();
        this.itemController.inicializaSistema();
        this.doacaoController.inicializaSistema();
    }

    public void finalizaSistema() {
        this.usuarioController.finalizaSistema();
        this.itemController.finalizaSistema();
        this.doacaoController.finalizaSistema();
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
        this.validarDoador(idDoador);
    	return this.itemController.cadastrarItem(this.usuarioController.getUsuario(idDoador),descricao, quantidade, tags);
    }

    public String exibeItem(int idItem, String idDoador) {
        return this.itemController.exibirItem(idItem, this.usuarioController.getUsuario(idDoador));
    }

    public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
        this.validarDoador(idDoador);
        return this.itemController.atualizarItem(idItem, this.usuarioController.getUsuario(idDoador), quantidade, tags);
    }

    public void removeItemParaDoacao(int idItem, String idDoador) {
        this.validarDoador(idDoador);
        this.itemController.removerItem(idItem, this.usuarioController.getUsuario(idDoador));
    }

    public String listaDescritorDeItensParaDoacao() {
        return this.itemController.listarDescritorDeItensParaDoacao(this.getTodosItensCadastradosEmDoador());
    }

    public String listaItensParaDoacao() {
        return this.itemController.listarItensParaDoacao(this.getTodosItensCadastradosEmDoador());
    }

    public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
        return this.itemController.pesquisarItemParaDoacaoPorDescricao(descricao, this.getTodosItensCadastradosEmDoador());
    }

    public int adicionaItemNecessario(String idReceptor, String descricao, int quantidade, String tags) {
        this.validarReceptor(idReceptor);
    	return this.itemController.cadastrarItem(this.usuarioController.getUsuario(idReceptor),descricao, quantidade, tags);
    }

    public String listaItensNecessarios() {
        return itemController.listarItensNecessarios(this.getTodosItensCadastradosEmReceptor());
    }

    public String atualizaItemNecessario(String idReceptor, int idItem, int quantidade, String tags) {
        return this.itemController.atualizarItem(idItem, this.usuarioController.getUsuario(idReceptor), quantidade, tags);
    }

    public void removeItemNecessario(String idReceptor, int idItem) {
        this.validarReceptor(idReceptor);
        this.itemController.removerItem(idItem, this.usuarioController.getUsuario(idReceptor));
    }

    public String realizaDoacao(int idItemNecessario, int idItemDoado, String data) {
        Validador.validarString(data,"Entrada invalida: data nao pode ser vazia ou nula.");
        Validador.validarInteiro(idItemDoado, "Entrada invalida: id do item nao pode ser negativo.");
        Validador.validarInteiro(idItemNecessario, "Entrada invalida: id do item nao pode ser negativo.");

        Item itemNecessario = this.getItemNecessario(idItemNecessario);
        Item itemDoado = this.getItemDoado(idItemDoado);
        this.validaItensDoacao(itemNecessario, itemDoado);


        String saida = this.doacaoController.realizaDoacao(itemNecessario, itemDoado, data);
        this.removeItensQuantidade0(idItemNecessario, idItemDoado);
        return saida;
    }

    private void validaItensDoacao(Item itemNecessario, Item itemDoado) {
        if (!itemNecessario.getDescricao().equals(itemDoado.getDescricao())) {
            throw new IllegalArgumentException("Os itens nao tem descricoes iguais.");
        }
    }

    public String listaDoacoes() {
        return this.doacaoController.listaDoacoes();
    }

    private void removeItensQuantidade0(int idItemNecessario, int idItemDoado) {
        Item itemNecessario = this.getItemNecessario(idItemNecessario);
        Item itemDoado = this.getItemDoado(idItemDoado);

        String idReceptor = itemNecessario.getIdUsuario().split("/")[1];
        String idDoador = itemDoado.getIdUsuario().split("/")[1];

        this.usuarioController.getUsuario(idReceptor).removeItensQtd0();
        this.usuarioController.getUsuario(idDoador).removeItensQtd0();
    }


    public String match(String idReceptor, int idItemNecessario) {
    	this.validarMatch(idReceptor, idItemNecessario);
    	return this.itemController.match(this.getTodosItensCadastradosEmDoador(), this.usuarioController.getUsuario(idReceptor).getItem(idItemNecessario));
    }

    private void validarReceptor(String idReceptor) {
        if (!this.isReceptor(idReceptor)) {
            throw new IllegalArgumentException("O Usuario deve ser um receptor: " + idReceptor + ".");
        }
    }

    private void validarDoador(String idDoador) {
        if (!this.isDoador(idDoador)) {
            throw new IllegalArgumentException("O Usuario deve ser um doador: " + idDoador + ".");
        }
    }

    private void validarExistenciaItem(String idReceptor, int idItemNecessario) {
        if (!usuarioController.getUsuario(idReceptor).containsItem(idItemNecessario)) {
            throw new IllegalArgumentException("Item nao encontrado: " + idItemNecessario + ".");
        }
    }

    private List<Item> getTodosItensCadastradosEmDoador() {
        List<Item> itensCadastrados = new ArrayList<>();

        for (Usuario usuario : this.usuarioController.getUsuarios().values()) {
            if (usuario.getStatus().equals("doador")) {
                for (Item item : usuario.getItens().values()) {
                    itensCadastrados.add(item);
                }
            }
        }

        return itensCadastrados;
    }

    private List<Item> getTodosItensCadastradosEmReceptor() {
        List<Item> itensCadastrados = new ArrayList<>();

        for (Usuario usuario : this.usuarioController.getUsuarios().values()) {
            if (usuario.getStatus().equals("receptor")) {
                for (Item item : usuario.getItens().values()) {
                    itensCadastrados.add(item);
                }
            }
        }
        return itensCadastrados;
    }

    private Item getItemNecessario(int idItemNecessario) {
        for (Item item : this.getTodosItensCadastradosEmReceptor()) {
            if (item.getId() == idItemNecessario) {
                return item;
            }
        }
        throw new IllegalArgumentException("Item nao encontrado: " + idItemNecessario + ".");
    }

    private Item getItemDoado(int idItemDoado) {
        for (Item item : this.getTodosItensCadastradosEmDoador()) {
            if (item.getId() == idItemDoado) {
                return item;
            }
        }
        throw new IllegalArgumentException("Item nao encontrado: " + idItemDoado  + ".");
    }

    private boolean isDoador(String idDoador) {
        return (this.usuarioController.getUsuario(idDoador).getStatus().equals("doador"));
    }

    private boolean isReceptor(String idReceptor) {
        return (this.usuarioController.getUsuario(idReceptor).getStatus().equals("receptor"));
    }

    private void validarMatch(String idReceptor, int idItemNecessario) {
        this.validarReceptor(idReceptor);
        Validador.validarInteiro(idItemNecessario, "Entrada invalida: id do item nao pode ser negativo.");
        this.validarExistenciaItem(idReceptor, idItemNecessario);
    }
}
