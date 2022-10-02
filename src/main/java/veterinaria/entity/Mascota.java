package veterinaria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "mascotas")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Ingrese el nombre de la mascota")
    private String nombre;
    private String raza;
    private Integer edad;

    @NotEmpty(message = "Ingrese el peso de la mascota")
    private Float peso;
}
