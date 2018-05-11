/**
 * Classe Sistema.
 * 
 * @author P.O.O. - Project - 2017/2018
 * @version 1.0
 */
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.Serializable;

public class Sistema implements Serializable
{
    /* variáveis de instância */
    private String password_admin;                                         /* Palavra Passe do Administrador */
    private Contribuinte contribuinte;                                     /* Contribuinte que se encontra dentro do Sistema */
    private Map<String, Contribuinte> registados;                          /* Dicionário que associa o NIF do Contribuinte (Chave) à sua informação (Valor) */
    private List<Fatura> faturas;                                          /* Lista com as faturas registadas */
    
    /* private Map<String, String> hash_password = new HashMap<>(); */     /* Dicionário que guarda as hash code das passwords */
    /* private Set<String> activities_available = new HashSet<>(); */

    /**
     * Método para fazer uma pausa no sistema de x segundos.
     * @param x
     * @return
     */
    private void time(int x)
    {
        try{
            Thread.sleep(x);
        }
        catch(Exception e){};
    }
    
    /**
     * Método que converte um objeto do tipo Date num objeto do tipo LocalDate.
     * @param Date
     * @return LocalDate
     */
    public LocalDate convertToLocalDate(Date dateToConvert)
    {
        return dateToConvert.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
    }
    
    /**
     * Construtor por omissão de Sistema
     * @param
     * @return
     */
    public Sistema()
    {
        this.password_admin = "admin123";
        this.contribuinte = null;
        this.registados = new HashMap<>();
        this.faturas = new ArrayList<>();
    }

