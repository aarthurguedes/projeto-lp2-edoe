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
	
	public static void validarString(String string, String msgErro) {
		validarStringNaoNula(string, msgErro);
		validarStringNaoVazia(string, msgErro);
	}
	
	private static void validarInteiroPositivo(int inteiro, String msgErro) {
		if (inteiro < 0 ) {
			throw new IllegalArgumentException(msgErro);
		}
	}
}
