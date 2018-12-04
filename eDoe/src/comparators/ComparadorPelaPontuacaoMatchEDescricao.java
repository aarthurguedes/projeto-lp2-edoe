package comparators;

import java.util.Comparator;

import eDoe.Item;

public class ComparadorPelaPontuacaoMatchEDescricao implements Comparator<Item>{

	@Override
	public int compare(Item o1, Item o2) {
		if (o1.getPontuacaoMatch() == o2.getPontuacaoMatch()) {
			return o1.getId() - o2.getId();
		}
		return  o1.getPontuacaoMatch() - o2.getPontuacaoMatch();
	}
}
