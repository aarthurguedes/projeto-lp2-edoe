package validacao;

import java.util.Map;

import abstrato.Usuario;

public class ValidadorControllers {
	
	/**
	* Objeto validador.
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
	* Método auxiliar que valida os parâmetros passados para o cadastramento do usuario.
	* 
	* @param id a identificacao do usuario
	* @param nome o nome do usuario
	* @param email o email do usuario
	* @param celular o numero do celular do usuario
	* @param classe a classe do usuario
	* @param usuarios o mapa de usuarios
	*/
	public void validaCadastramentoDoador(String id, String nome, String email, String celular, String classe, Map<String, Usuario> usuarios) {
		vb.validaUsuario(id, nome, email, celular, classe);
		if (usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id);
		}
	}
}
