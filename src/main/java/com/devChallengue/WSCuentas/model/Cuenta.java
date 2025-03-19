package com.devChallengue.WSCuentas.model;

import com.devChallengue.WSCuentas.service.ClienteFeignClient;
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
    @Column(unique = true)
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    // Se almacena el id del cliente (obtenido del microservicio de clientes)
    private Long clienteId;
}