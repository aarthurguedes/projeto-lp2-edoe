package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoacaoTest {

	private Doacao doacao;

	@BeforeEach
	public void criaDoacao() {
		doacao = new Doacao("11/10/2018", "Cave Johnson", "cadeira de rodas", 7, "Luiza Elisa Lopes");
	}

	@Test
	public void testGetDataValida() {
		assertEquals("11/10/2018", doacao.getData());
	}

	@Test
	public void testDataInvalida() {
		assertThrows(IllegalArgumentException.class,
				() -> new Doacao("", "Cave Johnson", "cadeira de rodas", 7, "Luiza Elisa Lopes"));
		assertThrows(IllegalArgumentException.class,
				() -> new Doacao(null, "Cave Johnson", "cadeira de rodas", 7, "Luiza Elisa Lopes"));
	}

	@Test
	public void testToString() {
		assertEquals(
				"11/10/2018 - doador: Cave Johnson, item: cadeira de rodas, quantidade: 7, receptor: Luiza Elisa Lopes",
				doacao.toString());
	}
	
	@Test 
	public void testDoacoesIguais() {
		Doacao doacao2 = new Doacao("11/10/2018", "Cave Johnson", "cadeira de rodas", 7, "Luiza Elisa Lopes");
		assertTrue(doacao.equals(doacao2));
		assertTrue(doacao.equals(doacao));
	}
	
	@Test
	public void testDoacoesDiferentes() {
		Doacao doacao2 = new Doacao("15/09/2016", "Lucas Fernandes", "camiseta", 100, "Murilo Luiz Brito");
		assertFalse(doacao.equals(doacao2));
		assertFalse(doacao.equals(null));
	}
}
