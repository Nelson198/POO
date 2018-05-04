/**
 * Classe Menu.
 * 
 * @author P.O.O. - Project - 2017/2018 
 * @version 1.0
 */
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu
{
    /**
     * Método para fazer uma pausa no sistema de x segundos.
     * @param x
     * @return
     */
    private static void time(int x)
    {
        try
        {
            Thread.sleep(x);
        }
        catch(Exception e){}
    }

    /**
     * Método que constrói o menu inicial para a interface do programa.
     * @param
     * @return
     */
    private static void show_menu_Principal()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("                                           ==================================================================================\n");
        sb.append("                                           ||                                Menu JavaFatura                               ||\n");
        sb.append("                                           ==================================================================================\n");
        sb.append("                                           || Opções:                                                                      ||\n");
        sb.append("                                           ||        1 ---> Login (Contribuintes)                                          ||\n");
        sb.append("                                           ||------------------------------------------------------------------------------||\n");
        sb.append("                                           ||        2 ---> Login (Administrador)                                          ||\n");
        sb.append("                                           ||        3 ---> Logout (Administrador)                                         ||\n");
        sb.append("                                           ||------------------------------------------------------------------------------||\n");
        sb.append("                                           ||        4 ---> Registar Contribuinte Individual                               ||\n");
        sb.append("                                           ||        5 ---> Registar Contribuinte Coletivo / Empresas                      ||\n");
        sb.append("                                           ||------------------------------------------------------------------------------||\n");
        sb.append("                                           ||        6 ---> Sair                                                           ||\n");
        sb.append("                                           ==================================================================================\n");
        System.out.print(sb);
    }
    
    /**
     * Método que constrói o menu dos Contribuintes Individuais que faz parte da interface do programa.
     * @param
     * @return
     */
    private static void show_menu_CI()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("                                 =======================================================================================================\n");
        sb.append("                                 ||                             Menu JavaFatura - Contribuintes Individuais                           ||\n");
        sb.append("                                 =======================================================================================================\n");
        sb.append("                                 || Opções:                                                                                           ||\n");
        sb.append("                                 ||        1 ---> Adicionar fatura associada a uma despesa                                            ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        2 ---> Associar classificação de actividade económica a um documento de despesa            ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        3 ---> Corrigir a classificação de actividade económica de um documento de despesa         ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n"); 
        sb.append("                                 ||        4 ---> Verificar despesas emitidas em seu nome                                             ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        5 ---> Verificar montante de dedução fiscal acumulado por si                               ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        6 ---> Verificar montante de dedução fiscal acumulado pelo agregado familiar               ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        7 ---> Logout                                                                              ||\n");
        sb.append("                                 =======================================================================================================\n");
        System.out.print(sb);
    }

    
    /**
     * Método que constrói o menu dos Contribuintes Coletivos / Empresas que faz parte da interface do programa.
     * @param
     * @return
     */
    private static void show_menu_CC()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("                                 =======================================================================================================\n");
        sb.append("                                 ||                       Menu JavaFatura - Contribuintes Coletivos / Empresas                        ||\n");
        sb.append("                                 =======================================================================================================\n");
        sb.append("                                 || Opções:                                                                                           ||\n");
        sb.append("                                 ||        1 ---> Adicionar fatura associada a uma despesa                                            ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        2 ---> Associar classificação de actividade económica a um documento de despesa            ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        3 ---> Corrigir a classificação de actividade económica de um documento de despesa         ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n"); 
        sb.append("                                 ||        4 ---> Verificar despesas emitidas em seu nome                                             ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        5 ---> Verificar montante de dedução fiscal acumulado por si                               ||\n");
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        7 ---> Obter a listagem das faturas, ordenada por data de emissão                          ||\n"); 
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        8 ---> Obter a listagem das faturas, ordenada por valor decrescente                        ||\n"); 
        sb.append("                                 ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                 ||        9 ---> Logout                                                                              ||\n");
        sb.append("                                 =======================================================================================================\n");
        System.out.print(sb);
    }

    /**
     * Método que inicializa o programa JavaFatura.
     */
    public static void main(String[] args)
    {   
        Menu m = new Menu();
        Sistema s = new Sistema();
        Scanner read = new Scanner(System.in);
        int opçao1, opçao2, opçao3;
        do
        {
            m.show_menu_Principal();
            System.out.print("Escolha uma opção: "); opçao1 = read.nextInt();
            System.out.print("\n");
            
            switch(opçao1)
            {
                case 1:
                    int r = s.loginContribuinte();
                    switch(r)
                    {
                        case -1:
                            System.out.print('\u000C');
                            break;
                           
                        case 1:
                            do
                            {
                                System.out.print('\u000C');
                                m.show_menu_CI();
                                System.out.print("Escolha uma opção: "); opçao2 = read.nextInt();
                                System.out.print("\n");
                            
                                switch(opçao2)
                                {
                                    case 1:
                                        break;
                                    
                                    case 4:
                                        break;
                                    
                                    case 7:
                                        break;
                                    
                                    
                                    default:
                                        System.out.print("Selecionou uma opção inválida!\nSe pretende sair do menu, por favor faça Logout.");
                                        m.time(2000);
                                        break;
                                }
                            }while(opçao2 != 7);
                            s.logoutContribuinte();
                            System.out.print('\u000C');
                            break;
                        
                        case 2:
                            do
                            {
                                System.out.print('\u000C');
                                m.show_menu_CC();
                                System.out.print("Escolha uma opção: "); opçao3 = read.nextInt();
                                System.out.print("\n");
                            
                                switch(opçao3)
                                {
                                    case 1:
                                        s.registarFaturas();
                                        break;
                                    
                                    case 8:
                                        s.faturas_ord_contribuinte_decrescente_despesa();
                                        break;
                                    
                                    case 9:
                                        break;
                                    
                                    default:
                                        System.out.print("Selecionou uma opção inválida!\nSe pretende sair do menu, por favor faça Logout.");
                                        m.time(2000);
                                        break;
                                }
                            }while(opçao3 != 9);
                            s.logoutContribuinte();
                            System.out.print('\u000C');
                            break;
                    }
                    break;
                
                case 2:
                    s.loginAdmin();
                    System.out.print('\u000C');
                    break;
                
                case 3:
                    s.logoutAdmin();
                    break;
                    
                case 4:
                    s.registar_CI();
                    System.out.print('\u000C');
                    break;
                    
                case 5:
                    s.registar_CC();
                    System.out.print('\u000C');
                    break;
                    
                case 6:
                    break;
                    
                case 7:
                    System.out.print(s.toString());
                    break;
            
                default:
                    System.out.print("\nSelecionou uma opção inválida!\nSe pretende sair do menu, por favor insira a opção 4.");
                    m.time(2000);
                    System.out.print('\u000C');
            }
        }while(opçao1 != 6);
        
        System.out.print("A sair do Sistema ...");
        read.close();
        m.time(1000);
        System.out.print('\u000C');
        System.exit(0);
    }
}