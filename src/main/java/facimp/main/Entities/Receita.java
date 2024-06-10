package facimp.main.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

// A classe Receita é uma entidade que representa uma receita médica.
@Entity
// Define o nome da tabela que será criada no banco de dados.
@Table(name = "receitas")
public class Receita {
    // Atributos da classe Receita.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento de um para um com a classe Consulta.
    @OneToOne
    // Define a coluna que será usada para fazer o relacionamento.
    @JoinColumn(name = "consulta_id")
    // Define que o atributo consulta não deve ser serializado.
    @JsonBackReference("consulta-receita")
    private Consulta consulta;

    private String descricao;
    private String data;
    private String horario;
    private String observacoes;
    private String medicamentos;

    // Construtor vazio da classe Receita.
    public Receita() {
    }

    // Getters e Setters da classe Receita.
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

}
