package veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import veterinaria.entity.Cliente;
import veterinaria.service.ClienteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping()
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(clienteService.createCliente(cliente) != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.createCliente(cliente));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findCliente(@PathVariable("id") Long id){
        if(clienteService.findClienteById(id) != null){
            return ResponseEntity.ok(clienteService.findClienteById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> findAllClientes(){
        if(clienteService.findAllClients().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteService.findAllClients());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable("id") Long id){
        if(clienteService.updateCliente(cliente, id) != null){
            return ResponseEntity.ok(clienteService.updateCliente(cliente, id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable("id") Long id){
        if(clienteService.deleteCliente(id) != null){
            return ResponseEntity.ok(clienteService.deleteCliente(id));
        }
        return ResponseEntity.notFound().build();
    }



}
