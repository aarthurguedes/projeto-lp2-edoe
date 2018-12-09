package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Classe;
import util.Util;

public class UsuarioTest {

	private Usuario usuario;

	@BeforeEach
	public void criaUsuario() {
		usuario = new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211",
				Classe.getClassePorString("PESSOA_FISICA"), "doador", 1);
	}

	@Test
	public void testConstrutor() {
		assertEquals("70513372911", usuario.getId());
		assertEquals("Elizabeth Ashe", usuario.getNome());
		assertEquals("elizabethcalamity@deadlock.com", usuario.getEmail());
		assertEquals("(83) 92918-0211", usuario.getCelular());
		assertEquals("PESSOA_FISICA", usuario.getClasse());
		assertEquals(1, usuario.getIdOrdem());
		assertEquals("doador", usuario.getStatus());
	}
 
	@Test
	public void testIdInvalido() {
		assertThrows(IllegalArgumentException.class,
				() -> new Usuario(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211",
						Classe.getClassePorString("PESSOA_FISICA"), "doador", 1));
		assertThrows(IllegalArgumentException.class,
				() -> new Usuario("", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211",
						Classe.getClassePorString("PESSOA_FISICA"), "doador", 1));
	}

	@Test
	public void testNomeInvalido() {
		assertThrows(IllegalArgumentException.class,
				() -> new Usuario("70513372911", null, "elizabethcalamity@deadlock.com", "(83) 92918-0211",
						Classe.getClassePorString("PESSOA_FISICA"), "doador", 1));
		assertThrows(IllegalArgumentException.class,
				() -> new Usuario("70513372911", "", "elizabethcalamity@deadlock.com", "(83) 92918-0211",
						Classe.getClassePorString("PESSOA_FISICA"), "doador", 1));
	}
	
	@Test
	public void testGetNomeEId() {
		assertEquals("Elizabeth Ashe/70513372911", usuario.getNomeEId());
	}

	@Test
	public void testEmailInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("70513372911", "Elizabeth Ashe", null,
				"(83) 92918-0211", Classe.getClassePorString("PESSOA_FISICA"), "doador", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("70513372911", "Elizabeth Ashe", "",
				"(83) 92918-0211", Classe.getClassePorString("PESSOA_FISICA"), "doador", 1));
	}

	@Test
	public void testCelularInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("70513372911", "Elizabeth Ashe",
				"elizabethcalamity@deadlock.com", null, Classe.getClassePorString("PESSOA_FISICA"), "doador", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("70513372911", "Elizabeth Ashe",
				"elizabethcalamity@deadlock.com", "", Classe.getClassePorString("PESSOA_FISICA"), "doador", 1));
	}

	@Test
	public void testClasseInvalida() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("70513372911", "Elizabeth Ashe",
				"elizabethcalamity@deadlock.com", "(83) 92918-0211", Classe.getClassePorString(null), "doador", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("70513372911", "Elizabeth Ashe",
				"elizabethcalamity@deadlock.com", "(83) 92918-0211", Classe.getClassePorString(""), "doador", 1));
	}

	@Test
	public void testToString() {
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador",
				usuario.toString());
	}

	@Test
	public void testUsuariossIguais() {
		Usuario usuario2 = new Usuario("70513372911", "Satya Vaswani", "satya@vishkarcorp.com", "(83) 99221-2571",
				Classe.getClassePorString("PESSOA_FISICA"), "doador", 1);
		assertTrue(usuario.equals(usuario2));
		assertTrue(usuario.equals(usuario));
	}

	@Test
	public void testUsuariosDiferentes() {
		Usuario usuario2 = new Usuario("70513372912", "Elizabeth Ashe", "elizabethcalamity@deadlock.com",
				"(83) 92918-0211", Classe.getClassePorString("PESSOA_FISICA"), "doador", 1);
		assertFalse(usuario.equals(usuario2));
		assertFalse(usuario.equals(null));
	}

	@Test
	public void testCadastrarItem() {
		usuario.cadastrarItem(1, new Descritor(Util.formatString("cadeira de rodas")), 5, "roda grande,cadeira");
		usuario.getItens().containsKey(1);
	}
	
	@Test
	public void testGetItem() {
		usuario.cadastrarItem(1, new Descritor(Util.formatString("cadeira de rodas")), 5, "roda grande,cadeira");
		Item i = usuario.getItem(1);
		assertEquals(i, usuario.getItem(1));
	}
	
	@Test
	public void testContaisItem() {
		usuario.cadastrarItem(1, new Descritor(Util.formatString("cadeira de rodas")), 5, "roda grande,cadeira");
		assertTrue(usuario.containsItem(1));
		assertFalse(usuario.containsItem(2));
	}

	@Test
	public void testExibirItem() {
		usuario.cadastrarItem(1, new Descritor(Util.formatString("cadeira de rodas")), 5, "roda grande,cadeira");
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", usuario.exibirItem(1));
	}
 
	@Test
	public void testAtualizarItem() {
		usuario.cadastrarItem(1, new Descritor(Util.formatString("cadeira de rodas")), 5, "roda grande,cadeira");
		// Atualizando a quantidade
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 10",
				usuario.atualizarItem(1, 10, "roda grande,cadeira")); 
		// Atualizando as tags
		assertEquals("1 - cadeira de rodas, tags: [roda pequena, cadeira], quantidade: 5",
				usuario.atualizarItem(1, 5, "roda pequena,cadeira")); 
		// Atualizando a quantidade e as tags
		assertEquals("1 - cadeira de rodas, tags: [roda pequena, cadeira], quantidade: 10",
				usuario.atualizarItem(1, 10, "roda pequena,cadeira")); 
	}

	@Test
	public void testRemoverItem() {
		usuario.cadastrarItem(1, new Descritor(Util.formatString("cadeira de rodas")), 5, "roda grande,cadeira");
		usuario.removerItem(1);
		assertFalse(usuario.getItens().containsKey(1));
	}
	
	@Test
	public void testRemoveItensQtd0() {
		usuario.cadastrarItem(1, new Descritor(Util.formatString("cadeira de rodas")), 5, "roda grande,cadeira");
		usuario.getItem(1).setQuantidade(0);
		usuario.removeItensQtd0();
		assertFalse(usuario.containsItem(1));
	}
	
	@Test
	public void testCompareTo() {
		// Comparacao por posicoes iguais
		assertEquals(0, usuario.compareTo(new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211",
				Classe.getClassePorString("PESSOA_FISICA"), "doador", 1)));
		// Comparacao por posicoes diferentes
		assertEquals(-1, usuario.compareTo(new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211",
				Classe.getClassePorString("PESSOA_FISICA"), "doador", 2)));
		usuario = new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211",
				Classe.getClassePorString("PESSOA_FISICA"), "doador", 2);
		assertEquals(1, usuario.compareTo(new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211",
				Classe.getClassePorString("PESSOA_FISICA"), "doador", 1)));
	}
}
