/**
 * Classe Contribuintes_Individuais.
 * 
 * @author P.O.O. - Project - 2017/2018 
 * @version 1.0
 */
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Contribuintes_Individuais extends Contribuintes
{
    // Variáveis de instância
    private int agregado_familiar; /* Nº de elementos do agregado familiar */
    private List<Integer> NIFs_agregado_familiar; /* Array com os NIF's de cada elemento do agregado familiar */
    private double coeficiente_fiscal; /* factor multiplicativo que é associado a cada despesa elegível */
    private List<String> atividades_economicas; /* Ativididades Económicas */

    /**
     * Construtor por omissão da classe Contribuintes_Individuais.
     * @param
     * @return
     */
    public Contribuintes_Individuais()
    {
        super();
        this.agregado_familiar = 0;
        this.NIFs_agregado_familiar = new ArrayList<>();
        this.coeficiente_fiscal = 0;
        this.atividades_economicas = new ArrayList<>();
    }
    
    /**
     * Construtor parametrizado da classe Contribuintes_Individuais.
     * @param NIF
     * @param email
     * @param nome
     * @param morada
     * @param password
     * @param agregado_familiar
     * @param NIFs_agregado_familiar
     * @param coeficiente_fiscal
     * @param atividades_economicas
     * @return
     */
    public Contribuintes_Individuais(int NIF_p, String email_p, String nome_p, String morada_p, String password_p, int agregado_familiar_p, List<Integer> NIFs_agregado_familiar_p, double coeficiente_fiscal_p, List<String> ae_p)
    {
        super(NIF_p, email_p, nome_p, morada_p, password_p);
        this.agregado_familiar = agregado_familiar_p;
        setNIFS_AGREGADO_FAMILIAR(NIFs_agregado_familiar_p);
        this.coeficiente_fiscal = coeficiente_fiscal_p;
        setATIVIDADES_ECONOMICAS(ae_p);
    }
    
    /**
     * Construtor de cópia da classe Contribuintes_Individuais.
     * @param Contribuintes_Individuais umContribuinte_Individual
     * @return
     */
    public Contribuintes_Individuais(Contribuintes_Individuais umContribuinte_Individual)
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
    public List getNIFS_AGREGADO_FAMILIAR()
    {
        List<Integer> nova = new ArrayList<>(this.getAGREGADO_FAMILIAR());
        for(int nif: this.NIFs_agregado_familiar)
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
    public void setNIFS_AGREGADO_FAMILIAR(List<Integer> NIFs_agregado_familiar_p)
    {
        this.NIFs_agregado_familiar = new ArrayList<Integer>(this.agregado_familiar);
        for(int nif: NIFs_agregado_familiar_p)
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
     * Método que verifica se a classe Contribuintes_Individuais d é igual à classe Contribuintes_Individuais que recebe a mensagem.
     * @param Object
     * @return boolean
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Contribuintes_Individuais c = (Contribuintes_Individuais) o;
        return (super.equals(c) && this.agregado_familiar == c.getAGREGADO_FAMILIAR()
                                && this.NIFs_agregado_familiar.equals(c.getNIFS_AGREGADO_FAMILIAR())
                                && this.coeficiente_fiscal == c.getCOEFICIENTE_FISCAL());
    }
    
    /**
     * Método que devolve a representação em String da classe Contribuintes_Individuais.
     * @param
     * @return String.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Contribuinte Individual:\n");
        sb.append("NIF: ").append(super.getNIF()).append(", ");
        sb.append("Email: ").append(super.getEMAIL()).append(", ");
        sb.append("Nome: ").append(super.getNOME()).append(", ");
        sb.append("Morada: ").append(super.getMORADA()).append(", ");
        sb.append("Password: ").append(super.getPASSWORD()).append(", ");
        sb.append("Nº de elementos do Agregado Familiar: ").append(this.getAGREGADO_FAMILIAR()).append(", ");
        sb.append("NIF's do Agregado Familiar: ").append(this.getNIFS_AGREGADO_FAMILIAR().toString()).append(", ");
        sb.append("Atividades Económicas: ").append(this.atividades_economicas.toString()).append(", ");
        sb.append("Coeficiente Fiscal: ").append(this.getCOEFICIENTE_FISCAL()).append("\n");
        return sb.toString();
    }
    
    /**
     * Método que faz uma cópia da classe Contribuintes_Individuais receptora da mensagem.
     * Para tal invoca o construtor de cópia.
     * @param
     * @return Contribuintes_Individuais clone da classe Contribuintes_Individuais que recebe a mensagem.
     */
    public Contribuintes_Individuais clone()
    {
        return new Contribuintes_Individuais(this);
    }
}
