package comparators;

import eDoe.Doacao;

import java.util.Comparator;

public class ComparadorPelaDataDoacao implements Comparator<Doacao> {
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
