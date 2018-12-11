package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.EDoeController;

class EDoeControllerTest {

	private EDoeController eDoeController;

	@BeforeEach
	public void criaEDoeController() {
		this.eDoeController = new EDoeController();
	}

	@Test
	public void testAdicionaDoador() {
		assertEquals("70513372911", this.eDoeController.adicionaDoador("70513372911", "Elizabeth Ashe",
				"Elizabeth Ashe", "(83) 92918-0211", "PESSOA_FISICA"));
	}

	private void adicionarDoador() {
		this.eDoeController.adicionaDoador("70513372911", "Elizabeth Ashe", "Elizabeth Ashe", "(83) 92918-0211",
				"PESSOA_FISICA");
	}

	@Test
	public void testPesquisaUsuarioPorId() {
		this.adicionarDoador();
		assertEquals("Elizabeth Ashe/70513372911, Elizabeth Ashe, (83) 92918-0211, status: doador",
				this.eDoeController.pesquisaUsuarioPorId("70513372911"));
	}

	@Test
	public void testPesquisaUsuarioPorNome() {
		this.adicionarDoador();
		assertEquals("Elizabeth Ashe/70513372911, Elizabeth Ashe, (83) 92918-0211, status: doador",
				this.eDoeController.pesquisaUsuarioPorNome("Elizabeth Ashe"));
	}

	@Test
	public void testAtualizaUsuario() {
		this.adicionarDoador();
		assertEquals("Amelie Lacroix/70513372911, amelie@talon.com, (83) 98331-9811, status: doador",
				this.eDoeController.atualizaUsuario("70513372911", "Amelie Lacroix", "amelie@talon.com",
						"(83) 98331-9811"));
	}

	@Test
	public void testRemoveUsuario() {
		this.adicionarDoador();
		this.eDoeController.removeUsuario("70513372911");
		this.adicionarDoador(); // Caso o usuario ainda estivesse cadastrado, uma excecao era esperada.
	}
	
	@Test
	public void testAdicionaDescritor() {
		this.eDoeController.adicionaDescritor("cadeira de rodas");
	}

	@Test
	public void testAdicionaItemParaDoacao() {
		this.adicionarDoador();
		assertEquals(1, this.eDoeController.adicionaItemParaDoacao("70513372911", "cadeira de rodas", 5,
				"roda grande,cadeira"));
	}

	private void adicionarDoadorEItem() {
		this.adicionarDoador();
		this.eDoeController.adicionaItemParaDoacao("70513372911", "cadeira de rodas", 5, "roda grande,cadeira");
	}

	@Test
	public void testExibeItem() {
		this.adicionarDoadorEItem();
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5",
				this.eDoeController.exibeItem(1, "70513372911"));
	}

	@Test
	public void testAtualizaItemParaDoacao() {
		this.adicionarDoadorEItem();
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 10",
				this.eDoeController.atualizaItemParaDoacao(1, "70513372911", 10, "roda grande,cadeira"));
	}
	
	@Test
	public void testRemoveItemParaDoacao() {
		this.adicionarDoadorEItem();
		this.eDoeController.removeItemParaDoacao(1, "70513372911");
		assertThrows(IllegalArgumentException.class, () -> this.eDoeController.removeItemParaDoacao(1, "70513372911"));
	}

	@Test
	public void testListaDescritorDeItensParaDoacao() {
		this.adicionarDoadorEItem();
		assertEquals("5 - cadeira de rodas", this.eDoeController.listaDescritorDeItensParaDoacao());
	}

	@Test
	public void testListaItensParaDoacao() {
		this.adicionarDoadorEItem();
		assertEquals(
				"1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5, doador: Elizabeth Ashe/70513372911",
				this.eDoeController.listaItensParaDoacao());
	}

	@Test
	public void testPesquisaItemParaDoacaoPorDescricao() {
		this.adicionarDoadorEItem();
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5",
				this.eDoeController.pesquisaItemParaDoacaoPorDescricao("cadeira de rodas"));
	}

	@Test
	public void testAdicionaItemNecessario() {
		this.eDoeController.lerReceptores("arquivos_sistema/novosReceptores.csv");
		assertEquals(1,
				this.eDoeController.adicionaItemNecessario("84473712044", "Livro", 1, "Infantil,Matematica,Didatico"));
	}

	private void adicionarItemNecessario() {
		this.eDoeController.lerReceptores("arquivos_sistema/novosReceptores.csv");
		this.eDoeController.adicionaItemNecessario("84473712044", "cadeira de rodas", 5, "roda grande,cadeira");
	}

	@Test
	public void testListaItensNecessarios() {
		this.adicionarItemNecessario();
		assertEquals(
				"1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5, Receptor: Murilo Luiz Brito/84473712044",
				this.eDoeController.listaItensNecessarios());
	}

	@Test
	public void testAtualizaItemNecessario() {
		this.adicionarItemNecessario();
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 20",
				this.eDoeController.atualizaItemNecessario("84473712044", 1, 20, "roda grande,cadeira"));
	}
	
	@Test
	public void testRemoveItemNecessario() {
		this.adicionarItemNecessario();
		this.eDoeController.removeItemNecessario("84473712044", 1);
		assertThrows(IllegalArgumentException.class, () -> this.eDoeController.removeItemNecessario("84473712044", 1));
	}

	@Test
	public void testMatch() {
		this.adicionarDoadorEItem();
		this.adicionarItemNecessario();
		assertEquals(
				"1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5, doador: Elizabeth Ashe/70513372911",
				this.eDoeController.match("84473712044", 2));
	}

	@Test
	public void testRealizaDoacao() {
		this.adicionarDoadorEItem();
		this.adicionarItemNecessario();
		assertEquals(
				"11/10/2018 - doador: Elizabeth Ashe/70513372911, item: cadeira de rodas, quantidade: 5, "
						+ "receptor: Murilo Luiz Brito/84473712044",
				this.eDoeController.realizaDoacao(2, 1, "11/10/2018"));
	}

	@Test
	public void testListaDoacoes() {
		this.adicionarDoadorEItem();
		this.adicionarItemNecessario();
		this.eDoeController.realizaDoacao(2, 1, "11/10/2018");
		assertEquals(
				"11/10/2018 - doador: Elizabeth Ashe/70513372911, item: cadeira de rodas, quantidade: 5, "
				+ "receptor: Murilo Luiz Brito/84473712044",
				this.eDoeController.listaDoacoes());
	}
}