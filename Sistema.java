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
    //variável de instância
    private Map<Integer, Contribuintes> c = new HashMap<>();
    private Set<String> activities_available = new HashSet<>();
    
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
    
    public void registar_CI()
    {
        List<Integer> nifs = new ArrayList<>();
        List<String> ats = new ArrayList<>();
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Individual:\n");
        System.out.print("NIF --> "); int nif = read1.nextInt();
        System.out.print("Email --> "); String email = read2.nextLine();
        System.out.print("Nome --> "); String nome = read2.nextLine();
        System.out.print("Morada --> "); String morada = read2.nextLine();
        System.out.print("Password --> "); String password = read2.nextLine();
        System.out.print("Nº de elementos do Agregado Familiar --> "); int numero_ag = read1.nextInt();
        for(int i = 1; i <= numero_ag; i++)
        {
                System.out.printf("NIF %d: ", i); int s = read1.nextInt(); nifs.add(s);
        }
        System.out.print("Atividades Económicas associadas: (Nota: Separe as atividades económicas por ,)"); String at = read2.nextLine();
        String[] s = at.split(" ");
        for(int i=0; i < s.length; i++)
        {
            ats.add(s[i]);
        }
        
        System.out.print("Coeficiente Fiscal --> "); double numero_cf = read1.nextDouble();

        read1.close(); read2.close();
        Contribuintes_Individuais ci = new Contribuintes_Individuais(nif, email, nome, morada, password, numero_ag, nifs, numero_cf, ats);
        
        if(!c.containsKey(ci.getNIF()))
        {
            c.put(ci.getNIF(), ci.clone());
        }
        else
        {
            System.out.print("Já existe um Contribuinte com este NIF no Sistema.");
        }
    }
    
    public void registar_CC()
    {
        List<String> ats = new ArrayList<>();
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Coletivo / Empresa:\n");
        System.out.print("NIF --> "); int nif = read1.nextInt();
        System.out.print("Email --> "); String email = read2.nextLine();
        System.out.print("Nome --> "); String nome = read2.nextLine();
        System.out.print("Morada --> "); String morada = read2.nextLine();
        System.out.print("Password --> "); String password = read2.nextLine();
        System.out.print("Quantas atividades económicas deseja associar à Empresa? "); int numero_at = read1.nextInt();
        for(int i = 1; i <= numero_at; i++)
        {
            System.out.printf("Atividade Económica %d: ", i); String at = read2.nextLine(); ats.add(at);
        }
        System.out.print("Coeficiente Fiscal --> "); double numero_cf = read1.nextDouble();
        
        read1.close(); read2.close();
        Contribuintes_Coletivos_Empresas cc = new Contribuintes_Coletivos_Empresas(nif, email, nome, morada, password, ats, numero_cf);
        
        if(!c.containsKey(cc.getNIF()))
        {
            c.put(cc.getNIF(), cc.clone());
        }
        else
        {
            System.out.print("Já existe um Contribuinte com este NIF no Sistema.");
        }
    }
    
    public void login_CI()
    {
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        System.out.print("Login Contribuinte Individual:\n");
        
        System.out.print("Utilizador / NIF: "); int username = read1.nextInt();
        System.out.print("Password: "); String password = read2.nextLine();
        
        if(c.containsKey(username) && c.get(username).getPASSWORD().equals(password))
        {
            System.out.print("Entrou com sucesso no Sistema!");
        }
        else
        {
            System.out.print("Dados inválidos!");
        }
    }
    
    public void login_CC()
    {
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        System.out.print("Login Contribuinte Coletivo / Empresa:\n");
        
        System.out.print("Utilizador / NIF: "); int username = read1.nextInt();
        System.out.print("Password: "); String password = read2.nextLine();
        
        if(c.containsKey(username) && c.get(username).getPASSWORD().equals(password))
        {
            System.out.print("Entrou com sucesso no Sistema!");
        }
        else
        {
            System.out.print("Dados inválidos!");
        }
    }
    
    /**
     * Método que devolve a representação em String da classe Sistema.
     * @return String.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Sistema:\n");
        sb.append(this.c.toString()).append("\n");
        return sb.toString();
    }
}
