package comparators;

import eDoe.Doacao;

import java.util.Comparator;

/**
* Representacao de um comparador de doacoes pela data.
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class ComparadorPelaDataDoacao implements Comparator<Doacao> {
	
	/**
	 * Metodo responsavel por verificar a igualdade entre as datas de duas doacoes.
	 */
    @Override
    public int compare(Doacao o1, Doacao o2) {
        if (o1.getData().split("/")[2] == o2.getData().split("/")[2]) {
            if (o1.getData().split("/")[1] == o2.getData().split("/")[1]) {
                return o1.getData().split("/")[0].compareTo(o2.getData().split("/")[0]);
            } else {
                return o1.getData().split("/")[1].compareTo(o2.getData().split("/")[1]);
            }
        } else {
            return o1.getData().split("/")[2].compareTo(o2.getData().split("/")[2]);
        }
    }
}
