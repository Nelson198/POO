/**
 * TODO:
 *  - Definir se é empresa interior no registo do contribuinte coletivo
 *  - Redução do imposto se o contribuinte for individual e tiver familia numerosa
 *  - Redução do imposto se o contribuinte for coletivo e for empresa interior
 *  - Função que dado um NIF vê se a familia é numerosa
 */

/**
 * Classe Sistema.
 * 
 * @author P.O.O. - Project - 2017/2018
 * @version 1.0
 */
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Comparator;
import java.lang.Double;
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
    private static final String password_admin = "admin123";                           /* Palavra Passe do Administrador */
    private Contribuinte contribuinte;                                                 /* Contribuinte que se encontra dentro do Sistema */
    private Map<String, Contribuinte> registados;                                      /* Dicionário que associa o NIF do Contribuinte (Chave) à sua informação (Valor) */
    private List<Fatura> faturas;                                                      /* Lista com as faturas registadas */
    private Map<String, double[]> atividades_economicas_disponiveis;                   /* Dicionário (Chave -> Atividade Económica; Valor -> [% de desconto, valor máximo de desconto] */
    private double[] descontos_ag;                                                     /* Array com os descontos extra associados ao nº de elementos do agregado familiar */

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
     * Método que verifica se uma data é válida.
     * @param String inDate
     * @return boolean
     */
    public boolean isValidDate(String inDate)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try{
            dateFormat.parse(inDate.trim());
        }catch (ParseException e){
            return false;
        }
        return true;
    }

    /**
     * Método que converte um objeto do tipo Date num objeto do tipo LocalDate.
     * @param Date
     * @return LocalDate
     */
    public LocalDate convertToLocalDate(Date dateToConvert)
    {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    /**
     * Construtor por omissão de Sistema.
     * @param
     * @return
     */
    public Sistema()
    {
        this.contribuinte = null;
        this.registados = new HashMap<>();
        this.faturas = new ArrayList<>();

        this.atividades_economicas_disponiveis = new HashMap<>();
        this.atividades_economicas_disponiveis.put("Despesas gerais familiares", new double[] {35.0, 250});
        this.atividades_economicas_disponiveis.put("Saúde", new double[] {15.0, 1000});
        this.atividades_economicas_disponiveis.put("Educação", new double[] {30.0, 800});
        this.atividades_economicas_disponiveis.put("Habitação", new double[] {15.0, 500});
        this.atividades_economicas_disponiveis.put("Restauração e similares", new double[] {15.0, 250});
        this.atividades_economicas_disponiveis.put("Transportes públicos", new double[] {20.0, 100});
        this.atividades_economicas_disponiveis.put("Comércio", new double[] {10.0, 500});
        this.atividades_economicas_disponiveis.put("Manutenção e Reparação de veículos", new double[] {15.0, 250});
        this.atividades_economicas_disponiveis.put("Serviços Bancários", new double[] {5.0, 400});
        this.atividades_economicas_disponiveis.put("Serviços de fornecimento de eletricidade e água", new double[] {20.0, 600});
        this.atividades_economicas_disponiveis.put("Cabeleireiros e Estética", new double[] {15.0, 250});
        this.atividades_economicas_disponiveis.put("Lares", new double[] {25.0, 400});
        this.atividades_economicas_disponiveis.put("Donativos", new double[] {0.0, 0.0});

        this.descontos_ag = new double[] {5.0, 10.0, 12.5, 15.0, 17.5};
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
    public Contribuinte getContribuinte()
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
     * Método que devolve o dicionário das atividades económicas do Sistema.
     * @param
     * return Map<String, double[]>
     */
    public Map<String, double[]> getAtividadesEconomicas()
    {
        Map<String, double[]> res = new HashMap<>();
        for(String s : this.atividades_economicas_disponiveis.keySet())
        {
            res.put(s, this.atividades_economicas_disponiveis.get(s));
        }
        return res;
    }
    
    /**
     * Método que atualiza o set das atividades económicas do Sistema.
     * @param List<String>
     * return
     */
    public void setAtividadesEconomicas(Map<String, double[]> atividades)
    {
        this.atividades_economicas_disponiveis = new HashMap<>();
        for(String s : atividades.keySet())
        {
            this.atividades_economicas_disponiveis.put(s, atividades.get(s));
        }
    }

    /**
     * Método que devolve a lista de descontos extra associada ao nº de elementos do agregado familiar. 
     * @param
     * @return
     */
    public double[] getDescontos_AG()
    {
        return this.descontos_ag;
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
            if(this.registados.get(username) instanceof Individual) r = 1;
            else if(this.registados.get(username) instanceof Coletivo) r = 2;
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

    /**
     * Método que regista um contribuinte individual no sistema.
     * @param
     * @return
     */
    public void registar_CI()
    {
        List<String> nifs = new ArrayList<>(); List<Integer> index = new ArrayList<>();
        Map<String, Double> ats = new HashMap<>();
        String nif, email, nome, morada, password, at, s; 
        boolean isNumeric, bool;
        int numero_ag;
        double cf, r;
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Individual:\n");
        do{
            System.out.print("NIF --> "); nif = read2.nextLine();
            isNumeric = nif.chars().allMatch(Character::isDigit); //Verificar se a string NIF é numérica.
        }while(nif.length() != 9 || isNumeric == false || nif.indexOf('1') != 0 && nif.indexOf('2') != 0);
        
        do{
            System.out.print("Email --> "); email = read2.nextLine();
        }while(email.indexOf('@') == -1 || email.indexOf('.') == -1);

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
            if(numero_ag == 1) {System.out.println("NIF 1: " + nif); nifs.add(nif);}
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
        
        do{
            for(String t: this.atividades_economicas_disponiveis.keySet())
            {
                System.out.print("Atividade Económica: " + t + " (S/N)? "); at = read2.nextLine();
                if(at.equals("S") || at.equals("s"))
                {
                    ats.put(t, 0.0);
                }
                else if(at.equals("N") || at.equals("n")) {}
                else
                {
                    System.out.print("Erro: Dados inválidos!");
                    time(1500);
                    return;
                }
            }
        }while(ats.size() == 0);
        
        do{
            System.out.print("Coeficiente Fiscal --> "); cf = read1.nextDouble();
        }while(cf <= 0);

        read1.close(); read2.close();
        Individual ci = new Individual(nif, email, nome, morada, password, index, numero_ag, nifs, cf, ats);
        
        if(!this.registados.containsKey(ci.getNIF()))
        {
            this.registados.put(ci.getNIF(), ci.clone());
            System.out.print("\nA registar ..."); time(1000);
            System.out.print("\nContribuinte registado com sucesso!"); time(1500);
        }
        else
        {
            System.out.print("\nAviso: Já existe um Contribuinte Individual com este NIF no Sistema."); time(1500);
        }
    }
    
    /**
     * Método que regista um contribuinte coletivo / Empresa no sistema.
     * @param
     * @return
     */
    public void registar_CC()
    {
        List<Integer> index = new ArrayList<>();
        Map<String, Double> ats = new HashMap<>();
        String nif, email, nome, morada, password, at, intr;
        boolean isNumeric, interior;
        double cf, r;
        Scanner read1 = new Scanner(System.in);
        Scanner read2 = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Coletivo / Empresa:\n");
        do{
            System.out.print("NIF --> "); nif = read2.nextLine();
            isNumeric = nif.chars().allMatch(Character::isDigit); /* Verificar se a string NIF é numérica. */
        }while(nif.length() != 9 || isNumeric == false || nif.indexOf('5') != 0);
        
        do{
            System.out.print("Email --> "); email = read2.nextLine();
        } while(email.indexOf('@') == -1 || email.indexOf('.') == -1);

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
            for(String t: this.atividades_economicas_disponiveis.keySet())
            {
                if(!t.equals("Despesas gerais familiares"))
                {
                    System.out.print("Atividade Económica: " + t + " (S/N)? "); at = read2.nextLine();
                    if(at.equals("S") || at.equals("s"))
                    {
                        ats.put(t, 0.0);
                    }
                    else if(at.equals("N") || at.equals("n")) {}
                    else
                    {
                        System.out.print("Erro: Dados inválidos!"); time(1500);
                        return;
                    }
                }
            }
        }while(ats.size() == 0);
        
        do{
            System.out.print("Coeficiente Fiscal --> "); cf = read1.nextDouble();
        }while(cf <= 0);

        do{
            System.out.print("Interior? (S/N): "); intr = read2.nextLine();
            if(intr.equals("S") || intr.equals("s"))
            {
                interior = true;
            }
            else if(intr.equals("N") || intr.equals("n"))
            {
                interior = false;
            }
            else
            {
                System.out.print("Introduziu um opção inválida!");
                return;
            }
        }while(interior != true && interior != false);

        read1.close(); read2.close();
        Coletivo cc = new Coletivo(nif, email, nome, morada, password, index, ats, cf, interior);
        
        if(!this.registados.containsKey(cc.getNIF()))
        {
            this.registados.put(cc.getNIF(),cc.clone());
            System.out.print("\nA registar ..."); time(1000);
            System.out.print("\nContribuinte registado com sucesso!"); time(1500);
        }
        else
        {
            System.out.print("\nAviso: Já existe um Contribuinte com este NIF no Sistema."); time(1500);
        }
    }
    
    /**
     * Método que permite submeter faturas por parte das Empresas / dos Contribuinte Coletivos.
     * @param
     * @return
     */
    public void registar_Faturas()
    {
        Coletivo c = ((Coletivo) this.contribuinte);
        StringBuilder sb = new StringBuilder();
        List<String> at = new ArrayList<>();
        boolean isNumeric;
        double valor, aux;
        String nif_ci, descriçao, atividades;
        Scanner read1 = new Scanner(System.in); Scanner read2 = new Scanner(System.in);
        
        System.out.print("Submissão de Fatura associada a uma despesa:\n");
        System.out.print("\nNIF do emitente --> "); String nif_e = this.contribuinte.getNIF(); System.out.print(nif_e);
        System.out.print("\nNome do emitente --> "); String nome_e = this.contribuinte.getNome(); System.out.print(nome_e);
        System.out.print("\n");

        if(c.getAtividades_Economicas().size() == 1)
        {
            at.add((String) c.getAtividades_Economicas().keySet().toArray()[0]);
            sb.append((String) c.getAtividades_Economicas().keySet().toArray()[0]);
            System.out.println("Atividade económica da despesa --> " + sb.toString());
        }
        else
        {
            for(String s: c.getAtividades_Economicas().keySet())
            {
                at.add(s);
                sb.append(s).append(", ");
            }
            String res = sb.toString(); res = res.substring(0, res.length()-2); 
            System.out.println("Possíveis atividades económicas da despesa --> " + res);
        }
        
        do
        {
            System.out.print("NIF do cliente --> "); nif_ci = read2.nextLine();
            isNumeric = nif_ci.chars().allMatch(Character::isDigit); //Verificar se a string NIF é numérica.
        }while(!this.registados.containsKey(nif_ci) || nif_e.equals(nif_ci) || nif_ci.length() != 9 || isNumeric == false || (nif_ci.indexOf('1') != 0 && nif_ci.indexOf('2') != 0 && nif_ci.indexOf('5') != 0));

        do
        {
            System.out.print("Descrição da despesa --> "); descriçao = read2.nextLine();
        }while(descriçao.length() == 0);
        
        do
        {
            System.out.print("Valor da despesa --> "); valor = read1.nextDouble();
        }while(valor <= 0);
        
        System.out.print("Data / Hora da despesa --> "); LocalDateTime data_hora = LocalDateTime.now(); System.out.print(data_hora.toString());
        
        Fatura f = new Fatura(nif_e, nome_e, data_hora, nif_ci, descriçao, at, valor, false);
        
        if(!this.faturas.contains(f))
        {
            if(at.size() >= 2) f.setPendente(true);
            
            this.faturas.add(f.clone());
        
            List<Integer> cc = this.registados.get(nif_e).getIndex();
            cc.add(this.faturas.indexOf(f));
            this.registados.get(nif_e).setIndex(cc);

            List<Integer> cliente = this.registados.get(nif_ci).getIndex();
            cliente.add(this.faturas.indexOf(f));
            this.registados.get(nif_ci).setIndex(cliente);
            
            if(at.size() == 1)
            {
                if(this.registados.get(nif_ci) instanceof Individual) 
                {
                    acumular_valor_despesa_CI(nif_ci, at.get(0), valor);
                }
                else if(this.registados.get(nif_ci) instanceof Coletivo) 
                {
                    acumular_valor_despesa_CC(nif_ci, at.get(0), valor);
                }
                System.out.print("\n\nA fatura foi submetida com sucesso no Sistema!");
                time(1000);
            }
            else if(at.size() >= 2)
            {
                System.out.print("\n\nA fatura foi submetida com sucesso no Sistema! Fatura Pendente de Validação por parte do Contribuinte!");
                time(1000);
            }
        }
        else System.out.print("\nAviso: Esta fatura já foi submetida anteriormente."); 
        time(2000);
    }
    
    /**
     * Método que trata de validar faturas pendentes por parte dos Contribuintes.
     * @param
     * @return
     */
    public void validar_faturas_pendentes()
    {
        String option;
        List<String> res = new ArrayList<>();
        Scanner read = new Scanner(System.in);
        
        for(int i: this.registados.get(this.contribuinte.getNIF()).getIndex())
        {
            if(this.faturas.get(i).getPendente() == true && this.faturas.get(i).getNIF_Cliente().equals(this.contribuinte.getNIF()))
            {
                System.out.println("---> Fatura por validar:\n");
                System.out.println(this.faturas.get(i).toString());
                for(String s : this.faturas.get(i).getNatureza_Despesa())
                {
                    System.out.print("Deseja associar a esta despesa a atividade económica " + s + "?(S/N) "); option = read.nextLine();
                    if(option.equals("S") || option.equals("s"))
                    {
                        res.add(s);
                        this.faturas.get(i).setNatureza_Despesa(res);
                        this.faturas.get(i).setPendente(false);
                        res.clear();
                        if(this.contribuinte instanceof Individual)
                        {
                            acumular_valor_despesa_CI(this.faturas.get(i).getNIF_Cliente(), this.faturas.get(i).getNatureza_Despesa().get(0), this.faturas.get(i).getValor_Despesa());
                        }
                        else if(this.contribuinte instanceof Coletivo)
                        {
                            acumular_valor_despesa_CC(this.faturas.get(i).getNIF_Cliente(), this.faturas.get(i).getNatureza_Despesa().get(0), this.faturas.get(i).getValor_Despesa());
                        }
                        System.out.print("\nA fatura foi validada com sucesso no Sistema!\n\n"); time(1500);
                        break;
                    }
                    else if (option.equals("N") || option.equals("n"))
                    {}
                    else
                    {
                        System.out.print("Erro: Dados introduzidos não estão corretos!"); time(1500);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Método que acumula o valor da despesa de uma fatura à respetiva atividade económica do cliente (Contribuinte Individual), para efeitos de dedução fiscal.
     * @param
     * @return
     */
    public void acumular_valor_despesa_CI(String nif_ci, String at, double despesa)
    {
        double d;
        Map<String, Double> map;
        if(this.registados.get(nif_ci) instanceof Individual)
        {
            Individual i = ((Individual) this.registados.get(nif_ci)).clone();
            int index = 1;
            for(String s : i.getAtividades_Economicas().keySet())
            {
                if(at.compareTo(s) == 0)
                {
                    d = i.getAtividades_Economicas().get(s);
                    map = i.getAtividades_Economicas();
                    map.put(s, d + despesa);
                    ((Individual) this.registados.get(nif_ci)).setAtividades_Economicas(map);
                    break;
                }
                else if(index == i.getAtividades_Economicas().keySet().size() && i.getAtividades_Economicas().containsKey("Despesas gerais familiares"))
                {
                    d = i.getAtividades_Economicas().get("Despesas gerais familiares");
                    map = i.getAtividades_Economicas();
                    map.put("Despesas gerais familiares", d + despesa);
                    ((Individual) this.registados.get(nif_ci)).setAtividades_Economicas(map);
                    break;
                }
                else index += 1;
            }
        }
    }
    
    /**
     * Método que acumula o valor da despesa de uma fatura à respetiva atividade económica do cliente (Contribuinte Coletivo).
     * @param
     * @return
     */
    public void acumular_valor_despesa_CC(String nif_cc, String at, double despesa)
    {
        double d;
        Map<String, Double> map;
        if(this.registados.get(nif_cc) instanceof Coletivo)
        {
            Coletivo c = ((Coletivo) this.registados.get(nif_cc)).clone();
            for(String s : c.getAtividades_Economicas().keySet())
            {
                if(at.compareTo(s) == 0)
                {
                    d = c.getAtividades_Economicas().get(s);
                    map = c.getAtividades_Economicas();
                    map.put(s, d + despesa);
                    ((Coletivo) this.registados.get(nif_cc)).setAtividades_Economicas(map);
                    break;
                }
            }
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
        String nif, data;
        Scanner read = new Scanner(System.in);
        
        do{
            System.out.print("Introduza o NIF do contribuinte: "); nif = read.nextLine();
        }while(!this.registados.containsKey(nif) || nif.equals(this.contribuinte.getNIF()));
        
        try{
            System.out.print("Introduza a data inicial (Nota: Use o formato dd/mm/aaaa): "); data = read.nextLine(); date1 = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            boolean b = isValidDate(data);
            if(b == false){
                System.out.print("\nErro: Inseriu um data inválida!\nPrima enter para continuar ..."); read.nextLine();
                return;
            }
            LocalDate ld1 = convertToLocalDate(date1);
            inicio = LocalDateTime.of(ld1, lt);
        }catch(Exception e){
            System.out.print("\nErro: Formato da data inserido. Utilize o formato indicado!\nPrima enter para continuar ..."); read.nextLine();
            return;
        };
        
        try{
            System.out.print("Introduza a data final (Nota: Use o formato dd/mm/aaaa): "); data = read.nextLine(); date2 = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            boolean b = isValidDate(data);
            if(b == false)
            {
                System.out.print("\nErro: Inseriu um data inválida!\nPrima enter para continuar ..."); read.nextLine();
                return;
            }
            LocalDate ld2 = convertToLocalDate(date2);
            fim = LocalDateTime.of(ld2, lt);
        }catch(Exception e){
            System.out.print("\nErro: Formato da data inserido. Utilize o formato indicado!\nPrima enter para continuar ..."); read.nextLine();
            return;
        };
        
        System.out.print("\n");
        for(int i: this.registados.get(nif).getIndex())
        {
            if(this.faturas.get(i).getNIF_Emitente().equals(this.contribuinte.getNIF()) && this.faturas.get(i).getData_Hora().isAfter(inicio) && this.faturas.get(i).getData_Hora().isBefore(fim))
            {
                System.out.println(this.faturas.get(i).toString());
            }
        }
        System.out.print("Prima enter para continuar ..."); read.nextLine();
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
        }while(!this.registados.containsKey(nif) || nif.equals(this.contribuinte.getNIF()));
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
            if(this.faturas.get(i).getNIF_Emitente().equals(this.contribuinte.getNIF()))
            {
                tree.add(this.faturas.get(i).clone());
            }
        }
        for(Fatura f: tree)
        {
            System.out.println(f.toString());
        }
        System.out.print("Prima enter para continuar ..."); read.nextLine();
    }
    
    /**
     * Método que indica o total facturado por uma empresa num determinado período.
     * @param
     * @return
     */
    public void total_faturado_CC()
    {
        double res = 0;
        LocalDateTime inicio = LocalDateTime.now(); LocalDateTime fim = LocalDateTime.now();
        LocalTime lt = LocalTime.of(0, 0, 0);
        Date date1, date2;
        String data;
        Scanner read = new Scanner(System.in);
        
        try{
            System.out.print("Introduza a data inicial (Nota: Use o formato dd/mm/aaaa): "); data = read.nextLine(); date1 = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            boolean b = isValidDate(data);
            if(b == false){
                System.out.print("\nErro: Inseriu um data inválida!\nPrima enter para continuar ..."); read.nextLine();
                return;
            }
            LocalDate ld1 = convertToLocalDate(date1);
            inicio = LocalDateTime.of(ld1, lt);
        }catch(Exception e){
            System.out.print("\nErro: Formato da data inserido. Utilize o formato indicado!\nPrima enter para continuar ..."); read.nextLine();
            return;
        };
        
        try{
            System.out.print("Introduza a data final (Nota: Use o formato dd/mm/aaaa): "); data = read.nextLine(); date2 = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            boolean b = isValidDate(data);
            if(b == false){
                System.out.print("\nErro: Inseriu um data inválida!\nPrima enter para continuar ..."); read.nextLine();
                return;
            }
            LocalDate ld2 = convertToLocalDate(date2);
            fim = LocalDateTime.of(ld2, lt);
        }catch(Exception e){
            System.out.print("\nErro: Formato da data inserido. Utilize o formato indicado!\nPrima enter para continuar ..."); read.nextLine();
            return;
        };
        
        System.out.print("\n");
        for(int i: this.registados.get(this.contribuinte.getNIF()))
        {
            if(this.faturas.get(i).getNIF_Emitente().equals(this.contribuinte.getNIF()) && this.faturas.get(i).getData_Hora().isAfter(inicio) && this.faturas.get(i).getData_Hora().isBefore(fim))
            {
                res += this.faturas.get(i).getValor_Despesa();
            }
        }
        System.out.printf("Total faturado pela Empresa / Instituição %s: %.2f €.", this.contribuinte.getNome(), res);
        System.out.print("\nPrima enter para continuar ..."); read.nextLine();
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
        
        for(int i: this.registados.get(this.contribuinte.getNIF()).getIndex())
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
        System.out.print("Prima enter para continuar ..."); read.nextLine();
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

        for(int i: this.registados.get(this.contribuinte.getNIF()).getIndex())
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
        System.out.print("Prima enter para continuar ..."); read.nextLine();
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
        for(int i: this.registados.get(this.contribuinte.getNIF().getIndex()))
        {
            System.out.println(this.faturas.get(i).toString());
        }
        System.out.print("Prima enter para continuar ..."); read.nextLine();
    }

    /**
     * Método que permite visualizar todas as faturas emitidas por parte de uma Empresa.
     * @param
     * @return
     */
    public void mostrar_faturas_emitidas_CC()
    {
        Scanner read = new Scanner(System.in);
        System.out.println("Faturas emitidas pela empresa " + this.contribuinte.getNome() + ", com NIF " + this.contribuinte.getNIF() + ":\n");
        for(int i: this.registados.get(this.contribuinte.getNIF().getIndex()))
        {
            if(this.faturas.get(i).getNIF_Emitente().equals(this.contribuinte.getNIF()))
            {
                System.out.println(this.faturas.get(i).toString());
            }
        }
        System.out.print("Prima enter para continuar ..."); read.nextLine();
    }

    /**
     * Método que permite visualizar todas as faturas de despesas feitas por parte de uma Empresa.
     * @param
     * @return
     */
    public void mostrar_faturas_para_CC()
    {
        Scanner read = new Scanner(System.in);
        System.out.println("Faturas de despesas feitas pela empresa " + this.contribuinte.getNome() + ", com NIF " + this.contribuinte.getNIF() + ":\n");
        for(int i: this.registados.get(this.contribuinte.getNIF().getIndex()))
        {
            if(this.faturas.get(i).getNIF_Cliente().equals(this.contribuinte.getNIF()))
            {
                System.out.println(this.faturas.get(i).toString());
            }
        }
        System.out.print("Prima enter para continuar ..."); read.nextLine();
    }    

    /**
     * Método que permite ao administrador ver todas as faturas do Sistema.
     * @param
     * @return
     */
    public void mostrar_faturas_Administrador()
    {
        Scanner read = new Scanner(System.in);
        System.out.println("Faturas de todo o Sistema:\n");
        for(Fatura f: this.faturas)
        {
            System.out.println(f.toString());
        }
        System.out.print("Prima enter para continuar ..."); read.nextLine();
    }

    /**
     * Método que permite ao administrador ver todos os Contribuintes registados do Sistema.
     * @param
     * @return
     */
    public void mostrar_Contribuintes_Administrador()
    {
        Scanner read = new Scanner(System.in);
        System.out.println("Contribuintes registados no Sistema:\n");
        for(Contribuinte c: this.registados.values())
        {
            System.out.println(c.toString());
        }
        System.out.print("Prima enter para continuar ..."); read.nextLine();
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
            for(int i: this.registados.get(c.getNIF()).getIndex())
            {
                res += this.faturas.get(i).getValor_Despesa();
            }
        }
        else if(c instanceof Coletivo)
        {
            for(int i: this.registados.get(c.getNIF()).getIndex())
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
        
        if(this.registados.values().size() < 10)
        {
            System.out.print("De momento não é possivel estabelecer a relação pedida por si, dado que não existem no mínimo 10 contribuintes no Sistema.\n");
        }
        else
        {
            System.out.println("TOP 10 Contribuintes com mais gastos no Sistema:\n");
            for(Contribuinte c: top)
            {
                if(i == 10) break;
                if(c instanceof Individual)
                {
                    System.out.printf("Contribuinte Individual %s, com NIF %s: %.2f €.\n", c.getNome(), c.getNIF(), gasto_Contribuinte(c));
                }
                else if(c instanceof Coletivo)
                {
                    System.out.printf("Empresa / Instituição %s, com NIF %s: %.2f €.\n", c.getNome(), c.getNIF(), gasto_Contribuinte(c));
                }
                i += 1;
            }
        }
        System.out.print("\nPrima enter para continuar ..."); read.nextLine();
    }

    /**
     * Método que conta o nº de faturas emitidas por uma determinada empresa.
     * @param Coletivo c, ou seja, uma empresa.
     * @return int i, ou seja, nº de faturas.
     */
    private int conta_faturas_emitidas_CC(Coletivo c)
    {
        int res = 0;
        for(int i: this.registados.get(c.getNIF()).getIndex())
        {
            if(this.faturas.get(i).getNIF_Emitente().equals(c.getNIF()))
            {
                res += 1;
            }
        }
        return res;
    }
    
    /**
     * Método que conta o nº empresas no Sistema.
     * @param
     * @return int i.
     */
    private int conta_numero_CC()
    {
        int res = 0;
        for(Contribuinte c: this.registados.values())
        {
            if(c instanceof Coletivo)
            {
                res += 1;
            }
        }
        return res;
    }    

    /**
     * Método que determina a relação das X empresas que mais faturas têm em todo o sistema 
     * e determina o montante de deduções fiscais que as despesas registadas (dessas empresas) representam.
     * @param
     * @return
     */
    public void relaçao_X_CC()
    {
        int i = 0;
        Scanner read = new Scanner(System.in); Scanner ler = new Scanner(System.in);
        System.out.print("Nº de empresas: "); int n = read.nextInt();

        TreeSet<Coletivo> top = new TreeSet<Coletivo>(new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                Coletivo c1 = (Coletivo) o1;
                Coletivo c2 = (Coletivo) o2;
                return conta_faturas_emitidas_CC(c1) > conta_faturas_emitidas_CC(c2) ? -1 : 1;
            }
        });

        for(Contribuinte c: this.registados.values())
        {
            if(c instanceof Coletivo)
            {
                top.add(((Coletivo) c).clone());
            }
        }
        
        if(n > conta_numero_CC())
        {
            System.out.print("\nDe momento não é possivel estabelecer a relação pedida por si, dado que não existem no mínimo " + n + " empresa(s) / instituição(ões) no Sistema.\n");
        }
        else
        {
            System.out.println("\nTOP " + n + " Empresas / Instituições com mais faturas emitidas no Sistema:\n");
            for(Coletivo c: top)
            {
                if(i == n) break;
                System.out.printf("Empresa / Instituição %s, com NIF %s: ---> %d faturas emitidas; ---> Dedução fiscal acumulada: %.2f €.\n", c.getNome(), c.getNIF(), conta_faturas_emitidas_CC(c), calcular_deduçao_fiscal_CC(c));
                i += 1;
            }
        }
        System.out.print("\nPrima enter para continuar ..."); ler.nextLine();
    }

    /**
     * Método que calcula o montante de dedução fiscal de um contribuinte Individual(individualmente (fazendo numero_af = 1) ou pelo agregado familiar (fazendo numero_af = nº de elementos do agregado familiar.).
     * @param int numero_af
     * @return
     */
    public void calcular_deduçao_fiscal_CI(int numero_af)
    {
        Scanner ler = new Scanner(System.in);
        Individual i = ((Individual) this.registados.get(this.contribuinte.getNIF())).clone();
        if (numero_af >= 5) numero_af = 5;
        double percentagem = 0; double maximo_valor = 0; double valor_deduzido = 0; double res = 0;

        for(String s : i.getAtividades_Economicas().keySet())
        {
            valor_deduzido = i.getAtividades_Economicas().get(s);
            percentagem = this.atividades_economicas_disponiveis.get(s)[0];
            if(s.compareTo("Despesas gerais familiares") == 0) maximo_valor = this.atividades_economicas_disponiveis.get(s)[1] * numero_af;
            else maximo_valor = this.atividades_economicas_disponiveis.get(s)[1];
            if(valor_deduzido <= maximo_valor)
            {
                res += valor_deduzido * (percentagem / 100);
                res += res * (this.descontos_ag.clone()[numero_af - 1] / 100);
            }
            else
            {
                res += maximo_valor;
                res += res * (this.descontos_ag.clone()[numero_af - 1] / 100);
            }
        }
        if(numero_af == 1)
        {
            System.out.printf("Montante de dedução fiscal acumulado por %s, com NIF %s: %.2f €.", i.getNome(), i.getNIF(), res);
        }
        else
        {
            System.out.printf("Montante de dedução fiscal acumulado pelo seu agregado familiar: %.2f €", res);
        }
        System.out.print("\n\nPrima enter para continuar ..."); ler.nextLine();
    }

    /**
     * Método que calcula o montante de dedução fiscal de um contribuinte coletivo.
     * @param
     * @return
     */
    public double calcular_deduçao_fiscal_CC(Coletivo c)
    {
        Scanner ler = new Scanner(System.in);
        double percentagem = 0; double maximo_valor = 0; double valor_deduzido = 0; double res = 0;

        for(String s : c.getAtividades_Economicas().keySet())
        {
            valor_deduzido = c.getAtividades_Economicas().get(s);
            percentagem = this.atividades_economicas_disponiveis.get(s)[0];
            maximo_valor = this.atividades_economicas_disponiveis.get(s)[1];
            if(valor_deduzido <= maximo_valor)
            {
                res += valor_deduzido * (percentagem / 100);
            }
            else
            {
                res += maximo_valor;
            }
        }
        return res;
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