package projecthope;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lucas Peixoto Nunes
 */
public class Usuario {
   
//Atributos
    private String user;
    private String senha;
    private boolean encontrado = false;
    private ArrayList<Energia> consum;
    private static MySQL bd;

    public ArrayList<Energia> getConsum() {
        return consum;
    }

    public void setConsum(ArrayList<Energia> consum) {
        this.consum = consum;
    }

    //construtor padrão
    public Usuario() {
    }

    //Construtor com parametros
    public Usuario(String user, String senha) {
        this.user = user;
        this.senha = senha;
    }

    //getter e setter
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public boolean getEncontrado() {
        return encontrado;
    }

    //metodos 
    
    public void bancoConectado(){
    bd = new MySQL(
                "upx",
                "root",
                "Lu666peixoto14");
        if (!bd.conectaBanco()) {
            System.exit(1);
        }
    }
    
    public void inserirUser(String user, String senha) {

       bancoConectado();

        String query = "INSERT INTO USUARIO (usuario,senha) VALUES(" + "'" + user + "','" + senha + "')";

        if (bd.insert(query)) {
            System.out.println("\nSeja bem vindo/a " + user);
        } else {
            System.out.println("\nErro ao cadastrar.");
        }
    }

    public void contaExistente(String user, String senha) {

       bancoConectado();

        String query = "SELECT usuario, senha FROM usuario WHERE usuario = '" + user + "' AND senha = '" + senha + "'";
        ResultSet set = bd.select(query);

        if (set == null) {
            System.out.println("\nNenhum usuario cadastrado.");
        } else {
            try {

                while (set.next()) {
                   this.setUser(set.getString(1));
                   this.setSenha(set.getString(2));
                    encontrado = true;
                }
                if (encontrado==false) {
                    System.out.println("\nNenhum usuario encontrado");
                }else{
                    System.out.println("\nOla, seja bem vindo/a de volta " + this.getUser()+", sentimos sua falta <3");
                }
            } catch (Exception e) {

                System.out.printf("Erro ao ler lista de usuarios: %s\n", e.getMessage());
            }
        }

    }

    public int IdUsuario(String nome, String senha) {
        
         bancoConectado();

        String query = "SELECT cod_usuario FROM Usuario WHERE usuario = '" + nome + "' AND senha = '" + senha + "'";
        ResultSet result = bd.select(query);

        try {
            if (result != null && result.next()) {
                return result.getInt("cod_usuario");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.printf("Erro ao obter o código do usuário: %s\n", e.getMessage());
            return -1;
        }
    }
    //fim
}
