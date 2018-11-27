package controllers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import abstrato.Usuario;
import eDoe.Doador;
import validacao.ValidadorControllers;

public class ItemController {

	private Set<String> descritores;
	private ValidadorControllers vc;
	private int idItem;
	
	public ItemController() {
		this.descritores = new HashSet<>();
		this.vc = new ValidadorControllers();
		this.idItem = 1;
	}

	/**
	 * @return the descritores
	 */
	public Set<String> getDescritores() {
		return descritores;
	}

	public void adicionaDescritor(String descricao) {
		vc.validaCadastramentoDescritor(descricao, descritores);
		descritores.add(descricao);
	}
	
	public int cadastrarItem(String idDoador, Map<String, Usuario> usuarios, String descricao, int quantidade, String tags) {
		vc.validaCadastramentoItem(idDoador, usuarios, 	descricao, quantidade, tags);
		
		Doador d = (Doador) usuarios.get(idDoador);
		
		if (vc.getIdItemIgual(d, descricao, tags) == 0) {
			d.cadastrarItem(this.idItem, descricao, quantidade, tags);
		} else {
			d.cadastrarItem(vc.getIdItemIgual(d, descricao, tags), descricao, quantidade, tags);
		}
		
		this.idItem++;
		return (this.idItem - 1);
	}
	
	public String exibirItem(int idItem, String idDoador, Map<String, Usuario> usuarios) {
		vc.verificaExistenciaUsuario(idDoador, usuarios);
		vc.verificaExistenciaItem(idItem, idDoador, usuarios);
		
		Doador d = (Doador) usuarios.get(idDoador);
		return d.exibirItem(idItem);
		
	}
	
	public String atualizarItem(int idItem, String idDoador, Map<String, Usuario> usuarios, int quantidade, String tags) {
		vc.validaAtualizacaoItem(idItem, idDoador, usuarios);
		
		Doador d = (Doador) usuarios.get(idDoador);
		return d.atualizarItem(idItem, quantidade, tags); 	
	}
	
	public void removerItem(String idDoador, Map<String, Usuario> usuarios, int idItem) {
		vc.validaRemocaoItem(idItem, idDoador, usuarios);
		Doador d = (Doador) usuarios.get(idDoador);
		d.removerItem(idItem);
	}
}
