package util;

/**
 * Classe criada para armazenar utilidades do codigo.
 *
 * @author Antonio Bertino de Vasconcelos Cabral Neto
 * @author Arthur Silva Lima Guedes
 * @author Danilo de Menezes Freitas
 * @author Talita Galdino Gouveia
 */
public class Util {
    /**
     * Metodo criado para formatar uma String para um padrao unico. No caso, Em letras minusculas e sem espacos no inicio 
     * e no fim.
     *
     * @param msg String com mensagem a ser formatada.
     * @return String com mensagem formatada no padrao previante dito.
     */
    public static String formatString(String msg) {
        return msg.toLowerCase().trim();
    }
}
