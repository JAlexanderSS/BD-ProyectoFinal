package org.obras.bdproyecto.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="RENGLON_INICIAL" )
public class RenglonInicial implements Serializable {
    @Id
    @Column(name = "id_renglon_inicial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRenglonInicial;
    @Column(name = "id_smip")
    private Integer idSmip;
    @Column(name = "num_renglon_trabajo")
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
