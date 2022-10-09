package veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import veterinaria.entity.Servicio;
import veterinaria.service.ServicioService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @PostMapping()
    public ResponseEntity<Servicio> createServicio(@Valid @RequestBody Servicio servicio, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioService.createServicio(servicio));
    }

    @GetMapping("/{id}")
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
