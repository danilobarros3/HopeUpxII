package projecthope;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Lucas Peixoto Nunes
 */
public class Energia {

    //instanciações
     Scanner sc = new Scanner(System.in);
     
    //atributos
    private double valorConta;
    private double valorKWHregiao;
    private double kwhMes;
    private int ideiaDeGasto;
    private int quantPessoa;
    private double gastoIdeal;
    private static MySQL bd;
    double gastoRealConta;
    String dataGasto;
    double gastoKwh;

    //Construtor padrão
    public Energia() {
    }

    //getters e setters
    public int getQuantPessoa() {
        return quantPessoa;
    }

    public void setQuantPessoa(int quantPessoa) {
        this.quantPessoa = quantPessoa;
    }

    public double getValorConta() {
        return valorConta;
    }

    public void setValorConta(double valorConta) {
        this.valorConta = valorConta;
    }

    public double getValorKWHregiao() {
        return valorKWHregiao;
    }

    public void setValorKWHregiao(double valorKWHregiao) {
        this.valorKWHregiao = valorKWHregiao;
    }

    public double getKwhMes() {
        return kwhMes;
    }

    public void setKwhMes(double kwhMes) {
        this.kwhMes = kwhMes;
    }

    public int ideiaDeGasto() {
        return ideiaDeGasto;
    }

    public void ideiaDeGasto(int ideiaDeGasto) {
        this.ideiaDeGasto = ideiaDeGasto;
    }

    public double getGastoIdeal(int idUsuario) {

        bancoConectado();

        String query = "SELECT meta_gasto FROM questionario INNER JOIN usuario "
                + "on questionario.cod_usuario = usuario.cod_usuario where usuario.cod_usuario= '" + idUsuario + "' limit 1";

        ResultSet result = bd.select(query);

        if (result == null) {
            System.out.println("\nNenhum usuario cadastrado.");
        } else {
            try {

                while (result.next()) {
                    this.setGastoIdeal(result.getDouble(1));

                }

            } catch (Exception e) {

                System.out.printf("Erro ao ler lista de usuarios: %s\n", e.getMessage());
            }
        }

        return gastoIdeal;
    }

    public void setGastoIdeal(double gastoIdeal) {
        this.gastoIdeal = gastoIdeal;
    }

    //metodos
    public void bancoConectado() {
        bd = new MySQL(
                "upx",
                "root",
                "Lu666peixoto14");
        if (!bd.conectaBanco()) {
            System.exit(1);
        }
    }

    public void questionario(int idUsuario) {

       System.out.print("""
                          
                              ___________________________________________________________________________
                             | # Antes de iniciar no Hope pedimos que voce responda nosso questionario # |
                             |---------------------------------------------------------------------------|
                             | 1) Qual o valor do KW/H da sua localidade?                                |
                             |___________________________________________________________________________|
                                   
                              --Resposta: """);
        valorKWHregiao = sc.nextDouble();

        sc.nextLine();

        System.out.print("""
                              ____________________________________________
                             |                                            |
                             |2) Quantas pessoas Moram em sua residencia? |
                             |____________________________________________|  
                         
                              --Resposta: """);
        quantPessoa = sc.nextInt();

        sc.nextLine();

        System.out.print("""
                              ___________________________________
                             |                                   |
                             |3) O quanto voce acredita gastar?  |
                             |-----------------------------------|
                             |1-Muito                            |
                             |2-Mediano                          |
                             |3-pouco                            |
                             |___________________________________|
                         
                              --resposta: """);
        ideiaDeGasto = sc.nextInt();

        String ideiaGString = "";
        switch (ideiaDeGasto) {
            case 1:
                ideiaGString = "Muito";
                break;
            case 2:
                ideiaGString = "Mediano";
                break;
            case 3:
                ideiaGString = "Pouco";
                break;
        }

        sc.nextLine();

        System.out.print("""
                              _________________________________________
                             |                                         |
                             |4) O quanto voce deseja passar a gastar? |
                             |_________________________________________|
                         
                              --resposta: """);
        gastoIdeal = sc.nextDouble();

        bancoConectado();

        String query = "INSERT INTO QUESTIONARIO (cod_usuario,VALOR_kwh_regiao,ideia_de_gasto,quantidade_pessoa,meta_gasto)"
                + "VALUES ('" + idUsuario + "','" + valorKWHregiao + "','" + ideiaGString + "','" + quantPessoa + "','" + gastoIdeal + "')";

        if (bd.insert(query)) {
            System.out.println("Questionario Salvo!");
        } else {
            System.out.println("\nErro");//alterar
        }

        sc.nextLine();
    }

