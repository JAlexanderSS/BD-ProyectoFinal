package org.obras.bdproyecto.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@Builder
public class RenglonPendienteDto implements Serializable {
    private Integer idRenglonPendiente;
    private Integer idSmip;
    private Integer numRenglonTrabajo;
    private String renglonTrabajo;
    private String unidadMedida;
    private Integer cantidad;
    private BigDecimal costoUnitario;
    private BigDecimal costoTotal;
}
