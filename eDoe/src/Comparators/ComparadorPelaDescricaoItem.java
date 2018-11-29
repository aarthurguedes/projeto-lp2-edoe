package Comparators;

import eDoe.Item;
import util.Util;

import java.util.Comparator;

public class ComparadorPelaDescricaoItem implements Comparator<Item> {
	
    @Override
    public int compare(Item o1, Item o2) {
        return Util.formatString(o1.getDescricao()).compareTo(Util.formatString(o2.getDescricao()));
    }
}
