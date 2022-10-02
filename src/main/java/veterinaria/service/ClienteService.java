package veterinaria.service;

import veterinaria.entity.Cliente;

import java.util.List;

public interface ClienteService {
    public Cliente createCliente(Cliente cliente);
    public Cliente findClienteById(Long id);
    public List<Cliente> findAllClients();
    public Cliente updateCliente(Cliente cliente, Long id);
    public Cliente deleteCliente(Long id);
}
