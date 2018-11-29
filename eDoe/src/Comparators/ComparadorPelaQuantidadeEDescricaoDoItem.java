package Comparators;

import eDoe.Item;
import util.Util;

import java.util.Comparator;

public class ComparadorPelaQuantidadeEDescricaoDoItem implements Comparator<Item> {
	
    @Override
    public int compare(Item o, Item o2) {
        if (o.getQuantidade() == o2.getQuantidade()) {
            return Util.formatString(o.getDescricao()).compareTo(Util.formatString(o2.getDescricao()));
        }
        return o2.getQuantidade() - o.getQuantidade();
    }
}
