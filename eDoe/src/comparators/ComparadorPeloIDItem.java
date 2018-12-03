package comparators;

import eDoe.Item;

import java.util.Comparator;

public class ComparadorPeloIDItem implements Comparator<Item> {
	
	/**
	 * Metodo responsavel por comparar a igualdade entre dois itens pelo seu id.
	 */
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getId() - o2.getId();
    }
}