    /**
     * Método que devolve a password do administrador.
     * @param
     * @return
     */
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
     * Método que devolve o dicionário de todos os Contribuinte registados no Sistema.
     * @param
     * @return Map<String, Contribuinte> com os Contribuinte registados no Sistema.
     */
    private Map<String, Contribuinte> getRegistados()
    {
        Map<String, Contribuinte> res = new HashMap<>();
        for(String s: this.registados.keySet())
        {
            res.put(s, this.registados.get(s).clone());
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
        this.registados = new HashMap<>();
        for(String s: dic.keySet())
        {
            this.registados.put(s, dic.get(s).clone());
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
     * Método que permite fazer o login dos Contribuinte no Sistema.
     * @param
     * @return r
     */
    public int login_Contribuinte()
    {
        int r = -1;
        Scanner read = new Scanner(System.in);
        
        System.out.print("Login:\n");
        System.out.print("Utilizador / NIF: "); String username = read.nextLine();
        System.out.print("Password: "); String password = read.nextLine();
        
        if(this.registados.containsKey(username) && this.registados.get(username).getPassword().equals(password))
        {
            this.contribuinte = this.registados.get(username).clone();
            if(this.registados.get(username) instanceof Individual)
            {
                r = 1;
            }
            else if(this.registados.get(username) instanceof Coletivo)
            {
                r = 2;
            }
            System.out.print("\nEntrou com sucesso no Sistema!");
            time(1000);
        }
        else
        {
            System.out.print("\nErro: Dados inválidos!");
            time(1000);
        }
        return r;
    }
    
    /**
     * Método que permite fazer o logout do Contribuinte no Sistema.
     * @param
     * @return
     */
    public void logout_Contribuinte()
    {
        this.contribuinte = null;
        System.out.print("Saiu com sucesso do Menu!");
        time(1000);
    }

    /**
     * Método que permite fazer o login do administrador.
     * @param
     * @return r
     */
    public int login_Administrador()
    {
        int r = -1;
        Scanner read = new Scanner(System.in);
        System.out.print("Password do Administrador --> "); String password = read.nextLine();
        if(password.equals(getPasswordAdmin()))
        {
            System.out.print("\nEntrou com sucesso no Sistema como Administrador.");
            time(1500);
            r = 0;
        }
        else
        {
            System.out.print("\nErro: Dados Inválidos!");
            time(1500);
        }
        return r;
    }

    /**
     * Método que permite fazer o logout do administrador.
     * @param
     * @return
     */
    public void logout_Administrador()
    {
        System.out.print("Saiu com sucesso do Menu!");
        time(1000);
    } 
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
        List<String> nifs = new ArrayList<>(); List<String> ats = new ArrayList<>(); List<Integer> index = new ArrayList<>();
        String nif, email, nome, morada, password, at, s; 
        boolean isNumeric, bool;
        int numero_ag;
        double numero_cf;
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Individual:\n");
        do{
            System.out.print("NIF --> "); nif = read2.nextLine();
            isNumeric = nif.chars().allMatch(Character::isDigit); //Verificar se a string NIF é numérica.
        }while(nif.length() != 9 || isNumeric == false || nif.indexOf('1') != 0 && nif.indexOf('2') != 0);
        
        do{
            System.out.print("Email --> "); email = read2.nextLine();
        }while(email.indexOf('@') == -1);

        do{
            System.out.print("Nome --> "); nome = read2.nextLine();
        }while(nome.length() == 0);
        
        do{
            System.out.print("Morada --> "); morada = read2.nextLine();
        }while(morada.length() == 0);
        
        do{
            System.out.print("Password --> "); password = read2.nextLine();
        }while(password.length() == 0);
        
        do{
            System.out.print("Nº de dependentes do agregado familiar --> "); numero_ag = read1.nextInt();
        }while(numero_ag < 1);
        
        do{
            bool = true;
            if(numero_ag == 1)
            {
                System.out.println("NIF 1: " + nif); nifs.add(nif);
            }
            else
            {
                System.out.println("NIF 1: " + nif); nifs.add(nif);
                for(int i = 2; i <= numero_ag; i++)
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
            }
        }while(bool == false);
        
        System.out.print("Atividades Económicas (nota: Separe as atividades económicas por um único hífen -) --> "); at = read2.nextLine();
        String[] ss = at.split("-");
        for(int i=0; i < ss.length; i++)
        {
            ats.add(ss[i]);
        }
        
        System.out.print("Coeficiente Fiscal --> "); numero_cf = read1.nextDouble();

        read1.close(); read2.close();
        Individual ci = new Individual(nif, email, nome, morada, password, index, numero_ag, nifs, numero_cf, ats);
        
        if(!this.registados.containsKey(ci.getNIF()))
        {
            System.out.print("\nA registar ...");
            this.registados.put(ci.getNIF(), ci.clone());
            time(1000);
            System.out.print("\nContribuinte registado com sucesso!");
            time(1500);
        }
        else
        {
            System.out.print("\nAviso: Já existe um Contribuinte Individual com este NIF no Sistema.");
            time(1500);
        }
    }
    
    /**
     * Método que regista um contribuinte coletivo / Empresa no sistema.
     * @param
     * @return
     */
    public void registar_CC()
    {
        List<String> ats = new ArrayList<>(); List<Integer> index = new ArrayList<>();
        String nif, email, nome, morada, password, atividades;
        boolean isNumeric;
        double numero_cf;
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Coletivo / Empresa:\n");
        
        do{
            System.out.print("NIF --> "); nif = read2.nextLine();
            isNumeric = nif.chars().allMatch(Character::isDigit); /* Verificar se a string NIF é numérica. */
        }while(nif.length() != 9 || isNumeric == false || nif.indexOf('5') != 0);
        
        do{
            System.out.print("Email --> "); email = read2.nextLine();
        } while(email.indexOf('@') == -1);

        do{
            System.out.print("Nome --> "); nome = read2.nextLine();
        }while(nome.length() == 0);
        
        do{
            System.out.print("Morada --> "); morada = read2.nextLine();
        }while(morada.length() == 0);
        
        do{
            System.out.print("Password --> "); password = read2.nextLine();
        }while(password.length() == 0);
        
        System.out.print("Atividades Económicas (nota: Separe as atividades económicas por um único hífen -) --> "); atividades = read2.nextLine();
        String[] ss = atividades.split("-");
        for(int i=0; i < ss.length; i++)
        {
            ats.add(ss[i]);
        }
        
        System.out.print("Coeficiente Fiscal --> "); numero_cf = read1.nextDouble();
        
        read1.close(); read2.close();
        Coletivo cc = new Coletivo(nif, email, nome, morada, password, index, ats, numero_cf);
        
        if(!this.registados.containsKey(cc.getNIF()))
        {
            System.out.print("\nA registar ...");
            this.registados.put(cc.getNIF(), cc.clone());
            time(1000);
            System.out.print("\nContribuinte registado com sucesso!");
            time(1500);
        }
        else
        {
            System.out.print("\nAviso: Já existe um Contribuinte com este NIF no Sistema.");
            time(1500);
        }
    }
    
    /**
     * Método que permite submeter faturas por parte das Empresas / dos Contribuinte Coletivos.
     * @param
     * @return
     */
    public void registar_Faturas()
    {
        boolean isNumeric;
        double valor;
        String nif_ci, descriçao, atividade;
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Submissão de Fatura associada a uma despesa:\n");
        System.out.print("\nNIF do emitente --> "); String nif_e = this.contribuinte.getNIF(); System.out.print(nif_e);
        System.out.print("\nNome do emitente --> "); String nome_e = this.contribuinte.getNome(); System.out.print(nome_e);
        System.out.print("\n");
        do
        {
            System.out.println("NIF do cliente --> "); nif_ci = read2.nextLine();
            isNumeric = nif_ci.chars().allMatch(Character::isDigit); //Verificar se a string NIF é numérica.
        } while(!this.registados.containsKey(nif_ci) || nif_e.equals(nif_ci) || nif_ci.length() != 9 || isNumeric == false || (nif_ci.indexOf('1') != 0 && nif_ci.indexOf('2') != 0 && nif_ci.indexOf('5') != 0));
        
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
        
        System.out.print("Data / Hora da despesa --> "); LocalDateTime data_hora = LocalDateTime.now(); System.out.print(data_hora.toString());
        
        Fatura f = new Fatura(nif_e, nome_e, data_hora, nif_ci, descriçao, atividade, valor);
        
        if(!this.faturas.contains(f))
        {
            this.faturas.add(f.clone());
            System.out.print("\n\nA fatura foi submetida com sucesso no Sistema!");
            
            List<Integer> cc = this.registados.get(nif_e).getIndex();
            cc.add(this.faturas.indexOf(f));
            this.registados.get(nif_e).setIndex(cc);
            
            List<Integer> cliente = this.registados.get(nif_ci).getIndex();
            cliente.add(this.faturas.indexOf(f));
            this.registados.get(nif_ci).setIndex(cliente);
            
            List<Integer> contribuinte_in_system = this.contribuinte.getIndex();
            contribuinte_in_system.add(this.faturas.indexOf(f));
            this.contribuinte.setIndex(contribuinte_in_system);
            //this.contribuinte = this.registados.get(nif_e).clone();
            
            time(1500);
        }
        else
        {
            System.out.print("\nAviso: Esta fatura já foi submetida anteriormente.");
            time(1500);
        }
    }
    
    /**
     * Método que obtem as listagens das facturas por contribuinte num determinado intervalo de datas, por parte das empresas.
     * @param 
     * @return
     */
    public void faturas_contribuinte_ord_intervalo_datas_CC()
    {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = LocalDateTime.now();
        LocalTime lt = LocalTime.of(0, 0, 0);
        Date date1, date2;
        String nif;
        Scanner read = new Scanner(System.in);
        
        do{
            System.out.print("Introduza o NIF do contribuinte: "); nif = read.nextLine();
        }while(!this.registados.containsKey(nif));
        
        try{
            System.out.print("Introduza a data inicial (Nota: Use o formato dd/mm/aaaa): "); date1 = new SimpleDateFormat("dd/MM/yyyy").parse(read.nextLine());
            LocalDate ld1 = convertToLocalDate(date1);
            inicio = LocalDateTime.of(ld1, lt);
        }catch(Exception e){};
        
        try{
            System.out.print("Introduza a data final (Nota: Use o formato dd/mm/aaaa): "); date2 = new SimpleDateFormat("dd/MM/yyyy").parse(read.nextLine());
            LocalDate ld2 = convertToLocalDate(date2);
            fim = LocalDateTime.of(ld2, lt);
        }catch(Exception e){};
        
        System.out.print("\n");
        for(int i: this.registados.get(nif).getIndex())
        {
            if(this.faturas.get(i).getNIF_Emitente().equals(this.contribuinte.getNIF()) && this.faturas.get(i).getData_Hora().isAfter(inicio) && this.faturas.get(i).getData_Hora().isBefore(fim))
            {
                System.out.println(this.faturas.get(i).toString());
            }
        }
        
        System.out.print("\nPrima enter para continuar ...");
        read.nextLine();
    }
    
    /**
     * Método que obtem as listagens das facturas por contribuinte ordenadas por valor decrescente de despesa, por parte das empresas.
     * @param
     * @return
     */
    public void faturas_contribuinte_ord_decrescente_despesa_CC()
    {
        String nif;
        Scanner read = new Scanner(System.in);
        
        do{
            System.out.print("Introduza o NIF do contribuinte: "); nif = read.nextLine();
        }while(!this.registados.containsKey(nif));
        System.out.print("\n");
        
        TreeSet<Fatura> tree = new TreeSet<Fatura>(new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                Fatura f1 = (Fatura) o1;
                Fatura f2 = (Fatura) o2;
                return (f1.getValor_Despesa() > f2.getValor_Despesa()) ? -1 : 1;
            }
        });
        for(int i: this.registados.get(nif).getIndex())
        {
            tree.add(this.faturas.get(i).clone());
        }
        for(Fatura f: tree)
        {
            System.out.println(f.toString());
        }
        
        System.out.print("Prima enter para continuar ...");
        read.nextLine();
    }
    
