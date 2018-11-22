package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReceptorTest {

	private Receptor r;
	
	@BeforeEach
	public void criaReceptor() {
		r = new Receptor("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br", "(31) 99776-7434", "PESSOA_FISICA");
	}
	
	@Test
	public void testConstrutor() {
		assertEquals("844.737.120-44", r.getId());
		assertEquals("Murilo Luiz Brito", r.getNome());
		assertEquals("muriloluizbrito-81@ipmmi.org.br", r.getEmail());
		assertEquals("(31) 99776-7434", r.getCelular());
		assertEquals("PESSOA_FISICA", r.getClasse());
	}
	
	@Test
	public void testIdInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Receptor(null, "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br", "(31) 99776-7434", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, () -> new Receptor("", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br", "(31) 99776-7434", "PESSOA_FISICA"));
	}
	
	@Test
	public void testNomeInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Receptor("84473712044", null, "muriloluizbrito-81@ipmmi.org.br", "(31) 99776-7434", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, () -> new Receptor("84473712044", "", "muriloluizbrito-81@ipmmi.org.br", "(31) 99776-7434", "PESSOA_FISICA"));
	}
	
	@Test
	public void testEmailInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Receptor("84473712044", "Murilo Luiz Brito", null, "(31) 99776-7434", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, () -> new Receptor("84473712044", "Murilo Luiz Brito", "", "(31) 99776-7434", "PESSOA_FISICA"));
	}
	
	@Test
	public void testCelularInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Receptor("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br", null, "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, () -> new Receptor("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br", "", "PESSOA_FISICA"));
	}
	
	@Test
	public void testClasseInvalida() {
		assertThrows(IllegalArgumentException.class, () -> new Receptor("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br", "(31) 99776-7434", null));
		assertThrows(IllegalArgumentException.class, () -> new Receptor("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br", "(31) 99776-7434", ""));
	}
	
	@Test
	public void testToString() {
		assertEquals("Murilo Luiz Brito/844.737.120-44, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", r.toString());
	}
	
	@Test
	public void testReceptoresIguais() {
		Receptor r2 = new Receptor("84473712044", "Tomás Otávio Lucas Teixeira", "tomas@ipmmi.org.br", "(79) 98977-0397", "PESSOA_FISICA");
		assertTrue(r.equals(r2));
	}
	
	@Test
	public void testReceptoresDiferentes() {
		Receptor r2 = new Receptor("84473712045", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br", "(31) 99776-7434", "PESSOA_FISICA");
		assertFalse(r.equals(r2));
	}
}
