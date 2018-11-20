package controllers;

import java.util.HashMap;
import java.util.Map;

import abstrato.Usuario;

/**
* Representacao de um controlador de usuarios. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class UsuarioController {
	
	/**
	* Mapa de usuarios.
	*/
	private Map<String, Usuario> usuarios;
	
	/**
	* Constroi o controlador dos usuarios.
	*
	*/
	public UsuarioController() {
		this.usuarios = new HashMap<>();
	}
	
	/**
	* @return o mapa de usuarios
	*/
	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}
}
