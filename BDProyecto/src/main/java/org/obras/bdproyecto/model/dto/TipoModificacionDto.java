package org.obras.bdproyecto.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class TipoModificacionDto implements Serializable {
    private Integer idTipoModificacion;
    private String modificacion;
}
