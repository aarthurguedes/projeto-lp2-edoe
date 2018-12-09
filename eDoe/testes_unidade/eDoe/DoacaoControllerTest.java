package eDoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.DoacaoController;
import enums.Classe;
import util.Util;

class DoacaoControllerTest {

	DoacaoController doacaoController;
	private Usuario doador;
	private Usuario receptor;

	private void criaDoadorEReceptor() {
		doador = new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211",
				Classe.getClassePorString("PESSOA_FISICA"), "doador", 1);
		receptor = new Usuario("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br", "(31) 99776-7434",
				Classe.getClassePorString("PESSOA_FISICA"), "receptor", 2);
	}

	private void cadastrarItens() {
		doador.cadastrarItem(1, new Descritor(Util.formatString("cadeira de rodas")), 5, "roda grande,cadeira");
		receptor.cadastrarItem(2, new Descritor(Util.formatString("cadeira de rodas")), 1, "roda grande,cadeira");
		doador.cadastrarItem(3, new Descritor(Util.formatString("colchao")), 7, "dormir,conforto");
		receptor.cadastrarItem(4, new Descritor(Util.formatString("colchao")), 45, "dormir,conforto");
		doador.cadastrarItem(5, new Descritor(Util.formatString("jaqueta de couro")), 21,
				"outfit,couro de boi,couro de bode");
		receptor.cadastrarItem(6, new Descritor(Util.formatString("jaqueta de couro")), 21,
				"outfit,couro de boi,couro de bode");
	}

	@BeforeEach
	public void criaDoacaoController() {
		doacaoController = new DoacaoController();
	}

	@Test
	public void testRealizaDoacao() {
		this.criaDoadorEReceptor();
		this.cadastrarItens();
		assertEquals(
				"11/10/2018 - doador: Elizabeth Ashe/70513372911, item: cadeira de rodas, quantidade: 1, receptor: Murilo Luiz Brito/84473712044",
				doacaoController.realizaDoacao(receptor.getItem(2), doador.getItem(1), "11/10/2018"));
	}

	@Test
	public void testListaDoacoes() {
		this.criaDoadorEReceptor();
		this.cadastrarItens();
		doacaoController.realizaDoacao(receptor.getItem(2), doador.getItem(1), "11/10/2018");
		doacaoController.realizaDoacao(receptor.getItem(4), doador.getItem(3), "15/09/2016");
		doacaoController.realizaDoacao(receptor.getItem(6), doador.getItem(5), "15/09/2016");
		assertEquals(
				"15/09/2016 - doador: Elizabeth Ashe/70513372911, item: jaqueta de couro, quantidade: 21, receptor: Murilo Luiz Brito/84473712044 | "
				+ "15/09/2016 - doador: Elizabeth Ashe/70513372911, item: colchao, quantidade: 7, receptor: Murilo Luiz Brito/84473712044 | "
				+ "11/10/2018 - doador: Elizabeth Ashe/70513372911, item: cadeira de rodas, quantidade: 1, receptor: Murilo Luiz Brito/84473712044",
				doacaoController.listaDoacoes());
	}
}
