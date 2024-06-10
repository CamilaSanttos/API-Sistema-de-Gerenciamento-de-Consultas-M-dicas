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
import facimp.main.Entities.Medico;
import facimp.main.Repositories.MedicoRepository;

// O controller MedicoController é responsável por lidar com as requisições HTTP
// relacionadas a entidade Medico.
@RestController
// Define o path base para as requisições HTTP.
@RequestMapping("/api/v1/medicos")
public class MedicoController {
    // Injeção de dependência do repositório MedicoRepository.
    @Autowired
    private MedicoRepository medicoRepository;

    // Método que retorna todos os médicos.
    @GetMapping
    public Iterable<Medico> getAllMedicos() {
        // Retorna todos os médicos.
        Iterable<Medico> medicos = medicoRepository.findAll();
        // Para cada médico, seta as consultas associadas a ele.
        for (Medico medico : medicos) {
            medico.setConsultas(medico.getConsultas());
        }
        return medicos;
    }

    // Método que retorna um médico pelo id.
    @GetMapping("/{id}")
    public Medico getMedicoById(@PathVariable Long id) {
        // Retorna o médico pelo id, caso não encontre, retorna null.
        return medicoRepository.findById(id).orElse(null);
    }

    // Método que cria um médico.
    @PostMapping
    public Medico createMedico(@RequestBody Medico medico) {
        // Salva o médico no banco de dados e retorna o médico salvo.
        return medicoRepository.save(medico);
    }

    // Método que atualiza um médico pelo id.
    @PutMapping("/{id}")
    public Medico updateMedico(@PathVariable Long id, @RequestBody Medico medico) {
        // Caso o médico não exista, retorna null.
        if (medicoRepository.findById(id).orElse(null) == null) {
            return null;
        } else {
            medico.setId(id);
            return medicoRepository.save(medico);
        }
    }

    // Método que deleta um médico pelo id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        // Tenta deletar o médico pelo id, caso não encontre, retorna um status 404.
        Medico medico = medicoRepository.findById(id).orElse(null);
        // Caso o médico exista, remove as consultas associadas a ele.
        if (medico != null) {
            try {
                List<Consulta> consultas = medico.getConsultas();
                for (Consulta consulta : consultas) {
                    consulta.setMedico(null);
                }

                medicoRepository.deleteById(id);

                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                return ResponseEntity.status(500).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}