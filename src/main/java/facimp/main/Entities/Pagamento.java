package facimp.main.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

// A classe Pagamento é uma entidade que representa um pagamento.
@Entity
@Table(name = "pagamentos")
public class Pagamento {
    // Atributos da classe Pagamento.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento de um para um com a classe Consulta.
    @OneToOne
    // Define a coluna que será usada para fazer o relacionamento.
    @JoinColumn(name = "consulta_id")
    // Define que o atributo consulta não deve ser serializado.
    @JsonBackReference(value = "consulta-pagamento")
    private Consulta consulta;

    private double valor;
    private String formaDePagamento;
    private String data;
    private String horario;

    // Construtor vazio da classe Pagamento.
    public Pagamento() {
    }

    // Getters e Setters da classe Pagamento.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

}
