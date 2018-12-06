package comparators;

import eDoe.Item;
import util.Util;

import java.util.Comparator;

/**
* Representacao de um comparador de item pela quantidade e pela descricao. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class ComparadorPelaQuantidadeEDescricaoDoItem implements Comparator<Item> {
	
	/**
	 * Metodo responsavel por comparar a igualdade entre dois itens pela sua descricao e quantidade.
	 */
    @Override
    public int compare(Item o, Item o2) {
        if (o.getQuantidade() == o2.getQuantidade()) {
            return Util.formatString(o.getDescricao()).compareTo(Util.formatString(o2.getDescricao()));
        }
        return o2.getQuantidade() - o.getQuantidade();
    }
}
