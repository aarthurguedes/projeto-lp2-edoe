package validacao;

import java.util.Map;

import abstrato.Usuario;

/**
* Representacao de um validador dos controladores.
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class ValidadorControllers {
	
	/**
	* Objeto validador base.
	*/
	private ValidadorBase vb;
	
	/**
	* Constroi o validador dos controladores.
	*
	*/
	public ValidadorControllers() {
		this.vb = new ValidadorBase();
	}
	
	/**
	* Metodo auxiliar que valida os parametros passados para o cadastramento do usuario doador.
	* 
	* @param id a identificacao do usuario doador
	* @param nome o nome do usuario doador
	* @param email o email do usuario doador
	* @param celular o numero do celular do usuario doador
	* @param classe a classe do usuario doador
	* @param usuarios o mapa de usuarios
	*/
	public void validaCadastramentoDoador(String id, String nome, String email, String celular, String classe, Map<String, Usuario> usuarios) {
		vb.validaUsuario(id, nome, email, celular, classe);
		if (usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id);
		}
	}
}
