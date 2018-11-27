package controllers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import abstrato.Usuario;
import eDoe.Doador;
import validacao.ValidadorControllers;

/**
* Representacao do controlador de itens. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class ItemController {

	/**
	 * Set de descricoes que os itens podem ter. 
	 */
	private Set<String> descritores;
	/**
	 * Objeto validador de controllers.
	 */
	private ValidadorControllers vc;
	/**
	 * Identificacao do item
	 */
	private int idItem;
	
	/**
	 * Constroi o controlador de itens.
	 */
	public ItemController() {
		this.descritores = new HashSet<>();
		this.vc = new ValidadorControllers();
		this.idItem = 1;
	}

	/**
	 * @return o set de descritores
	 */
	public Set<String> getDescritores() {
		return descritores;
	}

	/**
	 * Metodo responsavel por validar uma descricao e adicionar ela ao set de descritores.
	 * @param descricao representa a descricao que sera adicionada
	 */
	public void adicionaDescritor(String descricao) {
		vc.validaCadastramentoDescritor(descricao, descritores);
		descritores.add(descricao);
	}
	
	/**
	 * Metodo responsavel por cadastrar um item e validar seus parametros.
	 * @param idDoador representa o id do doador do item
	 * @param usuarios representa o map de usuarios
	 * @param descricao representa a descricao do item
	 * @param quantidade representa a quantidade daquele item
	 * @param tags representa as tags do item
	 * @return inteiro que representa a identificacao do item
	 */
	public int cadastrarItem(String idDoador, Map<String, Usuario> usuarios, String descricao, int quantidade, String tags) {
		vc.validaCadastramentoItem(idDoador, usuarios, 	descricao, quantidade, tags);
		
		Doador d = (Doador) usuarios.get(idDoador);
		if (vc.validaItensIguais(d, descricao, tags) == 0) {
			d.cadastrarItem(this.idItem, descricao, quantidade, tags);
		} else {
			d.cadastrarItem(vc.validaItensIguais(d, descricao, tags), descricao, quantidade, tags);
		}
		
		this.idItem++;
		return (this.idItem - 1);
	}
	
	/**
	 * Metodo responsavel por exibir um item.
	 * @param idItem representa a identificacao do item
	 * @param idDoador representa a identificacao do doador
	 * @param usuarios representa o map de usuarios
	 * @return String que representa o item a ser exibido
	 */
	public String exibirItem(int idItem, String idDoador, Map<String, Usuario> usuarios) {
		vc.verificaExistenciaUsuario(idDoador, usuarios);
		vc.verificaExistenciaItem(idItem, idDoador, usuarios);
		
		Doador d = (Doador) usuarios.get(idDoador);
		return d.exibirItem(idItem);
	}
	
	/**
	 * Metodo responsavel por atualizar um item.
	 * @param idItem representa a identificacao do item
	 * @param idDoador representa a identificacao do doador
	 * @param usuarios representa o map de usuarios
	 * @param quantidade representa a nova quantidade daquele item
	 * @param tags representa as novas tags do item
	 * @return string que representa o item atualizado
	 */
	public String atualizarItem(int idItem, String idDoador, Map<String, Usuario> usuarios, int quantidade, String tags) {
		vc.validaAtualizacaoItem(idItem, idDoador, usuarios);
		
		Doador d = (Doador) usuarios.get(idDoador);
		return d.atualizarItem(idItem, quantidade, tags); 	
	}
	
	/**
	 * Metodo responsavel por remover um item.
	 * @param idDoador representa a identificacao do doador
	 * @param usuarios representa o map de usuarios
	 * @param idItem representa a identificacao do item
	 */
	public void removerItem(String idDoador, Map<String, Usuario> usuarios, int idItem) {
		vc.validaRemocaoItem(idItem, idDoador, usuarios);
		
		Doador d = (Doador) usuarios.get(idDoador);
		d.removerItem(idItem);
	}
}
