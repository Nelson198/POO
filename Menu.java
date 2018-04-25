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
            StringBuilder sb = new StringBuilder();
            sb.append("                                           ========================================================================\n");
            sb.append("                                           ||                           Menu JavaFatura                          ||\n");
            sb.append("                                           ========================================================================\n");
            sb.append("                                           || Opções:                                                            ||\n");
            sb.append("                                           ||        1 ---> Login                                                ||\n");
            sb.append("                                           ||        2 ---> Logout                                               ||\n");
            sb.append("                                           ||--------------------------------------------------------------------||\n");
            sb.append("                                           ||        3 ---> Registar Contribuinte Individual                     ||\n");
            sb.append("                                           ||        4 ---> Registar Contribuinte Coletivo / Empresas            ||\n");
            sb.append("                                           ||--------------------------------------------------------------------||\n");
            sb.append("                                           ||        5 ---> Sair                                                 ||\n");
            sb.append("                                           ========================================================================\n");
            System.out.print(sb);
        
            System.out.print("Insira a opção: "); opçao = read.nextInt();
            System.out.print("\n");
            
            switch(opçao)
            {
                case 1:
                    s.login();
                    m.time(1500);
                    System.out.print('\u000C');
                    break;
   
                case 2:
                    s.logout();
                    m.time(1500);
                    System.out.print('\u000C');
                    break;
                
                case 3:
                    s.registar_CI();
                    m.time(2000);
                    System.out.print('\u000C');
                    break;
                    
                case 4:
                    s.registar_CC();
                    m.time(2000);
                    System.out.print('\u000C');
                    break;
                
                case 5:
                    break;
                    
                case 6:
                    System.out.println(s.toString());
                    break;
            
                default:
                    System.out.println("\nSelecionou uma opção inválida!");
                    read.close();
                    m.time(2000);
                    System.out.print('\u000C');
                    System.exit(0);
            }
        }
        while(opçao != 5);
        
        System.out.print("A sair do Sistema ...");
        read.close();
        m.time(1000);
        System.out.print('\u000C');
        System.exit(0);
    }
}
