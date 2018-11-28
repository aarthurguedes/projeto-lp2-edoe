package controllers;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*;

import eDoe.Item;
import eDoe.Usuario;
import util.Validador;
import util.Util;
import enums.Classe;

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
     * Set de descricoes que os itens podem ter.
     */
    private Set<String> descritores;
    /**
     * Objeto validador.
     */
    private Validador validador;
    /**
     * Inteiro representando a posição na qual o usuario foi cadastrado.
     */
    private int idOrdem;
    /**
     * Inteiro representando o id do item no mapa de itens do usuario.
     */
    private int idItem;
    
    /**
     * Constroi o controlador dos usuarios.
     */
    public UsuarioController() {
        this.usuarios = new HashMap<>();
        this.descritores = new HashSet<>();
        this.validador = new Validador();
        this.idOrdem = 1;
        this.idItem = 1;
    }

    /**
     * @return o mapa de usuarios
     */
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
    
    private void validarInexistenciaUsuario(String id) {
    	if (usuarios.containsKey(id)) {
            throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
        }
    }
    
    private void validarCadastroUsuario(String id, String nome, String email, String celular, String classe) {
    	validador.validarString(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        validador.validarString(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
        validador.validarString(email, "Entrada invalida: email nao pode ser vazio ou nulo.");
        validador.validarString(celular, "Entrada invalida: celular nao pode ser vazio ou nulo.");
        validador.validarString(classe, "Entrada invalida: classe nao pode ser vazia ou nula.");
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
    	this.validarInexistenciaUsuario(id);
    	this.validarCadastroUsuario(id, nome, email, celular, classe);

        Usuario usuario = new Usuario(id, nome, email, celular, Classe.getClassePorString(classe), "doador", this.idOrdem);
        usuarios.put(Util.formatString(id), usuario);
        this.idOrdem++;
        
        return this.usuarios.get(id).getId();
    }

    /**
     * Metodo criado para cadastro de um receptor no sistema.
     *
     * @param id      a identificacao do usuario receptor
     * @param nome    o nome do usuario receptor
     * @param email   o email do usuario receptor
     * @param celular o numero do celular do usuario receptor
     * @param classe  a classe do usuario receptor
     * @return String com id de identificao unica do usuario receptor
     */
    public String cadastrarReceptor(String id, String nome, String email, String celular, String classe) {
    	this.validarInexistenciaUsuario(id);
    	this.validarCadastroUsuario(id, nome, email, celular, classe);

        Usuario receptor = new Usuario(id, nome, email, celular, Classe.getClassePorString(classe), "receptor", this.idOrdem);
        usuarios.put(Util.formatString(id), receptor);
        this.idOrdem++;
        
        return id;
    }
    
    private void validarExistenciaUsuario(String id) {
    	if (!usuarios.containsKey(id)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
        }
    }
    
    /**
     * Valida os parametros e procura no sistema algum usuario com o id cadastrado. Como o id e unico, so podera existir um usuario com esse id.
     *
     * @param id do usuario  a ser pesquisado
     * @return String no formato: "Nome/id, email, (xx) yyyy-zzzz, status: status"
     */
    public String pesquisarUsuarioPorId(String id) {
        validador.validarString(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        this.validarExistenciaUsuario(id);
        
        return this.usuarios.get(Util.formatString(id)).toString();
    }

    /**
     * Valida os parametros e procura no sistema, usuarios cadastrados com o nome passado no parametro.
     * Caso haja mais de um usuario cadastrado com o mesmo nome completo, se retorna uma String com os dois nomes, porem ordenados com quem foi cadastrado primeiro..
     *
     * @param nome identificando o nome o qual será procurado
     * @return String no formato: "Nome/id, email, (xx) yyyy-zzzz, status: status"
     */
    public String pesquisarUsuarioPorNome(String nome) {
        validador.validarString(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");

        List<Usuario> usuarioList = new ArrayList<>(this.usuarios.values());
        Collections.sort(usuarioList);
        
        String saida = "";
        for (Usuario usuario : usuarioList) {
            if (Util.formatString(nome).equals(Util.formatString(usuario.getNome()))) {
                saida += usuario.toString() + " | ";
            }
        }
        if (saida.equals("")) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
        }
        
        return saida.substring(0, saida.length() - 3);
    }

    /**
     * Metodo de atualizacao dos dados do usuario.
     *
     * @param id identificao unica do usuario
     * @param nome do usuario a ser atualizado
     * @param email o email do usuario a ser atualizado
     * @param celular o celular do usuario a ser atualizado
     * @return o toString do usuario.
     */
    public String atualizarUsuario(String id, String nome, String email, String celular) {
        validador.validarString(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        this.validarExistenciaUsuario(id);

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
     * Metodo para remocao de usuarios do sistema
     *
     * @param id de identificacao do usuario.
     */
    public void removerUsuario(String id) {
        validador.validarString(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        this.validarExistenciaUsuario(id);
        usuarios.remove(id);
    }

    /**
     * Metodo para leitura dos arquivos .csv e decisao de escolhas entre: cadastro de receptores e atualizacao de dados do Usuario
     *
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
                } else if (pathname[1].equals("atualizaReceptores.csv")) {
                    this.atualizarUsuario(id, nome, email, celular);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo nao encontrado");
        }
    }

    public void adicionarDescritor(String descricao) {
        validador.validarString(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");

        for (String s: this.descritores) {
            if (s.toLowerCase().equals(descricao.toLowerCase())) {
                throw new IllegalArgumentException("Descritor de Item ja existente: " + s.toLowerCase() + ".");
            }
        }

        descritores.add(descricao);
    }

    private int getIdItensIguais(Usuario usuario, String descricao, String tags) {
        for (Item i : usuario.getItens().values()) {
            if (i.getDescricao().equals(descricao) && i.getTags().equals(tags)) {
                return i.getId();
            }
        }
        return 0;
    }
    
    /**
     * Metodo responsavel por cadastrar um item e validar seus parametros.
     *
     * @param idDoador representa o id do usuario doador do item
     * @param descricao representa a descricao do item
     * @param quantidade representa a quantidade daquele item
     * @param tags representa as tags do item
     * @return inteiro que representa a identificacao do item
     */
    public int cadastrarItem(String idDoador, String descricao, int quantidade, String tags) {
        validador.validarString(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
        validador.validarInteiro(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");
        validador.validarString(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        this.validarExistenciaUsuario(idDoador);

        Usuario usuario = this.usuarios.get(idDoador);
        if (this.getIdItensIguais(usuario, descricao, tags) == 0) {
            usuario.cadastrarItem(this.idItem, descricao, quantidade, tags);
        } else {
            usuario.cadastrarItem(this.getIdItensIguais(usuario, descricao, tags), descricao, quantidade, tags);
        }
        this.idItem++;
        
        return (this.idItem - 1);
    }

    private void validarExistenciaItem(Usuario usuario, int id) {
    	if (!usuario.containsItem(id)) {
            throw new IllegalArgumentException("Item nao encontrado: " + id + ".");  
        
    	}
    }
    
    /**
     * Metodo responsavel por exibir um item.
     * @param idItem representa a identificacao do item
     * @param idDoador representa a identificacao do doador
     * @return String que representa o item a ser exibido
     */
    public String exibirItem(int idItem, String idDoador) {
    	this.validarExistenciaUsuario(idDoador);
    	this.validarExistenciaItem(this.usuarios.get(idDoador), idItem);
    	
    	return this.usuarios.get(idDoador).exibirItem(idItem);
    }	

    /**
     * Metodo responsavel por atualizar um item.
     * @param idItem representa a identificacao do item
     * @param idDoador representa a identificacao do doador
     * @param quantidade representa a nova quantidade daquele item
     * @param tags representa as novas tags do item
     * @return string que representa o item atualizado
     */
    public String atualizarItem(int idItem, String idDoador, int quantidade, String tags) {
    	validador.validarInteiro(idItem, "Entrada invalida: id do item nao pode ser negativo.");
        validador.validarString(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        this.validarExistenciaUsuario(idDoador);
        this.validarExistenciaItem(this.usuarios.get(idDoador), idItem);
        
        return this.usuarios.get(idDoador).atualizarItem(idItem, quantidade, tags);
    }

    /**
     * Metodo responsavel por remover um item.
     * @param idDoador representa a identificacao do doador
     * @param idItem representa a identificacao do item
     */
    public void removerItem(int idItem, String idDoador) {
    	 validador.validarInteiro(idItem, "Entrada invalida: id do item nao pode ser negativo.");
         validador.validarString(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
         this.validarExistenciaUsuario(idDoador);
        
         if (this.usuarios.get(idDoador).getItens().size() == 0) {
            throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
         }
         this.validarExistenciaItem(this.usuarios.get(idDoador), idItem);
         
         this.usuarios.get(idDoador).removerItem(idItem);
    }
}
