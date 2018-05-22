/**
 * Classe Individual.
 * 
 * @author P.O.O. - Project - 2017/2018 
 * @version 1.0
 */
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Individual extends Contribuinte implements Serializable
{
    // Variáveis de instância
    private int agregado_familiar;                      /* Nº de elementos do agregado familiar */
    private int dependentes;                            /* Nº de dependentes, ou seja, de filhos */
    private double coeficiente_fiscal;                  /* Factor multiplicativo que é associado a cada despesa elegível */
    private Map<String, Double> atividades_economicas;  /* Dicionário: Chave -> Ativididade Económica; Valor -> valor das faturas por cada atividade económica */
    private boolean fam_numerosa;                       /* Booleano que indica se uma família é ou não numerosa */
    private int index_agregado;                         /* Inteiro com o índice do agregado a que pertence */

    /**
     * Construtor por omissão da classe Individual.
     * @param
     * @return
     */
    public Individual()
    {
        super();
        this.agregado_familiar = 1;
        this.dependentes = 0;
        this.NIFs_agregado_familiar = new ArrayList<>();
        this.coeficiente_fiscal = 0;
        this.atividades_economicas = new HashMap<>();
        this.atividades_economicas.put("Outros", 0.0);
        this.fam_numerosa = False;
        this.index_agregado = null;
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
     * @param coeficiente_fiscal
     * @param atividades_economicas
     * @return
     */
    public Individual(String NIF_p, String email_p, String nome_p, String morada_p, String password_p, List<Integer> index_p,
                      int agregado_familiar_p, int dependentes_p, List<String> NIFs_agregado_familiar_p, double coeficiente_fiscal_p, Map<String, Double> ae_p, int index_agregado_p)
    {
        super(NIF_p, email_p, nome_p, morada_p, password_p, index_p);
        this.dependentes = dependentes_p;
        this.agregado_familiar = agregado_familiar_p;
        this.coeficiente_fiscal = coeficiente_fiscal_p;
        setAtividades_Economicas(ae_p);
        setFam_numerosa(dependentes_p);
        this.index_agregado = index_agregado_p;
    }
    
    /**
     * Construtor de cópia da classe Individual.
     * @param Individual umContribuinte_Individual
     * @return
     */
    public Individual(Individual umContribuinte_Individual)
    {
        super(umContribuinte_Individual);
        this.agregado_familiar = umContribuinte_Individual.getAgregado_Familiar();
        this.dependentes = umContribuinte_Individual.getDependentes();
        this.coeficiente_fiscal = umContribuinte_Individual.getCoeficiente_Fiscal();
        this.atividades_economicas = umContribuinte_Individual.getAtividades_Economicas();
        this.fam_numerosa = umContribuinte_Individual.getFam_Numerosa();
        this.index_agregado = umContribuinte_Individual.getIndex_Agregado();
    }
    
    /**
     * Devolve o nº de elementos do agregado familiar do contribuinte individual.
     * @param
     * @return agreagado_familiar
     */
    public int getAgregado_Familiar()
    {
        return this.agregado_familiar;
    }
    
    /**
     * Atualiza o nº de elementos do agregado familiar do contribuinte individual.
     * @param agreagado_familiar
     * @return
     */
    public void setAgregado_Familiar(int agregado_familiar_p)
    {
        this.agregado_familiar = agregado_familiar_p;
    }

    /**
     * Devolve o nº de elementos dependentes do contribuinte individual.
     * @param
     * @return dependentes
     */
    public int getDependentes()
    {
        return this.dependentes;
    }
    
    /**
     * Atualiza o nº de dependentes do contribuinte individual.
     * @param dependentes
     * @return
     */
    public void setDependentes(int dependentes_p)
    {
        this.dependentes = dependentes_p;
    }
    
    /**
     * Devolve o array com os NIF's dos elementos do agregado familiar do contribuinte individual.
     * @param
     * @return NIFs_agregado_familiar
     */
    public int getIndex_Agregado()
    {
        return this.index_agregado;
    }
    
    /**
     * Atualiza o array com os NIF's dos elementos do agregado familiar do contribuinte individual.
     * @param NIFs_agregado_familiar
     * @return
     */
    public void setIndex_Agregado(int index_agregado_p)
    {
        this.index_agregado = index_agregado_p;
    }

    /**
     * Devolve o coeficiente fiscal do contribuinte individual.
     * @param
     * @return coeficiente_fiscal
     */
    public double getCoeficiente_Fiscal()
    {
        return this.coeficiente_fiscal;
    }
    
    /**
     * Atualiza o coeficiente fiscal do contribuinte individual.
     * @param coeficiente_fiscal
     * @return
     */
    public void setCoeficiente_Fiscal(double coeficiente_fiscal_p)
    {
        this.coeficiente_fiscal = coeficiente_fiscal_p;
    }
    
    /**
     * Devolve o dicionário das atividades económicas do contribuinte individual.
     * @param
     * @return Map<String, Double> atividades_economicas
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
     * Atualiza o dicionário das atividades económicas do contribuinte individual.
     * @param Map<String, Double> atividades_economicas
     * @return
     */
    public void setAtividades_Economicas(Map<String, Double> ae_p)
    {
        this.atividades_economicas = new HashMap<>();
        for(String s: ae_p.keySet())
        {
            this.atividades_economicas.put(s, ae_p.get(s));
        }
    }

    public boolean getFam_Numerosa()
    {
        return this.fam_numerosa;
    }

    public void setFam_Numerosa(int dependentes)
    {
        if (dependentes >= 4) this.fam_numerosa = True;
        else this.fam_numerosa = False;
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
        return (super.equals(c) && this.agregado_familiar == c.getAgregado_Familiar()
                                && this.NIFs_agregado_familiar.equals(c.getNifs_Agregado_Familiar())
                                && this.coeficiente_fiscal == c.getCoeficiente_Fiscal()
                                && this.atividades_economicas.equals(c.getAtividades_Economicas()));
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
        sb.append("Indices das faturas associadas: ").append(super.getIndex()).append(", ");
        sb.append("Nº de elementos do Agregado Familiar: ").append(this.getAgregado_Familiar()).append(", ");
        sb.append("NIF's do Agregado Familiar: ").append(this.getNifs_Agregado_Familiar().toString()).append(", ");
        sb.append("Atividades Económicas: ").append(this.atividades_economicas.toString()).append(", ");
        sb.append("Coeficiente Fiscal: ").append(this.getCoeficiente_Fiscal()).append(")\n");
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
