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
    private Map<String, Double> atividades_economicas;  /* Dicionário: Chave -> Ativididade Económica; Valor -> valor das faturas por cada atividade económica */
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
        this.atividades_economicas = new HashMap<>();
        this.atividades_economicas.put("Outros", 0.0);
        this.atividades_economicas.put("Despesas Gerais Familiares", 0.0);
        this.index_agregado = 0;
    }
    
    /**
     * Construtor parametrizado da classe Individual.
     * @param NIF
     * @param email
     * @param nome
     * @param morada
     * @param password
     * @param index
     * @param coeficiente_fiscal
     * @param agregado_familiar
     * @param dependentes
     * @param atividades_economicas
     * @return
     */
    public Individual(String NIF_p, String email_p, String nome_p, String morada_p, String password_p, List<Integer> index_p,
                      double coeficiente_fiscal_p, int agregado_familiar_p, int dependentes_p, Map<String, Double> ae_p, int index_agregado_p)
    {
        super(NIF_p, email_p, nome_p, morada_p, password_p, index_p, coeficiente_fiscal_p);
        this.dependentes = dependentes_p;
        this.agregado_familiar = agregado_familiar_p;
        setAtividades_Economicas(ae_p);
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
        this.atividades_economicas = umContribuinte_Individual.getAtividades_Economicas();    
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

    /**
     * Devolve o indice onde está armazenado o seu agregado.
     * @param
     * @return index_agreagado
     */
    public int getIndex_Agregado()
    {
        return this.index_agregado;
    }
    
    /**
     * Atualiza o indice onde está armazenado o seu agregado.
     * @param agreagado_familiar
     * @return
     */
    public void setIndex_Agregado(int index_agregado_p)
    {
        this.index_agregado = index_agregado_p;
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
                                && this.dependentes == c.getDependentes()
                                && this.atividades_economicas.equals(c.getAtividades_Economicas())
                                && this.index_agregado == c.getIndex_Agregado());
    }
    
    /**
     * Método que devolve a representação em String da classe Individual.
     * @param
     * @return String.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Contribuinte Individual:\n");
        sb.append("NIF: ").append(super.getNIF()).append("\n");
        sb.append("Email: ").append(super.getEmail()).append("\n");
        sb.append("Nome: ").append(super.getNome()).append("\n");
        sb.append("Morada: ").append(super.getMorada()).append("\n");
        sb.append("Password: ").append(super.getPassword()).append("\n");
        sb.append("Indices das faturas associadas: ").append(super.getIndex()).append("\n");
        sb.append("Coeficiente Fiscal: ").append(super.getCoeficiente_Fiscal()).append("\n");
        sb.append("Nº de elementos do Agregado Familiar: ").append(this.getAgregado_Familiar()).append("\n");
        sb.append("Nº de dependentes: ").append(this.getDependentes()).append("\n");
        sb.append("Atividades Económicas: ").append(this.atividades_economicas.toString()).append("\n");
        sb.append("Índice do agregado familiar a que pertence: ").append(this.getIndex_Agregado()).append("\n");
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
