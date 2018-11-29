package enums; 

public enum Classe {
    PESSOA_FISICA("PESSOA_FISICA"), IGREJA("IGREJA"), ORGAO_PUBLICO_MUNICIPAL("ORGAO_PUBLICO_MUNICIPAL"), ORGAO_PUBLICO_ESTADUAL("ORGAO_PUBLICO_ESTADUAL"), ORGAO_PUBLICO_FEDERAL("ORGAO_PUBLICO_FEDERAL"), ONG("ONG"), ASSOCIACAO("ASSOCIACAO"), SOCIEDADE("SOCIEDADE");
    
	private String classeUsuario;

    Classe(String classeUsuario) {
        this.classeUsuario = classeUsuario;
    }

    public String getClasse() {
    	return classeUsuario;
    }
    
    private static void validarClasse(String classe, String msgErro) {
    	if (classe == null || classe.trim().equals("")) {
    		throw new IllegalArgumentException(msgErro);
    	}
    }
    
    public static Classe getClassePorString(String classeUsuario) {
    	validarClasse(classeUsuario, "Entrada invalida: classe nao pode ser vazia ou nula.");
    	
    	for (Classe classe : Classe.values()) {
        	if (classe.getClasse().equals(classeUsuario)) {
				return classe;
			}
        }
    	
    	throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
    }
}

