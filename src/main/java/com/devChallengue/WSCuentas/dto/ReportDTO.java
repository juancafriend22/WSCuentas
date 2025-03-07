package com.devChallengue.WSCuentas.dto;

import lombok.Setter;
import lombok.Getter;
import java.util.List;

@Getter
@Setter
public class ReportDTO {
    private CuentaDTO cuenta;
    private List<MovimientoDTO> movimientos;
}
