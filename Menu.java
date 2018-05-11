/**
 * Classe Menu.
 * 
 * @author P.O.O. - Project - 2017/2018 
 * @version 1.0
 */
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;

public class Menu
{
    /**
     * Método para fazer uma pausa no sistema de x segundos.
     * @param x
     * @return
     */
    private static void time(int x)
    {
        try{
            Thread.sleep(x);
        }catch(Exception e){};
    }

    /**
     * Método que constrói o menu inicial para a interface do programa.
     * @param
     * @return
     */
    private static void show_menu_Principal()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("                                                    ==================================================================================\n");
        sb.append("                                                    ||                                Menu JavaFatura                               ||\n");
        sb.append("                                                    ==================================================================================\n");
        sb.append("                                                    || Opções:                                                                      ||\n");
        sb.append("                                                    ||        1 ---> Login (Contribuintes)                                          ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        2 ---> Login (Administrador)                                          ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        3 ---> Registar Contribuinte Individual                               ||\n");
        sb.append("                                                    ||        4 ---> Registar Contribuinte Coletivo / Empresas                      ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        5 ---> Sair                                                           ||\n");
        sb.append("                                                    ==================================================================================\n");
        System.out.print(sb);
    }

    /**
     * Método que constrói o menu inicial para a interface do programa.
     * @param
     * @return
     */
    private static void show_menu_Administrador()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("                                                    ==================================================================================\n");
        sb.append("                                                    ||                         Menu JavaFatura - Administrador                      ||\n");
        sb.append("                                                    ==================================================================================\n");
        sb.append("                                                    || Opções:                                                                      ||\n");
        sb.append("                                                    ||        1 ---> Ver todas as Faturas submetidas no Sistema.                    ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        2 ---> Ver todos os Contribuintes registados no Sistema.              ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        3 ---> Ver os 10 Contribuintes que mais gastaram em todo o Sistema.   ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        4 ---> ?                                                              ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");                                     
        sb.append("                                                    ||        5 ---> Logout.                                                        ||\n");
        sb.append("                                                    ==================================================================================\n");
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
        sb.append("                                             =======================================================================================================\n");
        sb.append("                                             ||                             Menu JavaFatura - Contribuintes Individuais                           ||\n");
        sb.append("                                             =======================================================================================================\n");
        sb.append("                                             || Opções:                                                                                           ||\n");
        sb.append("                                             ||        1 ---> Ver faturas que foram passadas em seu nome.                                         ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        2 ---> Obter a listagem das facturas, ordenada por data de emissão.                        ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        3 ---> Obter a listagem das facturas, ordenada por valor crescente de despesa.             ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        4 ---> Verificar montante de dedução fiscal acumulado por si.                              ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        5 ---> Verificar montante de dedução fiscal acumulado pelo agregado familiar.              ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        6 ---> Logout.                                                                             ||\n");
        sb.append("                                             =======================================================================================================\n");
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
        sb.append("                                             =======================================================================================================\n");
        sb.append("                                             ||                       Menu JavaFatura - Contribuintes Coletivos / Empresas                        ||\n");
        sb.append("                                             =======================================================================================================\n");
        sb.append("                                             || Opções:                                                                                           ||\n");
        sb.append("                                             ||        1 ---> Adicionar fatura.                                                                   ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        2 ---> Associar classificação de actividade económica a um documento de despesa            ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        3 ---> Corrigir a classificação de actividade económica de um documento de despesa         ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n"); 
        sb.append("                                             ||        4 ---> Ver despesas emitidas pela empresa.                                                 ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        5 ---> Obter a listagem das faturas de um contribuinte num determinado intervalo de datas. ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        6 ---> Obter a listagem das faturas de um contribuinte ordenada por valor decrescente.     ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        7 ---> Verificar o total faturado da empresa num determinado período.                      ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        8 ---> Obter a listagem das facturas da empresa, ordenada por data de emissão.             ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        9 ---> Obter a listagem das facturas da empresa, ordenada por valor crescente de despesa.  ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n"); 
        sb.append("                                             ||       10 ---> Logout.                                                                             ||\n");
        sb.append("                                             =======================================================================================================\n");
        System.out.print(sb);
    }

    /**
     * Método que inicializa o programa JavaFatura.
     * @param args
     * @return
     */
    public static void main(String[] args)
    {   
        int opçao1, opçao2, opçao3, opçao4;
        Scanner read = new Scanner(System.in);
        Menu m = new Menu(); Sistema s = null;
        
        try{
            s = s.ler_estado();
        }
        catch(ClassNotFoundException e){
            s = new Sistema();
            System.out.println("Erro de Leitura: Formato de ficheiro desconhecido.");
        }
        catch(FileNotFoundException e){
            s = new Sistema();
            System.out.println("Erro de Leitura: Ficheiro especificado não existe / não foi encontrado!");
        }
        catch(IOException e){
            s = new Sistema();
            System.out.println("Erro de Leitura: Erro ao ler ficheiro de estado.");
        }
        
        do
        {
            m.show_menu_Principal();
            System.out.print("Escolha uma opção: "); opçao1 = read.nextInt();
            System.out.print("\n");
            switch(opçao1)
            {
                case 1:
                    int r = s.login_Contribuinte();
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
                                        s.mostrar_faturas_CI();
                                        break;
                                    
                                    case 2:
                                        s.mostrar_faturas_ord_data_Contribuintes();
                                        break;
                                    
                                    case 3:
                                        s.mostrar_faturas_ord_valor_crescente_despesa_Contribuintes();
                                        break;

                                    case 4:
                                        break;
                                    
                                    case 5:
                                        break;
                                    
                                    case 6:
                                        break;
                                    
                                    default:
                                        System.out.print("Selecionou uma opção inválida!\nSe pretende sair do menu, por favor faça Logout.");
                                        m.time(2000);
                                        break;
                                }
                            }while(opçao2 != 6);
                            s.logout_Contribuinte();
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
                                        s.registar_Faturas();
                                        break;
                                    
                                    case 2:
                                        break;
                                    
                                    case 3:
                                        break;

                                    case 4:
                                        s.mostrar_faturas_CC();
                                        break;

                                    case 5:
                                        s.faturas_contribuinte_ord_intervalo_datas_CC();
                                        break;
                                    
                                    case 6:
                                        s.faturas_contribuinte_ord_decrescente_despesa_CC();
                                        break;
                                    
                                    case 7:
                                        s.total_faturado_CC();
                                        break;
                                        
                                    case 8:
                                        s.mostrar_faturas_ord_data_Contribuintes();
                                        break;
                                        
                                    case 9:
                                        s.mostrar_faturas_ord_valor_crescente_despesa_Contribuintes();
                                        break;
                                        
                                    case 10:
                                        break;
                                    
                                    default:
                                        System.out.print("Selecionou uma opção inválida!\nSe pretende sair do menu, por favor faça Logout.");
                                        m.time(2000);
                                        break;
                                }
                            }while(opçao3 != 10);
                            s.logout_Contribuinte();
                            System.out.print('\u000C');
                            break;
                    }
                    break;
                
                case 2:
                    int t = s.login_Administrador();
                    switch(t)
                    {
                        case -1:
                            System.out.print('\u000C');
                            break;

                        case 0:
                            do
                            {
                                System.out.print('\u000C');
                                m.show_menu_Administrador();
                                System.out.print("Escolha uma opção: "); opçao4 = read.nextInt();
                                System.out.print("\n");
                                switch(opçao4)
                                {
                                    case 1:
                                        s.mostrar_faturas_Administrador();
                                        break;
                                    
                                    case 2:
                                        s.mostrar_Contribuintes_Administrador();
                                        break;
                                    
                                    case 3:
                                        s.top_10_Administrador();
                                        break;

                                    case 4:
                                        
                                        break;
                                    
                                    case 5:
                                        break;
                                }
                            } while(opçao4 != 5);
                            s.logout_Administrador();
                            System.out.print('\u000C');
                            break;
                    }
                    break;
                    
                case 3:
                    s.registar_CI();
                    System.out.print('\u000C');
                    break;
                    
                case 4:
                    s.registar_CC();
                    System.out.print('\u000C');
                    break;
                    
                case 5:
                    break;
            
                default:
                    System.out.print("\nSelecionou uma opção inválida!\nSe pretende sair do menu, por favor insira a opção 5.");
                    m.time(2000);
                    System.out.print('\u000C');
            }
        }while(opçao1 != 5);
        
        System.out.print("A sair do Sistema ...");
        read.close();
        m.time(1000);
        
        try{
            s.gravar_estado();
        }
        catch(FileNotFoundException e){
            System.out.println("Erro de Escrita: Ficheiro especificado não existe / não foi encontrado!");
        }
        catch(IOException e){
            System.out.println("Erro de Escrita: Erro ao aceder ao ficheiro!");
        }
        
        System.out.print('\u000C');
        System.exit(0);
    }
}