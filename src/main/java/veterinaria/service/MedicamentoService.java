package veterinaria.service;

import veterinaria.entity.Cliente;
import veterinaria.entity.Medicamento;

import java.util.List;

public interface MedicamentoService {

    public Medicamento createMedicamento(Medicamento medicamento);
    public Medicamento findMedicamentoById(Long id);
    public List<Medicamento> findAllMedicamentos();
    public Medicamento updateMedicamento(Medicamento medicamento, Long id);
    public Medicamento deleteMedicamento(Long id);

}
