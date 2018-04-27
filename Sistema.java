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
import java.security.MessageDigest;

public class Sistema
{
    //variáveis de instância
    private Map<String, Contribuintes> registered = new HashMap<>();
    private Map<String, Contribuintes> in_system = new HashMap<>();
    private Map<String, String> hash_password = new HashMap<>(); /* Dicionário que guarda as hash code das passwords */
    
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
    
    /**
     * Método que gera um valor hash para uma dada password.
     * @param password
     * @return
     */
    private void hashMD5(String password) throws Exception
    {   
        MessageDigest md = MessageDigest.getInstance("MD5");
        StringBuffer sb;
        do
        {
            md.update(password.getBytes());
            byte byteData[] = md.digest();
 
            //convert the byte to hex format
            sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) 
            {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } while(this.hash_password.containsValue(sb.toString()));
        this.hash_password.put(password, sb.toString());
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
        String nif, email, nome, morada, password, at, s; 
        boolean isNumeric, bool;
        int numero_ag;
        double numero_cf;
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Individual:\n");
        
        do
        {
            System.out.print("NIF --> "); nif = read2.nextLine();
            isNumeric = nif.chars().allMatch(Character::isDigit); //Verificar se a string NIF é numérica.
        } while(nif.length() != 9 || isNumeric == false);
        
        do
        {
            System.out.print("Email --> "); email = read2.nextLine();
        } while(email.indexOf('@') == -1);

        System.out.print("Nome --> "); nome = read2.nextLine();    
        System.out.print("Morada --> "); morada = read2.nextLine();   
        System.out.print("Password --> "); password = read2.nextLine();
        
        do
        {
            System.out.print("Nº de dependentes do agregado familiar --> "); numero_ag = read1.nextInt();
        }while(numero_ag < 1);
        
        do
        {
            bool = true;
            for(int i = 1; i <= numero_ag; i++)
            {
                System.out.printf("NIF %d: ", i); s = read2.nextLine(); nifs.add(s);
            }
            if(!nifs.contains(nif))
            {
                bool = false; nifs.clear();
            }
            else
            {
                for(String a: nifs)
                {
                    if((a.chars().allMatch(Character::isDigit)) == false || a.length() != 9)
                    {
                        bool = false; nifs.clear(); break;
                    }
                }
            }
        } while(bool == false);
        
        System.out.print("Atividades Económicas (nota: Separe as atividades económicas por um único hífen -) --> "); at = read2.nextLine();
        String[] ss = at.split("-");
        for(int i=0; i < ss.length; i++)
        {
            ats.add(ss[i]);
        }
        
        System.out.print("Coeficiente Fiscal --> "); numero_cf = read1.nextDouble();

        read1.close(); read2.close();
        Contribuintes_Individuais ci = new Contribuintes_Individuais(nif, email, nome, morada, password, numero_ag, nifs, numero_cf, ats);
        
        if(!this.registered.containsKey(ci.getNIF()))
        {
            System.out.print("\nA registar ...");
            this.registered.put(ci.getNIF(), ci.clone());
            time(1000);
            System.out.print("\nContribuinte registado com sucesso!");
            time(1000);
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
        String nif, email, nome, morada, password, at;
        boolean isNumeric;
        double numero_cf;
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Coletivo / Empresa:\n");
        
        do
        {
            System.out.print("NIF --> "); nif = read2.nextLine();
            isNumeric = nif.chars().allMatch(Character::isDigit); //Verificar se a string NIF é numérica.
        } while(nif.length() != 9 || isNumeric == false);
        
        do
        {
            System.out.print("Email --> "); email = read2.nextLine();
        } while(email.indexOf('@') == -1);

        System.out.print("Nome --> "); nome = read2.nextLine();
        System.out.print("Morada --> "); morada = read2.nextLine();
        System.out.print("Password --> "); password = read2.nextLine();
        System.out.print("Atividades Económicas (nota: Separe as atividades económicas por um único hífen -) --> "); at = read2.nextLine();
        String[] ss = at.split("-");
        for(int i=0; i < ss.length; i++)
        {
            ats.add(ss[i]);
        }
        System.out.print("Coeficiente Fiscal --> "); numero_cf = read1.nextDouble();
        
        read1.close(); read2.close();
        Contribuintes_Coletivos_Empresas cc = new Contribuintes_Coletivos_Empresas(nif, email, nome, morada, password, ats, numero_cf);
        
        if(!this.registered.containsKey(cc.getNIF()))
        {
            System.out.print("\nA registar ...");
            this.registered.put(cc.getNIF(), cc.clone());
            time(1000);
            System.out.print("\nContribuinte registado com sucesso!");
            time(1000);
        }
        else
        {
            System.out.print("Aviso : Já existe um Contribuinte com este NIF no Sistema.");
        }
    }
    
    /**
     * Método que permite fazer o login dos Contribuintes no Sistema.
     * @param
     * @return r
     */
    public int login()
    {
        int r = -1;
        Scanner read = new Scanner(System.in);
        
        System.out.print("Login:\n");
        System.out.print("Utilizador / NIF: "); String username = read.nextLine();
        System.out.print("Password: "); String password = read.nextLine();
        
        if(this.registered.containsKey(username) && this.registered.get(username).getPASSWORD().equals(password) && (!this.in_system.containsKey(username)))
        {
            this.in_system.put(username, this.registered.get(username).clone());
            
            if(this.registered.get(username).getClass().getSimpleName() == "Contribuintes_Individuais")
            {
                r = 1;
                System.out.print("\nEntrou com sucesso no Sistema!");
                time(1000);
            }
            else if(this.registered.get(username).getClass().getSimpleName() == "Contribuintes_Coletivos_Empresas")
            {
                r = 2;
                System.out.print("\nEntrou com sucesso no Sistema!");
                time(1000);
            }
        }
        /*else if(this.registered.containsKey(username) && this.registered.get(username).getPASSWORD().equals(password) && (this.in_system.containsKey(username)))
        {
            System.out.print("\nAviso : Este utilizador já se encontra dentro do Sistema!");
            time(1000);
            r = 1;
        }*/
        else
        {
            System.out.print("\nErro : Dados inválidos!");
            time(1000);
        }
        return r;
    }
    
    /**
     * Método que permite fazer o logout dos Contribuintes no Sistema.
     * @param
     * @return
     */
    public void logout()
    {
        for(String g: this.in_system.keySet())
        {
            this.in_system.remove(g, this.in_system.get(g));
        }
        System.out.print("Saiu com sucesso do Sistema!");
        time(1000);
    }
    
    /**
     * Método que permite submeter faturas por parte do Contribuinte Individual.
     * @param
     * @return
     */
    public void faturas()
    {
        Scanner read = new Scanner(System.in);
        
        System.out.print("Submissão de Fatura associada a uma despesa:\n");
        
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
