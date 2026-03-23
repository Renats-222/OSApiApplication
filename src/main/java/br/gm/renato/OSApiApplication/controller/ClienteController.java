package br.gm.renato.OSApiApplication.controller;

import br.gm.renato.OSApiApplication.domain.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController 
public class ClienteController {
    
    List<Cliente> listaClientes;
    
    @GetMapping("/clientes")
    public List<Cliente> listas() {
        List<Cliente> listaClientes = new ArrayList<>();

        listaClientes.add(new Cliente(1, "KGe", "kge@teste.com", "11-99999-9999"));
        listaClientes.add(new Cliente(2, "Maria", "maria@teste.com", "11-88888-8888"));
        listaClientes.add(new Cliente(3, "Joao", "joao@teste.com", "11-77777-7777"));

        return listaClientes;
    }
}