    /**
     * Método que indica o total facturado por uma empresa num determinado período.
     * @param
     * @param
     * @return
     */
    public void total_faturado_CC()
    {
        double res = 0;
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = LocalDateTime.now();
        LocalTime lt = LocalTime.of(0, 0, 0);
        Date date1, date2;
        Scanner read = new Scanner(System.in);
        
        try{
            System.out.print("Introduza a data inicial (Nota: Use o formato dd/mm/aaaa): "); date1 = new SimpleDateFormat("dd/MM/yyyy").parse(read.nextLine());
            LocalDate ld1 = convertToLocalDate(date1);
            inicio = LocalDateTime.of(ld1, lt);
        }catch(Exception e){};
        
        try{
            System.out.print("Introduza a data final (Nota: Use o formato dd/mm/aaaa): "); date2 = new SimpleDateFormat("dd/MM/yyyy").parse(read.nextLine());
            LocalDate ld2 = convertToLocalDate(date2);
            fim = LocalDateTime.of(ld2, lt);
        }catch(Exception e){};
        
        System.out.print("\n");
        for(int i: this.contribuinte.getIndex())
        {
            if(this.faturas.get(i).getData_Hora().isAfter(inicio) && this.faturas.get(i).getData_Hora().isBefore(fim))
            {
                res += this.faturas.get(i).getValor_Despesa();
            }
        }
        System.out.println("Total faturado pela Empresa " + this.contribuinte.getNome() + ": " + res + " €.");
        System.out.print("\nPrima enter para continuar ...");
        read.nextLine();
    }
    
