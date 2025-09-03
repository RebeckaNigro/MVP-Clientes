package com.example.listadeclientes.api;

import com.example.listadeclientes.modelo.Cliente;
import com.example.listadeclientes.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

    /**
	 *
	 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteApiController {

    private final ClienteRepository clienteRepository;

    public ClienteApiController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listagem paginada com filtros opcionais
    @GetMapping
    public Page<Cliente> listarPaginado(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate dataNascimento,
            Pageable pageable) {

        Specification<Cliente> spec = Specification.where(null);

        if (nome != null && !nome.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }
        if (cpf != null && !cpf.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("cpf"), cpf));
        }
        if (email != null && !email.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
        }
        if (dataNascimento != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dataNascimento"), dataNascimento));
        }

        return clienteRepository.findAll(spec, pageable);
    }

    @GetMapping("/listar")
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @PostMapping("/salvar")
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        Cliente salvo = clienteRepository.save(cliente);
        return ResponseEntity
                .created(URI.create("/api/cliente/" + salvo.getId()))
                .body(salvo);
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
    }
    @PutMapping("/editar/{id}")
    public Cliente editar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));

        existente.setNome(cliente.getNome());
        existente.setCpf(cliente.getCpf());
        existente.setDataNascimento(cliente.getDataNascimento());
        existente.setEmail(cliente.getEmail());

        return clienteRepository.save(existente);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
        clienteRepository.delete(existente);
        return ResponseEntity.noContent().build();
    }

}
