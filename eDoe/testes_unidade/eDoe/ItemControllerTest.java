package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.ItemController;
import controllers.UsuarioController;
import temp.Doador;

class ItemControllerTest {
	
	private ItemController ic;
	private UsuarioController uc;
	private Doador d;

	private void criaItemController() {
		ic = new ItemController();
	}
	
	private void criaUsuarioController() {
		uc = new UsuarioController();
		uc.cadastrarDoador("58791093499", "Claudio Campelo", "campelao@gmail.com", "(83) 92413-3821", "PESSOA_FISICA");
	}
	
	@BeforeEach
	public void cadastraItemParaDoador() {
		criaItemController();
		criaUsuarioController();
		
		ic.cadastrarItem("58791093499", uc.getUsuarios(), "cadeira de rodas", 5, "roda grande,cadeira");
		d = (Doador) uc.getUsuarios().get("58791093499");
	}
	
	@Test
	public void testAdicionaDescritor() {
		ic.adicionaDescritor("cadeira de rodas");
		assertTrue(ic.getDescritores().contains("cadeira de rodas"));
	}
	
	@Test
	public void testCadastrarItem() {
		assertTrue(d.getItens().containsKey(1));
	}
	
	@Test
	public void testExibirItem() {
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", d.exibirItem(1));
	}
	
	@Test
	public void testAtualizarItem() {
		ic.atualizarItem(1, "58791093499", uc.getUsuarios(), 10, "testando,atualizacao");
		assertEquals(10, d.getItens().get(1).getQuantidade());
		assertEquals("testando,atualizacao", d.getItens().get(1).getTags());
	}
	
	@Test
	public void testRemoverItem() {
		ic.removerItem("58791093499", uc.getUsuarios(), 1);
		assertFalse(d.getItens().containsKey(1));
	}

}
