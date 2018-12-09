package eDoe;

import util.Util;

import java.io.Serializable;
import java.util.Objects;

/**
* Representacao de um descritor que possui descricao e quantidade. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class Descritor implements Comparable<Descritor>, Serializable {

    public static final long serialVersionUID = 3412184169915407379L;
    private String descricao;
    private int quantidade;

    /**
     * Constroi um descritor a partir de sua descricao e incializa sua quantidade com 0.
     * @param descricao representa a descricao de descritor
     */
    public Descritor(String descricao) {
        this.descricao = descricao;
        this.quantidade = 0;
    }

    /**
     * Constroi um descritor a partir de sua descricao e de sua quantidade.
     * @param descricao representa a descricao
     * @param quantidade representa a quantidade
     */
    public Descritor(String descricao, int quantidade) {
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    /**
     * Metodo responsavel por atualizar o valor de quantidade.
     * @param quantidade representa a nova quantidade
     */
    public void setQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    /**
     * Método criado para zerar a quantidade que esse descritor foi utilizado.
     */
    public void zeraQuantidade() {
        this.quantidade = 0;
    }

    /**
     * Metodo responsavel por retornar o valor atual de quantidade
     * @return valor atual de quantidade
     */
    public int getQuantidade() {
        return this.quantidade;
    }

    /**
     * Metodo responsavel por retornar a atual descricao.
     * @return valor atual de descricao
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Metodo responsavel por atualizar o valor de descricao, nesse caso, o novo valor de descricao sera somado ao valor anterior.
     * @param descricao representa a nova descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
	* Retorna a String que representa o descritor. Formato: quantidade - descricao
	* 
	* @return a representacao em String do descritor.
	*/
    @Override
    public String toString() {
        return this.quantidade + " - " + this.descricao;
    }

    /**
	* Retorna o boolean que representa se dois descrutores sao iguais, ou seja, se possuem a mesma descricao.
	* 
	* @param o o objeto que representa o outro descritor
	* @return o valor boolean da igualdade (ou nao) entre dois descritores
	*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Descritor descritor1 = (Descritor) o;
        return descricao.equals(descritor1.descricao);
    } 

    /**
	* Retorna o inteiro que representa a posicao do descritor na memoria.
	* 
	* @return a representacao numerica do descritor  
	*/
    @Override
    public int hashCode() {
        return Objects.hash(descricao);
    } 

	/**
	 * Metodo usado para comparar os descritores com base na descricao.
	 *
	 * @param o Objeto usuario para comparacao
	 * @return string usada para comparacao.
	 */
    @Override
    public int compareTo(Descritor o) {
        return Util.formatString(this.descricao).compareTo(Util.formatString(o.descricao));
    } 
}