    /**
     * Método que obter a listagem das facturas de um determinado contribuinte, ordenada por data de emissão (mais recente -> mais antiga).
     * @param
     * @return
     */
    public void mostrar_faturas_ord_data_Contribuintes()
    {
        Scanner read = new Scanner(System.in);
        TreeSet<Fatura> res = new TreeSet<Fatura>(new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                Fatura f1 = (Fatura) o1;
                Fatura f2 = (Fatura) o2;
                return f1.getData_Hora().isAfter(f2.getData_Hora()) ? -1 : 1;
            }
        });
        
        for(int i: this.contribuinte.getIndex())
        {
            res.add(this.faturas.get(i).clone());
        }

        if(this.contribuinte instanceof Individual)
        {
            System.out.print("Listagem de Faturas do Contribuinte Individual " + this.contribuinte.getNome() + " ordenada por data de emissão:\n\n");
        }
        else if (this.contribuinte instanceof Coletivo)
        {
            System.out.print("Listagem de Faturas da empresa " + this.contribuinte.getNome() + " ordenada por data de emissão:\n\n");

        }

        for(Fatura f: res)
        {
            System.out.println(f.toString());
        }
        
        System.out.print("\nPrima enter para continuar ...");
        read.nextLine();
    }
    
    /**
     * Método que obter a listagem das facturas de um determinado contribuinte, ordenada por valor crescente de despesa.
     * @param
     * @return
     */
    public void mostrar_faturas_ord_valor_crescente_despesa_Contribuintes()
    {
        Scanner read = new Scanner(System.in);
        TreeSet<Fatura> res = new TreeSet<Fatura>(new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                Fatura f1 = (Fatura) o1;
                Fatura f2 = (Fatura) o2;
                return f1.getValor_Despesa() < f2.getValor_Despesa() ? -1 : 1;
            }
        });

        for(int i: this.contribuinte.getIndex())
        {
            res.add(this.faturas.get(i).clone());
        }

        if(this.contribuinte instanceof Individual)
        {
            System.out.print("Listagem de Faturas do Contribuinte Individual " + this.contribuinte.getNome() + " ordenada por valor crescente de despesa:\n\n");
        }
        else if (this.contribuinte instanceof Coletivo)
        {
            System.out.print("Listagem de Faturas da empresa " + this.contribuinte.getNome() + " ordenada por valor crescente de despesa:\n\n");
        }

        for(Fatura f: res)
        {
            System.out.println(f.toString());
        }
        
        System.out.print("\nPrima enter para continuar ...");
        read.nextLine();
    }

    /**
     * Método que permite visualizar todas as faturas de um Contribuinte Individual.
     * @param
     * @return
     */
    public void mostrar_faturas_CI()
    {
        Scanner read = new Scanner(System.in);
        System.out.println("Faturas do contribuinte " + this.contribuinte.getNome() + ", com NIF " + this.contribuinte.getNIF() + ":\n");
        for(int i: this.contribuinte.getIndex())
        {
            System.out.println(this.faturas.get(i).toString());
        }

        System.out.print("Prima enter para continuar ...");
        read.nextLine();
    }

    /**
     * Método que permite visualizar todas as faturas de uma Empresa.
     * @param
     * @return
     */
    public void mostrar_faturas_CC()
    {
        Scanner read = new Scanner(System.in);
        System.out.println("Faturas da empresa " + this.contribuinte.getNome() + ", com NIF " + this.contribuinte.getNIF() + ":\n");
        for(int i: this.contribuinte.getIndex())
        {
            System.out.println(this.faturas.get(i).toString());
        }

        System.out.print("Prima enter para continuar ...");
        read.nextLine();
    }

    /**
     * Método que permite ao administrador ver todas as faturas do Sistema.
     */
    public void mostrar_faturas_Administrador()
    {
        Scanner read = new Scanner(System.in);
        System.out.println("Faturas de todo o Sistema:\n");
        for(Fatura f: this.faturas)
        {
            System.out.println(f.toString());
        }

        System.out.print("Prima enter para continuar ...");
        read.nextLine();
    }

    /**
     * Método que permite ao administrador ver todos os Contribuintes registados do Sistema.
     */
    public void mostrar_Contribuintes_Administrador()
    {
        Scanner read = new Scanner(System.in);
        System.out.println("Contribuintes registados no Sistema:\n");
        for(Contribuinte c: this.registados.values())
        {
            System.out.println(c.toString());
        }

        System.out.print("Prima enter para continuar ...");
        read.nextLine();
    }

    /**
     * Método que obtem o gasto de um determinado Contribuinte no Sistema JavaFatura.
     * @param Contribuinte c
     * @return double res
     */
    private double gasto_Contribuinte(Contribuinte c)
    {
        double res = 0;
        if(c instanceof Individual)
        {
            for(int i: c.getIndex())
            {
                res += this.faturas.get(i).getValor_Despesa();
            }
        }
        else if(c instanceof Coletivo)
        {
            for(int i: c.getIndex())
            {
                if(this.faturas.get(i).getNIF_Cliente().equals(c.getNIF()))
                {
                    res += this.faturas.get(i).getValor_Despesa();
                }
            }
        }
        return res;
    }

    /**
     * Método que obtem os 10 contribuintes que mais gastaram em todo o Sistema.
     */
    public void top_10_Administrador()
    {
        double aux;
        int i = 0;
        Scanner read = new Scanner(System.in);
        
        TreeSet<Contribuinte> top = new TreeSet<Contribuinte>(new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                Contribuinte c1 = (Contribuinte) o1;
                Contribuinte c2 = (Contribuinte) o2;
                return gasto_Contribuinte(c1) > gasto_Contribuinte(c2) ? -1 : 1;
            }
        });
        for(Contribuinte c: this.registados.values())
        {
            top.add(c.clone());
        }
        System.out.println(top.toString());
        System.out.println("TOP 10 Contribuintes com mais gastos no Sistema:\n");
        for(Contribuinte c: top)
        {
            if(i == 10)
            {
                break;
            }
            System.out.println("Contribuinte " + c.getNome() + ", com NIF " + c.getNIF() + ": " + gasto_Contribuinte(c) + " €.");
            i += 1;
        }
        
        System.out.print("\nPrima enter para continuar ...");
        read.nextLine();
    }

    /**
     * Método que grava o estado do Sistema em ficheiro.
     * @param
     * @return
     */
    public void gravar_estado() throws FileNotFoundException, IOException
    {
        FileOutputStream fos = new FileOutputStream("estado");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    
    /**
     * Método que lê o estado do Sistema em ficheiro.
     * @param
     * @return Sistema s
     */
    public static Sistema ler_estado() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        File f = new File("estado");
        Sistema s = null;

        if(!f.exists())
        {
            f.createNewFile();
            s = new Sistema();
        } 
        else 
        {
            FileInputStream fis = new FileInputStream("estado");
            ObjectInputStream ois = new ObjectInputStream(fis);

            s = (Sistema) ois.readObject();
            ois.close();
        }
        
        return s;
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
        sb.append("Registados:\n"); sb.append(this.registados.toString()).append("\n\n");
        sb.append("Faturas:\n"); sb.append(this.faturas.toString()).append("\n");
        return sb.toString();
    }
}