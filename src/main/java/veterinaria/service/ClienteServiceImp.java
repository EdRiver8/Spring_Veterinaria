package veterinaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import veterinaria.entity.Cliente;
import veterinaria.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteServiceImp implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findClienteById(Long id) {
        //clienteRepository.findById(id).orElse(null);
        if(clienteRepository.findClienteById(id) != null){
            return clienteRepository.findClienteById(id);
        }
        return null;
    }

    @Override
    public List<Cliente> findAllClients() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente updateCliente(Cliente cliente, Long id) {
        if(findClienteById(id) != null){
            // 'cliente' a actualizar llega sin id porque no se ha guardado en db, por ello se asigna el
            // id del cliente encontrado y se guarda el cliente que ingresa a actualizar
            cliente.setId(findClienteById(id).getId());
            return clienteRepository.save(cliente);
        }
        return null;
    }

    @Override
    public Cliente deleteCliente(Long id) {
        if(findClienteById(id) != null){
            Cliente cliente = findClienteById(id);
            clienteRepository.deleteById(id);
            return cliente;
        }
        return null;
    }
}
