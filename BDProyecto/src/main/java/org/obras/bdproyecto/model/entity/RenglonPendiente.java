package org.obras.bdproyecto.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "RENLGON_PENDIENTE")
public class RenglonPendiente {
    @Id
    @Column(name = "id_renglon_pendiente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRenglonPendiente;
    @Column(name = "id_smip")
    private Integer idSmip;
    @Column(name = "num_renglon_trabajo")
    private Integer numRenglonTrabajo;
    @Column(name = "renglon_trabajo")
    private String renglonTrabajo;
    @Column(name = "unidad_medida")
    private String unidadMedida;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "costo_unitario")
    private BigDecimal costoUnitario;
    @Column(name = "costo_total")
    private BigDecimal costoTotal;
}
