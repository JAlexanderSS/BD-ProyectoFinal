package org.obras.bdproyecto.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ProrrogaDto implements Serializable {
    private Integer idProrroga;
    private Integer idModificacion;
    private String justificacion;
    private Date fechaFinalModificada;
    private String numeroActa;
    private Date fechaActa;
    private String urlActa;
}
