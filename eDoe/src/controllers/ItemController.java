package controllers;

import java.util.HashMap;
import java.util.Map;

import abstrato.Usuario;
import eDoe.Doador;
import eDoe.Item;
import validacao.ValidadorControllers;

public class ItemController {
	
	private Map<Integer, Item> itens;
	private ValidadorControllers vc;
	private int idItem;
	
	public ItemController() {
		this.itens = new HashMap<>();
		this.vc = new ValidadorControllers();
		this.idItem = 1;
	}
	
	public int cadastrarItem(Map<String, Usuario> usuarios, String idDoador, String descricao, int quantidade, String tags) {
		vc.validaCadastramentoItem(usuarios, idDoador, descricao, quantidade, tags);
		
		Item item = new Item(this.idItem, descricao, quantidade, tags);
		itens.put(this.idItem, item);
		
		Doador d = (Doador) usuarios.get(idDoador);
		d.cadastrarItem(descricao, quantidade, tags);
		
		this.idItem++;
		return (this.idItem - 1);
	}
	
	public String exibirItem(int id, Map<String, Usuario> usuarios, String idDoador) {
		vc.verificaExistenciaUsuario(idDoador, usuarios);
		vc.verificaExistenciaItem(id, itens);
		return "";
	}
	
	public String atualizarItem(int id, Map<String, Usuario> usuarios, String idDoador, int quantidade, String tags) {
		vc.validaAtualizacaoItem(id, itens, usuarios, idDoador);
		return "";
	}
	
	public void removerItem(int id, Map<String, Usuario> usuarios, String idDoador) {
		vc.validaRemocaoItem(id, itens, usuarios, idDoador);
	}
}
