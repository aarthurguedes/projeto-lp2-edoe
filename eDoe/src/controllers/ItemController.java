package controllers;

import comparators.ComparadorPelaDescricaoItem;
import comparators.ComparadorPelaPontuacaoMatchEId;
import comparators.ComparadorPelaQuantidadeEDescricaoDoItem;
import comparators.ComparadorPeloIdItem;
import eDoe.Descritor;
import eDoe.Item;
import eDoe.Usuario;
import util.Util;
import util.Validador;

import java.io.*;
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
     * Metodo responsavel por inicializar o sistema fazendo a leitura do arquivo.
     */
    public void inicializaSistema() {
        this.lerArquivos();
    }

    /**
     * Metodo responsavel por finalizar o sistema escrevendo no arquivos.
     */
    public void finalizaSistema() {
        this.escreverArquivos();
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
    public String  listarItensParaDoacao(List<Item> itensCadastrados) {
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
        Collections.sort(itensCadastradosParaReceptor, new ComparadorPeloIdItem());

    	String saida = "";
    	for (Item item : itensCadastradosParaReceptor) {
            saida += item.toString() + ", Receptor: " + item.getIdUsuario() + " | ";
        }

        return saida.substring(0, saida.length() -3);
    }
    
    private void adicionaItensComDescritoresIguaisEmLista(List<Item> itens, Item itemReceptor, List<Item> itensMesmoDescritor) {
    	for (Item item : itens) {
    		if(Util.formatString(item.getDescricao()).equals(Util.formatString(itemReceptor.getDescricao()))) {
    			itensMesmoDescritor.add(item);
    		}
    	}
    }
    
    private void getPontuacao(Item itemDoador, Item itemReceptor) {
    	List<String> tagDoadorList = Arrays.asList(itemDoador.getTags().split(","));
    	List<String> tagReceptorList = Arrays.asList(itemReceptor.getTags().split(","));
    	
    	for (String tagReceptor: tagReceptorList) {
    		for (String tagDoador: tagDoadorList) {
    			if (tagReceptor.equals(tagDoador) && tagReceptorList.indexOf(tagReceptor) == tagDoadorList.indexOf(tagDoador)) {
    				itemDoador.setPontuacaoMatch(itemDoador.getPontuacaoMatch() + 10);
    			} else if (tagDoadorList.contains(tagReceptor)) {
    				itemDoador.setPontuacaoMatch(itemDoador.getPontuacaoMatch() + 5);
    			}
    		}
    	}
    }
    
    /**
     * Metodo responsavel por identificar possiveis matches entre os itens para doacao e os necessarios.
     * @param itens representa a lista de itens cadastrados para doacao
     * @param itemReceptor o item necessario do receptor
     * @return string que representa a lista dos matches
     */
    public String match(List<Item> itens, Item itemReceptor) {
    	List<Item> itensMesmoDescritor = new ArrayList<>();
    	adicionaItensComDescritoresIguaisEmLista(itens, itemReceptor, itensMesmoDescritor);
    	
    	for (Item item : itensMesmoDescritor) {
    		getPontuacao(item, itemReceptor);
    	}
 
    	Collections.sort(itensMesmoDescritor, new ComparadorPelaPontuacaoMatchEId());
    	
    	String saida = "";
    	for (Item item : itensMesmoDescritor) {
    		saida += item.toString() + ", doador: " + item.getIdUsuario() + " | ";
    	}

    	if (saida.length() > 0) {
            return saida.substring(0, saida.length() - 3);
        } else {
    	    return saida;
        }
    }
    
    private void escreverArquivos() {
        ObjectOutputStream oosDescritores = null;

        try {
            oosDescritores = new ObjectOutputStream(new FileOutputStream( "saves" + File.separator + "itemController.dat"));
            oosDescritores.writeObject(this.descritores);

        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void lerArquivos() {
        ObjectInputStream oisDescritores = null;

        try {
            oisDescritores = new ObjectInputStream(new FileInputStream("saves" + File.separator + "itemController.dat"));
            Map<String, Descritor> descritores = (HashMap<String, Descritor>) oisDescritores.readObject();
            this.descritores = descritores;

        } catch (IOException e) {
            this.escreverArquivos();
            this.inicializaSistema();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
