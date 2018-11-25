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
public abstract class Usuario implements Comparable <Usuario>{
	
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
     * Inteiro representando a posição na qual o usuario foi cadastrado.
     */
    protected int idOrdem;
    /**
	* Objeto validador base.
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
	public Usuario(String id, String nome, String email, String celular, String classe, int idOrdem) {
		vb.validaUsuario(id, nome, email, celular, classe);
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.idOrdem = idOrdem;
	}
	
	/**
	 * @return o atual id do usuario
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return o atual nome do usuario
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Metodo responsavel por alterar o nome do usuario
	 * @param nome o novo nome do usuario
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return o atual email do usuario
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Metodo responsavel por alterar o email do usuario
	 * @param email o novo email do usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return o atual numero de celular do usuario
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Metodo responsavel por mudar o numero do celular do usuario
	 * @param celular o novo numero de celular do usuario
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return a atual classe do usuario
	 */
	public String getClasse() {
		return classe;
	}
	
	/**
	 * Metodo responsavel por mudar a classe do usuario
	 * @param classe a nova classe do usuario
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	/**
	 * @return o id que representa a ordem de cadastro do usuario
	 */
	public int getIdOrdem() {
		return idOrdem;
	}
	
	/**
	* @return o status atual (doador/receptor) do usuario
	*/
	public abstract String getStatus();

	/**
	* Retorna a String que representa o usuario. Formato: Nome/Id, email, celular, status.
	* 
	* @return a representacao em String do usuario.
	*/
	@Override
	public String toString() {
		return this.nome + "/" + this.id + ", " + this.email + ", " + this.celular + ", status: " + this.getStatus();
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
	* @return o valor boolean da igualdade (ou nao) entre dois usuarios. 
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
