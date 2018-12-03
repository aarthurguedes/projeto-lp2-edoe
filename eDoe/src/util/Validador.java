package util;

public class Validador {
	
	private static void validarStringNaoNula(String string, String msgErro) {
		if (string == null) {
			throw new IllegalArgumentException(msgErro);
		}
	}
	
	private static void validarStringNaoVazia(String string, String msgErro) {
		if (string.trim().equals("")) {
			throw new IllegalArgumentException(msgErro);
		}
	}
	
	/**
     * Valida uma String, verificando se a mesma é nula ou vazia, caso seja, lanca uma excecao.
     *
     * @param string a String a ser validada.
     * @param msgErro a mensagem de erro a ser exibida caso a string seja vazia ou nula.
     */
	public static void validarString(String string, String msgErro) {
		validarStringNaoNula(string, msgErro);
		validarStringNaoVazia(string, msgErro);
	}
	
	private static void validarInteiroPositivo(int inteiro, String msgErro) {
		if (inteiro <= 0 ) {
			throw new IllegalArgumentException(msgErro);
		}
	}
	
	/**
     * Valida um inteiro, verificando se o mesmo é menor ou igual a 0, caso seja, lanca uma excecao.
     *
     * @param inteiro o valor int a ser validado.
     * @param msgErro a mensagem de erro a ser exibida caso o inteiro seja menor ou igual a 0.
     */
	public static void validarInteiro(int inteiro, String msgErro) {
		validarInteiroPositivo(inteiro, msgErro);
	}
}
