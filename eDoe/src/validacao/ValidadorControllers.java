package validacao;

import java.util.Map;

import abstrato.Usuario;
import eDoe.Doador;
import eDoe.Item;

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
	public void validaCadastramentoUsuario(String id, String nome, String email, String celular, String classe, Map<String, Usuario> usuarios) {
		if (usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}
		vb.validaUsuario(id, nome, email, celular, classe);
	}

    /**
     * Metodo auxiliar que valida a saida do pesquisaUsuarioPorId
     *
     * @param id de identificacao do usuario.
     * @param usuarios Mapa de usuario.
     */
	public void validaPesquisaUsuarioPorId(String id, Map<String, Usuario> usuarios) {
	    vb.validaId(id);
	    if (!usuarios.containsKey(id)) {
	        throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
        }
    }

    /**
     * Metodo auxiliar que valida os parametros passados para a pesquisa de usuarios pelo nome.
     *
     * @param nome
     */
    public void validaPesquisaUsuarioPorNome(String nome) {
	    vb.validaNome(nome);
    }

    /**
     * Metodo auxiliar que valida a saida do metodo de pesquisa por nome do usuario,
     *
     * @param saida a ser validada.
     * @param nome nome do usuario da pesquisa.
     */
    public void verificaExistenciaPesquisa(String saida, String nome) {
	    if (saida.equals("")) {
	        throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
        }
    }

    /**
     * Metodo auxiliar responsavel por verificar a existencia de um usuario no map, caso o usuario nao exista
     * nesse map, e lancada uma excecao
     * @param id representa a id do usuario
     * @param usuarios representa o map que contem os usuarios
     */
    public void verificaExistenciaUsuario(String id, Map<String, Usuario> usuarios) {
    	vb.validaId(id);
    	if (!usuarios.containsKey(id)) {
    		throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
    	}
    }

	public void validaCadastramentoItem(String descricao, int quantidade, String tags) {
    	vb.validaDescricaoItem(descricao);
    	vb.validaQuantidadeItens(quantidade);

    }
    
    public void verificaExistenciaItem(int id, Doador doador) {
    	if (!doador.containsItem(id)) {
    		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
    	}
    }
    
    public void validaAtualizacaoItem(int id, Doador doador) {
    	vb.validaIdItem(id);
    	verificaExistenciaItem(id, doador);
    }
    
    public void validaRemocaoItem(int id,Doador doador) {
    	verificaExistenciaItem(id, doador);
    	vb.validaIdItem(id);

    	if (doador.getItens().size() == 0) {
    		throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
    	}
    }
}
