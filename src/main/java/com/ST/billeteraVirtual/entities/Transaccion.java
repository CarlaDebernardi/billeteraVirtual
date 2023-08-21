package com.ST.billeteraVirtual.entities;

import com.ST.billeteraVirtual.enumerations.TipoDeOperacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="transaccion")

public class Transaccion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    /*@Column(name="FechaHora")*/
    private Date FechaHora=new Date();

    /*@Column(name="tipo")*/
    @Enumerated(EnumType.STRING)
    private TipoDeOperacion tipoDeOperacion;

    @ManyToOne
    /*@JoinColumn(name = "billeteraOrigen_id")*/
    private Billetera billeteraOrigen;

    @ManyToOne
    /*@JoinColumn(name = "billeteraDestino_id")*/
    private Billetera billeteraDestino;

   /* @Column(name="monedaOrigen")*/
    private Double montoMonedaOrigen;

    /*@Column(name="monedaDestino")*/
    private Double montoMonedaDestino;

    private String monedaOrigen;

    private String monedaDestino;

}
