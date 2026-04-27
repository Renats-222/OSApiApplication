package br.gm.renato.OSApiApplication.controller;

import br.gm.renato.OSApiApplication.domain.exception.dto.AtualizaStatusDTO;
import br.gm.renato.OSApiApplication.domain.model.Comentario;
import br.gm.renato.OSApiApplication.domain.model.OrdemServico;
import br.gm.renato.OSApiApplication.domain.model.StatusOrdemServico;
import br.gm.renato.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.gm.renato.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<OrdemServico> listarPorCliente(@PathVariable Long clienteId) {
        return ordemServicoRepository.findByClienteId(clienteId);
    }

    // ... outros métodos acima
    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordemServicoId) {
        // Buscamos a OS pelo ID. Se achar, retorna 200 OK. Se não, 404 Not Found.
        return ordemServicoRepository.findById(ordemServicoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(
            @PathVariable Long ordemServicoID,
            @Valid @RequestBody AtualizaStatusDTO statusDTO) {

        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
                ordemServicoID,
                statusDTO.status());

        if (optOS.isPresent()) {
            return ResponseEntity.ok(optOS.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/cliente/{clienteId}/status/{status}")
    public List<OrdemServico> listarPorClienteEStatus(
            @PathVariable Long clienteId,
            @PathVariable StatusOrdemServico status) {

        return ordemServicoRepository.findByClienteIdAndStatus(clienteId, status);
    }
    @GetMapping("/com-comentarios")
    public List<OrdemServico> listarComComentarios() {
        return ordemServicoRepository.findByComentariosIsNotEmpty();
    }

    @GetMapping("/sem-comentarios")
    public List<OrdemServico> listarSemComentarios() {
        return ordemServicoRepository.findByComentariosIsEmpty();
    }
}
