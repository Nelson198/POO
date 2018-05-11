/**
 * Classe Individual.
 * 
 * @author P.O.O. - Project - 2017/2018 
 * @version 1.0
 */
import java.util.List;
import java.util.ArrayList;

public class Individual extends Contribuinte
{
    // Variáveis de instância
    private int agregado_familiar;               /* Nº de elementos do agregado familiar */
    private List<String> NIFs_agregado_familiar; /* Array com os NIF's de cada elemento do agregado familiar */
    private double coeficiente_fiscal;           /* factor multiplicativo que é associado a cada despesa elegível */
    private List<String> atividades_economicas;  /* Ativididades Económicas */

    /**
     * Construtor por omissão da classe Individual.
     * @param
     * @return
     */
    public Individual()
    {
        super();
        this.agregado_familiar = 0;
        this.NIFs_agregado_familiar = new ArrayList<>();
        this.coeficiente_fiscal = 0;
        this.atividades_economicas = new ArrayList<>();
    }
    
    /**
     * Construtor parametrizado da classe Individual.
     * @param NIF
     * @param email
     * @param nome
     * @param morada
     * @param password
     * @param index
     * @param agregado_familiar
     * @param NIFs_agregado_familiar
     * @param coeficiente_fiscal
     * @param atividades_economicas
     * @return
     */
    public Individual(String NIF_p, String email_p, String nome_p, String morada_p, String password_p, List<Integer> index_p,
                      int agregado_familiar_p, List<String> NIFs_agregado_familiar_p, double coeficiente_fiscal_p, List<String> ae_p)
    {
        super(NIF_p, email_p, nome_p, morada_p, password_p, index_p);
        this.agregado_familiar = agregado_familiar_p;
        setNIFS_AGREGADO_FAMILIAR(NIFs_agregado_familiar_p);
        this.coeficiente_fiscal = coeficiente_fiscal_p;
        setATIVIDADES_ECONOMICAS(ae_p);
    }
    
    /**
     * Construtor de cópia da classe Individual.
     * @param Individual umContribuinte_Individual
     * @return
     */
    public Individual(Individual umContribuinte_Individual)
    {
        super(umContribuinte_Individual);
        this.agregado_familiar = umContribuinte_Individual.getAGREGADO_FAMILIAR();
        this.NIFs_agregado_familiar = umContribuinte_Individual.getNIFS_AGREGADO_FAMILIAR();
        this.coeficiente_fiscal = umContribuinte_Individual.getCOEFICIENTE_FISCAL();
        this.atividades_economicas = umContribuinte_Individual.getATIVIDADES_ECONOMICAS();
    }
    
    /**
     * Devolve o nº de elementos do agregado familiar do contribuinte individual.
     * @param
     * @return agreagado_familiar
     */
    public int getAGREGADO_FAMILIAR()
    {
        return this.agregado_familiar;
    }
    
    /**
     * Atualiza o nº de elementos do agregado familiar do contribuinte individual.
     * @param agreagado_familiar
     * @return
     */
    public void setAGREGADO_FAMILIAR(int agregado_familiar_p)
    {
        this.agregado_familiar = agregado_familiar_p;
    }
    
    /**
     * Devolve o array com os NIF's dos elementos do agregado familiar do contribuinte individual.
     * @param
     * @return NIFs_agregado_familiar
     */
    public List<String> getNIFS_AGREGADO_FAMILIAR()
    {
        List<String> nova = new ArrayList<>(this.getAGREGADO_FAMILIAR());
        for(String nif: this.NIFs_agregado_familiar)
        {
            nova.add(nif);
        }
        return nova;
    }
    
    /**
     * Atualiza o array com os NIF's dos elementos do agregado familiar do contribuinte individual.
     * @param NIFs_agregado_familiar
     * @return
     */
    public void setNIFS_AGREGADO_FAMILIAR(List<String> NIFs_agregado_familiar_p)
    {
        this.NIFs_agregado_familiar = new ArrayList<String>(this.agregado_familiar);
        for(String nif: NIFs_agregado_familiar_p)
        {
            this.NIFs_agregado_familiar.add(nif);
        }
    }

    /**
     * Devolve o coeficiente fiscal do contribuinte individual.
     * @param
     * @return coeficiente_fiscal
     */
    public double getCOEFICIENTE_FISCAL()
    {
        return this.coeficiente_fiscal;
    }
    
    /**
     * Atualiza o coeficiente fiscal do contribuinte individual.
     * @param coeficiente_fiscal
     * @return
     */
    public void setCOEFICIENTE_FISCAL(double coeficiente_fiscal_p)
    {
        this.coeficiente_fiscal = coeficiente_fiscal_p;
    }
    
    /**
     * Devolve a lista das atividades económicas do contribuinte individual.
     * @param
     * @return atividades_economicas
     */
    public List<String> getATIVIDADES_ECONOMICAS()
    {
        List<String> nova = new ArrayList<>();
        for(String s: this.atividades_economicas)
        {
            nova.add(s);
        }
        return nova;
    }
    
    /**
     * Atualiza a lista das atividades económicas do contribuinte individual.
     * @param atividades_economicas
     * @return
     */
    public void setATIVIDADES_ECONOMICAS(List<String> ae_p)
    {
        this.atividades_economicas= new ArrayList<>();
        for(String s: ae_p)
        {
            this.atividades_economicas.add(s);
        }
    }

    /**
     * Método que verifica se a classe Individual d é igual à classe Individual que recebe a mensagem.
     * @param Object
     * @return boolean
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Individual c = (Individual) o;
        return (super.equals(c) && this.agregado_familiar == c.getAGREGADO_FAMILIAR()
                                && this.NIFs_agregado_familiar.equals(c.getNIFS_AGREGADO_FAMILIAR())
                                && this.coeficiente_fiscal == c.getCOEFICIENTE_FISCAL());
    }
    
    /**
     * Método que devolve a representação em String da classe Individual.
     * @param
     * @return String.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Contribuinte Individual: ");
        sb.append("(NIF: ").append(super.getNIF()).append(", ");
        sb.append("Email: ").append(super.getEmail()).append(", ");
        sb.append("Nome: ").append(super.getNome()).append(", ");
        sb.append("Morada: ").append(super.getMorada()).append(", ");
        sb.append("Password: ").append(super.getPassword()).append(", ");
        sb.append("Indices das faturas associadas: ").append(this.getIndex()).append(", ");
        sb.append("Nº de elementos do Agregado Familiar: ").append(this.getAGREGADO_FAMILIAR()).append(", ");
        sb.append("NIF's do Agregado Familiar: ").append(this.getNIFS_AGREGADO_FAMILIAR().toString()).append(", ");
        sb.append("Atividades Económicas: ").append(this.atividades_economicas.toString()).append(", ");
        sb.append("Coeficiente Fiscal: ").append(this.getCOEFICIENTE_FISCAL()).append(")\n");
        return sb.toString();
    }
    
    /**
     * Método que faz uma cópia da classe Individual receptora da mensagem.
     * Para tal invoca o construtor de cópia.
     * @param
     * @return Individual clone da classe Individual que recebe a mensagem.
     */
    public Individual clone()
    {
        return new Individual(this);
    }
}
