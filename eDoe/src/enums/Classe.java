package enums;

public enum Classe {
    PESSOA_FISICA("PESSOA_FISICA"), IGREJA("IGREJA"), ORGAO_PUBLICO_MUNICIPAL("ORGAO_PUBLICO_MUNICIPAL"), ORGAO_PUBLICO_ESTADUAL("ORGAO_PUBLICO_ESTADUAL"), ORGAO_PUBLICO_FEDERAL("ORGAO_PUBLICO_FEDERAL"), ONG("ONG"), ASSOCIACAO("ASSOCIACAO"), SOCIEDADE("SOCIEDADE");
    public String tipo;


    Classe(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }
}

