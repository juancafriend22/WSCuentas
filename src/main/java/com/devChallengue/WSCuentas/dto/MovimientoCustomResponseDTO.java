package com.devChallengue.WSCuentas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoCustomResponseDTO {
    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private Boolean estado;
    private Double movimiento;
    private Double saldoDisponible;
}
