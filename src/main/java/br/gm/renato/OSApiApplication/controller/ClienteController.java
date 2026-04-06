package br.gm.renato.OSApiApplication.controller;

import br.gm.renato.OSApiApplication.domain.model.Cliente;
import br.gm.renato.OSApiApplication.domain.repository.ClienteRepository;
import br.gm.renato.OSApiApplication.domain.service.ClienteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> listas() {
        return clienteRepository.findAll();
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @PutMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteID, @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteID);

        clienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{clienteID}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        clienteService.excluir(clienteID);
        return ResponseEntity.noContent().build();
    }
}
