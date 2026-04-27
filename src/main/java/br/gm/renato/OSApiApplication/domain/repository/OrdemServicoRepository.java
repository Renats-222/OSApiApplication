package br.gm.renato.OSApiApplication.domain.repository;

import br.gm.renato.OSApiApplication.domain.model.OrdemServico;
import br.gm.renato.OSApiApplication.domain.model.StatusOrdemServico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByClienteId(Long clienteId);
    List<OrdemServico> findByClienteIdAndStatus(Long clienteId, StatusOrdemServico status);
    List<OrdemServico> findByComentariosIsNotEmpty();
    List<OrdemServico> findByComentariosIsEmpty();
}

