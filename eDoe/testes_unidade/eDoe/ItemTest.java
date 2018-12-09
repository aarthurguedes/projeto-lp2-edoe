package eDoe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Classe;

class ItemTest {
	
 	private Item item;
	
	@BeforeEach
	public void criaItem() {
		item = new Item(1,  new Descritor("cadeira de rodas"), 5, "roda grande,cadeira", "70513372911");
	}
	
	@Test
	public void testConstrutor() {
		assertEquals(1, item.getId());
		assertEquals("cadeira de rodas", item.getDescricao());
		assertEquals(new Descritor("cadeira de rodas"), item.getDescritor());
		assertEquals(5, item.getQuantidade());
		assertEquals("roda grande,cadeira", item.getTags());
		assertEquals(0, item.getPontuacaoMatch());
	}
	
	@Test
	public void testIdInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Item(-1, new Descritor("cadeira de rodas"), 5, "roda grande,cadeira", "70513372911"));
	}
	
	@Test
	public void testDescricaoInvalida() {
		assertThrows(IllegalArgumentException.class, () -> new Item(1, new Descritor(null), 5, "roda grande,cadeira", "70513372911"));
		assertThrows(IllegalArgumentException.class, () -> new Item(1, new Descritor(""), 5, "roda grande,cadeira", "70513372911"));
	}
	
	@Test
	public void testQuantidadeInvalida() {
		assertThrows(IllegalArgumentException.class, () -> new Item(1, new Descritor("cadeira de rodas"), 0, "roda grande,cadeira", "70513372911"));
		assertThrows(IllegalArgumentException.class, () -> new Item(1, new Descritor("cadeira de rodas"), -1, "roda grande,cadeira", "70513372911"));
	} 
	
	@Test
	public void tesGetIdUsuario() {
		Usuario usuario = new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211",
				Classe.getClassePorString("PESSOA_FISICA"), "doador", 1);
		usuario.cadastrarItem(1,  new Descritor("cadeira de rodas"), 5, "roda grande,cadeira");
		assertEquals("70513372911", item.getIdUsuario());
	}
	 
	@Test
	public void testToString() {
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", item.toString());
	}
	
	@Test
	public void testItensIguais() {
		Item item2 = new Item(2, new Descritor("cadeira de rodas"), 10, "roda grande,cadeira", "70513372911");
		assertTrue(item.equals(item2));
		assertTrue(item.equals(item));
	}
	
	@Test
	public void testItensDiferentes() {
		//Descricao diferente do Item item
		Item i2 = new Item(1, new Descritor("cadeiras de rodas"), 5, "roda grande,cadeira", "70513372911"); 
		// Tags diferentes do Item item
		Item i3 = new Item(1, new Descritor("cadeira de rodas"), 5, "roda grande,cadeiras", "70513372911"); 
		// Descricao e Tags diferentes do Item item
		Item i4 = new Item(1, new Descritor("cadeiras de rodas"), 5, "roda grande,cadeiras", "70513372911");
		
		assertFalse(item.equals(i2));
		assertFalse(item.equals(i3));
		assertFalse(item.equals(i4));
		assertFalse(item.equals(null));
	}
 }