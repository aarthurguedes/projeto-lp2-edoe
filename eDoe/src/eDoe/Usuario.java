package eDoe;

import enums.Classe;
import util.Validador;

/**
* Representacao de um usuario, que possui nome, email, celular, classe e id. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class Usuario implements Comparable <Usuario> {
	
	/**
	* Identificacao do usuario.
	*/
	private String id;
	/**
	* Nome do usuario.
	*/
	private String nome;
	/**
	* Email do usuario.
	*/
	private String email;
	/**
	* Celular do usuario.
	*/
	private String celular;
	/**
	* Status do usuario.
	*/
	private String status;
	/**
	* Classe do usuario.
	*/
	private Classe classe;
    /**
     * Inteiro representando a posição na qual o usuario foi cadastrado.
     */
	private int idOrdem;
    /**
	* Objeto validador.
	*/
	private Validador validador = new Validador();
	
	/**
	* Constroi o usuario a partir do seu id, nome, email, celular e classe.
	*
	* @param id a identificacao do usuario
	* @param nome o nome do usuario
	* @param email o email do usuario
	* @param celular o numero do celular do usuario
	* @param classe a classe do usuario
	*/
	public Usuario(String id, String nome, String email, String celular, Classe classe, String status, int idOrdem) {
		validador.validarString(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		validador.validarString(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
		validador.validarString(email, "Entrada invalida: email nao pode ser vazio ou nulo.");
		validador.validarString(celular, "Entrada invalida: celular nao pode ser vazio ou nulo.");
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.status = status;
		this.idOrdem = idOrdem;
	}
	
	/**
	 * @return o id do usuario
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return o nome do usuario
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome o novo nome do usuario
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return o email do usuario
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email o novo email do usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return o numero de celular do usuario
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular o novo numero de celular do usuario
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	/**
	 * @return o id que representa a ordem de cadastro do usuario
	 */
	public int getIdOrdem() {
		return idOrdem;
	}
	
	/**
	* Retorna a String que representa o usuario. Formato: Nome/Id, email, celular, status.
	* 
	* @return a representacao em String do usuario.
	*/
	@Override
	public String toString() {
		return this.nome + "/" + this.id + ", " + this.email + ", " + this.celular + ", status: " + "STATUS AQUI";
	}
	
	/**
	* Retorna o inteiro que representa a posicao do Usuario na memoria.
	* 
	* @return a representacao numerica do Usuario.  
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	* Retorna o boolean que representa se dois usuarios sao iguais, ou seja, se possuem o mesmo id.
	* 
	* @param obj o objeto que representa o outro usuario
	* @return o valor boolean da igualdade (ou nao) entre dois clientes. 
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    public int compareTo(Usuario usuario) {
        return this.idOrdem - usuario.idOrdem;
    }
}
