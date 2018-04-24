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
     * Método para fazer uma pausa no sistema de 2 segundos.
     */
    private static void time()
    {
        try
        {
            Thread.sleep(2000);
        }
        catch(Exception e){}
    }
    
    /**
     * Método que inicializa o programa JavaFatura.
     */
    public static void main(String[] args)
    {   
        Menu m = new Menu();
        Sistema s = new Sistema();
        Scanner read = new Scanner(System.in);
        int opçao = 0;
        do
        {
            System.out.println("========================================================================");
            System.out.println("||                           Menu JavaFatura                          ||");
            System.out.println("========================================================================");
            System.out.println("|| Opções:                                                            ||");
            System.out.println("||        1 ---> Login Contribuinte Individual                        ||");
            System.out.println("||        2 ---> Login Contribuinte Coletivo / Empresas               ||");
            System.out.println("||--------------------------------------------------------------------||");
            System.out.println("||        3 ---> Registar Contribuinte Individual                     ||");
            System.out.println("||        4 ---> Registar Contribuinte Coletivo / Empresas            ||");
            System.out.println("||--------------------------------------------------------------------||");
            System.out.println("||        5 ---> Sair                                                 ||");
            System.out.println("========================================================================");
        
            System.out.print("Insira a opção: "); opçao = read.nextInt();
            System.out.print("\n");
            
            switch(opçao)
            {
                case 1:
                    s.login_CI();
                    m.time();
                    System.out.print('\u000C');
                    break;
   
                case 2:
                    s.login_CC();
                    m.time();
                    System.out.print('\u000C');
                    break;
                
                case 3:
                    s.registar_CI();
                    System.out.print('\u000C');
                    break;
                    
                case 4:
                
                    break;
                
                case 5:
                    break;
                    
                case 6:
                    System.out.println(s.toString());
                    break;
            
                default:
                    System.out.println("Selecionou uma opção inválida!");
                    read.close();
                    m.time();
                    System.out.print('\u000C');
                    System.exit(0);
            }
        }
        while(opçao != 5);
        
        System.out.print("A sair do Sistema ...");
        read.close();
        m.time();
        System.out.print('\u000C');
        System.exit(0);
    }
}
