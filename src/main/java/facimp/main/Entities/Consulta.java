package facimp.main.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

// A classe Consulta é uma entidade que representa uma consulta médica.
@Entity
@Table(name = "consultas")
public class Consulta {
    // Atributos da classe Consulta.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private String horario;
    private double valor;
    private String motivo;
    private String observacoes;

    // Relacionamento de muitos para um com a classe Medico.
    @ManyToOne
    // Define a coluna que será usada para fazer o relacionamento.
    @JoinColumn(name = "medico_id")
    // Define que o atributo medico não deve ser serializado.
    @JsonBackReference("medico-consultas")
    private Medico medico;

    // Relacionamento de muitos para um com a classe Paciente.
    @ManyToOne
    // Define a coluna que será usada para fazer o relacionamento.
    @JoinColumn(name = "paciente_id")
    // Define que o atributo paciente não deve ser serializado.
    @JsonBackReference("paciente-consultas")
    private Paciente paciente;

    // Relacionamento de um para um com a classe Receita.
    @OneToOne(mappedBy = "consulta")
    // Define que o atributo receita deve ser serializado.
    @JsonManagedReference("consulta-receita")
    private Receita receita;

    // Relacionamento de um para um com a classe Pagamento.
    @OneToOne(mappedBy = "consulta")
    // Define que o atributo pagamento deve ser serializado.
    @JsonManagedReference("consulta-pagamento")
    private Pagamento pagamento;

    // Construtor vazio da classe Consulta.
    public Consulta() {
    }

    // Getters e Setters da classe Consulta.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

}
