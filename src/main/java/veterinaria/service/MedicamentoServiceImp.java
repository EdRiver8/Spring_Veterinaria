package veterinaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import veterinaria.entity.Medicamento;
import veterinaria.repository.MedicamentoRepository;

import java.util.List;

@Service
public class MedicamentoServiceImp implements MedicamentoService{

    private MedicamentoRepository medicamentoRepository;

    @Autowired
    public MedicamentoServiceImp (MedicamentoRepository medicamentoRepository){
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public Medicamento createMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public Medicamento findMedicamentoById(Long id) {
        return medicamentoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Medicamento> findAllMedicamentos() {
        if(medicamentoRepository.findAll().isEmpty()){
            return null;
        }
        return medicamentoRepository.findAll();
    }

    @Override
    public Medicamento updateMedicamento(Medicamento medicamento, Long id) {
        if(findMedicamentoById(id)!=null){
            medicamento.setId(findMedicamentoById(id).getId());
            return createMedicamento(medicamento);
        }
        return null;
    }

    @Override
    public Medicamento deleteMedicamento(Long id) {
        if(findMedicamentoById(id)!=null){
            Medicamento medicamento = findMedicamentoById(id);
            medicamentoRepository.deleteById(id);
            return  medicamento;
        }
        return null;
    }
}
