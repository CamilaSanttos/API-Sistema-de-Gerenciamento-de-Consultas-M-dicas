package facimp.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import facimp.main.Entities.Consulta;
import facimp.main.Entities.Paciente;
import facimp.main.Repositories.PacienteRepository;

// O controller PacienteController é responsável por lidar com as requisições HTTP
// relacionadas a entidade Paciente.
@RestController
// Define o path base para as requisições HTTP.
@RequestMapping("/api/v1/pacientes")
public class PacienteController {
    // Injeção de dependência do repositório PacienteRepository.
    @Autowired
    private PacienteRepository pacienteRepository;

    // Método que retorna todos os pacientes.
    @GetMapping
    public Iterable<Paciente> getAllPacientes() {
        // Retorna todos os pacientes.
        Iterable<Paciente> pacientes = pacienteRepository.findAll();
        // Para cada paciente, seta as consultas associadas a ele.
        for (Paciente paciente : pacientes) {
            paciente.setConsultas(paciente.getConsultas());
        }
        return pacientes;
    }

    // Método que retorna um paciente pelo id.
    @GetMapping("/{id}")
    public Paciente getPacienteById(@PathVariable Long id) {
        // Retorna o paciente pelo id, caso não encontre, retorna null.
        return pacienteRepository.findById(id).orElse(null);
    }

    // Método que cria um paciente.
    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        // Salva o paciente no banco de dados e retorna o paciente salvo.
        return pacienteRepository.save(paciente);
    }

    // Método que atualiza um paciente pelo id.
    @PutMapping("/{id}")
    public Paciente updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        // Caso o paciente não exista, retorna null.
        if (pacienteRepository.findById(id).orElse(null) == null) {
            return null;
        } else {
            paciente.setId(id);
            return pacienteRepository.save(paciente);
        }
    }

    // Método que deleta um paciente pelo id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        // Tenta deletar o paciente pelo id, caso não encontre, retorna um status 404.
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        // Verifica se o paciente existe, caso exista, remove as consultas associadas a
        // ele e deleta o paciente.
        if (paciente != null) {
            try {
                List<Consulta> consultas = paciente.getConsultas();
                for (Consulta consulta : consultas) {
                    consulta.setPaciente(null);
                }
                pacienteRepository.deleteById(id);

                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                return ResponseEntity.status(500).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}