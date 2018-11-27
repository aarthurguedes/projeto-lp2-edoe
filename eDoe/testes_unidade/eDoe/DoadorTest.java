package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import temp.Doador;

class DoadorTest {
	
	private Doador d;

	@BeforeEach
	public void criaDoador() {
		d = new Doador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", 0);
		d.cadastrarItem(1, "cadeira de rodas", 5, "roda grande,cadeira");
	}
	
	@Test
	public void testConstrutor() {
		assertEquals("70513372911", d.getId());
		assertEquals("Elizabeth Ashe", d.getNome());
		assertEquals("elizabethcalamity@deadlock.com", d.getEmail());
		assertEquals("(83) 92918-0211", d.getCelular());
		assertEquals("PESSOA_FISICA", d.getClasse());
		assertEquals(0, d.getIdOrdem());
	}
	
	@Test
	public void testIdInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Doador(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", 0));
		assertThrows(IllegalArgumentException.class, () -> new Doador("", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", 0));
	}
	
	@Test
	public void testNomeInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", null, "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", 0));
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", 0));
	}
	
	@Test
	public void testEmailInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", null, "(83) 92918-0211", "PESSOA_FISICA", 0));
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", "", "(83) 92918-0211", "PESSOA_FISICA", 0));
	}
	
	@Test
	public void testCelularInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", null, "PESSOA_FISICA", 0));
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "", "PESSOA_FISICA", 0));
	}
	
	@Test
	public void testClasseInvalida() {
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", null, 0));
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "", 0));
	}
	
	@Test
	public void testToString() {
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", d.toString());
	}
	
	@Test
	public void testDoadoresIguais() {
		Doador d2 = new Doador("70513372911", "Satya Vaswani", "satya@vishkarcorp.com", "(83) 99221-2571", "PESSOA_FISICA", 1);
		assertTrue(d.equals(d2));
	}
	
	@Test
	public void testDoadoresDiferentes() {
		Doador d2 = new Doador("70513372912", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", 0);
		assertFalse(d.equals(d2));
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
		d.atualizarItem(1, 2, null);
		assertEquals(2, d.getItens().get(1).getQuantidade());
		
		d.atualizarItem(1, 2, "");
		assertEquals(2, d.getItens().get(1).getQuantidade());
		
		d.atualizarItem(1, 5, "testando,mudanca,tags");
		assertEquals(5, d.getItens().get(1).getQuantidade());
		assertEquals("testando,mudanca,tags", d.getItens().get(1).getTags());
	}
	
	@Test
	public void testRemoverItem() {
		d.removerItem(1);
		assertFalse(d.getItens().containsKey(1));
	}
}
