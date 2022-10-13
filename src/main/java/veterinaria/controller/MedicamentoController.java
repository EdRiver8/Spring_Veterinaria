package veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import veterinaria.entity.Medicamento;
import veterinaria.service.MedicamentoService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;


    //******* USANDO THYMELEAF PARA LOS TEMPLATES ***************/

    @GetMapping(value = {"", "/{id}"})
    public String nuevo(Model model, @PathVariable(required = false, name = "id") Long id){
        if(id == null){
            model.addAttribute("medicamento", new Medicamento()); // modelo de medicamento para llenarlo en el formulario
        }  
        // id != null, actualizar medicamento  
        else{
            // sino existe el id del medicamento            
            if(medicamentoService.findMedicamentoById(id) == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id de medicamento no encontrado en la DB.");
            }
            Medicamento medicamento = medicamentoService.findMedicamentoById(id);
            model.addAttribute("medicamento", medicamento);// llena el form con los datos de la medicamento encontrado            
        }    
        model.addAttribute("medicamentos", medicamentoService.findAllMedicamentos()); // lista de medicamentos guardadas
        return "medicamento"; // los dos modelos 'medicamento' y 'medicamentos' los envia al html 'medicamento'
    }

    @PostMapping(value = {"", "/{id}"})
    public String actualizarMedicamento(@Valid Medicamento medicamento, @PathVariable(name = "id", required = false) Long id, Model model){
        // si no tiene id, se va a crear la medicamentos
        if(id == null){
            model.addAttribute("medicamento", medicamentoService.createMedicamento(medicamento));
            return "redirect:/medicamentos";
        }
        // sino, se va a actualizar
        model.addAttribute("medicamento", medicamentoService.updateMedicamento(medicamento, id));
        return "redirect:/medicamentos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMedicamento(@PathVariable("id") Long id){
        if(medicamentoService.findMedicamentoById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id de medicamento no encontrado en la DB.");
        }
        medicamentoService.deleteMedicamento(id);
        return "redirect:/medicamentos";
    }







    //******* USANDO PETICIONES HTTP => *******/


    public MedicamentoController (MedicamentoService medicamentoService){
        this.medicamentoService = medicamentoService;
    }

    @PostMapping("/new")
    private ResponseEntity<Medicamento> createMedicamento(@Valid @RequestBody Medicamento medicamento, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.createMedicamento(medicamento));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Medicamento> findMedicamento(@PathVariable("id") Long id){
        if(medicamentoService.findMedicamentoById(id) == null){
            return new ResponseEntity<Medicamento>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(medicamentoService.findMedicamentoById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Medicamento>> findAllMedicamentos(){
        if(medicamentoService.findAllMedicamentos().isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicamentoService.findAllMedicamentos());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Medicamento> updateMedicamento(@Valid @RequestBody Medicamento medicamento, @PathVariable("id") Long id, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(medicamentoService.findMedicamentoById(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicamentoService.updateMedicamento(medicamento, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Medicamento> deleteMedicamento(@PathVariable("id") Long id){
        if(medicamentoService.findMedicamentoById(id) != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(medicamentoService.deleteMedicamento(id));
        }
        return ResponseEntity.notFound().build();
    }

}
