package br.gm.renato.OSApiApplication.controller;

import br.gm.renato.OSApiApplication.domain.model.OrdemServico;
import br.gm.renato.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.gm.renato.OSApiApplication.domain.service.OrdemServicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
}