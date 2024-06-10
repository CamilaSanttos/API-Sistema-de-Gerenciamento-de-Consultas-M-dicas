package facimp.main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import facimp.main.Entities.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // PacienteRepository é uma interface que estende JpaRepository, que é fornecida
    // pelo Spring Data JPA.
    // Essa interface fornece métodos de acesso aos dados do tipo Author no banco de
    // dados.

    // Paciente é o tipo de entidade com o qual essa interface está associada.

    // Long especifica o tipo de dados do ID da entidade, que neste caso é Paciente.
    // Ou seja, os IDs dos Pacientes são do tipo Long.

    // Com essa interface, é possível ter acesso a métodos como save, findById,
    // findAll, delete, entre outros,
    // para realizar operações de CRUD no banco de dados associadas à entidade
    // Paciente.
    // O Spring Data JPA implementa esses métodos automaticamente com base nas
    // convenções de nomenclatura e mapeamento da entidade.
}