    public void reQuestionario(int idUsuario) {
        Scanner sc = new Scanner(System.in);
         System.out.print("""
                              ___________________________________________________________________________
                             | # Antes de iniciar no Hope pedimos que voce responda nosso questionario # |
                             |---------------------------------------------------------------------------|
                             | 1) Qual o valor do KW/H da sua localidade?                                |
                             |___________________________________________________________________________|
                                   
                              --Resposta: """);
        valorKWHregiao = sc.nextDouble();

        sc.nextLine();

        System.out.print("""
                              ____________________________________________
                             |                                            |
                             |2) Quantas pessoas Moram em sua residencia? |
                             |____________________________________________|  
                         
                              --Resposta: """);
        quantPessoa = sc.nextInt();

        sc.nextLine();

        System.out.print("""
                              ___________________________________
                             |                                   |
                             |3) O quanto voce acredita gastar?  |
                             |-----------------------------------|
                             |1-Muito                            |
                             |2-Mediano                          |
                             |3-pouco                            |
                             |___________________________________|
                         
                              --resposta: """);
        ideiaDeGasto = sc.nextInt();
        
        String ideiaGString = "";
        switch (ideiaDeGasto) {
            case 1:
                ideiaGString = "Muito";
                break;
            case 2:
                ideiaGString = "Mediano";
                break;
            case 3:
                ideiaGString = "Pouco";
                break;
        }

        sc.nextLine();

        System.out.print("""
                              _________________________________________
                             |                                         |
                             |4) O quanto voce deseja passar a gastar? |
                             |_________________________________________|
                         
                              --resposta: """);
        gastoIdeal = sc.nextDouble();

        bancoConectado();

        String query = "UPDATE QUESTIONARIO SET VALOR_kwh_regiao = '" + valorKWHregiao + "', ideia_de_gasto = '" + ideiaGString
                + "', quantidade_pessoa = '" + quantPessoa + "', meta_gasto = '" + gastoIdeal
                + "' WHERE cod_usuario = '" + idUsuario + "'";

        if (bd.insert(query)) {
            System.out.println("Questionario Salvo!");
        } else {
            System.out.println("\nErro");//alterar
        }

        sc.nextLine();
    }

    public void consumo(int idUsuario) {
        
         System.out.print(""" 
                           ________________________________________
                          |                                        |
                          | Qual o valor da sua conta de energia?  |
                          |________________________________________|
                          
                           --Resposta: """);
                    gastoRealConta = sc.nextDouble();

                    sc.nextLine();

                    System.out.print("""
                                     _______________________________________________
                                    |                                               |
                                    | Insira data do gasto deste jeito: Ano-mes-dia |
                                    |_______________________________________________|
                                     
                                      --Resposta: """);
                    dataGasto = sc.nextLine();

        bancoConectado();

        String query = "INSERT INTO CONSUMO(cod_usuario,DATA_GASTO,gasto_real, gasto_ideal)"
                + " VALUES ('" +idUsuario+"','"+ dataGasto +"','"+gastoRealConta+"','" + gastoIdeal + "')";

        if (bd.insert(query)) {
            System.out.println("\nConta inserida");
        } else {
            System.out.println("\nErro ao armazenar");
        }

    }
    
    public String mostrarConsumo(int idUsuario){
        
        bancoConectado();
        
         String query = "SELECT gasto_real, data_gasto FROM consumo where cod_usuario = '"+idUsuario+"'";
        
        ResultSet set = bd.select(query);
        
        if (set == null) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            try {
                boolean encontrado = false;
                while (set.next()) {
                    
                           gastoRealConta = set.getInt(1);
                           dataGasto = set.getString(2);
                           
                    System.out.printf("\nData do gasto: %s | Gasto no mes: %.2f",dataGasto,gastoRealConta);
                    encontrado = true;
                }
                if (!encontrado) {
                    System.out.println("Nenhuma conta cadastrada.");
                }
            } catch (Exception e) {
                System.out.printf(
                        "Erro ao ler lista de carros: %s\n",
                        e.getMessage());
            }
        }
    return "";
    }
    
    public void simulador(int idUsuario){
        
        double kwh =0;
        double tempo =0;
        double valor =0;
        
        System.out.print("\nQuantos aparelhos são:");
        int quant = sc.nextInt();
        
        sc.nextLine();
        
        if(gastoKwh==0){
        System.out.print("Quanto vale o kw/h da sua localidade: ");
        valor = sc.nextDouble();
        sc.nextLine();
        }
        
        for(int i=0;i <quant;i++){
            
            System.out.print("Kw/h do aparelho:");
            kwh =+ sc.nextDouble();
            
            sc.nextLine();
            
            System.out.print("Tempo médio de uso:");
            tempo =+ sc.nextDouble();
            
            sc.nextLine();
    }
        gastoKwh = (kwh * tempo)*valor;
        
            bancoConectado();

        String query = "UPDATE Consumo SET gasto_kwh = '"+gastoKwh+"' WHERE cod_usuario = '"+idUsuario+"'";

        if (bd.insert(query)) {
            System.out.printf("\nvalor medio gasto: %.2f",gastoKwh);
        } else {
            System.out.println("\nErro ao armazenar");
        }
        
       
    }

}
