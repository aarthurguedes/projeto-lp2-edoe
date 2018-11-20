package util;

/**
 * Classe criada para armazenar utilidades do codigo.
 *
 * @author Antonio Bertino de Vasconcelos Cabral Neto
 * @author Arthur Silva Guedes
 * @author Danilo
 * @author Talita
 */
public class util {
    /**
     * Metodo criado para formatar uma String para um padrao unico. No caso, Em letras minusculas e sem espacos no inicio e no fim.
     *
     * @param msg String com mensagem a ser formatada.
     * @return String com mensagem formatada no padrao previante dito.
     */
    public static String format(String msg) {
        return msg.toLowerCase().trim();
    }
}
