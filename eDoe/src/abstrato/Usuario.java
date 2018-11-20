package abstrato;

import validacao.ValidadorBase;

/**
* Representacao de um usuario, que possui nome, email, celular, classe e id. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public abstract class Usuario {
	
	/**
	* Identificacao do usuario.
	*/
	protected String id;
	/**
	* Nome do usuario.
	*/
	protected String nome;
	/**
	* Email do usuario.
	*/
	protected String email;
	/**
	* Celular do usuario.
	*/
	protected String celular;
	/**
	* Classe do usuario.
	*/
	protected String classe;
	/**
	* Objeto validador.
	*/
	private ValidadorBase vb = new ValidadorBase();
	
	/**
	* Constroi o usuario a partir do seu id, nome, email, celular e classe.
	*
	* @param id a identificacao do usuario
	* @param nome o nome do usuario
	* @param email o email do usuario
	* @param celular o numero do celular do usuario
	* @param classe a classe do usuario
	*/
	public Usuario(String id, String nome, String email, String celular, String classe) {
		vb.validaUsuario(id, nome, email, celular, classe);
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
	}
	
	/**
	* @return o status (doador/receptor) do usuario
	*/
	public abstract String getStatus();

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

	/**
	* Retorna a String que representa o usuario. Formato: Nome/Id, email, celular, status.
	* 
	* @return a representação em String do usuario.
	*/
	@Override
	public String toString() {
		return this.nome + "/" + this.id + ", " + this.email + ", " + this.getStatus();
	}
}
