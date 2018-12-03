package eDoe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.ItemController;
import controllers.UsuarioController;
import java.util.ArrayList;
import java.util.List;

class ItemControllerTest {

	private ItemController itemController;
	private UsuarioController usuarioController = new UsuarioController();

	private void criaDoadorECadastraItens() {
		usuarioController.cadastrarDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com",
				"(83) 92918-0211", "PESSOA_FISICA");

		itemController.cadastrarItem(usuarioController.getUsuario("70513372911"), "cadeira de rodas", 5,
				"roda grande,cadeira");
		itemController.cadastrarItem(usuarioController.getUsuario("70513372911"), "colchao", 5,
				"colchao kingsize,conforto,dormir");
		itemController.cadastrarItem(usuarioController.getUsuario("70513372911"), "cobertor", 10, "lencol,conforto");
	}

	private void criaReceptorECadastraItens() {
		usuarioController.cadastrarReceptor("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br",
				"(31) 99776-7434", "PESSOA_FISICA");

		itemController.cadastrarItem(usuarioController.getUsuario("84473712044"), "Livro", 1,
				"Infantil,Matematica,Didatico");
		itemController.cadastrarItem(usuarioController.getUsuario("84473712044"), "Toalha de Banho", 2,
				"Adulto,TAM G,Azul");
		itemController.cadastrarItem(usuarioController.getUsuario("84473712044"), "Frauda", 15, "Higiene,Infantil,P");

	}

	@BeforeEach
	public void criaItemController() {
		itemController = new ItemController();
		criaDoadorECadastraItens();
		criaReceptorECadastraItens();
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
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5",
				usuarioController.getUsuario("70513372911").exibirItem(1));
	}

	@Test
	public void testAtualizarItem() {
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 10", itemController
				.atualizarItem(1, usuarioController.getUsuario("70513372911"), 10, "roda grande,cadeira"));
		assertEquals("1 - cadeira de rodas, tags: [outfit, couro de cobra], quantidade: 5", itemController
				.atualizarItem(1, usuarioController.getUsuario("70513372911"), 5, "outfit,couro de cobra"));
		assertEquals("1 - cadeira de rodas, tags: [outfit, couro de cobra], quantidade: 10", itemController
				.atualizarItem(1, usuarioController.getUsuario("70513372911"), 10, "outfit,couro de cobra"));
	}

	@Test
	public void testRemoverItem() {
		itemController.removerItem(1, usuarioController.getUsuario("70513372911"));
		assertFalse(usuarioController.getUsuario("70513372911").getItens().containsKey(1));
	}

	private List<Item> getTodosItensCadastradosEmDoador() {
		List<Item> itensCadastrados = new ArrayList<>();

		for (Usuario usuario : this.usuarioController.getUsuarios().values()) {
			if (usuario.getStatus().equals("doador")) {
				for (Item item : usuario.getItens().values()) {
					itensCadastrados.add(item);
				}
			}
		}

		return itensCadastrados;
	}

	@Test
	public void testListarDescritorDeItensParaDoacao() {
		assertEquals(
				"5 - cadeira de rodas | 10 - cobertor | 5 - colchao | 0 - frauda | 0 - livro | 0 - toalha de banho",
				itemController.listarDescritorDeItensParaDoacao(getTodosItensCadastradosEmDoador()));
	}

	@Test
	public void testListarItensParaDoacao() {
		assertEquals("3 - cobertor, tags: [lencol, conforto], quantidade: 10, doador: Elizabeth Ashe/70513372911 | "
				+ "1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5, doador: Elizabeth Ashe/70513372911 | "
				+ "2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5, doador: Elizabeth Ashe/70513372911",
				itemController.listarItensParaDoacao(this.getTodosItensCadastradosEmDoador()));
	}

	@Test
	public void testPesquisarItemParaDoacaoPorDescricao() {
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", itemController
				.pesquisarItemParaDoacaoPorDescricao("cadeira de rodas", this.getTodosItensCadastradosEmDoador()));
	}

	private List<Item> getTodosItensCadastradosEmReceptor() {
		List<Item> itensCadastrados = new ArrayList<>();

		for (Usuario usuario : this.usuarioController.getUsuarios().values()) {
			if (usuario.getStatus().equals("receptor")) {
				for (Item item : usuario.getItens().values()) {
					itensCadastrados.add(item);
				}
			}
		}

		return itensCadastrados;
	}

	@Test
	public void testListarItensNecessarios() {
		assertEquals(
				"4 - livro, tags: [Infantil, Matematica, Didatico], quantidade: 1, Receptor: Murilo Luiz Brito/84473712044 | "
				+ "5 - toalha de banho, tags: [Adulto, TAM G, Azul], quantidade: 2, Receptor: Murilo Luiz Brito/84473712044 | "
				+ "6 - frauda, tags: [Higiene, Infantil, P], quantidade: 15, Receptor: Murilo Luiz Brito/84473712044",
				itemController.listarItensNecessarios(getTodosItensCadastradosEmReceptor()));
	}
}
