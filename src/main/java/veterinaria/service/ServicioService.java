package veterinaria.service;

import veterinaria.entity.Servicio;

import java.util.List;

public interface ServicioService {
    public Servicio createServicio(Servicio servicio);
    public Servicio findService(Long id);
    public List<Servicio> findAllServices();
    public Servicio updateServicio(Servicio servicio, Long id);
    public Servicio deleteServicio(Long id);
}
