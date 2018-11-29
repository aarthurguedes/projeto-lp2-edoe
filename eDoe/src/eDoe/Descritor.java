package eDoe;

import util.Util;

import java.util.Objects;

public class Descritor implements Comparable<Descritor> {
   
	private String descricao;
    private int quantidade;

    public Descritor(String descricao) {
        this.descricao = descricao;
        this.quantidade = 0;
    }

    public Descritor(String descricao, int quantidade) {
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao += descricao;
    }

    @Override
    public String toString() {
        return this.quantidade + " - " + this.descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Descritor descritor1 = (Descritor) o;
        return descricao.equals(descritor1.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao);
    }

    @Override
    public int compareTo(Descritor o) {
        return Util.formatString(this.descricao).compareTo(Util.formatString(o.descricao));
    }
}
