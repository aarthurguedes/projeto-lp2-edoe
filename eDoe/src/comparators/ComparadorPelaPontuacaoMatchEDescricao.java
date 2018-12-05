package comparators;

import java.util.Comparator;

import eDoe.Item;

public class ComparadorPelaPontuacaoMatchEDescricao implements Comparator<Item>{

	@Override
	public int compare(Item o1, Item o2) {
		if (o2.getPontuacaoMatch() == o1.getPontuacaoMatch()) {
			return o1.getId() - o2.getId();
		}
		return  o2.getPontuacaoMatch() - o1.getPontuacaoMatch();
	}
}
