package org.obras.bdproyecto.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class TipoDocCambioDto implements Serializable {
    private Integer idTipoDocCambio;
    private String tipoDocumentoCambio;
}
