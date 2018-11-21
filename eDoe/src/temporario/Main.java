package temporario;

import controllers.UsuarioController;

public class Main {
    public static void main(String[] args) {
        UsuarioController uc = new UsuarioController();

        uc.lerReceptores("arquivos_sistema/novosReceptores.csv");
    }
}
