package controllers;

import java.util.HashMap;
import java.util.Map;

import abstrato.Usuario;
import eDoe.Doador;
import eDoe.Item;
import validacao.ValidadorControllers;

public class ItemController {

	private ValidadorControllers vc;
	private int idItem;
	
	public ItemController() {
		this.vc = new ValidadorControllers();
		this.idItem = 1;
	}
	
	public int cadastrarItem(Doador doador, String descricao, int quantidade, String tags) {
		vc.validaCadastramentoItem(descricao, quantidade, tags);
		Item item = new Item(this.idItem, descricao, quantidade, tags);
		doador.cadastrarItem(descricao, quantidade, tags);
		
		this.idItem++;
		return (this.idItem - 1);
	}
	
	public String exibirItem(Doador doador, int idItem) {
		vc.verificaExistenciaItem(idItem, doador);
		return "";
	}
	
	public String atualizarItem(Doador doador, int idItem, int quantidade, String tags) {
		vc.validaAtualizacaoItem(idItem, doador);
		return "";
	}
	
	public void removerItem(Doador doador, int idItem) {
		vc.validaRemocaoItem(idItem, doador);
	}
}
