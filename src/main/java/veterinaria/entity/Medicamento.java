package veterinaria.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotEmpty
    private String dosis;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "medicamento")
//    @JsonManagedReference
//    private List<Servicio> servicio;

//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "servicio_id", nullable = true)
//    private Servicio servicio;
}
