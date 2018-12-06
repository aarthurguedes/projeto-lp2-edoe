package comparators;

import eDoe.Item;

import util.Util;

import java.util.Comparator;

/**
* Representacao de um comparador de itens pela descricao.
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class ComparadorPelaDescricaoItem implements Comparator<Item> {
	
	/**
	 * Metodo responsavel por comparar a igualdade entre dois itens, pela sua descricao.
	 */
    @Override
    public int compare(Item o1, Item o2) {
        return Util.formatString(o1.getDescricao()).compareTo(Util.formatString(o2.getDescricao()));
    }
}
