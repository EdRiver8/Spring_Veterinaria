package veterinaria.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import veterinaria.entity.Cliente;
import veterinaria.service.ClienteService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    
    //******* USANDO THYMELEAF PARA LOS TEMPLATES ***************/

    @GetMapping()
    public String clientes(Model model){
        model.addAttribute("clientes", clienteService.findAllClients());
        // retorna la pagina web o la vista de todos los clientes
        return "clientes"; 
    }

    // redirecciona a la pagina de crear contacto
    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("cliente", new Cliente());
        return "cliente_form";
    }

    @PostMapping("/nuevo")
    public String addCliente(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes ra){
        // List<Mascota> mascotas = cliente.getMascota();
        if(result.hasErrors()){
            model.addAttribute("cliente", cliente);
            return "cliente_form";
        }
        if (cliente.getId() != null){
            logger.warn("Trying to create a Cliente with id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        model.addAttribute("cliente", clienteService.createCliente(cliente));
        ra.addFlashAttribute("msgExito", "Cliente Guardado!");
        return "redirect:/clientes";
    }

    @GetMapping("/{id}/editar")
    public String editCliente(Model model, @PathVariable("id") Long id){
        if(clienteService.findClienteById(id) == null){
            throw new EntityNotFoundException();
        }
        Cliente cliente = clienteService.findClienteById(id);
        model.addAttribute("cliente", cliente);
        return "cliente_form";
    }

    @PostMapping("/{id}/editar")
    public String actulizar(@PathVariable("id") Long id, @Validated Cliente cliente, BindingResult result, Model model, RedirectAttributes ra){
        // List<Mascota> mascotas = cliente.getMascota();
        if(clienteService.findClienteById(id) == null){
            throw new EntityNotFoundException();
        }
        if(result.hasErrors()){
            model.addAttribute("cliente", cliente);
            return "cliente_form";
        }
        model.addAttribute("cliente", clienteService.updateCliente(cliente, id));
        ra.addFlashAttribute("msgExito", "Cliente Actualizado!");
        return "redirect:/clientes";
    }




    //******* USANDO PETICIONES HTTP => *******/

    @GetMapping("all")
    public ResponseEntity<List<Cliente>> findAllClientes(){
        if(clienteService.findAllClients().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteService.findAllClients());
    }

    @PostMapping("/new")
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (cliente.getId() != null){
            logger.warn("Trying to create a Cliente with id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.createCliente(cliente));
    }

    @GetMapping("/find/{id}")
    @ApiOperation("Buscar un cliente por id")
    public ResponseEntity<Cliente> findCliente(@ApiParam("Clave primaria de tipo Long") @PathVariable("id") Long id){
        if(clienteService.findClienteById(id) != null){
//            List<Mascota> mascotas = (clienteService.findClienteById(1L).getMascota());
            return ResponseEntity.ok(clienteService.findClienteById(id));
        }
        return ResponseEntity.notFound().build();
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
        logger.warn("Trying to delete a non existent cliente");
        return ResponseEntity.notFound().build();
    }



}
