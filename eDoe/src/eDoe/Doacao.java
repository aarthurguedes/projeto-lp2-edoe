package eDoe;

import java.io.Serializable;
import java.util.Objects;

import util.Validador;

/**
* Representacao de uma doacao que ira possuir uma data, um doador, um item, 
* a quantidade do item e o seu receptor. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class Doacao implements Serializable {

    public static final long serialVersionUID = 2076231716948746056L;
    private String data;
    private String doador;
    private String item;
    private int quantidade;
    private String receptor;

    /**
     * Constroi uma doacao
     * @param data representa a data que a doacao foi realizada
     * @param doador representa o doador
     * @param item representa o item que sera doado
     * @param quantidade representa a quantidade daquele item
     * @param receptor representa o receptor da doacao
     */
    public Doacao(String data, String doador, String item, int quantidade, String receptor) {
        Validador.validarString(data, "Entrada invalida: data nao pode ser vazia ou nula.");
    	
    	this.data = data;
        this.doador = doador;
        this.item = item;
        this.quantidade = quantidade;
        this.receptor = receptor;
    }
 
	/**
	 * Retorna a String que representa a doacao. Formato: data - doador: , item: , quantidade: , receptor: 
	 *
	 * @return a representacao em String da doacao.
	 */
    @Override
    public String toString() {
        return this.data + " - doador: " + this.doador + ", item: " + this.item + ", quantidade: " + this.quantidade + ", receptor: " + this.receptor;
    }
 
    /**
     * Metodo responsavel por retornar a data atual
     * @return String que representa a data
     */
    public String getData() {
        return this.data;
    } 

	/**
	 * Retorna o boolean que representa se duas doacoes sao iguais.
	 *
	 * @param obj o objeto que representa a outra doacao
	 * @return o valor boolean da igualdade (ou nao) entre duas doacoes.
	 */
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
 
	/**
	 * Retorna o inteiro que representa a posicao da doacao na memoria.
	 *
	 * @return a representacao numerica da doacao.
	 */
    @Override
    public int hashCode() {
        return Objects.hash(data, doador, item, quantidade, receptor);
    }
}
