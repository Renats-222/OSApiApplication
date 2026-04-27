
package br.gm.renato.OSApiApplication.controller;

import br.gm.renato.OSApiApplication.domain.model.Comentario;
import br.gm.renato.OSApiApplication.domain.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ordem-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comentario adicionarComentario(@PathVariable Long ordemServicoId,
            @RequestBody Comentario comentario) {
        return ordemServicoService.adicionarComentario(ordemServicoId, comentario.getDescricao());
    }
}
