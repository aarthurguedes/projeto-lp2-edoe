package controllers;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*;
import abstrato.Usuario;
import eDoe.Doador;
import eDoe.Receptor;
import validacao.ValidadorControllers;
import util.Util;


/**
* Representacao de um controlador de usuarios. 
*
* @author Antonio Bertino de Vasconcelos Cabral Neto
* @author Arthur Silva Lima Guedes
* @author Danilo de Menezes Freitas
* @author Talita Galdino Gouveia
*/
public class UsuarioController {
	
	/**
	* Mapa de usuarios.
	*/
	private Map<String, Usuario> usuarios;

	/**
	* Objeto validador.
	*/
	private ValidadorControllers vc;
	/**
	* Inteiro representando a posição na qual o usuario foi cadastrado.
	*/
	private int idOrdem;
	
	/**
	* Constroi o controlador dos usuarios.
	*
	*/
	public UsuarioController() {
		this.usuarios = new HashMap<>();
		this.vc = new ValidadorControllers();
		this.idOrdem = 0;
	}
	
	/**
	* @return o mapa de usuarios
	*/
	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}
	
	/**
	* Valida os parametros passados e cadastra o doador.
	*
	* @param id a identificacao do usuario
	* @param nome o nome do usuario
	* @param email o email do usuario
	* @param celular o numero do celular do usuario
	* @param classe a classe do usuario
     * @return String com id de identificao unica do usuario
	*/
	public String cadastrarDoador(String id, String nome, String email, String celular, String classe) {
        vc.validaCadastramento(id, nome, email, celular, classe, usuarios);
        Usuario doador = new Doador(id, nome, email, celular, classe, this.idOrdem);
        this.idOrdem ++;
        usuarios.put(Util.formatString(id), doador);
        
        return id;
    }

    /**
     * Metodo criado para cadastro de um receptor no sistema.
     * @param id a identificacao do usuario receptor
     * @param nome o nome do usuario receptor
     * @param email o email do usuario receptor
     * @param celular o numero do celular do usuario receptor
     * @param classe a classe do usuario receptor
     * @return String com id de identificao unica do usuario receptor
     */
    public String cadastrarReceptor(String id, String nome, String email, String celular, String classe) {
	    vc.validaCadastramento(id, nome, email, celular, classe, this.usuarios) ;
	    Usuario receptor = new Receptor(id, nome, email, celular, classe, this.idOrdem);
	    this.idOrdem ++;
	    usuarios.put(Util.formatString(id), receptor);
	    
        return id;
    }

    /**
     *  Valida os parametros e procura no sistema, usuarios cadastrados com o nome passado no parametro.
     *  Caso haja mais de um usuario cadastrado com o mesmo nome completo, se retorna uma String com os dois nomes, porem ordenados com quem foi cadastrado primeiro..
     *
     * @param nome identificando o nome o qual será procurado
     * @return String no formato: "Nome/id, email, (xx) yyyy-zzzz, status: status"
     */
    public String  pesquisarUsuarioPorNome(String nome) {
	    vc.validaPesquisaUsuarioPorNome(nome);

        String saida = "";

        List<Usuario> usuarioList = new ArrayList<>(this.usuarios.values());
        Collections.sort(usuarioList);


        for (Usuario usuario : usuarioList) {
            if (Util.formatString(nome).equals(Util.formatString(usuario.getNome()))) {
                saida += usuario.toString() + " | ";
            }
        }

        vc.validaExistenciaPesquisa(saida, nome);

        return saida.substring(0, saida.length() - 3);
    }


    /**
     * Valida os parametros e procura no sistema algum usuario com o id cadastrado. Como o id e unico, so podera existir um usuario com esse id.
     *
     * @param id do usuario  a ser pesquisado
     * @return String no formato: "Nome/id, email, (xx) yyyy-zzzz, status: status"
     */
    public String pesquisarUsuarioPorId(String id)  {
        vc.validaPesquisaUsuarioPorId(id, this.usuarios);
        return this.usuarios.get(Util.formatString(id)).toString();
    }

    /**
     * Metodo de atualizacao dos dados do usuario.
     *
     * @param id identificao unica do usuario
     * @param nome do usuario a ser atualizado
     * @param email email do usuario a ser atualizado
     * @param celular celular do usuario a ser atualizado
     * @return toString do usuario.
     */
    public String atualizarUsuario(String id, String nome, String email, String celular) {
    	vc.validaExistenciaUsuario(id, usuarios);

    	if (nome != null && !nome.trim().equals("")) {
    		usuarios.get(id).setNome(nome);
    	}
    	if (email != null && !email.trim().equals("")) {
    		usuarios.get(id).setEmail(email);
        }
        if (celular != null && !celular.trim().equals("")) {
            usuarios.get(id).setCelular(celular);
    	}
    	return usuarios.get(id).toString();
    }

    /**
     *  Metodo para remocao de usuarios do sistema
     * @param id de identificacao do usuario.
     */
    public void removerUsuario(String id) {
    	vc.validaExistenciaUsuario(id, usuarios);
    	usuarios.remove(id);
    }

    /**
     * Metodo para leitura dos arquivos .csv e decisao de escolhas entre: cadastro de receptores e atualizacao de dados do Usuario
     * @param caminho do arquvio .csv
     */
    public void lerReceptores(String caminho) {
        String[] pathname = caminho.split("/");

        try {
            File csvFile = new File(pathname[0] + File.separator + pathname[1]);
            Scanner sc = new Scanner(csvFile);
            String linha = sc.nextLine();  //Leitura do Cabeçalho

            while (sc.hasNextLine()) {
                linha = sc.nextLine();
                String[] dadosReceptor = linha.split(",");
                String id = dadosReceptor[0];
                String nome = dadosReceptor[1];
                String email = dadosReceptor[2];
                String celular = dadosReceptor[3];
                String classe = dadosReceptor[4];

                if (pathname[1].equals("novosReceptores.csv")) {
                    this.cadastrarReceptor(id, nome, email, celular, classe);
                } else if (pathname[1].equals("atualizaReceptores.csv")){
                    this.atualizarUsuario(id, nome, email, celular);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo nao encontrado");
        }
    }
}
