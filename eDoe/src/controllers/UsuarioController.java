package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstrato.Usuario;
import eDoe.Doador;
import validacao.ValidadorControllers;
import util.Util;

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
        usuarios.put(Util.formatString(id), doador);
        return id;
    }

    /**
     *  Valida os parametros e procura no sistema, usuarios cadastrados com o nome passado no parametro.
     *  Caso haja mais de um usuario cadastrado com o mesmo nome completo, se retorna uma String com os dois nomes, porem ordenados com quem foi cadastrado primeiro..
     *
     * @param nome identificando o nome o qual será procurado
     * @return String no formato:
      "Nome/id, email, (xx) yyyy-zzzz, status: status"
     */
    public String  pesquisaUsuarioPorNome(String nome) {
	    vc.validaPesquisaUsuarioPorNome(nome);

        List<Usuario> usuariosList = new ArrayList<>(this.usuarios.values());
        String saida = "";

        for (Usuario usuario : usuariosList) {
            if (Util.formatString(nome).equals(Util.formatString(usuario.getNome()))) {
                saida += usuario.toString() + ", ";
            }
        }

        vc.validaExistenciaPesquisa(saida, nome);

        return saida.substring(0, saida.length() - 2);
    }


    /**
     * Valida os parametros e procura no sistema algum usuario com o id cadastrado. Como o id e unico, so podera existir um usuario com esse id.
     *
     * @param id do usuario  a ser pesquisado
     * @return String no formato:
    "Nome/id, email, (xx) yyyy-zzzz, status: status"
     *
     */
    public String pesquisaUsuarioPorId(String id)  {
        vc.validaPesquisaUsuarioPorId(id, this.usuarios);

        return this.usuarios.get(Util.formatString(id)).toString();
    }

}
