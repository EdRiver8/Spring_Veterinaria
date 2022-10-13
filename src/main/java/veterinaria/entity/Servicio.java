package veterinaria.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreationTimestamp
    @Column(name = "creado")
    private LocalDate fechaServicio;

    @UpdateTimestamp
    @Column(name = "actualizado")
    private LocalDate fechaServicioActualizacion;

    @NotBlank(message = "Debe ingresar una descripcion del servicio")
    @Column(name = "descripcion")
    private String descripcion;

   @ManyToOne
   @JoinColumn(name = "mascota_id", nullable = true)
   @JsonBackReference
   private Mascota mascota;

   @ManyToOne
   @JoinColumn(name = "medicamento_id", updatable = false, nullable = false)
   @JsonBackReference
   private Medicamento medicamento;
//
//    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Medicamento> medicamento;

}
