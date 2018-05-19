/**
 * Classe dos Contribuintes Coletivos, ou seja, de Empresas.
 * 
 * @author P.O.O. - Project - 2017/2018 
 * @version 1.0
 */
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Coletivo extends Contribuinte implements Serializable
{
    // Variáveis de instância
    private Map<String, Double> atividades_economicas;
    private double coeficiente_fiscal;
    private boolean interior;
    
    /**
     * Construtor por omissão de Coletivo
     * @param
     * @return 
     */
    public Coletivo()
    {
        super();
        this.atividades_economicas = new HashMap<>();
        this.coeficiente_fiscal = 0;
        this.interior = false;
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
     * @param interior
     * @return
     */
    public Coletivo(String NIF_p, String email_p, String nome_p, String morada_p, String password_p, List<Integer> index_p, 
                    Map<String, Double> atividades_economicas_p, double coeficiente_fiscal_p, boolean interior)
    {
        super(NIF_p, email_p, nome_p, morada_p, password_p, index_p);
        setAtividades_Economicas(atividades_economicas_p);
        this.coeficiente_fiscal = coeficiente_fiscal_p;
        this.interior = interior;
    }
    
    /**
     * Construtor de cópia de Coletivo
     * @param Coletivo
     * @return
     */
    public Coletivo(Coletivo c)
    {
        super(c);
        this.atividades_economicas = c.getAtividades_Economicas();
        this.coeficiente_fiscal = c.getCoeficiente_Fiscal();
        this.interior = c.getInterior();
    }
    
    /**
     * Devolve o array com as atividades económicas da empresa.
     * @param
     * @return atividades_economicas
     */
    public Map<String, Double> getAtividades_Economicas()
    {
        Map<String, Double> nova = new HashMap<>();
        for(String s: this.atividades_economicas.keySet())
        {
            nova.put(s, this.atividades_economicas.get(s));
        }
        return nova;
    }
    
    /**
     * Atualiza o array com as atividades económicas da empresa.
     * @param atividades_economicas
     * @return
     */
    public void setAtividades_Economicas(Map<String, Double> atividades_economicas_p)
    {
        this.atividades_economicas = new HashMap<>();
        for(String ae: atividades_economicas_p.keySet())
        {
            this.atividades_economicas.put(ae, atividades_economicas_p.get(ae));
        }
    }

    /**
     * Devolve o coeficiente fiscal da empresa.
     * @param 
     * @return coeficiente_fiscal
     */
    public double getCoeficiente_Fiscal()
    {
        return this.coeficiente_fiscal;
    }

    /**
     * Atualiza o coeficiente fiscal da empresa.
     * @param coeficiente_fiscal
     * @return
     */
    public void setCoeficiente_Fiscal(double coeficiente_fiscal_p)
    {
        this.coeficiente_fiscal = coeficiente_fiscal_p;
    }

    /**
     * Indica se empresa é do interior
     * @param
     * @return interior
     */
    public boolean getInterior() {
        return this.interior;
    }

    /**
     * Atualiza o estatuto da empresa (se é Empresa Interior ou não)
     * @param interior
     * @return
     */
    public void setInterior(boolean interior) {
        this.interior = interior;
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
        return (super.equals(c) && this.atividades_economicas.equals(c.getAtividades_Economicas())
                                && this.coeficiente_fiscal == c.getCoeficiente_Fiscal());
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
        sb.append("Indíces das Faturas associadas: ").append(super.getIndex()).append(", ");
        sb.append("Atividades Económicas: ").append(this.getAtividades_Economicas().toString()).append(", ");
        sb.append("Coeficiente Fiscal: ").append(this.getCoeficiente_Fiscal()).append(")\n");
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
