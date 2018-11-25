package controllers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import abstrato.Usuario;
import eDoe.Doador;
import validacao.ValidadorControllers;

public class ItemController {

	/**
	 * Set dos descritores dos itens que serao adicionador
	 */
	private Set<String> descritores;
	
	/**
	 * Objeto validador
	 */
	private ValidadorControllers vc;
	
	/**
	 * Identificacao do intem
	 */
	private int idItem;
	
	/**
	 * Constroi o controlador de itens
	 */
	public ItemController() {
		this.descritores = new HashSet<>();
		this.vc = new ValidadorControllers();
		this.idItem = 1;
	}
	
	/**
	 * Metodo responsavel por adicionar uma descricao ao set de descricoes dos itens e por validar
	 * essas descricoes
	 * @param descricao representa uma descricao a ser adicionada
	 */
	public void adicionaDescritor(String descricao) {
		vc.validaCadastramentoDescritor(descricao, descritores);
		descritores.add(descricao);
	}
	
	/**
	 * Metodo que valida e cadastra um item a um doador
	 * @param doador representa o doador daquele item
	 * @param descricao representa a descricao do item que esta sendo cadastrado
	 * @param quantidade representa a quantidade de itens
	 * @param tags representa as tags do item
	 * @return a identificacao do item que foi cadastrado
	 */
	public int cadastrarItem(Doador doador, String descricao, int quantidade, String tags) {
		vc.validaCadastramentoItem(descricao, quantidade, tags);
		doador.cadastrarItem(descricao, quantidade, tags);
		this.idItem++;
		return (this.idItem - 1);
	}
	
	/**
	 * Metodo resposavel por exibir um item especifico
	 * @param idItem representa a identificacao do item
	 * @param idDoador representa a identificacao do usuario
	 * @param usuarios representa o map de usuarios
	 * @return string que representa o item a ser exibido
	 */
	public String exibirItem(int idItem, String idDoador, Map<String, Usuario> usuarios) {
		vc.verificaExistenciaItem(idItem, idDoador, usuarios);
		vc.verificaExistenciaUsuario(idDoador, usuarios);
		
		Doador d = (Doador) usuarios.get(idDoador);
		return d.exibirItem(idItem);
		
	}
	
	/**
	 * Metodo responsavel por atualizar um item de um doador
	 * @param idItem representa a identificacao do item
	 * @param idDoador representa a identificacao do doador
	 * @param usuarios representa o map de usuarios
	 * @param quantidade representa a quantidade daquele item
	 * @param tags representa as tags daquele item
	 * @return string que representa o item atualizado
	 */
	public String atualizarItem(int idItem, String idDoador, Map<String, Usuario> usuarios, int quantidade, String tags) {
		vc.validaAtualizacaoItem(idItem, idDoador, usuarios);
		
		Doador d = (Doador) usuarios.get(idDoador);
		return d.atualizarItem(idItem, quantidade, tags); 
		
	}
	
	/**
	 * Metodo responsavel por remover um item de um doador
	 * @param idDoador representa a identificacao do doador
	 * @param usuarios representa o map de usuarios
	 * @param idItem representa a identificacao do item a ser removido
	 */
	public void removerItem(String idDoador, Map<String, Usuario> usuarios, int idItem) {
		vc.validaRemocaoItem(idItem, idDoador, usuarios);
		Doador d = (Doador) usuarios.get(idDoador);
		d.removerItem(idItem);
	}
}
