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
    
    private static void validarClasseNaoNula(String classe, String msgErro) {
    	if(classe == null) {
    		throw new IllegalArgumentException(msgErro);
    	}
    }
    
    private static void validarClasseNaoVazia(String classe, String msgErro) {
    	if(classe.trim().equals("")) {
    		throw new IllegalArgumentException(msgErro);
    	}
    }
    
    private static void validarClasse(String classe, String msgErro) {
    	validarClasseNaoNula(classe, msgErro);
    	validarClasseNaoVazia(classe, msgErro);
    }
    
    public static Classe getClassePorString(String classeUsuario) {
        validarClasse(classeUsuario, "Entrada invalida: classe nao pode ser vazia ou nula.");
    	
    	for(Classe classe : Classe.values()) {
        	if (classe.getClasse().equals(classe)) {
				return classe;
			}
        }
        throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
    }
}

