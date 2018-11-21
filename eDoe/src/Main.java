import controllers.UsuarioController;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        UsuarioController uc = new UsuarioController();

        System.out.println(uc.cadastrarDoador("123456", "Neto", "neto@gmail.com", "98277948", "IGREJA"));
        System.out.println(uc.pesquisaUsuarioPorNome("Neto"));
        System.out.println(uc.pesquisaUsuarioPorId("123456"));
        System.out.println(uc.cadastrarDoador("1234567", "Neto", "neto@outlook.com", "98277948", "IGREJA"));
        System.out.println(uc.pesquisaUsuarioPorNome("Neto"));



        //EXEMPLO DE LEITURA DE ARQUIVO CSV POR LINENUMBERREADER
        LineNumberReader lr = new LineNumberReader(new FileReader("csv" + File.separator + "teste.csv"));

        String saida = "";
        String linhaLida = lr.readLine();

        do {
           linhaLida = lr.readLine();

           if (linhaLida != null) {
               String[] lista = linhaLida.split(",");
               String matricula = lista[0];
               String nome = lista[1];
               String nota1= lista[2];
               String nota2 = lista[3];
               String nota3 = lista[4];

               System.out.println(nome);
               System.out.println(matricula);
               System.out.println(nota1);
               System.out.println(nota2);
               System.out.println(nota3);
           }
        } while (linhaLida != null);
    }
}

