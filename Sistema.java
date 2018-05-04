/**
 * Classe Sistema.
 * 
 * @author P.O.O. - Project - 2017/2018
 * @version 1.0
 */
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.security.MessageDigest;

public class Sistema
{
    /* variáveis de instância */
    private String password_admin = "admin123";                      /* Palavra Passe do Administrador */
    
    private Contribuinte contribuinte = new Contribuinte();        /* Contribuinte que se encontra dentro do Sistema */
    private Map<String, Contribuinte> registered = new HashMap<>(); /* Dicionário que associa o NIF do Contribuinte (Chave) à sua informação (Valor) */
    private List<Fatura> faturas = new ArrayList<>();                /* Lista com as faturas registadas */
    
    /* private Map<String, String> hash_password = new HashMap<>(); */     /* Dicionário que guarda as hash code das passwords */
    /* private Set<String> activities_available = new HashSet<>(); */
    
    private String getPasswordAdmin()
    {
        return this.password_admin;
    }
    
    /**
     * Método que devolve o Contribuinte (Individual ou Coletivo) que se encontra dentro do Sistema.
     * @param
     * @return Contribuinte que se encontra dentro do Sistema.
     */
    private Contribuinte getContribuinte()
    {
        return this.contribuinte.clone();
    }
    
    /**
     * Método que atualiza o Contribuinte (Individual ou Coletivo) que se encontra dentro do Sistema.
     * @param Contribuinte
     * @return
     */
    private void setContribuinte(Contribuinte c)
    {
        
    }
    
    /**
     * Método que devolve o dicionário de todos os Contribuinte registados no Sistema.
     * @param
     * @return Map<String, Contribuinte> com os Contribuinte registados no Sistema.
     */
    private Map<String, Contribuinte> getRegistados()
    {
        Map<String, Contribuinte> res = new HashMap<>();
        for(String s: this.registered.keySet())
        {
            res.put(s, this.registered.get(s).clone());
        }
        return res;
    }
    
    /**
     * Método que atualiza o dicionário de todos os Contribuinte registados no Sistema.
     * @param Map<String, Contribuinte> com os Contribuinte registados no Sistema.
     * @return
     */
    private void setRegistados(Map<String, Contribuinte> dic)
    {
        this.registered = new HashMap<>();
        for(String s: dic.keySet())
        {
            this.registered.put(s, dic.get(s).clone());
        }
    }
    
    /**
     * Método que devolve a lista das faturas submetidas no Sistema.
     * @param
     * @return List<Faturas>
     */
    private List<Fatura> getFaturas()
    {
        List<Fatura> res = new ArrayList<>();
        for(Fatura f: this.faturas)
        {
            res.add(f.clone());
        }
        return res;
    }
    
    /**
     * Método que atualiza a lista das faturas submetidas no Sistema.
     * @param List<Faturas>
     * @return
     */
    private void setFatura(List<Fatura> l)
    {
        this.faturas = new ArrayList<>();
        for(Fatura f: l)
        {
            this.faturas.add(f.clone());
        }
    }
    
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
    /*private void hashMD5(String password) throws Exception
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
    }*/
    
