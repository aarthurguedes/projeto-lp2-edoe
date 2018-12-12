package controllers;

import eDoe.Item;  
import eDoe.Usuario;
import util.Validador;

import java.util.ArrayList;
import java.util.List;

/**
* Representacao de um controlador geral do sistema Edoe. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/

public class EDoeController {
	
    private ItemController itemController;
    private UsuarioController usuarioController;
    private DoacaoController doacaoController;

    /**
     * Constroi um controlador do Edoe.
     */
    public EDoeController() {
        this.itemController = new ItemController();
        this.usuarioController = new UsuarioController();
        this.doacaoController = new DoacaoController();
    } 

    /**
     * Metodo responsavel por iniciar o sistema, fazendo a leitura dos arquivos
     * nos controladores de usuario, item e doacao.
     */
    public void iniciaSistema() {
        this.usuarioController.inicializaSistema();
        this.itemController.inicializaSistema();
        this.doacaoController.inicializaSistema();
    }

    /**
     * Metodo responsavel por finalizar o sistema, escrevendo arquivos
     * nos controladores de usuario, item e doacao.
     */
    public void finalizaSistema() {
        this.usuarioController.finalizaSistema();
        this.itemController.finalizaSistema();
        this.doacaoController.finalizaSistema();
    }

    /**
     * Metodo responsavel por chamar o metodo de cadastrar um doador.
     * @param id representa a identificacao do doador
     * @param nome representa o nome do doador
     * @param email representa o email do doador
     * @param celular representa o celular do doador
     * @param classe representa a classe do doador
     * @return String que representa o doador que foi cadastrado
     */
    public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
        return this.usuarioController.cadastrarDoador(id, nome, email, celular, classe);
    }

    /**
     * Metodo responsavel por chamar o metodo de pesquisar um usuario pela identificacao.
     * @param id representa a identificacao do usuario
     * @return String que representa o usuario que estava sendo pesquisado.
     */
    public String pesquisaUsuarioPorId(String id) {
        return this.usuarioController.pesquisarUsuarioPorId(id);
    }

    /**
     * Metodo responsavel por chamar o metodo de pesquisar um usuario pelo seu nome.
     * @param nome representa o nome do usuario
     * @return String que representa o usuario que estava sendo pesquisado.
     */ 
    public String pesquisaUsuarioPorNome(String nome) {
        return this.usuarioController.pesquisarUsuarioPorNome(nome);
    }

    /**
     * Metodo responsavel por chamar o metodo de atualizar um usuario.
     * @param id representa a identificacao do usuario
     * @param nome representa o nome do usuario
     * @param email representa o email do usuario
     * @param celular representa o celular do usuario
     * @return String que representa o usuario atualizado.
     */
    public String atualizaUsuario(String id, String nome, String email, String celular) {
        return this.usuarioController.atualizarUsuario(id, nome, email, celular);
    }

    /**
     * Metodo responsavel por chamar o metodo que remove um usuario
     * @param id representa a identificacao do usuario
     */
    public void removeUsuario(String id) {
        this.usuarioController.removerUsuario(id);
    }

    /**
     * Metodo responsavel por chamar o metodo que ira ler os receptores
     * @param caminho representa o caminho do arquivo
     */
    public void lerReceptores(String caminho) {
        this.usuarioController.lerReceptores(caminho);
    }

    /**
     * Metodo responsavel por chamar o metodo de adicionar um descritor
     * @param descricao representa a descricao
     */
    public void adicionaDescritor(String descricao) {
        this.itemController.adicionarDescritor(descricao);
    }
    
    private boolean isDoador(String idDoador) {
        return (this.usuarioController.getUsuario(idDoador).getStatus().equals("doador"));
    }

    private void validarDoador(String idDoador) {
        if (!this.isDoador(idDoador)) {
            throw new IllegalArgumentException("O Usuario deve ser um doador: " + idDoador + ".");
        }
    }

    /**
     * Metodo responsavel por chamar o metodo de adicionar um item para doacao.
     * @param idDoador representa a identificacao do doador
     * @param descricao representa a descricao do item
     * @param quantidade representa a quantidade daquele item
     * @param tags representa as tags do item
     * @return String que representa o item adicionado.
     */
    public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
        this.validarDoador(idDoador);
    	return this.itemController.cadastrarItem(this.usuarioController.getUsuario(idDoador),descricao, quantidade, tags);
    }

    /**
     * Metodo responsavel por chamar o metodo de exibir um item.
     * @param idItem representa a identificacao do item.
     * @param idDoador representa a identificacao do doador
     * @return String que representa o item a ser exibido.
     */
    public String exibeItem(int idItem, String idDoador) {
        return this.itemController.exibirItem(idItem, this.usuarioController.getUsuario(idDoador));
    }

    /**
     * Metodo responsavel por chamar o metodo de atualizar um item para doacao
     * @param idItem representa a identificacao do item
     * @param idDoador representa a identificacao do doador
     * @param quantidade representa a quantidade daquele item
     * @param tags representa as tags do item
     * @return String que representa o item atualizado.
     */
    public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
        this.validarDoador(idDoador);
        return this.itemController.atualizarItem(idItem, this.usuarioController.getUsuario(idDoador), quantidade, tags);
    }

    /**
     * Metodo responsavel por chamar o metodo de remover um item para doacao
     * @param idItem representa a identificacao do item
     * @param idDoador representa a identificacao do doador
     */
    public void removeItemParaDoacao(int idItem, String idDoador) {
        this.validarDoador(idDoador);
        this.itemController.removerItem(idItem, this.usuarioController.getUsuario(idDoador));
    } 

    /**
     * Metodo responsavel por chamar o metodo de ler os descritores de itens para doacao.
     * @return String que representa os descritores(ordenados) para doacao.
     */
    public String listaDescritorDeItensParaDoacao() {
        return this.itemController.listarDescritorDeItensParaDoacao(this.getTodosItensCadastradosEmDoador());
    }

    /**
     * Metodo responsavel por chamar o metodo de listar os itens para doacao.
     * @return String que representa a lista(ordenada) dos itens disponiveis para doacao.
     */
    public String listaItensParaDoacao() {
        return this.itemController.listarItensParaDoacao(this.getTodosItensCadastradosEmDoador());
    }

    /**
     * Metodo responsavel chamar o metodo de pesquisar um item para doacao pela descricao
     * @param descricao representa a descricao do item
     * @return String que representa o item que estava sendo procurado
     */
    public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
        return this.itemController.pesquisarItemParaDoacaoPorDescricao(descricao, this.getTodosItensCadastradosEmDoador());
    }
    
    private boolean isReceptor(String idReceptor) {
        return (this.usuarioController.getUsuario(idReceptor).getStatus().equals("receptor"));
    }
    
    private void validarReceptor(String idReceptor) {
        if (!this.isReceptor(idReceptor)) {
            throw new IllegalArgumentException("O Usuario deve ser um receptor: " + idReceptor + ".");
        }
    }

    /**
     * Metodo responsavel por chamar o metodo de adicionar um item necessario.
     * @param idReceptor representa a identificacao do receptor
     * @param descricao representa a descricao do item
     * @param quantidade representa a quantidade do item
     * @param tags representa as tags do item
     * @return String que representa o item que foi adicionado
     */
    public int adicionaItemNecessario(String idReceptor, String descricao, int quantidade, String tags) {
        this.validarReceptor(idReceptor);
    	return this.itemController.cadastrarItem(this.usuarioController.getUsuario(idReceptor),descricao, quantidade, tags);
    }

    /**
     * Metodo responsavel chamar o metodo de listar os itens necessarios
     * @return String que representa a lista(ordenada) dos itens necessarios.
     */
    public String listaItensNecessarios() {
        return itemController.listarItensNecessarios(this.getTodosItensCadastradosEmReceptor());
    }

    /**
     * Metodo responsavel por chamar o metodo que atualiza um item necessario
     * @param idReceptor representa a identificacao do receptor
     * @param idItem representa a identificacao do item
     * @param quantidade representa a quantidade daquele item
     * @param tags representa as tags do item
     * @return String que representa o item atualizado
     */
    public String atualizaItemNecessario(String idReceptor, int idItem, int quantidade, String tags) {
        return this.itemController.atualizarItem(idItem, this.usuarioController.getUsuario(idReceptor), quantidade, tags);
    }

    /**
     * Metodo responsavel por cahamr o metodo que remove um item necessario.
     * @param idReceptor representa a identificacao do receptor
     * @param idItem representa a identificacao do item
     */
    public void removeItemNecessario(String idReceptor, int idItem) {
        this.validarReceptor(idReceptor);
        this.itemController.removerItem(idItem, this.usuarioController.getUsuario(idReceptor));
    }

    /**
     * Metodo responsavel por realizar uma doacao.
     * @param idItemNecessario representa a identificacao do item necessario
     * @param idItemDoado representa a identificacao do item doado
     * @param data representa a data que foi realizada a doacao
     * @return String que representa a doacao que foi realizada.
     */
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

    /**
     * Metodo responsavel por chamar o metodo que lista as doacoes
     * @return String que representa a lista(ordenada) de doacoes.
     */
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

    private void validarMatch(String idReceptor, int idItemNecessario) {
        this.validarReceptor(idReceptor);
        Validador.validarInteiro(idItemNecessario, "Entrada invalida: id do item nao pode ser negativo.");
        this.validarExistenciaItem(idReceptor, idItemNecessario);
    }
    
    /**
     * Metodo resonponsavel por verificar o match entre os itens disponiveis e o item do receptor. 
     * @param idReceptor representa a identificacao do receptor
     * @param idItemNecessario representa a identificacao do item necessario
     * @return String que representa o item.
     */
    public String match(String idReceptor, int idItemNecessario) {
    	this.validarMatch(idReceptor, idItemNecessario);
    	return this.itemController.match(this.getTodosItensCadastradosEmDoador(), this.usuarioController.getUsuario(idReceptor).getItem(idItemNecessario));
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
}
