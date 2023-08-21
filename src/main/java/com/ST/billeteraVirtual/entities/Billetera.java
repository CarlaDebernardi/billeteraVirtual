package com.ST.billeteraVirtual.entities;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="billetera")
@Entity
public class Billetera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*@Column(name="BTC")*/
    @OneToOne
    private Usuario usuario;

    private Double BTC= 0.0;

   /* @Column(name="ETH")*/
    private Double ETH= 0.0;

    /*@Column(name="ARS")*/
    private Double ARS=0.0;

    /*@Column(name="saldoEnARS")*/
    private Double saldo= 0.0;

    @OneToMany /*(cascade = CascadeType.ALL, orphanRemoval = true)*/
    /*@JoinTable(
            name="billetera_transaccion",
            joinColumns = @JoinColumn(name="billetera_id"),
            inverseJoinColumns = @JoinColumn (name="transaccion_id"))*/
    private List<Transaccion> transaccion;


}
