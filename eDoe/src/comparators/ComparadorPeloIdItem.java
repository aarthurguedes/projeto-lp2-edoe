package comparators;

import eDoe.Item;

import java.util.Comparator;

/**
* Representacao de um comparador de item pelo id. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class ComparadorPeloIdItem implements Comparator<Item> {
	
	/**
	 * Metodo responsavel por comparar a igualdade entre dois itens pelo seu id.
	 */
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getId() - o2.getId();
    }
}
