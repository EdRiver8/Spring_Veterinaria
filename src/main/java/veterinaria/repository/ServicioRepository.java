package veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import veterinaria.entity.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
