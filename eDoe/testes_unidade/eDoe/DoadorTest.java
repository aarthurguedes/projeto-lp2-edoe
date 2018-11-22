package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoadorTest {
	
	private Doador d;

	@BeforeEach
	public void criaDoador() {
		d = new Doador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA");
	}
	
	@Test
	public void testConstrutor() {
		assertEquals("705.133.729-11", d.getId());
		assertEquals("Elizabeth Ashe", d.getNome());
		assertEquals("elizabethcalamity@deadlock.com", d.getEmail());
		assertEquals("(83) 92918-0211", d.getCelular());
		assertEquals("PESSOA_FISICA", d.getClasse());
	}
	
	@Test
	public void testIdInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Doador(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, () -> new Doador("", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA"));
	}
	
	@Test
	public void testNomeInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", null, "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA"));
	}
	
	@Test
	public void testEmailInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", null, "(83) 92918-0211", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", "", "(83) 92918-0211", "PESSOA_FISICA"));
	}
	
	@Test
	public void testCelularInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", null, "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "", "PESSOA_FISICA"));
	}
	
	@Test
	public void testClasseInvalida() {
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", null));
		assertThrows(IllegalArgumentException.class, () -> new Doador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", ""));
	}
	
	@Test
	public void testToString() {
		assertEquals("Elizabeth Ashe/705.133.729-11, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", d.toString());
	}
	
	@Test
	public void testDoadoresIguais() {
		Doador d2 = new Doador("70513372911", "Satya Vaswani", "satya@vishkarcorp.com", "(83) 99221-2571", "PESSOA_FISICA");
		assertTrue(d.equals(d2));
	}
	
	@Test
	public void testDoadoresDiferentes() {
		Doador d2 = new Doador("70513372912", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA");
		assertFalse(d.equals(d2));
	}
}
