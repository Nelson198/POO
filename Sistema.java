/**
 * Classe Sistema.
 * 
 * @author P.O.O. - Project - 2017/2018
 * @version 1.0
 */
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Sistema
{
    //variáveis de instância
    private Map<String, Contribuintes> registered = new HashMap<>();
    private Map<String, Contribuintes> in_system = new HashMap<>();
    private Set<String> activities_available = new HashSet<>();
    
    /**
     * Método para fazer uma pausa no sistema de x segundos.
     * @param x
     * @return
     */
    private void time(int x)
    {
        try
        {
            Thread.sleep(x);
        }
        catch(Exception e){}
    }
    
    private void registar_Atividades_Economicas()
    {
        this.activities_available.add("Saúde");
        this.activities_available.add("Educação");
        this.activities_available.add("Imóveis");
        this.activities_available.add("Lares");
        this.activities_available.add("Manutenção e Reparação de veículos");
        this.activities_available.add("Alojamento");
        this.activities_available.add("Restauração");
        this.activities_available.add("Cabeleireiros");
        this.activities_available.add("Veterinários");
        this.activities_available.add("Transportes Públicos");
        this.activities_available.add("Serviços Bancários");
        this.activities_available.add("Eletricidade e Água");
    }
    
    /**
     * Método que regista um contribuinte individual no sistema.
     * @param
     * @return
     */
    public void registar_CI()
    {
        List<String> nifs = new ArrayList<>();
        List<String> ats = new ArrayList<>();
        String nif;
        boolean isNumeric;
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Individual:\n");
        
        do
        {
            System.out.print("NIF --> "); nif = read2.nextLine();
            isNumeric = nif.chars().allMatch(Character::isDigit); //Verificar se a string NIF é numérica.
        } while(nif.length() != 9 || isNumeric == false);
        
        System.out.print("Email --> "); String email = read2.nextLine();
        System.out.print("Nome --> "); String nome = read2.nextLine();    
        System.out.print("Morada --> "); String morada = read2.nextLine();   
        System.out.print("Password --> "); String password = read2.nextLine();  
        System.out.print("Nº de elementos do Agregado Familiar --> "); int numero_ag = read1.nextInt();
        
        for(int i = 1; i <= numero_ag; i++)
        {
                System.out.printf("NIF %d: ", i); String s = read2.nextLine(); nifs.add(s);
        }
        
        System.out.print("Atividades Económicas (nota: Separe as atividades económicas por um único espaço) --> "); String at = read2.nextLine();
        String[] s = at.split(" ");
        for(int i=0; i < s.length; i++)
        {
            ats.add(s[i]);
        }
        
        System.out.print("Coeficiente Fiscal --> "); double numero_cf = read1.nextDouble();

        read1.close(); read2.close();
        Contribuintes_Individuais ci = new Contribuintes_Individuais(nif, email, nome, morada, password, numero_ag, nifs, numero_cf, ats);
        
        if(!this.registered.containsKey(ci.getNIF()))
        {
            System.out.print("\nA registar ...");
            this.registered.put(ci.getNIF(), ci.clone());
            time(1000);
            System.out.print("\nContribuinte registado com sucesso!");
        }
        else
        {
            System.out.print("Aviso : Já existe um Contribuinte com este NIF no Sistema.");
        }
    }
    
    /**
     * Método que regista um contribuinte coletivo / Empresa no sistema.
     * @param
     * @return
     */
    public void registar_CC()
    {
        List<String> ats = new ArrayList<>();
        String nif;
        boolean isNumeric;
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Coletivo / Empresa:\n");
        
        do
        {
            System.out.print("NIF --> "); nif = read2.nextLine();
            isNumeric = nif.chars().allMatch(Character::isDigit); //Verificar se a string NIF é numérica.
        } while(nif.length() != 9 || isNumeric == false);
        
        System.out.print("Email --> "); String email = read2.nextLine();
        System.out.print("Nome --> "); String nome = read2.nextLine();
        System.out.print("Morada --> "); String morada = read2.nextLine();
        System.out.print("Password --> "); String password = read2.nextLine();
        System.out.print("Atividades Económicas (nota: Separe as atividades económicas por um único espaço) --> "); String at = read2.nextLine();
        String[] s = at.split(" ");
        for(int i=0; i < s.length; i++)
        {
            ats.add(s[i]);
        }
        System.out.print("Coeficiente Fiscal --> "); double numero_cf = read1.nextDouble();
        
        read1.close(); read2.close();
        Contribuintes_Coletivos_Empresas cc = new Contribuintes_Coletivos_Empresas(nif, email, nome, morada, password, ats, numero_cf);
        
        if(!this.registered.containsKey(cc.getNIF()))
        {
            System.out.print("\nA registar ...");
            this.registered.put(cc.getNIF(), cc.clone());
            time(1000);
            System.out.print("\nContribuinte registado com sucesso!");
        }
        else
        {
            System.out.print("Aviso : Já existe um Contribuinte com este NIF no Sistema.");
        }
    }
    
    /**
     * Método que permite fazer o login dos Contribuintes no Sistema.
     * @param
     * @return
     */
    public void login()
    {
        Scanner read = new Scanner(System.in);
        System.out.print("Login:\n");
        
        System.out.print("Utilizador / NIF: "); String username = read.nextLine();
        System.out.print("Password: "); String password = read.nextLine();
        
        if(this.registered.containsKey(username) && this.registered.get(username).getPASSWORD().equals(password) && (!this.in_system.containsKey(username)))
        {
            this.in_system.put(username, this.registered.get(username).clone());
            System.out.print("\nEntrou com sucesso no Sistema!");
        }
        else if(this.registered.containsKey(username) && this.registered.get(username).getPASSWORD().equals(password) && (this.in_system.containsKey(username)))
        {
            System.out.print("\nAviso : Este utilizador já se encontra dentro do Sistema!");
        }
        else
        {
            System.out.print("\nErro : Dados inválidos!");
        }
    }
    
    /**
     * Método que permite fazer o logout dos Contribuintes no Sistema.
     * @param
     * @return
     */
    public void logout()
    {
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        System.out.print("Logout:\n");
        
        System.out.print("Utilizador / NIF: "); int username = read1.nextInt();
        System.out.print("Password: "); String password = read2.nextLine();
        
        if(this.in_system.containsKey(username) && this.in_system.get(username).getPASSWORD().equals(password))
        {
            this.in_system.remove(username, this.in_system.get(username));
            System.out.print("\nSaiu com sucesso do Sistema!");
        }
        else
        {
            System.out.print("\nErro : Dados inválidos!");
        }
    }
    
    /**
     * Método que devolve a representação em String da classe Sistema.
     * @param
     * @return String.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Sistema:\n\n");
        sb.append("Registados:\n"); sb.append(this.registered.toString()).append("\n\n");
        sb.append("In:\n"); sb.append(this.in_system.toString()).append("\n");
        return sb.toString();
    }
}
