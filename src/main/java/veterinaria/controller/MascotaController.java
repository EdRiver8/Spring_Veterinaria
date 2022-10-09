package veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import veterinaria.entity.Mascota;
import veterinaria.service.MascotaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @PostMapping()
    public ResponseEntity<Mascota> createMascota(@Valid @RequestBody Mascota mascota, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.creatMascota(mascota));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> findMascota(@PathVariable("id") Integer id){
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
    public ResponseEntity<Mascota> updateMascota(@Valid @RequestBody Mascota mascota, @PathVariable("id") Integer id, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(mascotaService.findMascota(id)==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mascotaService.updateMascota(mascota, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mascota> deleteMascota(@PathVariable Integer id){
        if(mascotaService.findMascota(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mascotaService.deleteMascota(id));
    }

}
