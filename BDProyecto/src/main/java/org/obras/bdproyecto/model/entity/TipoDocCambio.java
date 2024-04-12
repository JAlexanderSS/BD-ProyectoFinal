package org.obras.bdproyecto.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TIPO_DOC_CAMBIO")
public class TipoDocCambio implements Serializable {
    @Id
    @Column(name = "id_tipo_doc_cambio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoDocCambio;
    @Column(name = "tipo_documento_cambio")
    private String tipoDocumentoCambio;
}
