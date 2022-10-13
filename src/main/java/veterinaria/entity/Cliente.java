package veterinaria.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debe ingresar el numero de cedula")
    private Integer cedula;

    @NotBlank(message = "Debe ingresar el nombre")
    @Size(max = 20)
    private String nombres;

    @NotBlank(message = "Debe ingresar los apellidos")
    private String apellidos;

    private String direccion;

    @Size(min = 7, max = 13, message = "Ingrese un numero valido de 7-15 digitos")
    private String telefono;

//    mapeado por el nombre del atributo con el que se relaciona
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Mascota> mascota;
}
