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
@Table(name ="TIPO_MODIFICACION")
public class TipoModificacion implements Serializable {
    @Id
    @Column(name = "id_tipo_modificacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoModificacion;
    @Column(name = "modificacion")
    private String modificacion;
}
