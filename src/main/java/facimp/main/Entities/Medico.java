package facimp.main.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// A classe Medico é uma entidade que representa um médico.
@Entity
@Table(name = "medicos")
public class Medico {
    // Atributos da classe Medico.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crm;
    private String especialidade;
    private String telefone;
    private String email;
    private String dataNascimento;
    private String sexo;
    private String cpf;

    // Relacionamento de um para muitos com a classe Consulta.
    @OneToMany(mappedBy = "medico")
    // Define que o atributo consultas do tipo lista deve ser serializado.
    @JsonManagedReference("medico-consultas")
    private List<Consulta> consultas;

    // Construtor vazio da classe Medico.
    public Medico() {

    }

    // Getters e Setters da classe Medico.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
