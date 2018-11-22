package eDoe;

import abstrato.Usuario;

/**
* Representacao de um doador, que possui nome, email, celular, classe e id. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class Doador extends Usuario{

	/**
	* Constroi o doador a partir do seu id, nome, email, celular e classe.
	*
	* @param id a identificacao do doador
	* @param nome o nome do doador
	* @param email o email do doador
	* @param celular o numero do celular do doador
	* @param classe a classe do doador
	*/
	public Doador(String id, String nome, String email, String celular, String classe, int idOrdem) {
		super(id, nome, email, celular, classe, idOrdem);
	}

	/**
	* @return o status do doador
	*/
	@Override
	public String getStatus() {
		return "doador";
	}
}
