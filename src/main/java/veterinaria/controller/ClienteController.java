package veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import veterinaria.entity.Cliente;
import veterinaria.service.ClienteService;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping()
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente){
        if(clienteService.createCliente(cliente) != null){
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
}