    /*private void registar_Atividades_Economicas()
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
    }*/
    
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
        } while(nif.length() != 9 || isNumeric == false || nif.indexOf('1') != 0 && nif.indexOf('2') != 0);
        
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
        Individual ci = new Individual(nif, email, nome, morada, password, numero_ag, nifs, numero_cf, ats);
        
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
        } while(nif.length() != 9 || isNumeric == false || nif.indexOf('5') != 0);
        
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
        Coletivo cc = new Coletivo(nif, email, nome, morada, password, ats, numero_cf);
        
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
     * Método que permite fazer o login dos Contribuinte no Sistema.
     * @param
     * @return r
     */
    public int loginContribuinte()
    {
        int r = -1;
        Scanner read = new Scanner(System.in);
        
        System.out.print("Login:\n");
        System.out.print("Utilizador / NIF: "); String username = read.nextLine();
        System.out.print("Password: "); String password = read.nextLine();
        
        if(this.registered.containsKey(username) && this.registered.get(username).getPassword().equals(password))
        {
            this.contribuinte = this.registered.get(username).clone();
            
            if(this.registered.get(username).getClass().getSimpleName() == "Individual")
            {
                r = 1;
                System.out.print("\nEntrou com sucesso no Sistema!");
                time(1000);
            }
            else if(this.registered.get(username).getClass().getSimpleName() == "Coletivo")
            {
                r = 2;
                System.out.print("\nEntrou com sucesso no Sistema!");
                time(1000);
            }
        }
        else
        {
            System.out.print("\nErro : Dados inválidos!");
            time(1000);
        }
        return r;
    }
    
    /**
     * Método que permite fazer o logout do Contribuinte no Sistema.
     * @param
     * @return
     */
    public void logoutContribuinte()
    {
        this.contribuinte = new Contribuinte();
        System.out.print("Saiu com sucesso do Sistema!");
        time(1000);
    }

    /**
     * Método que permite fazer o login do administrador.
     * @param
     * @return
     */
    public void loginAdmin()
    {
        Scanner read = new Scanner(System.in);
        System.out.print("Password do Administrador ---> "); String password = read.nextLine();
        if(password.equals(getPasswordAdmin()))
        {
            System.out.print("\nEntrou com sucesso no Sistema como Administrador.");
            time(1500);
        }
        else
        {
            System.out.print("\nErro: Dados Inválidos!");
            time(1500);
        }
    }

    /**
     * Método que permite fazer o logout do administrador.
     * @param
     * @return
     */
    public void logoutAdmin()
    {
        
    } 
    
    /**
     * Método que permite submeter faturas por parte das Empresas / dos Contribuinte Coletivos.
     * @param
     * @return
     */
    public void registarFaturas()
    {
        boolean isNumeric;
        double valor;
        String nif_ci, descriçao, atividade;
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Submissão de Fatura associada a uma despesa:\n");
        System.out.print("\nNIF do emitente --> "); String nif_e = this.contribuinte.getNIF(); System.out.print(nif_e);
        System.out.print("\nNome do emitente --> "); String designaçao_e = this.contribuinte.getNome(); System.out.print(designaçao_e);
        
        do
        {
            System.out.print("\nNIF do cliente --> "); nif_ci = read2.nextLine();
            isNumeric = nif_ci.chars().allMatch(Character::isDigit); //Verificar se a string NIF é numérica.
        } while(nif_ci.length() != 9 || isNumeric == false || (nif_ci.indexOf('1') != 0 && nif_ci.indexOf('2') != 0 && nif_ci.indexOf('5') != 0));
        
        do
        {
            System.out.print("Descrição da despesa --> "); descriçao = read2.nextLine();
        }while(descriçao.length() == 0);
        
        do
        {   
            System.out.print("Natureza / Atividade económica da despesa --> "); atividade = read2.nextLine();
        }while(atividade.length() == 0);
        
        do
        {
            System.out.print("Valor da despesa --> "); valor = read1.nextDouble();
        }while(valor <= 0);
        
        System.out.print("\nData / Hora da despesa --> "); LocalDateTime dt = LocalDateTime.now(); System.out.print(dt.toString());
        
        Fatura f = new Fatura(nif_e, designaçao_e, dt, nif_ci, descriçao, atividade, valor);
        
        if(!this.faturas.contains(f))
        {
            this.faturas.add(f.clone());
            System.out.print("\nA fatura foi submetida com sucesso no Sistema!");
            time(1000);
        }
        else
        {
            System.out.print("\nAviso: Esta fatura já foi submetida anteriormente.");
            time(2000);
        }
    }
    
    /**
     * Método que obtem as listagens das facturas por contribuinte num determinado intervalo de datas, por parte das empresas.
     * @param Contribuinte c
     * @param LocalDateTime inicio
     * @param LocalDateTime fim
     * @return List<Faturas>
     */
    public List<Fatura> faturas_ord_contribuinte_datas(Contribuinte c, LocalDateTime inicio, LocalDateTime fim)
    {
        List<Fatura> res = new ArrayList<>();
        for(Fatura f: this.faturas)
        {
            if(c.getNIF().equals(f.getNIF_CLIENTE()) && f.getDATA_HORA_DESPESA().isAfter(inicio) && f.getDATA_HORA_DESPESA().isBefore(fim))
            {
                res.add(f.clone());
            }
        }
        return res;
    }
    
    /**
     * Método que obtem as listagens das facturas por contribuinte ordenadas por valor decrescente de despesa, por parte das empresas.
     * @param
     * @return
     */
    public void faturas_ord_contribuinte_decrescente_despesa()
    {
        double valor_aux = 0;
        Scanner read = new Scanner(System.in);
        System.out.print("Insira o NIF do contribuinte para o qual pretende visualizar as faturas ---> "); String nif = read.nextLine();
        
        List<Fatura> res = new ArrayList<>();
        for(Fatura f: this.faturas)
        {
            if(nif.equals(f.getNIF_CLIENTE()))
            {
                res.add(f.clone());
            }
        }
        Collections.sort(res);
        System.out.print(res.toString());
        time(5000);
    }
    
    /**
     * Método que indica o total facturado por uma empresa num determinado período.
     * @param inicio
     * @param fim
     * @return
     */
    public double total_faturado(LocalDateTime inicio, LocalDateTime fim)
    {
        double res = 0;
        for(Fatura f: this.faturas)
        {
            if(f.getDATA_HORA_DESPESA().isAfter(inicio) && f.getDATA_HORA_DESPESA().isBefore(fim))
            {
                res += f.getVALOR_DESPESA();
            }
        }
        return res;
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
        sb.append("Contribuinte dentro do Sistema:\n"); sb.append(this.contribuinte.toString()).append("\n");
        return sb.toString();
    }
}
