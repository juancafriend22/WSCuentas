package com.devChallengue.WSCuentas.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter

public class MovimientoDTO {
    private Long id;
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Long cuentaId;
}
