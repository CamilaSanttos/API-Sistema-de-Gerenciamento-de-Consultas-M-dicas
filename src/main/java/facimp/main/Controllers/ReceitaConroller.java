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

import facimp.main.Entities.Receita;
import facimp.main.Repositories.ReceitaRepository;

// O controller ReceitaConroller é responsável por lidar com as requisições HTTP
// relacionadas a entidade Receita.
@RestController
// Define o path base para as requisições HTTP.
@RequestMapping("/api/v1/receitas")
public class ReceitaConroller {
    // Injeção de dependência do repositório ReceitaRepository.
    @Autowired
    private ReceitaRepository receitaRepository;

    // Método que retorna todas as receitas.
    @GetMapping
    public Iterable<Receita> getAllReceitas() {
        // Retorna todas as receitas.
        return receitaRepository.findAll();
    }

    // Método que retorna uma receita pelo id.
    @GetMapping("/{id}")
    public Receita getReceitaById(@PathVariable Long id) {
        // Retorna a receita pelo id, caso não encontre, retorna null.
        return receitaRepository.findById(id).orElse(null);
    }

    // Método que cria uma receita.
    @PostMapping
    public Receita createReceita(@RequestBody Receita receita) {
        // Salva a receita no banco de dados e retorna a receita salva.
        return receitaRepository.save(receita);
    }

    // Método que atualiza uma receita pelo id.
    @PutMapping("/{id}")
    public Receita updateReceita(@PathVariable Long id, @RequestBody Receita receita) {
        // Caso a receita não exista, retorna null.
        if (receitaRepository.findById(id).orElse(null) == null) {
            return null;
        } else {
            receita.setId(id);
            return receitaRepository.save(receita);
        }
    }

    // Método que deleta uma receita pelo id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceita(@PathVariable Long id) {
        // Tenta deletar a receita pelo id, caso não encontre, retorna um status 404.
        try {
            receitaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
