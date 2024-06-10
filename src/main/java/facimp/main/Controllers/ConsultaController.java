package facimp.main.Controllers;

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
import facimp.main.Repositories.ConsultaRepository;

// O controller ConsultaController é responsável por lidar com as requisições HTTP 
// relacionadas a entidade Consulta.
@RestController
// Define o path base para as requisições HTTP.
@RequestMapping("/api/v1/consultas")
public class ConsultaController {
    // Injeção de dependência do repositório ConsultaRepository.
    @Autowired
    private ConsultaRepository consultaRepository;

    // Método que retorna todas as consultas.
    @GetMapping
    public Iterable<Consulta> getAllConsultas() {
        // Retorna todas as consultas.
        return consultaRepository.findAll();
    }

    // Método que retorna uma consulta pelo id.
    @GetMapping("/{id}")
    public Consulta getConsultaById(@PathVariable Long id) {
        // Retorna a consulta pelo id, caso não encontre, retorna null.
        return consultaRepository.findById(id).orElse(null);
    }

    // Método que cria uma consulta.
    @PostMapping
    public Consulta createConsulta(@RequestBody Consulta consulta) {
        // Salva a consulta no banco de dados e retorna a consulta salva.
        return consultaRepository.save(consulta);
    }

    // Método que atualiza uma consulta pelo id.
    @PutMapping("/{id}")
    public Consulta updateConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        // Caso a consulta não exista, retorna null.
        if (consultaRepository.findById(id).orElse(null) == null) {
            return null;
        } else {
            consulta.setId(id);
            return consultaRepository.save(consulta);
        }
    }

    // Método que deleta uma consulta pelo id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        // Tenta deletar a consulta pelo id, caso não encontre, retorna notFound.
        try {
            consultaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
