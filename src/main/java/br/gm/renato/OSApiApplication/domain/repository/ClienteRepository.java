package br.gm.renato.OSApiApplication.domain.repository;

import br.gm.renato.OSApiApplication.domain.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String Nome);
    List<Cliente> findByNomeContaining(String Nome);
    Cliente findByEmail(String email);
}