package eDoe;

import java.util.Objects;

public class Descritor {
    private String descritor;
    private int quantidade;

    public Descritor(String descritor) {
        this.descritor = descritor;
        this.quantidade = 1;
    }

    public void contaUm() {
        this.quantidade ++;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public String getDescritor() {
        return this.descritor;
    }

    public void setDescritor(String descritor) {
        this.descritor = descritor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Descritor descritor1 = (Descritor) o;
        return descritor.equals(descritor1.descritor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descritor);
    }
}
