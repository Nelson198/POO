/**
 * Classe dos Contribuintes Coletivos, ou seja, de Empresas.
 * 
 * @author P.O.O. - Project - 2017/2018 
 * @version 1.0
 */
import java.util.List;
import java.util.ArrayList;

public class Coletivo extends Contribuinte
{
    // Variáveis de instância
    private List<String> atividades_economicas;
    private double coeficiente_fiscal;
    
    /**
     * Construtor por omissão de Coletivo
     * @param
     * @return 
     */
    public Coletivo()
    {
        super();
        this.atividades_economicas = new ArrayList<String>();
        this.coeficiente_fiscal = 0;
    }
    
    /**
     * Construtor parametrizado de Coletivo
     * @param NIF
     * @param email
     * @param nome
     * @param morada
     * @param password
     * @param atividades_economicas
     * @param coeficiente_fiscal
     * @return
     */
    public Coletivo(String NIF_p, String email_p, String nome_p, String morada_p, String password_p, List<Integer> index_p, 
                                            List<String> atividades_economicas_p, double coeficiente_fiscal_p)
    {
        super(NIF_p, email_p, nome_p, morada_p, password_p, index_p);
        setATIVIDADES_ECONOMICAS(atividades_economicas_p);
        this.coeficiente_fiscal = coeficiente_fiscal_p;
    }
    
    /**
     * Construtor de cópia de Coletivo
     * @param Coletivo
     * @return
     */
    public Coletivo(Coletivo umContribuinte_Coletivo_Empresa)
    {
        super(umContribuinte_Coletivo_Empresa);
        this.atividades_economicas = umContribuinte_Coletivo_Empresa.getATIVIDADES_ECONOMICAS();
        this.coeficiente_fiscal = umContribuinte_Coletivo_Empresa.getCOEFICIENTE_FISCAL();
    }
    
    /**
     * Devolve o array com as atividades económicas da empresa.
     * @param
     * @return atividades_economicas
     */
    public List<String> getATIVIDADES_ECONOMICAS()
    {
        List<String> nova = new ArrayList<String>();
        for(String s: this.atividades_economicas)
        {
            nova.add(s);
        }
        return nova;
    }
    
    /**
     * Atualiza o array com as atividades económicas da empresa.
     * @param atividades_economicas
     * @return
     */
    public void setATIVIDADES_ECONOMICAS(List<String> atividades_economicas_p)
    {
        this.atividades_economicas = new ArrayList<>();
        for(String ae: atividades_economicas_p)
        {
            this.atividades_economicas.add(ae);
        }
    }

    /**
     * Devolve o coeficiente fiscal da empresa.
     * @param 
     * @return coeficiente_fiscal
     */
    public double getCOEFICIENTE_FISCAL()
    {
        return this.coeficiente_fiscal;
    }

    /**
     * Atualiza o coeficiente fiscal da empresa.
     * @param coeficiente_fiscal
     * @return
     */
    public void setCOEFICIENTE_FISCAL(double coeficiente_fiscal_p)
    {
        this.coeficiente_fiscal = coeficiente_fiscal_p;
    }

    /**
     * Método que verifica se a classe Coletivo d é igual à classe Coletivo que recebe a mensagem.
     * @param Object
     * @return boolean
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Coletivo c = (Coletivo) o;
        return (super.equals(c) && this.atividades_economicas.equals(c.getATIVIDADES_ECONOMICAS())
                                && this.coeficiente_fiscal == c.getCOEFICIENTE_FISCAL());
    }
   
    /**
     * Método que devolve a representação em String da classe Coletivo
     * @param
     * @return String.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Contribuinte Coletivo: ");
        sb.append("(NIF: ").append(super.getNIF()).append(", ");
        sb.append("Email: ").append(super.getEmail()).append(", ");
        sb.append("Nome: ").append(super.getNome()).append(", ");
        sb.append("Morada: ").append(super.getMorada()).append(", ");
        sb.append("Password de Acesso: ").append(super.getPassword()).append(", ");
        sb.append("Indíces das Faturas: ").append(super.getIndex()).append(", ");
        sb.append("Atividades Económicas: ").append(this.getATIVIDADES_ECONOMICAS().toString()).append(", ");
        sb.append("Coeficiente Fiscal: ").append(this.getCOEFICIENTE_FISCAL()).append(")\n");
        return sb.toString();
    }
    
    /**
     * Método que faz uma cópia da classe Coletivo receptora da mensagem.
     * Para tal invoca o construtor de cópia.
     * @param
     * @return Coletivo clone da classe Coletivo que recebe a mensagem.
     */
    public Coletivo clone()
    {
        return new Coletivo(this);
    }
}
