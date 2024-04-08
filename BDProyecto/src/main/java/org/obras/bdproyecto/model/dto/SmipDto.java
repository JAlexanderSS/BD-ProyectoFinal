package org.obras.bdproyecto.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@Builder
public class SmipDto implements Serializable {
    private Integer idSmip;
    private Integer numeroSmip;
    private Integer idSnip;
    private BigDecimal monto;
}
