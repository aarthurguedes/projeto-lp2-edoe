package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

public static void readFile(String pasta, String arquivo) {
    try {
        File csvFile = new File(pasta + File.separator + arquivo);
        Scanner sc = new Scanner(csvFile);
        String linha = sc.nextLine();  //Leitura do Cabeçalho

        while (sc.hasNextLine()) {
            linha = sc.nextLine();
            System.out.println(linha);
        }

    } catch (FileNotFoundException e) {
        System.err.println("Arquivo nao encontrado");
    }
    }
}
