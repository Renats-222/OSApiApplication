package br.gm.renato.OSApiApplication.controller;

import br.gm.renato.OSApiApplication.domain.model.Comentario;
import br.gm.renato.OSApiApplication.domain.repository.ComentarioRepository;
import br.gm.renato.OSApiApplication.domain.service.OrdemServicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping
    public List<Comentario> listarPorOrdemServico(@PathVariable Long ordemServicoId) {
        return comentarioRepository.findByOrdemServicoId(ordemServicoId);
    }

    @GetMapping("/{comentarioId}")
    public ResponseEntity<Comentario> buscarPorId(
            @PathVariable Long ordemServicoId,
            @PathVariable Long comentarioId) {

        return comentarioRepository.findById(comentarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long ordemServicoId, 
            @PathVariable Long comentarioId) {
        
        // Verifica se o comentário existe antes de tentar apagar
        if (!comentarioRepository.existsById(comentarioId)) {
            return ResponseEntity.notFound().build();
        }
        
        // Se existir, apaga do banco de dados
        comentarioRepository.deleteById(comentarioId);
        
        // Retorna 204 No Content (Padrão REST para deleção com sucesso)
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{comentarioId}")
    public ResponseEntity<Comentario> atualizar(
            @PathVariable Long ordemServicoId,
            @PathVariable Long comentarioId,
            @RequestBody Comentario comentario) {

        return comentarioRepository.findById(comentarioId)
                .map(comentarioExistente -> {
                    // Atualizamos apenas a descrição enviada no JSON
                    comentarioExistente.setDescricao(comentario.getDescricao());
                    
                    // Salvamos a alteração no banco
                    comentarioRepository.save(comentarioExistente);
                    
                    return ResponseEntity.ok(comentarioExistente);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
