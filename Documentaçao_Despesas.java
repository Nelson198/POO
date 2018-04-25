/**
 * Classe das Despesas.
 * 
 * @author P.O.O. - Project - 2017/2018
 * @version 1.0
 */
import java.time.LocalDateTime;

public class Documentaçao_Despesas
{
    // Variáveis de Instância
    private int NIF_emitente;
    private String designação_emitente;
    private LocalDateTime data_hora_despesa;
    private int NIF_cliente;
    private String descrição_despesa;
    private String natureza_despesa; //atividade económica
    private double valor_despesa;

    /**
     * Construtor por omissão de Documentação_Despesas.
     * @param
     * @return
     */
    public Documentaçao_Despesas()
    {
        this.NIF_emitente = 0;
        this.designação_emitente = "N/D";
        this.data_hora_despesa = LocalDateTime.now();
        this.NIF_cliente = 0;
        this.descrição_despesa = "N/D";
        this.natureza_despesa = "N/D";
        this.valor_despesa = 0;
    }
    
    /**
     * Construtor parametrizado de Documentação_Despesas.
     * @param NIF_emitente
     * @param designação
     * @param data_hora
     * @param NIF_cliente
     * @param descrição
     * @param natureza
     * @param valor
     * @return
     */
    public Documentaçao_Despesas(int NIF_e, String designação_e, LocalDateTime data_hora_d, int NIF_c, String descrição_d, String natureza_d, double valor_d)
    {
        this.NIF_emitente = NIF_e;
        this.designação_emitente = designação_e;
        this.data_hora_despesa = data_hora_d;
        this.NIF_cliente = NIF_c;
        this.descrição_despesa = descrição_d;
        this.natureza_despesa = natureza_d;
        this.valor_despesa = valor_d;
    }
    
    /**
     * Construtor de cópia de Documentação_Despesas.
     * @param Documentaçao_Despesas
     * @return
     */
    public Documentaçao_Despesas(Documentaçao_Despesas umaDespesa)
    {
        this.NIF_emitente = umaDespesa.getNIF_EMITENTE();
        this.designação_emitente = umaDespesa.getDESIGNAÇÃO_EMITENTE();
        this.data_hora_despesa = umaDespesa.getDATA_HORA_DESPESA();
        this.NIF_cliente = umaDespesa.getNIF_CLIENTE();
        this.descrição_despesa = umaDespesa.getDESCRIÇÃO_DESPESA();
        this.natureza_despesa = umaDespesa.getNATUREZA_DESPESA();
        this.valor_despesa = umaDespesa.getVALOR_DESPESA();
    }
    
    /**
     * Devolve o NIF do emitente da Despesa.
     * @param
     * @return NIF_emitente
     */
    public int getNIF_EMITENTE()
    {
        return this.NIF_emitente;
    }
    
    /**
     * Atualiza o NIF do emitente da Despesa.
     * @param NIF_emitente
     * @return
     */
    public void setNIF_EMITENTE(int NIF_e)
    {
        this.NIF_emitente = NIF_e;
    }
    
    /**
     * Devolve a designação do emitente da Despesa.
     * @param
     * @return designação_emitente
     */
    public String getDESIGNAÇÃO_EMITENTE()
    {
        return this.designação_emitente;
    }
    
    /**
     * Atualiza a designação do emitente da Despesa.
     * @param designação_emitente
     * @return
     */
    public void setDESIGNAÇÃO_EMITENTE(String designação_e)
    {
        this.designação_emitente = designação_e;
    }
    
    /**
     * Devolve a data/hora da Despesa.
     * @param
     * @return data_hora_despesa
     */
    public LocalDateTime getDATA_HORA_DESPESA()
    {
        return this.data_hora_despesa;
    }
    
    /**
     * Atualiza a data da Despesa.
     * @param data_hora_despesa
     * @return
     */
    public void setDATA_HORA_DESPESA(LocalDateTime data_hora_d)
    {
        this.data_hora_despesa = data_hora_d;
    }
    
