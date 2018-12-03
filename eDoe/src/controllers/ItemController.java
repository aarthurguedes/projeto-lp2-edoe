package controllers;

import comparators.ComparadorPelaDescricaoItem;
import comparators.ComparadorPelaQuantidadeEDescricaoDoItem;
import comparators.ComparadorPeloIDItem;
import eDoe.Descritor;
import eDoe.Item;
import eDoe.Usuario;
import util.Util;
import util.Validador;

import java.util.*;

/**
* Representacao de um controlador de itens. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class ItemController {
	private Map<String, Descritor> descritores;
    private int idItemDoacao;

    /**
    * Constroi um controlador de itens.
    */
    public ItemController() {
        this.descritores = new HashMap<>();
        this.idItemDoacao = 1;
    }

    /**
     * Metodo responsavel por retornar o valor atual do map dos descritores.
     * @return valor atual do map de descritores
     */
    public Map<String, Descritor> getDescritores() {
    	return this.descritores;
    }

    /**
     * Metodo responsavel por validar e adicionar um descritor ao map de descricoes.
     * @param descricao representa a descricao que sera adicionada
     */
    public void adicionarDescritor(String descricao) {
    	Validador.validarString(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");

        for (Descritor descritor: this.descritores.values()) {
            if (descritor.getDescricao().toLowerCase().equals(descricao.toLowerCase())) {
                throw new IllegalArgumentException("Descritor de Item ja existente: " + descritor.getDescricao().toLowerCase() + ".");
            }
        }

        descritores.put(Util.formatString(descricao),new Descritor(Util.formatString(descricao)));
    }

    private int getIdItensIguais(Usuario usuario, String descricao, String tags) {
        Descritor d = new Descritor(Util.formatString(descricao));
    	
    	for (Item i : usuario.getItens().values()) {
            if (i.getDescricao().equals(d.getDescricao()) && i.getTags().equals(tags)) {
                return i.getId();
            }
        }
        return 0;
    }

    /**
     * Metodo responsavel por cadastrar um item e validar seus parametros.
     *
     * @param descricao representa a descricao do item
     * @param quantidade representa a quantidade daquele item
     * @param tags representa as tags do item
     * @return inteiro que representa a identificacao do item
     */
    public int cadastrarItem(Usuario usuario, String descricao, int quantidade, String tags) {
    	Validador.validarString(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
    	Validador.validarInteiro(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");

        if (!this.descritores.containsKey(Util.formatString(descricao))) {
            this.descritores.put(Util.formatString(descricao), new Descritor(Util.formatString(descricao)));
        }
        
        if (this.getIdItensIguais(usuario, descricao, tags) == 0) {
        	usuario.cadastrarItem(this.idItemDoacao, new Descritor(Util.formatString(descricao)), quantidade, tags);
        	
        	this.idItemDoacao++;
        	return (this.idItemDoacao - 1);
        } else {
        	usuario.getItem(this.getIdItensIguais(usuario, descricao, tags)).setQuantidade(quantidade);

        	return this.getIdItensIguais(usuario, descricao, tags);
        }
    }

    private void validarExistenciaItem(Usuario usuario, int id) {
        if (!usuario.containsItem(id)) {
            throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
        }
    }

    /**
     * Metodo responsavel por exibir um item.
     * @param idItem representa a identificacao do item
     * @return String que representa o item a ser exibido
     */
    public String exibirItem(int idItem, Usuario usuario) {
    	this.validarExistenciaItem(usuario, idItem);

        return usuario.exibirItem(idItem);
    }

    /**
     * Metodo responsavel por atualizar um item.
     * @param idItem representa a identificacao do item
     * @param quantidade representa a nova quantidade daquele item
     * @param tags representa as novas tags do item
     * @return string que representa o item atualizado
     */
    public String atualizarItem(int idItem, Usuario usuario, int quantidade, String tags) {
    	Validador.validarInteiro(idItem, "Entrada invalida: id do item nao pode ser negativo.");
        this.validarExistenciaItem(usuario, idItem);

        return usuario.atualizarItem(idItem, quantidade, tags);
    }

    /**
     * Metodo responsavel por remover um item.
     * @param idItem representa a identificacao do item
     */
    public void removerItem(int idItem, Usuario usuario) {
    	Validador.validarInteiro(idItem, "Entrada invalida: id do item nao pode ser negativo.");

        if (usuario.getItens().size() == 0) {
            throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
        }
        
        this.validarExistenciaItem(usuario, idItem);
        usuario.removerItem(idItem);
    }

    /**
     * Metodo responsavel por listar os descritores de itens para adocao.
     * @param itensCadastrados representa a lista de itens cadastrados
     * @return lista de descritores dos itens que estao para adocao.
     */
    public String listarDescritorDeItensParaDoacao(List<Item> itensCadastrados) {
    	this.atualizarQuantidadeDescritores(itensCadastrados);
        
    	List<Descritor> descritorList = new ArrayList<>(this.descritores.values());
        Collections.sort(descritorList);

        String listaDescritores = "";
        for (Descritor descritor : descritorList) {
            listaDescritores += descritor.toString() + " | ";
        }

        this.zeraQuantidadeDescritores();
        return listaDescritores.substring(0, listaDescritores.length() - 3);
    }

    private void atualizarQuantidadeDescritores(List<Item> itensCadastrados) {
        List<Descritor> descritorList = new ArrayList<>(this.descritores.values());
        Collections.sort(descritorList);

        for (Descritor descritor : descritorList) {
            for (Item item : itensCadastrados) {
                if(Util.formatString(item.getDescricao()).equals(Util.formatString(descritor.getDescricao()))) {
                    descritor.setQuantidade(item.getQuantidade());
                }
            }
        }
    }

    /**
     * Criado para zerar a quantidade que cada descritor foi utilizado.
     */
    private void zeraQuantidadeDescritores() {
        for (Descritor descritor : this.descritores.values()) {
            descritor.zeraQuantidade();
        }
    }
    
    /**
     * Metodo responsavel por listar os itens disponiveis para adocao.
     * @param itensCadastrados representa a lista de itens cadastrados no eDoe
     * @return string que representa os itens disponiveis para adocao
     */
    public String listarItensParaDoacao(List<Item> itensCadastrados) {
        Collections.sort(itensCadastrados, new ComparadorPelaQuantidadeEDescricaoDoItem());

        String itensListados = "";
        for (Item item2 : itensCadastrados) {
            itensListados += item2.toString() + ", doador: " + item2.getIdUsuario() + " | ";
        }
        
        return itensListados.substring(0, itensListados.length() -3);
    }
    
    /**
     * Metodo responsavel por pesquisar um item para adocao pela sua descricao.
     * @param descricao representa a descricao do item
     * @param itensCadastrados representa a lista de itens cadastrados
     * @return string que representa o item que estava sendo pesquisado
     */
    public String pesquisarItemParaDoacaoPorDescricao(String descricao, List<Item> itensCadastrados) {
    	Validador.validarString(descricao, "Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");

        Collections.sort(itensCadastrados, new ComparadorPelaDescricaoItem());

        String saida = "";
        for (Item item : itensCadastrados) {
            if(Util.formatString(item.getDescricao()).contains(Util.formatString(descricao))) {
                saida += item.toString() + " | ";
            }
        }
        
        return saida.substring(0, saida.length() -3);
    }

    /**
     * Metodo responsavel por listar os itens necessarios.
     * @param itensCadastradosParaReceptor representa a lista de itens cadastrados para o receptor
     * @return string que representa a lista de itens necessarios
     */
    public String listarItensNecessarios(List<Item> itensCadastradosParaReceptor) {
        Collections.sort(itensCadastradosParaReceptor, new ComparadorPeloIDItem());

    	String saida = "";
    	for (Item item : itensCadastradosParaReceptor) {
            saida += item.toString() + ", Receptor: " + item.getIdUsuario() + " | ";
        }

        return saida.substring(0, saida.length() -3);
    }
}
