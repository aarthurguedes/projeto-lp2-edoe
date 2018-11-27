package temp;

import eDoe.Usuario;

/**
* Representacao de um receptor, que possui nome, email, celular, classe e id. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class Receptor {

	/**
	* Constroi o receptor a partir do seu id, nome, email, celular e classe.
	*
	* @param id a identificacao do receptor
	* @param nome o nome do receptor
	* @param email o email do receptor
	* @param celular o numero do celular do receptor
	* @param classe a classe do receptor
	*/
	public Receptor(String id, String nome, String email, String celular, String classe, int idOrdem) {
//		super(id, nome, email, celular, classe, idOrdem);
	}

	/**
	* @return o status do receptor
	*/
	public String getStatus() {
		return "receptor";
	}
}