    /**
     * Devolve o NIF do cliente da Despesa.
     * @param
     * @return NIF_cliente
     */
    public int getNIF_CLIENTE()
    {
        return this.NIF_cliente;
    }
    
    /**
     * Atualiza o NIF do cliente da Despesa.
     * @param NIF_cliente
     * @return
     */
    public void setNIF_CLIENTE(int NIF_c)
    {
        this.NIF_cliente = NIF_c;
    }
    
    /**
     * Devolve a descrição da Despesa.
     * @param
     * @return descrição_despesa
     */
    public String getDESCRIÇÃO_DESPESA()
    {
        return this.descrição_despesa;
    }
    
    /**
     * Atualiza a descrição da Despesa.
     * @param descrição_despesa
     * @return
     */
    public void setDESCRIÇÃO_DESPESA(String descrição_d)
    {
        this.descrição_despesa = descrição_d;
    }
    
    /**
     * Devolve a natureza da Despesa, ou seja, a atividade económica a que esta está associada.
     * @param
     * @return natureza_despesa
     */
    public String getNATUREZA_DESPESA()
    {
        return this.natureza_despesa;
    }
    
    /**
     * Atualiza a natureza da Despesa, ou seja, a atividade económica a que esta está associada.
     * @param natureza_despesa
     * @return
     */
    public void setNATUREZA_DESPESA(String natureza_d)
    {
        this.natureza_despesa = natureza_d;
    }
    
    /**
     * Devolve o montante da Despesa.
     * @param
     * @return valor_despesa
     */
    public double getVALOR_DESPESA()
    {
        return this.valor_despesa;
    }

    /**
     * Atualiza o montante da Despesa.
     * @param valor_despesa
     * @return
     */
    public void setVALOR_DESPESA(double valor_d)
    {
        this.valor_despesa = valor_d;
    }

    /**
     * Método que verifica se a Documentação_Despesas d é igual à Documentação_Despesas que recebe a mensagem.
     * @param Object
     * @return boolean
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Documentaçao_Despesas d = (Documentaçao_Despesas) o;
        return (this.NIF_emitente == d.getNIF_EMITENTE()
             && this.designação_emitente.equals(d.getDESIGNAÇÃO_EMITENTE())
             && this.data_hora_despesa.isEqual(d.getDATA_HORA_DESPESA())
             && this.NIF_cliente == d.getNIF_CLIENTE()
             && this.descrição_despesa.equals(d.getDESCRIÇÃO_DESPESA())
             && this.natureza_despesa.equals(d.getNATUREZA_DESPESA())
             && this.valor_despesa == d.getVALOR_DESPESA());
    }
    
    /**
     * Método que devolve a representação em String da classe Documentação_Despesas.
     * @param
     * @return String.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Despesa:\n");
        sb.append("(NIF do Emitente: ").append(this.getNIF_EMITENTE()).append(", ");
        sb.append("Designação do Emitente: ").append(this.getDESIGNAÇÃO_EMITENTE()).append(", ");
        sb.append("Data/Hora da Despesa: ").append(this.getDATA_HORA_DESPESA()).append(", ");
        sb.append("NIF do Cliente: ").append(this.getNIF_CLIENTE()).append(", ");
        sb.append("Descrição da Despesa: ").append(this.getDESCRIÇÃO_DESPESA()).append(", ");
        sb.append("Natureza da Despesa (Atividade Económica): ").append(this.getNATUREZA_DESPESA()).append(", ");
        sb.append("Montante da Despesa: ").append(this.getVALOR_DESPESA()).append(")\n");
        return sb.toString();
    }
    
    /**
     * Método que faz uma cópia da classe Documentação_Despesas receptora da mensagem.
     * Para tal invoca o construtor de cópia.
     * @param
     * @return Documentação_Despesas clone da classe Documentação_Despesas que recebe a mensagem.
     */
    public Documentaçao_Despesas clone()
    {
        return new Documentaçao_Despesas(this);
    }
}
