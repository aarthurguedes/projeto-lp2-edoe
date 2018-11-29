package eDoe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.ItemController;
import controllers.UsuarioController;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

class ItemControllerTest {

	private ItemController itemController;
	private UsuarioController usuarioController;
	
	private void criaUsuarioECadastraItem() {
		this.usuarioController = new UsuarioController();
		usuarioController.cadastrarDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA");
		itemController.cadastrarItem(usuarioController.getUsuario("70513372911"), "cadeira de rodas", 5, "roda grande,cadeira");
	}

	private List<Item> getTodosItensCadastrados() {
		List<Item> itensCadastrados = new ArrayList<>();

		for (Usuario usuario : this.usuarioController.getUsuarios().values()) {
			for (Item item : usuario.getItens().values()) {
				itensCadastrados.add(item);
			}
		}

		return itensCadastrados;
	}
	
	@BeforeEach
	public void criaItemController() {
		itemController = new ItemController();
		criaUsuarioECadastraItem();
	}
	
	@Test
	public void testAdicionarDescritorValido() {
		assertTrue(itemController.getDescritores().containsKey("cadeira de rodas"));
	}


	@Test
	public void testAdicionarDescritorInvalido() {
		assertThrows(IllegalArgumentException.class, () -> itemController.adicionarDescritor("cadeira de rodas"));
	}
	
	@Test
	public void testCadastrarItem() {
		assertTrue(usuarioController.getUsuario("70513372911").containsItem(1));
	}
	
	@Test
	public void testExibirItem() {
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", usuarioController.getUsuario("70513372911").exibirItem(1));
	}
	
	@Test
	public void testAtualizarItem() {
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 10", itemController.atualizarItem(1, usuarioController.getUsuario("70513372911"), 10, "roda grande,cadeira"));
		assertEquals("1 - cadeira de rodas, tags: [outfit, couro de cobra], quantidade: 5", itemController.atualizarItem(1, usuarioController.getUsuario("70513372911"), 5, "outfit,couro de cobra"));
		assertEquals("1 - cadeira de rodas, tags: [outfit, couro de cobra], quantidade: 10", itemController.atualizarItem(1, usuarioController.getUsuario("70513372911"), 10, "outfit,couro de cobra"));
	}
	
	@Test
	public void testRemoverItem() {
		itemController.removerItem(1, usuarioController.getUsuario("70513372911"));
		assertFalse(usuarioController.getUsuario("70513372911").getItens().containsKey(1));
	}
	
	@Test
	public void testListarDescritorDeItensParaDoacao() {
		itemController.adicionarDescritor("curso de programacao");
		itemController.adicionarDescritor("Cobertor");
		assertEquals("5 - cadeira de rodas | 0 - cobertor | 0 - curso de programacao", itemController.listarDescritorDeItensParaDoacao(this.getTodosItensCadastrados()));
	}
}
