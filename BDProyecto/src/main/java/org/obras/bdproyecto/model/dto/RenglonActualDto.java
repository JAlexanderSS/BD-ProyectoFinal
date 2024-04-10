package org.obras.bdproyecto.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@Builder
public class RenglonActualDto implements Serializable {
    private Integer idRenglonActual;
    private Integer idSmip;
    private Integer numRenglonTrabajo;
    private String renglonTrabajo;
    private String unidadMedida;
    private BigDecimal cantidad;
    private BigDecimal costoUnitario;
    private BigDecimal costoTotal;
}
