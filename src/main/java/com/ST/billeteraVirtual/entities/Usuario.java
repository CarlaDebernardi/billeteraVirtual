package com.ST.billeteraVirtual.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*Para casos reales, utilizar @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")*/
    private Long id;

    /*@Column(name="dni")*/
    private String dni;

    /*@Column(name="sexo")*/
    private String sexo;

    /*@Column(name="nombre")*/
    private String nombre;

    /*@Column(name="apellido")*/
    private String apellido;

    /*@Column(name="email")*/
    private String email;

    /*@Column(name="telefono")*/
    private String telefono;

   /* @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name="usuario_billetera",
            joinColumns = @JoinColumn(name="usuario_id"),
            inverseJoinColumns = @JoinColumn (name="billetera_id")
    )*/
    @OneToMany
    private List<Billetera> billeteras= new ArrayList<>();

    /*@Column(name="saldoTotal")*/
    private Double saldoTotal;  /*Es la suma del saldo de todas las billeteras*/

}
