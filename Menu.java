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
        }catch(Exception e){System.out.print("Erro!"); return;};
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
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
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
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        1 ---> Ver todas as Faturas submetidas no Sistema.                    ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        2 ---> Ver todos os Contribuintes registados no Sistema.              ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        3 ---> Ver todos os Agregados Familiares registados no Sistema.       ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        4 ---> Ver os 10 Contribuintes que mais gastaram em todo o Sistema.   ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");
        sb.append("                                                    ||        5 ---> Ver a relação das X empresas que mais faturas têm em todo o    ||\n");
        sb.append("                                                    ||               sistema e determinar o montante de deduções fiscais que as     ||\n");
        sb.append("                                                    ||               despesas registadas (dessas empresas) representam.             ||\n");
        sb.append("                                                    ||------------------------------------------------------------------------------||\n");                                     
        sb.append("                                                    ||        6 ---> Logout.                                                        ||\n");
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
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        1 ---> Ver faturas de despesas feitas em seu nome.                                         ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        2 ---> Validar faturas pendentes.                                                          ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        3 ---> Alterar atividade económica de fatura(s).                                           ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        4 ---> Obter a listagem das facturas, ordenada por data de emissão.                        ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        5 ---> Obter a listagem das facturas, ordenada por valor crescente de despesa.             ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        6 ---> Verificar montante de dedução fiscal acumulado por si.                              ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        7 ---> Verificar montante de dedução fiscal acumulado pelo agregado familiar.              ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        8 ---> Logout.                                                                             ||\n");
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
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        1 ---> Adicionar fatura.                                                                   ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        2 ---> Anular fatura.                                                                      ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        3 ---> Validar faturas pendentes.                                                          ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        4 ---> Ver faturas emitidas pela empresa.                                                  ||\n");
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        5 ---> Ver faturas de despesas feitas pela empresa.                                        ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");        
        sb.append("                                             ||        6 ---> Obter a listagem das faturas de um contribuinte num determinado intervalo de datas. ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        7 ---> Obter a listagem das faturas de um contribuinte ordenada por valor decrescente.     ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        8 ---> Verificar o total faturado da empresa num determinado período.                      ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||        9 ---> Obter a listagem das facturas da empresa, ordenada por data de emissão.             ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n");
        sb.append("                                             ||       10 ---> Obter a listagem das facturas da empresa, ordenada por valor crescente de despesa.  ||\n"); 
        sb.append("                                             ||---------------------------------------------------------------------------------------------------||\n"); 
        sb.append("                                             ||       11 ---> Logout.                                                                             ||\n");
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
        String opçao1, opçao2, opçao3, opçao4, option;
        boolean isNumeric;
        int choice1, choice2, choice3, choice4, choice;
        Scanner read = new Scanner(System.in);
        Menu m = new Menu(); 
        Sistema s;
        
        try{
            s = Sistema.ler_estado();
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
            do {
                System.out.print('\u000C');
                m.show_menu_Principal();
                System.out.print("Escolha uma opção: "); 
                opçao1 = read.nextLine();
                isNumeric = opçao1.chars().allMatch(Character::isDigit);
            }while(!isNumeric);
            choice1 = Integer.parseInt(opçao1);
            System.out.print("\n");
            switch(choice1)
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
                                do {
                                    System.out.print('\u000C');
                                    m.show_menu_CI();
                                    System.out.print("Escolha uma opção: "); 
                                    opçao2 = read.nextLine();
                                    isNumeric = opçao2.chars().allMatch(Character::isDigit);
                                }while(!isNumeric);
                                choice2 = Integer.parseInt(opçao2);
                                System.out.print("\n");
                            
                                switch(choice2)
                                {
                                    case 1:
                                        s.mostrar_faturas_CI();
                                        break;
                                        
                                    case 2:
                                        s.validar_faturas_pendentes();
                                        break;
                                    
                                    case 3:
                                        s.revalidar_faturas_pendentes();
                                        break;

                                    case 4:
                                        s.mostrar_faturas_ord_data_Contribuintes();
                                        break;
                                    
                                    case 5:
                                        s.mostrar_faturas_ord_valor_crescente_despesa_Contribuintes();
                                        break;

                                    case 6:
                                        s.calcular_deduçao_fiscal_CI(s.getNIF_Contribuinte());
                                        break;
                                    
                                    case 7:
                                        Individual i = (Individual) s.getRegistados().get(s.getNIF_Contribuinte());
                                        s.calcular_deduçao_fiscal_agregado(i.getIndex_Agregado());
                                        break;
                                    
                                    case 8:
                                        break;
                                    
                                    default:
                                        System.out.print("Selecionou uma opção inválida!\nSe pretende sair do menu, por favor faça Logout.");
                                        m.time(2000);
                                        break;
                                }
                            }while(choice2 != 7);
                            s.logout_Contribuinte();
                            System.out.print('\u000C');
                            break;
                        
                        case 2:
                            do
                            {
                                do {
                                    System.out.print('\u000C');
                                    m.show_menu_CC();
                                    System.out.print("Escolha uma opção: ");
                                    opçao3 = read.nextLine();
                                    isNumeric = opçao3.chars().allMatch(Character::isDigit);
                                }while(!isNumeric);
                                choice3 = Integer.parseInt(opçao3);
                                System.out.print("\n");
                            
                                switch(choice3)
                                {
                                    case 1:
                                        s.registar_Faturas();
                                        try{
                                            s.gravar_estado();
                                        } catch(FileNotFoundException e) {
                                            System.out.println("Erro de Escrita: Ficheiro especificado não existe / não foi encontrado!");
                                        } catch(IOException e) {
                                            System.out.println("Erro de Escrita: Erro ao aceder ao ficheiro!");
                                        }
                                        break;

                                    case 2:
                                        s.mostrar_faturas_emitidas_CC();
                                        do {
                                            System.out.println("\nSelecione o número da fatura que pretende anular:\n");
                                            option = read.nextLine();
                                            isNumeric = option.chars().allMatch(Character::isDigit);
                                        }while(!isNumeric);
                                        choice = Integer.parseInt(option);
                                        s.anular_fatura(choice);
                                        try{
                                            s.gravar_estado();
                                        } catch(FileNotFoundException e) {
                                            System.out.println("Erro de Escrita: Ficheiro especificado não existe / não foi encontrado!");
                                        } catch(IOException e) {
                                            System.out.println("Erro de Escrita: Erro ao aceder ao ficheiro!");
                                        }
                                    
                                    case 3:
                                        s.validar_faturas_pendentes();
                                        break;

                                    case 4:
                                        s.mostrar_faturas_emitidas_CC();
                                        break;

                                    case 5:
                                        s.mostrar_faturas_para_CC();
                                        break;

                                    case 6:
                                        s.faturas_contribuinte_ord_intervalo_datas_CC();
                                        break;
                                    
                                    case 7:
                                        s.faturas_contribuinte_ord_decrescente_despesa_CC();
                                        break;
                                    
                                    case 8:
                                        s.total_faturado_CC();
                                        break;
                                        
                                    case 9:
                                        s.mostrar_faturas_ord_data_Contribuintes();
                                        break;
                                        
                                    case 10:
                                        s.mostrar_faturas_ord_valor_crescente_despesa_Contribuintes();
                                        break;
                                        
                                    case 11:
                                        break;
                                    
                                    default:
                                        System.out.print("Selecionou uma opção inválida!\nSe pretende sair do menu, por favor faça Logout.");
                                        m.time(2000);
                                        break;
                                }
                            }while(choice3 != 10);
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
                                do {
                                    System.out.print('\u000C');
                                    m.show_menu_Administrador();
                                    System.out.print("Escolha uma opção: ");
                                    opçao4 = read.nextLine();
                                    isNumeric = opçao4.chars().allMatch(Character::isDigit);
                                }while(!isNumeric);
                                choice4 = Integer.parseInt(opçao4);
                                System.out.print("\n");
                                switch(choice4)
                                {
                                    case 1:
                                        s.mostrar_faturas_Administrador();
                                        break;
                                    
                                    case 2:
                                        s.mostrar_Contribuintes_Administrador();
                                        break;
                                    
                                    case 3:
                                        s.mostrar_Agregados_Familiares_Administrador();
                                        break;
                                    
                                    case 4:
                                        s.top_10_Administrador();
                                        break;

                                    case 5:
                                        s.relaçao_X_CC();
                                        break;
                                    
                                    case 6:
                                        break;
                                }
                            } while(choice4 != 6);
                            s.logout_Administrador();
                            System.out.print('\u000C');
                            break;
                    }
                    break;
                    
                case 3:
                    s.registar_CI();
                    try{
                    s.gravar_estado();
                    } catch(FileNotFoundException e) {
                        System.out.println("Erro de Escrita: Ficheiro especificado não existe / não foi encontrado!");
                    } catch(IOException e) {
                        System.out.println("Erro de Escrita: Erro ao aceder ao ficheiro!");
                    }
                    System.out.print('\u000C');
                    break;
                    
                case 4:
                    s.registar_CC();
                    try{
                    s.gravar_estado();
                    } catch(FileNotFoundException e) {
                        System.out.println("Erro de Escrita: Ficheiro especificado não existe / não foi encontrado!");
                    } catch(IOException e) {
                        System.out.println("Erro de Escrita: Erro ao aceder ao ficheiro!");
                    }
                    System.out.print('\u000C');
                    break;
                    
                case 5:
                    break;
            
                default:
                    System.out.print("Selecionou uma opção inválida!\nSe pretende sair do menu, por favor insira a opção 5.");
                    m.time(2000);
                    System.out.print('\u000C');
            }
        }while(choice1 != 5);
        
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