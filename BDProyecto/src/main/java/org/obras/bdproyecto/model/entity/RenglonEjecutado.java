package org.obras.bdproyecto.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "RENGLON_EJECUTADO")
public class RenglonEjecutado {
    @Id
    @Column(name = "id_renglon_ejecutado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRenglonEjecutado;
    @Column(name = "id_smip")
    private Integer idSmip;
    @Column(name = "num_renglon_tranajo")
    private Integer numRenglonTrabajo;
    @Column(name = "renglon_trabajo")
    private String renglonTrabajo;
    @Column(name = "unidad_medida")
    private String unidadMedida;
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Column(name = "costo_unitario")
    private BigDecimal costoUnitario;
    @Column(name = "costo_total")
    private BigDecimal costoTotal;
}
