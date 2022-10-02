package veterinaria.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private String nombres;

    @NotBlank(message = "Debe ingresar los apellidos")
    private String apellidos;

    private String direccion;
    private String telefono;
}
