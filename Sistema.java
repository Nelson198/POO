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
    private String nif_contribuinte;                                                   /* NIF do Contribuinte que se encontra dentro do Sistema */
    private Map<String, Contribuinte> registados;                                      /* Dicionário que associa o NIF do Contribuinte - Chave - à sua informação - Valor */
    private List<Fatura> faturas;                                                      /* Lista com as faturas registadas */
    private Map<String, double[]> atividades_economicas_disponiveis;                   /* Dicionário Chave -> Atividade Económica; Valor -> [% de desconto, valor máximo de desconto] */
    private List<List<String>> agregados;                                              /* List com todos os agregados familiares do sistema */
    private List<String> concelhos_fiscais;                                            /* List com os concelhos com benefícios fiscais */

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
        this.nif_contribuinte = "N/D";
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
        this.atividades_economicas_disponiveis.put("Outros", new double[] {0.0, 0.0});

        this.concelhos_fiscais = new ArrayList<>();
        this.concelhos_fiscais.add("Cabeceiras de Basto");
        this.concelhos_fiscais.add("Évora");
        this.concelhos_fiscais.add("Vilar Formoso");
        this.concelhos_fiscais.add("Chaves");
        this.concelhos_fiscais.add("Bragança");
        this.concelhos_fiscais.add("Celorico de Basto");
        this.concelhos_fiscais.add("Fundão");
        this.concelhos_fiscais.add("Covilhã");
        this.concelhos_fiscais.add("Reguengos de Monsaraz");
        this.concelhos_fiscais.add("Beja");
        this.concelhos_fiscais.add("Mourão");
        this.concelhos_fiscais.add("Marvão");
        this.concelhos_fiscais.add("Pedrógão Grande");
        this.concelhos_fiscais.add("Guarda");
        this.concelhos_fiscais.add("Macedo de Cavaleiros");
        this.concelhos_fiscais.add("Castelo de Vide");
        
        this.agregados = new ArrayList<>();
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
     * Método que devolve o NIF do Contribuinte (Individual ou Coletivo) que se encontra dentro do Sistema.
     * @param
     * @return NIF Contribuinte que se encontra dentro do Sistema.
     */
    public String getNIF_Contribuinte()
    {
        return this.nif_contribuinte;
    }
    
    /**
     * Método que devolve o dicionário de todos os Contribuinte registados no Sistema.
     * @param
     * @return Map<String, Contribuinte> com os Contribuinte registados no Sistema.
     */
    public Map<String, Contribuinte> getRegistados()
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
            this.nif_contribuinte = username;
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
        this.nif_contribuinte = "N/D";
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
        List<String> nifs = new ArrayList<>(); 
        List<Integer> index = new ArrayList<>();
        Map<String, Double> ats = new HashMap<>();
        String nif, email, nome, morada, password, at, s, numero; 
        boolean isNumeric, bool;
        int numero_ag, dependentes, index_agregado = -1;
        double cf;
        Scanner read = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Individual:\n");
        do{
            System.out.print("NIF --> "); nif = read.nextLine();
            isNumeric = nif.chars().allMatch(Character::isDigit);
        }while(nif.length() != 9 || isNumeric == false || nif.indexOf('1') != 0 && nif.indexOf('2') != 0);
        
        do{
            System.out.print("Email --> "); email = read.nextLine();
        }while(email.indexOf('@') == -1 || email.indexOf('.') == -1);

        do{
            System.out.print("Nome --> "); nome = read.nextLine();
        }while(nome.length() == 0);
        
        do{
            System.out.print("Morada --> "); morada = read.nextLine();
        }while(morada.length() == 0);
        
        do{
            System.out.print("Password --> "); password = read.nextLine();
        }while(password.length() == 0);
        
        do{
            System.out.print("Nº de pessoas do agregado familiar --> "); numero = read.nextLine();
            isNumeric = numero.chars().allMatch(Character::isDigit);
            numero_ag = Integer.parseInt(numero);
        }while(numero_ag < 1 || isNumeric == false);

        do {
            System.out.print("Nº de dependentes (ou seja, nº de filho(s)) --> "); numero = read.nextLine();
            isNumeric = numero.chars().allMatch(Character::isDigit);
            dependentes = Integer.parseInt(numero);
        }while(numero_ag <= dependentes || isNumeric == false);
        
        do{
            bool = true;
            if(numero_ag == 1) {
                System.out.println("NIF 1: " + nif); 
                nifs.add(nif);
            }
            else
            {
                System.out.println("NIF 1: " + nif); 
                nifs.add(nif);

                for(int i = 2; i <= numero_ag; i++)
                {
                    System.out.printf("NIF %d: ", i);
                    s = read.nextLine();
                    nifs.add(s);
                }

                if(!nifs.contains(nif))
                {
                    bool = false; 
                    nifs.clear();
                } else {
                    for(String a: nifs)
                    {
                        if((a.chars().allMatch(Character::isDigit)) == false || a.length() != 9)
                        {
                            bool = false;
                            nifs.clear();
                            break;
                        }
                    }
                }

                if(!this.agregados.contains(nifs)) {
                    this.agregados.add(nifs);
                    index_agregado = this.agregados.size()-1;
                } else {
                    index_agregado = this.agregados.indexOf(nifs);
                }
            }
        }while(bool == false);
        
        do{
            for(String t: this.atividades_economicas_disponiveis.keySet())
            {
                System.out.print("Atividade Económica: " + t + " (S/N)? "); at = read.nextLine();
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
            System.out.print("Coeficiente Fiscal --> "); numero = read.nextLine();
            isNumeric = numero.chars().allMatch(Character::isDigit);
            cf = Double.parseDouble(numero);
        }while(cf <= 0 || isNumeric == false);

        read.close();
        Individual ci = new Individual(nif, email, nome, morada, password, index, numero_ag, dependentes, nifs, cf, ats, index_agregado);
        
        if(!this.registados.containsKey(nif))
        {
            this.registados.put(nif, ci.clone());
            System.out.print("\nA registar ..."); time(1000);
            System.out.print("\nContribuinte registado com sucesso!"); time(1000);
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
        Map<String, Double> avs = new HashMap<>();
        String nif, email, nome, morada, password, at, concelho, numero;
        boolean isNumeric, interior;
        double cf;
        Scanner read = new Scanner(System.in);
        
        System.out.print("Registar Contribuinte Coletivo / Empresa:\n");
        do{
            System.out.print("NIF --> "); nif = read.nextLine();
            isNumeric = nif.chars().allMatch(Character::isDigit);
        }while(nif.length() != 9 || isNumeric == false || nif.indexOf('5') != 0);
        
        do{
            System.out.print("Email --> "); email = read.nextLine();
        } while(email.indexOf('@') == -1 || email.indexOf('.') == -1);

        do{
            System.out.print("Nome --> "); nome = read.nextLine();
        }while(nome.length() == 0);
        
        do{
            System.out.print("Morada --> "); morada = read.nextLine();
        }while(morada.length() == 0);
        
        do{
            System.out.print("Password --> "); password = read.nextLine();
        }while(password.length() == 0);
        
        do{
            for(String t: this.atividades_economicas_disponiveis.keySet())
            {
                if(!t.equals("Despesas gerais familiares"))
                {
                    System.out.print("Atividade Económica: " + t + " (S/N)? "); at = read.nextLine();
                    if(at.equals("S") || at.equals("s"))
                    {
                        ats.put(t, 0.0);
                        avs.put(t, 0.0);

                    }
                    else if(at.equals("N") || at.equals("n"))
                    {}
                    else
                    {
                        System.out.print("Erro: Dados inválidos!"); time(1500);
                        return;
                    }
                }
            }
        }while(ats.size() == 0);
        
        do{
            System.out.print("Coeficiente Fiscal --> "); numero = read.nextLine();
            isNumeric = numero.chars().allMatch(Character::isDigit);
            cf = Double.parseDouble(numero);
        }while(cf <= 0 || isNumeric == false);

        do{
            System.out.print("Concelho: "); concelho = read.nextLine();
            for (String s: this.concelhos_fiscais)
            {
                if(s.compareTo(concelho) == 0) {
                    interior = true;
                }
            }
            interior = false;
        }while(concelho.length() == 0);

        read.close();
        Coletivo cc = new Coletivo(nif, email, nome, morada, password, index, ats, avs, cf, interior);
        
        if(!this.registados.containsKey(nif))
        {
            this.registados.put(nif, cc.clone());
            System.out.print("\nA registar ..."); time(1000);
            System.out.print("\nContribuinte registado com sucesso!"); time(1000);
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
        Coletivo c = ((Coletivo) this.registados.get(this.nif_contribuinte)).clone();
        StringBuilder sb = new StringBuilder();
        List<String> at = new ArrayList<>();
        boolean isNumeric;
        double valor, aux;
        String nif_ci, descriçao, atividades, numero;
        Scanner read = new Scanner(System.in);
        
        System.out.print("Submissão de Fatura associada a uma despesa:\n");
        System.out.print("\nNIF do emitente --> "); String nif_e = this.nif_contribuinte; System.out.print(nif_e);
        System.out.print("\nNome do emitente --> "); String nome_e = this.registados.get(nif_e).getNome(); System.out.print(nome_e);
        System.out.print("\n");

        if(c.getAtividades_Economicas().size() == 1)
        {
            at.add((String) c.getAtividades_Economicas().keySet().toArray()[0]);
            sb.append((String) c.getAtividades_Economicas().keySet().toArray()[0]);
            System.out.println("Atividade económica da Empresa --> " + sb.toString());
        }
        else
        {
            for(String s: c.getAtividades_Economicas().keySet())
            {
                at.add(s);
                sb.append(s).append(", ");
            }
            String res = sb.toString(); res = res.substring(0, res.length()-2); 
            System.out.println("Atividades económicas da Empresa --> " + res);
        }
        
        do
        {
            System.out.print("NIF do cliente --> "); nif_ci = read.nextLine();
            isNumeric = nif_ci.chars().allMatch(Character::isDigit); //Verificar se a string NIF é numérica.
        }while(!this.registados.containsKey(nif_ci) || nif_e.equals(nif_ci) || nif_ci.length() != 9 || isNumeric == false || (nif_ci.indexOf('1') != 0 && nif_ci.indexOf('2') != 0 && nif_ci.indexOf('5') != 0));

        do
        {
            System.out.print("Descrição da despesa --> "); descriçao = read.nextLine();
        }while(descriçao.length() == 0);
        
        do
        {
            System.out.print("Valor da despesa --> "); numero = read.nextLine();
            valor = Double.parseDouble(numero);
        }while(valor <= 0);
        
        System.out.print("Data / Hora da despesa --> "); 
        LocalDateTime data_hora = LocalDateTime.now(); 
        System.out.print(data_hora.toString());
        read.close();

        Fatura f = new Fatura(nif_e, nome_e, data_hora, nif_ci, descriçao, at, valor, false, -1);
        
        if(!this.faturas.contains(f))
        {
            if(at.size() >= 2) f.setPendente(true);
            else if(at.size() == 1) f.setIndice(0);
            
            this.faturas.add(f.clone());
        
            List<Integer> cc = this.registados.get(nif_e).getIndex();
            cc.add(this.faturas.indexOf(f));
            this.registados.get(nif_e).setIndex(cc);

            List<Integer> cliente = this.registados.get(nif_ci).getIndex();
            cliente.add(this.faturas.indexOf(f));
            this.registados.get(nif_ci).setIndex(cliente);
            
            if(at.size() == 1)
            {
                if(this.registados.get(nif_ci) instanceof Individual) {
                    acumular_valor_despesa_CI(nif_ci, at.get(0), valor);
                }
                else if(this.registados.get(nif_ci) instanceof Coletivo) {
                    acumular_valor_despesa_CC(nif_ci, at.get(0), valor);
                }

                acumular_vendas_CC(f.getNIF_Emitente(), at.get(0), valor);

                System.out.print("\n\nA fatura foi submetida com sucesso no Sistema!"); time(1000);
            }
            else if(at.size() >= 2)
            {
                System.out.print("\n\nA fatura foi submetida com sucesso no Sistema!\nFatura pendente de validação por parte do Contribuinte!"); time(2000);
            }
        }
        else System.out.print("\nAviso: Esta fatura já foi submetida anteriormente."); time(2000);
    }
    
    /**
     * Método que trata de validar faturas pendentes por parte dos Contribuintes.
     * @param
     * @return
     */
    public void validar_faturas_pendentes()
    {
        String option;
        Scanner read = new Scanner(System.in);
        
        for(int i: this.registados.get(this.nif_contribuinte).getIndex())
        {
            if((this.faturas.get(i).getPendente() && this.faturas.get(i).getNIF_Cliente().equals(this.nif_contribuinte)) ||
               (!this.faturas.get(i).getPendente() && this.faturas.get(i).getNatureza_Despesa().size() >= 2 && this.faturas.get(i).getNIF_Cliente().equals(this.nif_contribuinte)))
            {
                System.out.println("---> Fatura por validar:\n");
                System.out.println(this.faturas.get(i).toString());

                for(String s : this.faturas.get(i).getNatureza_Despesa())
                {
                    System.out.print("Deseja associar a esta despesa a atividade económica " + s + "? (S/N): "); option = read.nextLine();

                    if(option.equals("S") || option.equals("s"))
                    {
                        this.faturas.get(i).setPendente(false);
                        this.faturas.get(i).setIndice(this.faturas.get(i).getNatureza_Despesa().indexOf(s));

                        if(this.registados.get(this.nif_contribuinte) instanceof Individual)
                        {
                            acumular_valor_despesa_CI(this.faturas.get(i).getNIF_Cliente(), s, this.faturas.get(i).getValor_Despesa());
                        }
                        else if(this.registados.get(this.nif_contribuinte) instanceof Coletivo)
                        {
                            acumular_valor_despesa_CC(this.faturas.get(i).getNIF_Cliente(), s, this.faturas.get(i).getValor_Despesa());
                        }

                        acumular_vendas_CC(this.faturas.get(i).getNIF_Emitente(), s, this.faturas.get(i).getValor_Despesa());

                        System.out.print("\nA fatura foi validada com sucesso no Sistema!\n\n"); time(1000);
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
            else if(index == i.getAtividades_Economicas().keySet().size() && i.getAtividades_Economicas().containsKey("Outros"))
            {
                d = i.getAtividades_Economicas().get("Outros");
                map = i.getAtividades_Economicas();
                map.put("Outros", d + despesa);
                ((Individual) this.registados.get(nif_ci)).setAtividades_Economicas(map);
                break;
            }
            else index += 1;
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
    
    /**
     * Método que acumula o valor das vendas de uma empresa, por setor de atividade económica.
     * 
     * @param NIF - da empresa
     * @param at - Atividade económica a acumular venda
     * @param valor - da venda
     * @return
     */
    
    public void acumular_vendas_CC(String NIF, String at, double valor)
    {
        Coletivo c = (Coletivo) this.registados.get(NIF);
        Map<String, Double> nova = c.getAcumulado_Vendas();
        for(String s: nova.keySet())
        {
            if(s.compareTo(at) == 0) {
                nova.get(s) += valor;
                break;
            }
        }
        c.setAcumulado_Vendas(nova);
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
        }while(!this.registados.containsKey(nif) || nif.equals(this.nif_contribuinte));
        
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
            if(this.faturas.get(i).getNIF_Emitente().equals(this.nif_contribuinte) && this.faturas.get(i).getData_Hora().isAfter(inicio) && this.faturas.get(i).getData_Hora().isBefore(fim))
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
        }while(!this.registados.containsKey(nif) || nif.equals(this.nif_contribuinte));
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
            if(this.faturas.get(i).getNIF_Emitente().equals(this.nif_contribuinte))
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
        for(int i: this.registados.get(this.nif_contribuinte).getIndex())
        {
            if(this.faturas.get(i).getNIF_Emitente().equals(this.nif_contribuinte) && this.faturas.get(i).getData_Hora().isAfter(inicio) && this.faturas.get(i).getData_Hora().isBefore(fim))
            {
                res += this.faturas.get(i).getValor_Despesa();
            }
        }
        System.out.printf("Total faturado pela Empresa / Instituição %s: %.2f €.", this.registados.get(this.nif_contribuinte).getNome(), res);
        System.out.print("\nPrima enter para continuar ..."); read.nextLine();
    }
    
    /**
     * Método que obter a listagem das facturas de um determinado Contribuinte (Individual / Coletivo), ordenada por data de emissão (mais recente -> mais antiga).
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
        
        for(int i: this.registados.get(this.nif_contribuinte).getIndex())
        {
            res.add(this.faturas.get(i).clone());
        }

        if(this.registados.get(this.nif_contribuinte) instanceof Individual)
        {
            System.out.print("Listagem de Faturas do Contribuinte Individual " + this.registados.get(this.nif_contribuinte).getNome() + " ordenada por data de emissão:\n\n");
        }
        else if (this.registados.get(this.nif_contribuinte) instanceof Coletivo)
        {
            System.out.print("Listagem de Faturas da empresa " + this.registados.get(this.nif_contribuinte).getNome() + " ordenada por data de emissão:\n\n");
        }

        for(Fatura f: res)
        {
            System.out.println(f.toString());
        }
        System.out.print("Prima enter para continuar ..."); read.nextLine();
    }
    
    /**
     * Método que obter a listagem das facturas de um determinado Contribuinte (Individual / Coletivo), ordenada por valor crescente de despesa.
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

        for(int i: this.registados.get(this.nif_contribuinte).getIndex())
        {
            res.add(this.faturas.get(i).clone());
        }

        if(this.registados.get(this.nif_contribuinte) instanceof Individual)
        {
            System.out.print("Listagem de Faturas do Contribuinte Individual " + this.registados.get(this.nif_contribuinte).getNome() + " ordenada por valor crescente de despesa:\n\n");
        }
        else if (this.registados.get(this.nif_contribuinte) instanceof Coletivo)
        {
            System.out.print("Listagem de Faturas da empresa " + this.registados.get(this.nif_contribuinte).getNome() + " ordenada por valor crescente de despesa:\n\n");
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
        System.out.println("Faturas do contribuinte " + this.registados.get(this.nif_contribuinte).getNome() + ", com NIF " + this.registados.get(this.nif_contribuinte).getNIF() + ":\n");
        for(int i: this.registados.get(this.nif_contribuinte).getIndex())
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
        System.out.println("Faturas emitidas pela empresa " + this.registados.get(this.nif_contribuinte).getNome() + ", com NIF " + this.registados.get(this.nif_contribuinte).getNIF() + ":\n");
        for(int i: this.registados.get(this.nif_contribuinte).getIndex())
        {
            if(this.faturas.get(i).getNIF_Emitente().equals(this.nif_contribuinte))
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
        System.out.println("Faturas de despesas feitas pela empresa " + this.registados.get(this.nif_contribuinte).getNome() + ", com NIF " + this.registados.get(this.nif_contribuinte).getNIF() + ":\n");
        for(int i: this.registados.get(this.nif_contribuinte).getIndex())
        {
            if(this.faturas.get(i).getNIF_Cliente().equals(this.nif_contribuinte))
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
            System.out.print(f.show());
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
                System.out.printf("Empresa / Instituição %s, com NIF %s: ---> %d faturas emitidas; ---> Dedução fiscal: %.2f €.\n", c.getNome(), c.getNIF(), conta_faturas_emitidas_CC(c), calcular_deduçao_fiscal_CC(c));
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
    public void calcular_deduçao_fiscal_CI(String n)
    {
        double percentagem = 0; 
        double maximo_valor = 0; 
        double valor_total = 0; 
        double valor_deduzido = 0;
        Individual i = (Individual) this.getRegistados().get(n);
        Map<String, Double> sats = i.getAtividades_Economicas();

        System.out.println("/n"+ i.getNome() + ", NIF: " + n + ":");

        for(String s: this.atividades_economicas_disponiveis.keySet())
        {
            if (s.compareTo("Outros") == 0) {
                System.out.println(s + ": Não dedutível.");
            }

            if(sats.containsKey(s)) {
                percentagem = this.atividades_economicas_disponiveis.get(s)[0];
                maximo_valor = this.atividades_economicas_disponiveis.get(s)[1];
                valor_total = sats.get(s);
                valor_deduzido = valor_total * percentagem;

                if (valor_deduzido <= maximo_valor) {
                    System.out.println(s + ": " + valor_deduzido + "€ deduzidos.");
                }  
            } else {
                System.out.println(s + ": " + "0.0€ deduzidos.");
            }
        }
    }
    
    /**
     * Método que calcula a dedução fiscal acumulada por um agregado familiar registado em Sistema.
     * @param
     * @return
     */
    public void calcular_deduçao_fiscal_agregado(int i)
    {
        List<String> agregado = this.agregados.get(i);
        double valor = 0;

        for(String c: agregado)
        {
            calcular_deduçao_fiscal_CI(c);
        }
    }

    /**
     * Método que calcula o montante de deduções fiscais que as despesas registadas por uma empresa representam.
     * @param Contribuinte Coletivo
     * @return valor_deduzido
     */
    public double calcular_deduçao_fiscal_CC(Coletivo c)
    {
        for(String s: sats.keySet())
        {
            valor_deduçoes += sats.get(s);
        }
        return valor_deduçoes;
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