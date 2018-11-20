package validacao;

/**
* Representação de um validador das classes bases.
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class ValidadorBase {
	
	private void validaNome(String nome) {
		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada ivalida: nome nao pode ser vazio ou nulo.");
		}
	}
	
	private void validaEmail(String email) {
		if (email == null || email.trim().equals("")) {
			throw new IllegalArgumentException("Entrada ivalida: email nao pode ser vazio ou nulo.");
		}
	}
	
	private void validaCelular(String celular) {
		if (celular == null || celular.trim().equals("")) {
			throw new IllegalArgumentException("Entrada ivalida: celular nao pode ser vazio ou nulo.");
		}
	}
	
	private void validaClasse(String classe) {
		if (classe == null || classe.trim().equals("")) {
			throw new IllegalArgumentException("Entrada ivalida: classe nao pode ser vazia ou nula.");
		} else if (classe.equals("EMPRESA")) {
			throw new IllegalArgumentException("Entrada ivalida: classe invalida.");
		}
	}
	
	private void validaId(String id) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	* Método auxiliar que valida os parâmetros passados para a construção do usuario.
	* 
	* @param id a identificacao do usuario
	* @param nome o nome do usuario
	* @param email o email do usuario
	* @param celular o numero do celular do usuario
	* @param classe a classe do usuario
	*/
	public void validaUsuario(String id, String nome, String email, String celular, String classe) {
		validaNome(nome);
		validaEmail(email);
		validaCelular(celular);
		validaClasse(classe);
		validaId(id);
	}
}
