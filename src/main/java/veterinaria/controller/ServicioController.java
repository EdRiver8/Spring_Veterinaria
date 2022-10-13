package veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import veterinaria.entity.Servicio;
import veterinaria.service.ServicioService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;


    //******* USANDO THYMELEAF PARA LOS TEMPLATES ***************/

    @GetMapping(value = {"", "/{id}"})
    public String nuevo(Model model, @PathVariable(required = false, name = "id") Long id){
        if(id == null){
            model.addAttribute("servicio", new Servicio()); // modelo de servicio para llenarlo en el formulario
        }  
        // id != null, actualizar servicio  
        else{
            // sino existe el id del servbicio            
            if(servicioService.findService(id) == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id de servicio no encontrado en la DB.");
            }
            Servicio servicio = servicioService.findService(id);
            model.addAttribute("servicio", servicio);// llena el form con los datos de la servicio encontrado            
        }    
        model.addAttribute("servicios", servicioService.findAllServices()); // lista de servicios guardadas
        return "servicio"; // los dos modelos 'servicio' y 'servicios' los envia al html 'servicio'
    }

    @PostMapping(value = {"", "/{id}"})
    public String actualizarServicio(@Valid Servicio servicio, @PathVariable(name = "id", required = false) Long id, Model model){
        // si no tiene id, se va a crear la servicio
        if(id == null){
            model.addAttribute("servicio", servicioService.createServicio(servicio));
            return "redirect:/servicios";
        }
        // sino, se va a actualizar
        model.addAttribute("servicio", servicioService.updateServicio(servicio, id));
        return "redirect:/servicios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable("id") Long id){
        if(servicioService.findService(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id de servicio no encontrado en la DB.");
        }
        servicioService.deleteServicio(id);
        return "redirect:/servicios";
    }







    //******* USANDO PETICIONES HTTP => *******/

    @PostMapping("/new")
    public ResponseEntity<Servicio> createServicio(@Valid @RequestBody Servicio servicio, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioService.createServicio(servicio));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Servicio> findServicio(@PathVariable("id") Long id){
        if(servicioService.findService(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(servicioService.findService(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Servicio>> findAll(){
        if(servicioService.findAllServices().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(servicioService.findAllServices());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Servicio> updateServicio(@Valid @RequestBody Servicio servicio, @PathVariable("id") Long id, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(servicioService.findService(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body(servicioService.updateServicio(servicio, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Servicio> deleteServicio(@PathVariable("id") Long id){
        if (servicioService.findService(id) != null){
            return ResponseEntity.ok(servicioService.deleteServicio(id));
        }
        return ResponseEntity.notFound().build();
    }

}
