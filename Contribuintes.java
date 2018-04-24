/**
 * Classe Contribuintes.
 * 
 * @author P.O.O. - Project - 2017/2018 
 * @version 1.01
 */
public class Contribuintes
{
    // variáveis de instância
    private int NIF;
    private String email;
    private String nome;
    private String morada;
    private String password;
    
    /**
     * Construtor por omissão da classe Contribuintes.
     * @param
     * @return
     */
    public Contribuintes()
    {
        this.NIF = 0;
        this.email = "N/D";
        this.nome = "N/D";
        this.morada = "N/D";
        this.password = "N/D";
    }
    
    /**
     * Construtor parametrizado da classe Contribuintes.
     * @param NIF
     * @param email
     * @param nome
     * @param morada
     * @param password
     * @return
     */
    public Contribuintes(int NIF_p, String email_p, String nome_p, String morada_p, String password_p)
    {
        this.NIF = NIF_p;
        this.email = email_p;
        this.nome = nome_p;
        this.morada = morada_p;
        this.password = password_p;
    }
    
    /**
     * Construtor de cópia da classe Contribuintes.
     * @param Contribuintes umContribuinte
     * @return
     */
    public Contribuintes(Contribuintes umContribuinte)
    {
        this.NIF = umContribuinte.getNIF();
        this.email = umContribuinte.getEMAIL();
        this.nome = umContribuinte.getNOME();
        this.morada = umContribuinte.getMORADA();
        this.password = umContribuinte.getPASSWORD();
    }
    
    /**
     * Devolve o NIF do contribuinte da classe Contribuintes.
     * @param
     * @return NIF
     */
    public int getNIF()
    {
        return this.NIF;
    }
    
    /**
     * Atualiza o NIF do contribuinte da classe Contribuintes.
     * @param NIF
     * @return
     */
    public void setNIF(int NIF_p)
    {
        this.NIF = NIF_p;
    }
    
    /**
     * Devolve o email do contribuinte da classe Contribuintes.
     * @param
     * @return email 
     */
    public String getEMAIL()
    {
        return this.email;
    }
    
    /**
     * Atualiza o email do contribuinte da classe Contribuintes.
     * @param email
     * @return 
     */
    public void setEMAIL(String email_p)
    {
        this.email = email_p;
    }
    
    /**
     * Devolve o nome do contribuinte da classe Contribuintes.
     * @param
     * @return nome
     */
    public String getNOME()
    {
        return this.nome;
    }
    
    /**
     * Atualiza o nome do contribuinte da classe Contribuintes.
     * @param nome
     * @return 
     */
    public void setNOME(String nome_p)
    {
        this.nome = nome_p;
    }
    
    /**
     * Devolve a morada do contribuinte da classe Contribuintes.
     * @param
     * @return morada
     */
    public String getMORADA()
    {
        return this.morada;
    }
    
    /**
     * Atualiza a morada do contribuinte da classe Contribuintes.
     * @param morada
     * @return
     */
    public void setMORADA(String morada_p)
    {
        this.morada = morada_p;
    }
    
    /**
     * Devolve a password de acesso do contribuinte da classe Contribuintes.
     * @param
     * @return password
     */
    public String getPASSWORD()
    {
        return this.password;
    }
    
    /**
     * Atualiza a password de acesso do contribuinte da classe Contribuintes.
     * @param password
     * @return
     */
    public void setPASSWORD(String password_p)
    {
        this.password = password_p;
    }
    
    /**
     * Método que devolve a representação em String da classe Contribuintes.
     * @param
     * @return String
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Contribuinte:\n");
        sb.append("(NIF: ").append(getNIF()).append(", ");
        sb.append("Email: ").append(getEMAIL()).append(", ");
        sb.append("Nome: ").append(getNOME()).append(", ");
        sb.append("Morada: ").append(getMORADA()).append(", ");
        sb.append("Password de Acesso: ").append(getPASSWORD()).append(")\n");
        return sb.toString();
    }
    
    /**
     * Método que verifica se a classe Contribuintes c é igual à classe Contribuintes que recebe a mensagem.
     * @param Object
     * @return Boolean
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Contribuintes c = (Contribuintes) o;
        return (this.NIF == c.getNIF()
             && this.email.equals(c.getEMAIL())
             && this.nome.equals(c.getNOME())
             && this.morada.equals(c.getMORADA())
             && this.password.equals(c.getPASSWORD()));
    }
    
    /**
     * Método que faz uma cópia da classe Contribuintes receptora da mensagem.
     * Para tal invoca o construtor de cópia.
     * @param
     * @return Contribuintes clone da classe Contribuintes que recebe a mensagem.
     */
    public Contribuintes clone()
    {
        return new Contribuintes(this);
    }
}
