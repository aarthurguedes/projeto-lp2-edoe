package enums;

import util.Validador;

/**
* Representacao de um enum que possui as classes que um usuario pode ter 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public enum Classe {
    PESSOA_FISICA("PESSOA_FISICA"), IGREJA("IGREJA"), ORGAO_PUBLICO_MUNICIPAL("ORGAO_PUBLICO_MUNICIPAL"), ORGAO_PUBLICO_ESTADUAL("ORGAO_PUBLICO_ESTADUAL"), ORGAO_PUBLICO_FEDERAL("ORGAO_PUBLICO_FEDERAL"), ONG("ONG"), ASSOCIACAO("ASSOCIACAO"), SOCIEDADE("SOCIEDADE");
    
	private String classeUsuario;

    Classe(String classeUsuario) {
        this.classeUsuario = classeUsuario;
    }

    /**
     * Metodo responsavel por retornar o valor atual da classe.
     * @return valor atual da classe 
     */
    public String getClasse() {
    	return classeUsuario;
    }
   
    /**
     * Metodo responsavel por retornar a classe do usuario
     * @param classeUsuario representa a classe do usuario
     * @return valor atual da classe do usuario
     */
    public static Classe getClassePorString(String classeUsuario) {
    	Validador.validarString(classeUsuario, "Entrada invalida: classe nao pode ser vazia ou nula.");
    	
    	for (Classe classe : Classe.values()) {
        	if (classe.getClasse().equals(classeUsuario)) {
				return classe;
			}
        }
    	
    	throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
    }
}

