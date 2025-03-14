package com.devChallengue.WSCuentas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
}
