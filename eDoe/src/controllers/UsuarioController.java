package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

import abstrato.Usuario;
import eDoe.Doador;
import eDoe.Receptor;
import validacao.ValidadorControllers;
import util.Util;

import javax.swing.text.MaskFormatter;

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
	* Constroi o controlador dos usuarios.
	*
	*/
	public UsuarioController() {
		this.usuarios = new HashMap<>();
		this.vc = new ValidadorControllers();
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
        String idFormatado = this.formatID(id);
        Usuario doador = new Doador(idFormatado, nome, email, celular, classe);
        usuarios.put(Util.formatString(id), doador);
        return this.usuarios.get(Util.formatString(id)).getId();
    }

    /**
     * Método criado para cadastro de um receptor no sistema.
     * @param id a identificacao do usuario
     * @param nome o nome do usuario
     * @param email o email do usuario
     * @param celular o numero do celular do usuario
     * @param classe a classe do usuario
     * @return String com id de identificao unica do usuario
     */
    public String cadastrarReceptor(String id, String nome, String email, String celular, String classe) {
	    vc.validaCadastramento(id, nome, email, celular, classe, this.usuarios) ;
	    String idFormatado = this.formatID(id);
	    Usuario receptor = new Receptor(idFormatado, nome, email, celular, classe);
	    usuarios.put(Util.formatString(id), receptor);
        return this.usuarios.get(Util.formatString(id)).getId();
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

        List<Usuario> usuariosList = new ArrayList<>(this.usuarios.values());
        String saida = "";

        for (Usuario usuario : usuariosList) {
            if (Util.formatString(nome).equals(Util.formatString(usuario.getNome()))) {
                saida += usuario.toString() + ", ";
            }
        }

        vc.validaExistenciaPesquisa(saida, nome);

        return saida.substring(0, saida.length() - 2);
    }


    /**
     * Valida os parametros e procura no sistema algum usuario com o id cadastrado. Como o id e unico, so podera existir um usuario com esse id.
     *
     * @param id do usuario  a ser pesquisado
     * @return String no formato: "Nome/id, email, (xx) yyyy-zzzz, status: status"
     */
    public String pesquisarUsuarioPorId(String id)  {
        vc.validaPesquisaUsuarioPorId(id, this.usuarios);
        String idFormato = this.formatID(id);
        return this.usuarios.get(Util.formatString(idFormato)).toString();
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
    	
    	if (nome != null) {
    		usuarios.get(id).setNome(nome);
    		return usuarios.get(id).toString();
    	} else if (email != null) {
    		usuarios.get(id).setEmail(email);
    		return usuarios.get(id).toString();
    	} else {
    		usuarios.get(id).setCelular(celular);
    		return usuarios.get(id).toString();
    	}
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

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo nao encontrado");
        }
    }

    private String formatID(String id){
        String saida = "";

        try {
            if (id.length() == 11) {
                MaskFormatter mask = new MaskFormatter("###.###.###-##");
                mask.setValueContainsLiteralCharacters(false);
                saida += mask.valueToString(id);
            } else {
                MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
                mask.setValueContainsLiteralCharacters(false);
                saida += mask.valueToString(id);
            }
        } catch (ParseException e) {
            System.err.println("Erro na formatação do ID");
        }

        return saida;
    }




























}
