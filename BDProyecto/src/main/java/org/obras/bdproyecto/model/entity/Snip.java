package org.obras.bdproyecto.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="SNIP" )
public class Snip implements Serializable {
    @Id
    @Column(name = "id_snip")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSnip;
    @Column(name = "numero_snip")
    private Integer noSnip;
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
}
