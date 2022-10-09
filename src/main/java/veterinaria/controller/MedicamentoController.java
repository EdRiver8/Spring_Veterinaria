package veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import veterinaria.entity.Medicamento;
import veterinaria.service.MedicamentoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {


    private MedicamentoService medicamentoService;

    public MedicamentoController (MedicamentoService medicamentoService){
        this.medicamentoService = medicamentoService;
    }

    @PostMapping()
    private ResponseEntity<Medicamento> createMedicamento(@Valid @RequestBody Medicamento medicamento, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.createMedicamento(medicamento));
    }

    @GetMapping("/{id}")
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
