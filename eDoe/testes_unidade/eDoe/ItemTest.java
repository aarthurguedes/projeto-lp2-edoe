package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {

	private Item i;
	
	@BeforeEach
	public void criaItem() {
		i = new Item(1, "cadeira de rodas", 5, "roda grande,cadeira");
	}
	
	@Test
	public void testConstrutor() {
		assertEquals(1, i.getId());
		assertEquals("cadeira de rodas", i.getDescricao());
		assertEquals(5, i.getQuantidade());
		assertEquals("roda grande,cadeira", i.getTags());
	}
	
	@Test
	public void testIdInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Item(-1, "cadeira de rodas", 5, "roda grande,cadeira"));
	}
	
	@Test
	public void testDescricaoInvalida() {
		assertThrows(IllegalArgumentException.class, () -> new Item(1, null, 5, "roda grande,cadeira"));
		assertThrows(IllegalArgumentException.class, () -> new Item(1, "", 5, "roda grande,cadeira"));
	}
	
	@Test
	public void testQuantidadeInvalida() {
		assertThrows(IllegalArgumentException.class, () -> new Item(1, "cadeira de rodas", 0, "roda grande,cadeira"));
		assertThrows(IllegalArgumentException.class, () -> new Item(1, "cadeira de rodas", -1, "roda grande,cadeira"));
	}
	
	@Test
	public void testToString() {
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", i.toString());
	}
	
	@Test
	public void testItensIguais() {
		Item i2 = new Item(2, "cadeira de rodas", 10, "roda grande,cadeira");
		assertTrue(i.equals(i2));
	}
	
	@Test
	public void testItensDiferentes() {
		Item i2 = new Item(1, "cadeira com rodas", 5, "roda grande,cadeira"); //Descricao diferente do Item i
		Item i3 = new Item(1, "cadeira de rodas", 5, "roda grande,cadeiras"); // Tags diferentes do Item i
		Item i4 = new Item(1, "cadeiras de rodas", 5, "roda grande,cadeiras"); // Descricao e Tags diferentes do Item i
		
		assertFalse(i.equals(i2));
		assertFalse(i.equals(i3));
		assertFalse(i.equals(i4));
	}

}
