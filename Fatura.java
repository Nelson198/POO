/**
 * Classe da Fatura.
 * 
 * @author P.O.O. - Project - 2017/2018
 * @version 1.0
 */
import java.util.Comparator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Fatura implements Serializable
{
    // Variáveis de Instância
    private String NIF_emitente;
    private String nome_emitente;
    private LocalDateTime data_hora_despesa;
    private String NIF_cliente;
    private String descrição_despesa;
    private String natureza_despesa; //atividade económica
    private double valor_despesa;

    /**
     * Construtor por omissão de Fatura.
     * @param
     * @return
     */
    public Fatura()
    {
        this.NIF_emitente = "N/D";
        this.nome_emitente = "N/D";
        this.data_hora_despesa = LocalDateTime.now();
        this.NIF_cliente = "N/D";
        this.descrição_despesa = "N/D";
        this.natureza_despesa = "N/D";
        this.valor_despesa = 0;
    }
    
    /**
     * Construtor parametrizado de Fatura.
     * @param NIF_emitente
     * @param nome_emitente
     * @param data_hora
     * @param NIF_cliente
     * @param descrição
     * @param natureza
     * @param valor
     * @return
     */
    public Fatura(String NIF_e, String nome_e, LocalDateTime data_hora_d, String NIF_c, String descrição_d, String natureza_d, double valor_d)
    {
        this.NIF_emitente = NIF_e;
        this.nome_emitente = nome_e;
        this.data_hora_despesa = data_hora_d;
        this.NIF_cliente = NIF_c;
        this.descrição_despesa = descrição_d;
        this.natureza_despesa = natureza_d;
        this.valor_despesa = valor_d;
    }
    
    /**
     * Construtor de cópia de Fatura.
     * @param Documentaçao_Despesas
     * @return
     */
    public Fatura(Fatura umaDespesa)
    {
        this.NIF_emitente = umaDespesa.getNIF_Emitente();
        this.nome_emitente = umaDespesa.getNome_Emitente();
        this.data_hora_despesa = umaDespesa.getData_Hora();
        this.NIF_cliente = umaDespesa.getNIF_Cliente();
        this.descrição_despesa = umaDespesa.getDescriçao_Despesa();
        this.natureza_despesa = umaDespesa.getNatureza_Despesa();
        this.valor_despesa = umaDespesa.getValor_Despesa();
    }
    
    /**
     * Devolve o NIF do emitente da Despesa.
     * @param
     * @return NIF_emitente
     */
    public String getNIF_Emitente()
    {
        return this.NIF_emitente;
    }
    
    /**
     * Atualiza o NIF do emitente da Despesa.
     * @param NIF_emitente
     * @return
     */
    public void setNIF_Emitente(String NIF_e)
    {
        this.NIF_emitente = NIF_e;
    }
    
    /**
     * Devolve a designação do emitente da Despesa.
     * @param
     * @return designação_emitente
     */
    public String getNome_Emitente()
    {
        return this.nome_emitente;
    }
    
    /**
     * Atualiza a designação do emitente da Despesa.
     * @param designação_emitente
     * @return
     */
    public void setNome_Emitente(String designação_e)
    {
        this.nome_emitente = designação_e;
    }
    
    /**
     * Devolve a data/hora da Despesa.
     * @param
     * @return data_hora_despesa
     */
    public LocalDateTime getData_Hora()
    {
        return this.data_hora_despesa;
    }
    
    /**
     * Atualiza a data da Despesa.
     * @param data_hora_despesa
     * @return
     */
    public void setData_Hora(LocalDateTime data_hora_d)
    {
        this.data_hora_despesa = data_hora_d;
    }
    
    /**
     * Devolve o NIF do cliente da Despesa.
     * @param
     * @return NIF_cliente
     */
    public String getNIF_Cliente()
    {
        return this.NIF_cliente;
    }
    
    /**
     * Atualiza o NIF do cliente da Despesa.
     * @param NIF_cliente
     * @return
     */
    public void setNIF_Cliente(String NIF_c)
    {
        this.NIF_cliente = NIF_c;
    }
    
    /**
     * Devolve a descrição da Despesa.
     * @param
     * @return descrição_despesa
     */
    public String getDescriçao_Despesa()
    {
        return this.descrição_despesa;
    }
    
    /**
     * Atualiza a descrição da Despesa.
     * @param descrição_despesa
     * @return
     */
    public void setDescriçao_Despesa(String descrição_d)
    {
        this.descrição_despesa = descrição_d;
    }
    
    /**
     * Devolve a natureza da Despesa, ou seja, a atividade económica a que esta está associada.
     * @param
     * @return natureza_despesa
     */
    public String getNatureza_Despesa()
    {
        return this.natureza_despesa;
    }
    
    /**
     * Atualiza a natureza da Despesa, ou seja, a atividade económica a que esta está associada.
     * @param natureza_despesa
     * @return
     */
    public void setNatureza_Despesa(String natureza_d)
    {
        this.natureza_despesa = natureza_d;
    }
    
    /**
     * Devolve o montante da Despesa.
     * @param
     * @return valor_despesa
     */
    public double getValor_Despesa()
    {
        return this.valor_despesa;
    }

    /**
     * Atualiza o montante da Despesa.
     * @param valor_despesa
     * @return
     */
    public void setValor_Despesa(double valor_d)
    {
        this.valor_despesa = valor_d;
    }

    /**
     * Método que verifica se a Fatura d é igual à Fatura que recebe a mensagem.
     * @param Object
     * @return boolean
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Fatura d = (Fatura) o;
        return (this.NIF_emitente.equals(d.getNIF_Emitente())
             && this.nome_emitente.equals(d.getNome_Emitente())
             && this.data_hora_despesa.isEqual(d.getData_Hora())
             && this.NIF_cliente.equals(d.getNIF_Cliente())
             && this.descrição_despesa.equals(d.getDescriçao_Despesa())
             && this.natureza_despesa.equals(d.getNatureza_Despesa())
             && this.valor_despesa == d.getValor_Despesa());
    }

    /**
     * Método que devolve a representação em String da classe Fatura.
     * @param
     * @return String.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Esta fatura foi emitida pela Empresa ").append(this.getNome_Emitente()).append(", cujo NIF é ").append(this.getNIF_Emitente()).append(".\n");
        sb.append("O/A contribuinte com NIF ").append(this.getNIF_Cliente()).append(" adquiriu um(uns) / uma/(umas) ").append(this.getDescriçao_Despesa());
        sb.append(" num valor de ").append(this.getValor_Despesa()).append(" €, às ");
        sb.append(this.getData_Hora().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        sb.append(" na data ").append(this.getData_Hora().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))).append(".\n");
        return sb.toString();
    }
    
    /**
     * Método que faz uma cópia da classe Faturas receptora da mensagem.
     * Para tal invoca o construtor de cópia.
     * @param
     * @return Fatura clone da classe Fatura que recebe a mensagem.
     */
    public Fatura clone()
    {
        return new Fatura(this);
    }
}
