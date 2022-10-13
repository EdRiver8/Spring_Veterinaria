package veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import veterinaria.entity.Mascota;
import veterinaria.service.MascotaService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;


    //******* USANDO THYMELEAF PARA LOS TEMPLATES ***************/

    /**
     * Devuelve al endpoint un modelo mascota vacio si el id es nulo, o los datos del
     * modelo mascota encontrados en la db segun el id que se pase
     * @param model para generar la vista
     * @param id opcional, si se envia, se puede editar la info de la mascota encontrada, sino, se crea una nueva con los datos vacios
     * @return retorna la vista 'mascota' con un modelo mascota para ser llenado y la lista de mascotas en la db
     */
    @GetMapping(value = {"", "/{id}"})
    public String nueva(Model model, @PathVariable(required = false, name = "id") Long id){
        if(id == null){
            // Crear la mastoca
            model.addAttribute("mascota", new Mascota()); // modelo de mascota para llenarlo en el formulario
        }  
        // id != null, actualizar mascota  
        else{
            // sino existe el id de la mascota            
            if(mascotaService.findMascota(id) == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id de mascota no encontrado en la DB.");
            }
            Mascota mascota = mascotaService.findMascota(id);
            model.addAttribute("mascota", mascota);// llena el form con los datos de la mascota encontrada
            
        }    
        model.addAttribute("mascotas", mascotaService.findAllMascotas()); // lista de mascotas guardadas
        return "mascota"; // los dos modelos 'mascota' y 'mascotas' los envia al html 'mascota'
    }

    /**
     * Guarda la informacion suministrada del modelo mascota en el formulario
     * @param mascota, objeto de tipo mascota con la info para guardar en la db
     * @param id, es opcional, si se envia es porque se esta editando una mascota, sino, es porque se va a crear una nueva
     * @param model, vista a generar
     * @return redireccionamiento al endpoint '/mascotas'
     */
    @PostMapping(value = {"", "/{id}"})
    public String actualizarMascota(@Valid Mascota mascota, @PathVariable(name = "id", required = false) Long id, Model model){
        // si no tiene id, se va a crear la mascota
        if(id == null){
            model.addAttribute("mascota", mascotaService.creatMascota(mascota));
            return "redirect:/mascotas";
        }
        // sino, se va a actualizar
        model.addAttribute("mascota", mascotaService.updateMascota(mascota, id));
        return "redirect:/mascotas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable("id") Long id){
        if(mascotaService.findMascota(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id de mascota no encontrado en la DB.");
        }
        mascotaService.deleteMascota(id);
        return "redirect:/mascotas";
    }






    
    
    //******* USANDO PETICIONES HTTP => *******/

    @PostMapping("/new")
    public ResponseEntity<Mascota> createMascota(@Valid @RequestBody Mascota mascota, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.creatMascota(mascota));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Mascota> findMascota(@PathVariable("id") Long id){
        if(mascotaService.findMascota(id) != null){
            return ResponseEntity.ok(mascotaService.findMascota(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Mascota>> findAllMascotas(){
        if(mascotaService.findAllMascotas().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mascotaService.findAllMascotas());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mascota> updateMascota(@Valid @RequestBody Mascota mascota, @PathVariable("id") Long id, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(mascotaService.findMascota(id)==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mascotaService.updateMascota(mascota, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mascota> deleteMascota(@PathVariable Long id){
        if(mascotaService.findMascota(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mascotaService.deleteMascota(id));
    }

}
