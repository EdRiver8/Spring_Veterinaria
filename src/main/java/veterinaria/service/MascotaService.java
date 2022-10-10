package veterinaria.service;

import veterinaria.entity.Mascota;

import java.util.List;

public interface MascotaService {
    public Mascota creatMascota(Mascota mascota);
    public Mascota findMascota(Long id);
    public List<Mascota> findAllMascotas();
    public Mascota updateMascota(Mascota mascota ,Long id);
    public Mascota deleteMascota(Long id);
}
