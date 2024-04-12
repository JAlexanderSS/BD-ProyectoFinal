package org.obras.bdproyecto.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "PRORROGA")
public class Prorroga implements Serializable {
    @Id
    @Column(name = "id_prorroga")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProrroga;
    @Column(name = "id_modificacion")
    private Integer idModificacion;
    @Column(name = "justificacion")
    private String justificacion;
    @Column(name = "fecha_final_modificada")
    private Date fechaFinalModificada;
    @Column(name = "numero_acta")
    private String numeroActa;
    @Column(name = "fecha_acta")
    private Date fechaActa;
    @Column(name = "url_acta")
    private String urlActa;
}
