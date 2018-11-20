package controllers;

import java.util.HashMap;
import java.util.Map;

import abstrato.Usuario;
import eDoe.Doador;
import validacao.ValidadorControllers;

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
	* Objeto validador.
	*/
	private ValidadorControllers vc;
	
	/**
	* Constroi o controlador dos usuarios.
	*
	*/
	public UsuarioController() {
		this.usuarios = new HashMap<>();
		this.vc = new ValidadorControllers();
	}
	
	/**
	* @return o mapa de usuarios
	*/
	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}
	
	/**
	* Valida os parametros passados e cadastra o doador.
	*
	* @param id a identificacao do usuario
	* @param nome o nome do usuario
	* @param email o email do usuario
	* @param celular o numero do celular do usuario
	* @param classe a classe do usuario
	*/
	public String cadastrarDoador(String id, String nome, String email, String celular, String classe) {
        vc.validaCadastramentoDoador(id, nome, email, celular, classe, usuarios);
        Usuario doador = new Doador(id, nome, email, celular, classe);
        usuarios.put(id, doador);
        return id;
    }
}
