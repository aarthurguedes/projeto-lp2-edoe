package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.UsuarioController;

class UsuarioControllerTest {
	
	private UsuarioController usuarioController;

	@BeforeEach
	public void criaUsuarioController() {
		usuarioController = new UsuarioController();
		usuarioController.cadastrarDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA");
		usuarioController.cadastrarReceptor("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br", "(31) 99776-7434", "PESSOA_FISICA");
	}
	
	@Test
	public void testCadastrarDoador() {
		assertTrue(usuarioController.getUsuarios().containsKey("70513372911")); // Testando a existencia do doador cadastrado
		assertEquals("59238650111", usuarioController.cadastrarDoador("59238650111", "Satya Vaswani", "satya@vishkarcorp.com", "(83) 99221-2571", "PESSOA_FISICA")); // Testando o retorno do método
	}
	
	@Test
	public void testCadastrarReceptor() {
		assertTrue(usuarioController.getUsuarios().containsKey("84473712044")); // Testando a existencia do receptor cadastrado 
		assertEquals("80643201009", usuarioController.cadastrarReceptor("80643201009", "Tomás Otávio Lucas Teixeira", "tomas@ipmmi.org.br", "(79) 98977-0397", "PESSOA_FISICA")); // Testando o retorno do método
	}
	
	@Test
	public void testPesquisarUsuarioPorNome() {
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", usuarioController.pesquisarUsuarioPorNome("Elizabeth Ashe")); // Pesquisando o doador cadastrado pelo nome
		assertEquals("Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", usuarioController.pesquisarUsuarioPorNome("Murilo Luiz Brito")); // Pesquisando o receptor cadastrado pelo nome
	}
	
	@Test
	public void testPesquisarUsuarioPorId() {
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", usuarioController.pesquisarUsuarioPorId("70513372911")); // Pesquisando o doador cadastrado pelo id
		assertEquals("Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", usuarioController.pesquisarUsuarioPorId("84473712044")); // Pesquisando o receptor cadastrado pelo id
	}
	
	@Test
	public void testAtualizarUsuario() {
		//Atualizando o doador cadastrado
		usuarioController.atualizarUsuario("70513372911", "Matheus", "matheus@ccc.ufcg.edu.br", "(00) 0011-2233");
		assertEquals("Matheus/70513372911, matheus@ccc.ufcg.edu.br, (00) 0011-2233, status: doador", usuarioController.pesquisarUsuarioPorId("70513372911"));
		
		//Atualizando o receptor cadastrado
		usuarioController.atualizarUsuario("84473712044", "Pedro", "pedro@ccc.ufcg.edu.br", "(11) 4455-6677");
		assertEquals("Pedro/84473712044, pedro@ccc.ufcg.edu.br, (11) 4455-6677, status: receptor", usuarioController.pesquisarUsuarioPorId("84473712044"));
	}
	
	@Test
	public void testRemoverUsuario() {
		//Removendo o doador cadastrado
		usuarioController.removerUsuario("70513372911");
		assertFalse(usuarioController.getUsuarios().containsKey("70513372911"));
		
		//Removendo o receptor cadastrado
		usuarioController.removerUsuario("84473712044");
		assertFalse(usuarioController.getUsuarios().containsKey("84473712044"));
	}
}
