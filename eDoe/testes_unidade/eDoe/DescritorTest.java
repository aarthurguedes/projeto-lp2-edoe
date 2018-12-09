package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DescritorTest {

	private Descritor descritor;
	
	@BeforeEach
	public void criaDescritor() {
		descritor = new Descritor("cadeira de rodas");
	}
	
	@Test
	public void testConstrutor1() {
		assertEquals("cadeira de rodas", descritor.getDescricao());
		assertEquals(0, descritor.getQuantidade());
	}
	
	@Test
	public void testConstrutor2() {
		descritor = new Descritor("cadeira de rodas", 1);
		assertEquals("cadeira de rodas", descritor.getDescricao());
		assertEquals(1, descritor.getQuantidade());
	}
	
	@Test
	public void testZeraQuantidade() {
		descritor = new Descritor("cadeira de rodas", 1);
		descritor.zeraQuantidade();
		assertEquals(0, descritor.getQuantidade());
	}
	
	@Test
	public void testToString() {
		assertEquals("0 - cadeira de rodas", descritor.toString());
	}
	
	@Test
	public void testDescritoresIguais() {
		Descritor descritor2 = new Descritor("cadeira de rodas", 1);
		assertTrue(descritor.equals(descritor2));
		assertTrue(descritor.equals(descritor));
	}
	
	@Test
	public void testDescritoresDiferentes() {
		Descritor descritor2 = new Descritor("cadeira de roda");
		assertFalse(descritor.equals(descritor2));
		assertFalse(descritor.equals(null));
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, descritor.compareTo(new Descritor("cadeira de rodas")));
		assertEquals(2, descritor.compareTo(new Descritor("armario")));
		assertEquals(-10, descritor.compareTo(new Descritor ("mesa")));
	}

}
