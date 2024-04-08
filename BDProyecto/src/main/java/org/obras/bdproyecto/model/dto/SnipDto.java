package org.obras.bdproyecto.model.dto;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
@Data
@ToString
@Builder
public class SnipDto implements Serializable {
    private Integer idSnip;
    private Integer noSnip;
    private String nombreProyecto;
}
