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

import facimp.main.Entities.Pagamento;
import facimp.main.Repositories.PagamentoRepository;

// O controller PagamentoController é responsável por lidar com as requisições HTTP
// relacionadas a entidade Pagamento.
@RestController
// Define o path base para as requisições HTTP.
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {
    // Injeção de dependência do repositório PagamentoRepository.
    @Autowired
    private PagamentoRepository pagamentoRepository;

    // Método que retorna todos os pagamentos.
    @GetMapping
    public Iterable<Pagamento> getAllPagamentos() {
        // Retorna todos os pagamentos.
        return pagamentoRepository.findAll();
    }

    // Método que retorna um pagamento pelo id.
    @GetMapping("/{id}")
    public Pagamento getPagamentoById(@PathVariable Long id) {
        // Retorna o pagamento pelo id, caso não encontre, retorna null.
        return pagamentoRepository.findById(id).orElse(null);
    }

    // Método que cria um pagamento.
    @PostMapping
    public Pagamento createPagamento(@RequestBody Pagamento pagamento) {
        // Salva o pagamento no banco de dados e retorna o pagamento salvo.
        return pagamentoRepository.save(pagamento);
    }

    // Método que atualiza um pagamento pelo id.
    @PutMapping("/{id}")
    public Pagamento updatePagamento(@PathVariable Long id, @RequestBody Pagamento pagamento) {
        // Caso o pagamento não exista, retorna null.
        if (pagamentoRepository.findById(id).orElse(null) == null) {
            return null;
        } else {
            pagamento.setId(id);
            return pagamentoRepository.save(pagamento);
        }
    }

    // Método que deleta um pagamento pelo id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable Long id) {
        // Tenta deletar o pagamento pelo id, caso não encontre, retorna notFound.
        try {
            pagamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
