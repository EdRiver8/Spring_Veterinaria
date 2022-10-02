package veterinaria.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
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

    @Size(max = 15)
    private String telefono;
}
