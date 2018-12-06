package comparators;

import java.util.Comparator;

import eDoe.Item;

/**
* Representacao de um comparador de item pela pontuacao e, se necessario, pelo id. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class ComparadorPelaPontuacaoMatchEId implements Comparator<Item>{

	/**
	 * Metodo responsavel por comparar a igualdade entre dois itens pela sua pontuacao de match e, caso essa seja igual, pelo id.
	 */
	@Override
	public int compare(Item o1, Item o2) {
		if (o2.getPontuacaoMatch() == o1.getPontuacaoMatch()) {
			return o1.getId() - o2.getId();
		}
		return  o2.getPontuacaoMatch() - o1.getPontuacaoMatch();
	}
}
