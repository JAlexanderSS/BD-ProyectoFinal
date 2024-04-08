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
@Table(name ="SMIP" )
public class Smip implements Serializable {
    @Id
    @Column(name = "id_smip")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSmip;
    @Column(name = "numero_smip")
    private Integer numeroSmip;
    @Column(name = "id_snip")
    private Integer idSnip;
    @Column(name = "monto")
    private BigDecimal monto;
}
