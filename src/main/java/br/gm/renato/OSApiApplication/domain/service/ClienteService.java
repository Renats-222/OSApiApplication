
package br.gm.renato.OSApiApplication.domain.service;

import br.gm.renato.OSApiApplication.domain.exception.DomainException;
import br.gm.renato.OSApiApplication.domain.model.Cliente;
import br.gm.renato.OSApiApplication.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new DomainException("Já existe um cliente cadastrado com esse email!");
        }

        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
