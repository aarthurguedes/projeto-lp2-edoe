package controllers;

import Comparators.ComparadorPelaDescricaoItem;
import Comparators.ComparadorPelaQuantidadeEDescricaoDoItem;
import eDoe.Descritor;
import eDoe.Item;
import eDoe.Usuario;
import util.Util;
import util.Validador;

import java.util.*;

public class ItemController {
    private Map<String, Descritor> descritores;
    private Validador validador;
    private int idItem;

    public ItemController() {
        this.descritores = new HashMap<>();
        this.validador = new Validador();
        this.idItem = 1;
    }

    public void adicionarDescritor(String descricao) {
        validador.validarString(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");

        for (Descritor descritor: this.descritores.values()) {
            if (descritor.getDescricao().toLowerCase().equals(descricao.toLowerCase())) {
                throw new IllegalArgumentException("Descritor de Item ja existente: " + descritor.getDescricao().toLowerCase() + ".");
            }
        }

        Descritor descritor = new Descritor(Util.formatString(descricao));

        descritores.put(Util.formatString(descricao),descritor);
    }

    private int getIdItensIguais(Usuario usuario, String descricao, String tags) {
        for (Item i : usuario.getItens().values()) {
            if (i.getDescricao().equals(descricao) && i.getTags().equals(tags)) {
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
        validador.validarString(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
        validador.validarInteiro(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");

        Descritor descritor = new Descritor(Util.formatString(descricao));

        if (!this.descritores.containsKey(Util.formatString(descricao))) {
            this.descritores.put(Util.formatString(descricao), new Descritor(Util.formatString(descricao)));
        }

        if (this.getIdItensIguais(usuario, descricao, tags) == 0) {
            usuario.cadastrarItem(this.idItem, descritor, quantidade, tags);
        } else {
            usuario.cadastrarItem(this.getIdItensIguais(usuario, descricao, tags), descritor, quantidade, tags);
        }
        this.idItem++;

        return (this.idItem - 1);
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
        validador.validarInteiro(idItem, "Entrada invalida: id do item nao pode ser negativo.");
        this.validarExistenciaItem(usuario, idItem);

        return usuario.atualizarItem(idItem, quantidade, tags);
    }

    /**
     * Metodo responsavel por remover um item.
     * @param idItem representa a identificacao do item
     */
    public void removerItem(int idItem, Usuario usuario) {
        validador.validarInteiro(idItem, "Entrada invalida: id do item nao pode ser negativo.");

        if (usuario.getItens().size() == 0) {
            throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
        }
        this.validarExistenciaItem(usuario, idItem);

        usuario.removerItem(idItem);
    }

    public String listaDescritorDeItensParaDoacao(Map<String, Usuario> usuarios) {
        this.atualizaQuantidadeDescritores(usuarios);
        List<Descritor> descritorList = new ArrayList<>(this.descritores.values());
        Collections.sort(descritorList);

        String listaDescritores = "";
        for (Descritor descritor : descritorList) {
            listaDescritores += descritor.toString() + " | ";
        }
        return listaDescritores.substring(0, listaDescritores.length() - 3);
    }

    private void atualizaQuantidadeDescritores(Map<String, Usuario> usuarios) {
        List<Descritor> descritorList = new ArrayList<>(this.descritores.values());
        Collections.sort(descritorList);

        for (Usuario usuario: usuarios.values()) {
            for (Descritor descritor : descritorList) {
                for (Item item : usuario.getItens().values()) {
                    if(Util.formatString(item.getDescricao()).equals(Util.formatString(descritor.getDescricao()))) {
                        descritor.setQuantidade(item.getQuantidade());
                    }
                }
            }
        }
    }
    public String listaItensParaDoacao(List<Item> itensCadastrados) {
        Collections.sort(itensCadastrados, new ComparadorPelaQuantidadeEDescricaoDoItem());

        String itensListados = "";
        for (Item item2 : itensCadastrados) {
            itensListados += item2.toString() + ", doador: " + item2.getIdDoador() + " | ";
        }

        return itensListados.substring(0, itensListados.length() -3);

    }


    public String pesquisaItemParaDoacaoPorDescricao(String descricao, List<Item> itensCadastrados) {
        validador.validarString(descricao, "Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");

        Collections.sort(itensCadastrados, new ComparadorPelaDescricaoItem());

        String saida = "";
        for (Item item : itensCadastrados) {
            if(Util.formatString(item.getDescricao()).contains(Util.formatString(descricao))) {
                saida += item.toString() + " | ";
            }
        }
        return saida.substring(0, saida.length() -3);
    }
}
