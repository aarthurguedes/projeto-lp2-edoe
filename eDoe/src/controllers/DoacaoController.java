package controllers;

import comparators.ComparadorPelaDataDoacao;
import eDoe.Descritor;
import eDoe.Doacao;
import eDoe.Item;

import java.io.*;
import java.util.*;

public class DoacaoController {
    
	private Set<Doacao> doacoes;

    public DoacaoController() {
        this.doacoes = new HashSet<>();
    } 

    public void inicializaSistema() {
        this.lerArquivos();
    }

    public void finalizaSistema() {
        this.escreverArquivos();
    }

    public String realizaDoacao(Item itemNecessario, Item itemDoado, String data) {
        Doacao doacao = new Doacao(data, itemDoado.getIdUsuario(), itemNecessario.getDescricao(), this.getQuantidade(itemNecessario, itemDoado), itemNecessario.getIdUsuario());
        this.doacoes.add(doacao);
        return doacao.toString();
    }

    private int getQuantidade(Item itemNecessario, Item itemDoado) {
        int qtdItemNecessario = itemNecessario.getQuantidade();
        int qtdItemDoado = itemDoado.getQuantidade();
        int qtdDoacao = 0;

        if (qtdItemDoado > qtdItemNecessario) {
            qtdDoacao = qtdItemNecessario;
            itemDoado.setQuantidade(itemDoado.getQuantidade() - qtdItemNecessario);
            itemNecessario.setQuantidade(0);
        } else if (qtdItemDoado == qtdItemNecessario) {
            qtdDoacao = qtdItemDoado;
            itemDoado.setQuantidade(0);
            itemNecessario.setQuantidade(0); 
        } else if (qtdItemNecessario > qtdItemDoado) {
            qtdDoacao = qtdItemDoado;
            itemNecessario.setQuantidade(itemNecessario.getQuantidade() - qtdItemDoado);
            itemDoado.setQuantidade(0);
        }

        return qtdDoacao;
    }
 
    public String listaDoacoes() {
        List<Doacao> doacaosList = new ArrayList<>(this.doacoes);
        Collections.sort(doacaosList, new ComparadorPelaDataDoacao());

        String saida = "";

        for (Doacao doacao : doacaosList) {
            saida += doacao.toString() + " | ";
        }

        return saida.substring(0, saida.length() - 3);
    }

    private void escreverArquivos() {
        ObjectOutputStream doisDoacao = null;

        try {
            doisDoacao = new ObjectOutputStream(new FileOutputStream( "saves" + File.separator + "doacaoController.dat"));
            doisDoacao.writeObject(this.doacoes);

        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void lerArquivos() {
        ObjectInputStream oisDoacao = null;

        try {
            oisDoacao = new ObjectInputStream(new FileInputStream("saves" + File.separator + "doacaoController.dat"));
            Set<Doacao> doacao = (HashSet<Doacao>) oisDoacao.readObject();
            this.doacoes = doacao;

        } catch (IOException e) {
            this.escreverArquivos();
            this.inicializaSistema();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
