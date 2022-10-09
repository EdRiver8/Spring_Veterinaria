package veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import veterinaria.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
