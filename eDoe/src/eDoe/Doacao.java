package eDoe;

import java.io.Serializable;
import java.util.Objects;

public class Doacao implements Serializable {

    public static final long serialVersionUID = 2076231716948746056L;
    private String data;
    private String doador;
    private String item;
    private int quantidade;
    private String receptor;

    public Doacao(String data, String doador, String item, int quantidade, String receptor) {
        this.data = data;
        this.doador = doador;
        this.item = item;
        this.quantidade = quantidade;
        this.receptor = receptor;
    }

    @Override
    public String toString() {
        return this.data + " - doador: " + this.doador + ", item: " + this.item + ", quantidade: " + this.quantidade + ", receptor: " + this.receptor;
    }

    public String getData() {
        return this.data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doacao doacao = (Doacao) o;
        return Double.compare(doacao.quantidade, quantidade) == 0 &&
                Objects.equals(data, doacao.data) &&
                Objects.equals(doador, doacao.doador) &&
                Objects.equals(item, doacao.item) &&
                Objects.equals(receptor, doacao.receptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, doador, item, quantidade, receptor);
    }
}
