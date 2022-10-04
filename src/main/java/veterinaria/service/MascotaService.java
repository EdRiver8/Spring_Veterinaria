package veterinaria.service;

import veterinaria.entity.Mascota;

import java.util.List;

public interface MascotaService {
    public Mascota creatMascota(Mascota mascota);
    public Mascota findMascota(Integer id);
    public List<Mascota> findAllMascotas();
    public Mascota updateMascota(Mascota mascota ,Integer id);
    public Mascota deleteMascota(Integer id);
}
