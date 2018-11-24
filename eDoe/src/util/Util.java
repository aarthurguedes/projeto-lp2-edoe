package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Random;
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

    /**
     * Criado para gerar uma String aleatoria de 7 digitos;
     * @return String aleatoria com de tamanho 7.
     */
    private static String randomString() {
        Random random = new Random();
        String[] letras = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"};

        String aleatorio = "";
        for (int i = 0; i < 7; i++) {
            int randomPos = random.nextInt(36);
            aleatorio += letras[randomPos];
        }
        return aleatorio;
    }

    /**
     * Criado para retornar id unico para adicionar em um mapa.
     *
     * @param mapa que possui id para comparação, a modo de criar uma identificação unica.
     * @return String representando um id unico.
     */
    public static String CriaIDUnico(Map<String, Object> mapa) {
        boolean possui = true;

        String id = "";
        while (possui) {
            id = randomString();
            if (!mapa.containsKey(id)) {
                possui = false;
            }
        }
        return id;
    }
}

