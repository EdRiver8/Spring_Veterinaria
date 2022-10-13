package veterinaria.service;

import org.springframework.stereotype.Service;
import veterinaria.entity.Mascota;
import veterinaria.repository.MascotaRepository;

import java.util.List;

@Service
public class MascotaServiceImp implements MascotaService{


    private final MascotaRepository mascotaRepository;

    public MascotaServiceImp (MascotaRepository mascotaRepository){
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public Mascota creatMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota findMascota(Long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Mascota> findAllMascotas() {
        if(mascotaRepository.findAll().isEmpty()){
            return null;
        }
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota updateMascota(Mascota mascota, Long id) {
        if(findMascota(id) != null){
            mascota.setId(findMascota(id).getId());
            return mascotaRepository.save(mascota);
        }
        return null;
    }

    @Override
    public Mascota deleteMascota(Long id) {
        if(findMascota(id) != null){
            Mascota mascota = findMascota(id);
            mascotaRepository.delete(findMascota(id));
            return mascota;
        }
        return null;
    }
}
