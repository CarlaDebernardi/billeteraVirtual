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

    private String nombre;

    private Double BTC= 0.0;

    private Double ETH= 0.0;

    private Double ARS=0.0;

    private Double saldo= 0.0;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name="billetera_transaccion",
            joinColumns = @JoinColumn(name="billetera_id"),
            inverseJoinColumns = @JoinColumn (name="transaccion_id"))
    private List<Transaccion> transaccion;


}
