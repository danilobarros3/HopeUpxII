package projecthope;

import java.util.Scanner;

/**
 * Organização: Tech Corp. Software: Hope Programadores: Kaique Gonçalvez Mattos
 * e Lucas Peixoto Nunes
 */
public class Principal {

    public static void main(String[] args) {

        //instanciações
        Scanner sc = new Scanner(System.in);
        Usuario usuario = new Usuario();
        Energia ener = new Energia();
        GuiaEco guia = new GuiaEco();

        //variaveis
        int escolha;
        String senha, user;
        boolean cadastro = false;
        double kwh;
        int quantPessoa, ideiaDeGasto, idUsuario = 0;
        double gastoIdeal;
        double gastoRealConta;
        String dataGasto;

///////////////////////////////////Boas_Vindas//////////////////////////////////
        {
            System.out.println(""" 
________________________________________________________________________________
             SEJA BEM-VINDO AO                                            
                   ##     ##  #######  ########  ########
                   ##     ## ##     ## ##     ## ##
                   ##     ## ##     ## ##     ## ##
                   ######### ##     ## ########  ######
                   ##     ## ##     ## ##        ##
                   ##     ## ##     ## ##        ##
                   ##     ##  #######  ##        ######## 
                                                        -by: Tech Corp.
________________________________________________________________________________                           
                   """);
        }
/////////////////////////////////Login/cadastro/////////////////////////////////
        do {
            System.out.print("""
                             
                                ____________________
                               | # LOGIN/CADASTRO # |
                               |--------------------|
                               | 1-Login            |
                               | 2-Criar Usuario    | 
                               | 3-Sair             |
                               |____________________|
                             
                                --Resposta:""");
            escolha = sc.nextInt();

            sc.nextLine();

            switch (escolha) {

                case 1:
                    System.out.print("\nUsuario:");
                    user = sc.nextLine();

                    System.out.print("Senha:");
                    senha = sc.nextLine();

                    usuario.contaExistente(user, senha);
                    idUsuario = usuario.IdUsuario(user, senha);

                    break;

                case 2:
                    System.out.print("\nUsuario:");
                    user = sc.nextLine();

                    System.out.print("Senha:");
                    senha = sc.nextLine();

                    usuario.inserirUser(user, senha);
                    idUsuario = usuario.IdUsuario(user, senha);

                    cadastro = true;
                    break;
                case 3:
                    System.out.print("Finalizando software...");
                    System.exit(0);
                    break;
                default:
                    if (escolha > 3) {
                        System.out.print("\nNumero invalido, tente de novo.");
                    }
                    break;
            }

        } while (escolha == 1 && usuario.getEncontrado() == false);
//////////////////////////////////Questionario//////////////////////////////////
        if (cadastro == true) {
            ener.questionario(idUsuario);
        }

/////////////////////////////////Menu_Principal/////////////////////////////////  
        do {
            System.out.printf("""
                              
                              ________________________
                             |   # MENU PRINCIPAL #   |
                             |------------------------|
                             | Meta Atual:%.2f    
                             |                        |
                             | 1-Inserir Contas       |
                             | 2-Guia de economia     |
                             | 3-Refazer questionario |
                             | 4-Mostrar gastos       |
                             | 5-Sair                 |
                             |________________________|  
                              
                              --Resposta: """, ener.getGastoIdeal(idUsuario));
            escolha = sc.nextInt();

            sc.nextLine();

            switch (escolha) {
                case 1:
                    ener.consumo(idUsuario);
                    break;

                case 2:
                    System.out.print(guia);
                    break;
                case 3:
                    ener.reQuestionario(idUsuario);
                    break;
                case 4:
                    System.out.print("\n------------------------------------------");
                    ener.mostrarConsumo(idUsuario);
                     System.out.print("\n------------------------------------------\n");
                     break;
                     
                default:
                    if (escolha > 5) {
                        System.out.print("\nNumero invalido, tente de novo.");
                    }
                    break;
            }

        } while (escolha != 5);

        System.out.print("\nFinalizando Programa...");

        sc.close();
    }
}
