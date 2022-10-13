package veterinaria.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
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

    @Min(value = 1, message = "Ingrese el peso de la mascota")
    private Float peso;

    @ManyToOne(optional = true)
    @JoinColumn(name = "cliente_id", updatable = true, nullable = true) //  nombre de la columna en la db
    //    @NotNull(message = "Debe ingresar la moscata que trajo el cliente")
    @JsonBackReference // solo carga la mascota desde el cliente, mas no se carga el cliente cuando se busque la mascota
    private Cliente cliente;

   @OneToMany(mappedBy = "mascota", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JsonManagedReference
   private List<Servicio> servicio;
}
