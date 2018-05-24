/**
 * Classe da Fatura.
 * 
 * @author P.O.O. - Project - 2017/2018
 * @version 1.0
 */
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Fatura implements Serializable
{
    // Variáveis de Instância
    private String NIF_emitente;                    /* NIF da empresa que venda o produto/serviço */
    private String nome_emitente;                   /* Nome da empresa que vende o produto/serviço */
    private LocalDateTime data_hora_despesa;        /* Data e hora da emissão da fatura */
    private String NIF_cliente;                     /* NIF do comprador */
    private String descrição_despesa;               /* Descreve o produto/serviço transacionado */
    private List<String> naturezas_despesa;         /* Atividades económicas possíveis */
    private double valor_despesa;                   /* Indica o valor da despesa */
    private boolean pendente;                       /* Indica se a fatura está por validar */
    private List<String> natureza_despesa;          /* Atividade económica atual - posição 0 - e todas as anteriores. */ 

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
        this.naturezas_despesa = new ArrayList<>();
        this.valor_despesa = 0;
        this.pendente = false;
        this.natureza_despesa = new ArrayList<>();
    }
    
    /**
     * Construtor parametrizado de Fatura.
     * @param NIF_emitente
     * @param nome_emitente
     * @param data_hora
     * @param NIF_cliente
     * @param descrição
     * @param naturezas_despesa
     * @param valor
     * @param natureza_despesa
     * @return
     */
    public Fatura(String NIF_e, String nome_e, LocalDateTime data_hora_d, String NIF_c, String descrição_d, List<String> naturezas_despesa, double valor_d, boolean pendente, List<String> natureza_despesa)
    {
        this.NIF_emitente = NIF_e;
        this.nome_emitente = nome_e;
        this.data_hora_despesa = data_hora_d;
        this.NIF_cliente = NIF_c;
        this.descrição_despesa = descrição_d;
        setNaturezas_Despesa(naturezas_despesa);
        this.valor_despesa = valor_d;
        this.pendente = pendente;
        setNatureza_Despesa(natureza_despesa);
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
        this.naturezas_despesa = umaDespesa.getNaturezas_Despesa();
        this.valor_despesa = umaDespesa.getValor_Despesa();
        this.pendente = umaDespesa.getPendente();
        this.natureza_despesa = umaDespesa.getNatureza_Despesa();
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
     * Devolve as naturezas possíveis da Despesa, ou seja, as atividades económicas possíveis.
     * @param
     * @return naturezas_despesa
     */
    public List<String> getNaturezas_Despesa()
    {
        List<String> res = new ArrayList<>();
        for(String s: this.naturezas_despesa)
        {
            res.add(s);
        }
        return res;
    }
    
    /**
     * Atualiza as naturezas da Despesa, ou seja, as atividades económicas possíveis.
     * @param naturezas_despesa
     * @return
     */
    public void setNaturezas_Despesa(List<String> naturezas_d)
    {
        this.naturezas_despesa = new ArrayList<>();
        for(String s: naturezas_d)
        {
            this.naturezas_despesa.add(s);
        }
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
     * Método que devolve a indicação de a fatura estar ou não pendente de validação por parte do contribuinte.
     * @param
     * @return boolean
     */
    public boolean getPendente()
    {
        return this.pendente;
    }
    
    /**
     * Método que atualiza a indicação de a fatura estar ou não pendente de validação por parte do contribuinte.
     * @param boolean
     * @return
     */
    public void setPendente(boolean b)
    {
        this.pendente = b;
    }

    /**
     * Devolve a natureza da Despesa na posição 0, ou seja, a atividade económica a que esta está associada e todas as anteriores.
     * @param
     * @return natureza_despesa
     */
    public List<String> getNatureza_Despesa()
    {
        List<String> res = new ArrayList<>();
        for(String s: this.natureza_despesa)
        {
            res.add(s);
        }
        return res;
    }
    
    /**
     * Atualiza a natureza da Despesa na posição 0, ou seja, a atividade económica a que esta está associada e todas as anteriores.
     * @param natureza_despesa
     * @return
     */
    public void setNatureza_Despesa(List<String> natureza_d)
    {
        this.natureza_despesa = new ArrayList<>();
        for(String s: natureza_d)
        {
            this.natureza_despesa.add(s);
        }
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
             && this.naturezas_despesa.equals(d.getNaturezas_Despesa())
             && this.valor_despesa == d.getValor_Despesa()
             && this.pendente == d.getPendente()
             && this.natureza_despesa.equals(d.getNatureza_Despesa()));
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
        sb.append("O/A contribuinte com NIF ").append(this.getNIF_Cliente()).append(" comprou: ").append(this.getDescriçao_Despesa());
        sb.append(" num valor de ").append(this.getValor_Despesa()).append("€, às ");
        sb.append(this.getData_Hora().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        sb.append(" na data ").append(this.getData_Hora().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))).append(".\n");
        
        if(this.getPendente() == true)
        {
            sb.append("Esta fatura está pendente de validação por parte do contribuinte com NIF: " + this.getNIF_Cliente() + ".\n");
        }
        else
        {
            sb.append("Atividade económica associada: " + this.getNatureza_Despesa().get(0) + "\n");
        }
        return sb.toString();
    }

    /**
     * Método que devolve a representação em String da classe Fatura para o administrador.
     * @param
     * @return String.
     */
    public String show()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa emitente: ").append(this.getNome_Emitente()).append("\n");
        sb.append("NIF da Empresa emitente: ").append(this.getNIF_Emitente()).append("\n");
        sb.append("NIF do Contribuinte cliente: ").append(this.getNIF_Cliente()).append("\n");
        sb.append("Descrição: ").append(this.getDescriçao_Despesa()).append("\n");
        sb.append("Valor da despesa: ").append(this.getValor_Despesa()).append(" €").append("\n");
        sb.append("Data da despesa: ").append(this.getData_Hora().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))).append("\n");
        sb.append("Hora da despesa: ").append(this.getData_Hora().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).append("\n");
        sb.append("Pendente? ").append(this.getPendente()).append("\n");
        sb.append("Atividade(s) económica(s) associada(s): ").append(this.getNatureza_Despesa().toString()).append("\n");
        sb.append("Atividade económica atual: ").append(this.getNatureza_Despesa()).append("\n\n");
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
