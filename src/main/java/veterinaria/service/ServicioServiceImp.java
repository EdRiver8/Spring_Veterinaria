package veterinaria.service;

import org.springframework.stereotype.Service;
import veterinaria.entity.Servicio;
import veterinaria.repository.ServicioRepository;

import java.util.List;

@Service
public class ServicioServiceImp implements ServicioService{

    private ServicioRepository servicioRepository;

    public ServicioServiceImp (ServicioRepository servicioRepository){
        this.servicioRepository = servicioRepository;
    }

    @Override
    public Servicio createServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio findService(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Servicio> findAllServices() {
        if(servicioRepository.findAll().isEmpty()){
            return null;
        }
        return servicioRepository.findAll();
    }

    @Override
    public Servicio updateServicio(Servicio servicio, Long id) {
        if(findService(id) != null){
            servicio.setId(findService(id).getId());
            servicio.setFechaServicio(findService(id).getFechaServicio());
            return createServicio(servicio);
        }
        return null;
    }

    @Override
    public Servicio deleteServicio(Long id) {
        if(findService(id) != null){
            Servicio servicio = findService(id);
            servicioRepository.deleteById(id);
            return servicio;
        }
        return null;
    }
}
