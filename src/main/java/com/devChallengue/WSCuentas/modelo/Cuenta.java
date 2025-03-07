package com.devChallengue.WSCuentas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCuenta;

    private String tipoCuenta;
    private Double SaldoInicial;
    private Boolean estado;


    @Column(name = "cliente_id")
    private Long clienteId;
}
