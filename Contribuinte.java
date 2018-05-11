/**
 * Classe Contribuinte.
 * 
 * @author P.O.O. - Project - 2017/2018 
 * @version 1.01
 */
import java.util.List;
import java.util.ArrayList;
public class Contribuinte
{
    // variáveis de instância
    private String NIF;
    private String email;
    private String nome;
    private String morada;
    private String password;
    private List<Integer> index;
    
    /**
     * Construtor por omissão da classe Contribuinte.
     * @param
     * @return
     */
    public Contribuinte()
    {
        this.NIF = "N/D";
        this.email = "N/D";
        this.nome = "N/D";
        this.morada = "N/D";
        this.password = "N/D";
        this.index = new ArrayList<>();
    }
    
    /**
     * Construtor parametrizado da classe Contribuinte.
     * @param NIF
     * @param email
     * @param nome
     * @param morada
     * @param password
     * @param index
     * @return
     */
    public Contribuinte(String NIF_p, String email_p, String nome_p, String morada_p, String password_p, List<Integer> index)
    {
        this.NIF = NIF_p;
        this.email = email_p;
        this.nome = nome_p;
        this.morada = morada_p;
        this.password = password_p;
        setIndex(index);
    }
    
    /**
     * Construtor de cópia da classe Contribuinte.
     * @param Contribuinte umContribuinte
     * @return
     */
    public Contribuinte(Contribuinte umContribuinte)
    {
        this.NIF = umContribuinte.getNIF();
        this.email = umContribuinte.getEmail();
        this.nome = umContribuinte.getNome();
        this.morada = umContribuinte.getMorada();
        this.password = umContribuinte.getPassword();
        this.index = umContribuinte.getIndex();
    }
    
    /**
     * Devolve o NIF do contribuinte da classe Contribuinte.
     * @param
     * @return NIF
     */
    public String getNIF()
    {
        return this.NIF;
    }
    
    /**
     * Atualiza o NIF do contribuinte da classe Contribuinte.
     * @param NIF
     * @return
     */
    public void setNIF(String NIF_p)
    {
        this.NIF = NIF_p;
    }
    
    /**
     * Devolve o email do contribuinte da classe Contribuinte.
     * @param
     * @return email 
     */
    public String getEmail()
    {
        return this.email;
    }
    
    /**
     * Atualiza o email do contribuinte da classe Contribuinte.
     * @param email
     * @return 
     */
    public void setEmail(String email_p)
    {
        this.email = email_p;
    }
    
    /**
     * Devolve o nome do contribuinte da classe Contribuinte.
     * @param
     * @return nome
     */
    public String getNome()
    {
        return this.nome;
    }
    
    /**
     * Atualiza o nome do contribuinte da classe Contribuinte.
     * @param nome
     * @return 
     */
    public void setNome(String nome_p)
    {
        this.nome = nome_p;
    }
    
    /**
     * Devolve a morada do contribuinte da classe Contribuinte.
     * @param
     * @return morada
     */
    public String getMorada()
    {
        return this.morada;
    }
    
    /**
     * Atualiza a morada do contribuinte da classe Contribuinte.
     * @param morada
     * @return
     */
    public void setMorada(String morada_p)
    {
        this.morada = morada_p;
    }
    
    /**
     * Devolve a password de acesso do contribuinte da classe Contribuinte.
     * @param
     * @return password
     */
    public String getPassword()
    {
        return this.password;
    }
    
    /**
     * Atualiza a password de acesso do contribuinte da classe Contribuinte.
     * @param password
     * @return
     */
    public void setPassword(String password_p)
    {
        this.password = password_p;
    }
    
    /**
     * Devolve a lista de indices das faturas associadas.
     * @param
     * @return res
     */
    public List<Integer> getIndex()
    {
        List<Integer> res = new ArrayList<>();
        for(int i: this.index)
        {
            res.add(i);
        }
        return res;
    }
    
    /**
     * Atualiza a lista de indices das faturas associadas.
     * @param res
     * @return
     */
    public void setIndex(List<Integer> res)
    {
        this.index = new ArrayList<>();
        for(int i: res)
        {
            this.index.add(i);
        }
    }
    
    /**
     * Método que devolve a representação em String da classe Contribuinte.
     * @param
     * @return String
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Contribuinte:\n");
        sb.append("(NIF: ").append(this.getNIF()).append(", ");
        sb.append("Email: ").append(this.getEmail()).append(", ");
        sb.append("Nome: ").append(this.getNome()).append(", ");
        sb.append("Morada: ").append(this.getMorada()).append(", ");
        sb.append("Password de Acesso: ").append(getPassword()).append(", ");
        sb.append("Lista de indices das faturas associadas: ").append(this.getIndex()).append(")\n");
        return sb.toString();
    }
    
    /**
     * Método que verifica se a classe Contribuinte c é igual à classe Contribuinte que recebe a mensagem.
     * @param Object
     * @return Boolean
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Contribuinte c = (Contribuinte) o;
        return (this.NIF.equals(c.getNIF())
             && this.email.equals(c.getEmail())
             && this.nome.equals(c.getNome())
             && this.morada.equals(c.getMorada())
             && this.password.equals(c.getPassword())
             && this.index.equals(c.getIndex()));
    }
    
    /**
     * Método que faz uma cópia da classe Contribuinte receptora da mensagem.
     * Para tal invoca o construtor de cópia.
     * @param
     * @return Contribuinte clone da classe Contribuinte que recebe a mensagem.
     */
    public Contribuinte clone()
    {
        return new Contribuinte(this);
    }
}
