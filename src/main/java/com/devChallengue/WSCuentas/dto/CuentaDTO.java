package com.devChallengue.WSCuentas.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CuentaDTO {

    private String numeroCuenta;
    private String tipoCuenta;
    private Double SaldoInicial;
    private Boolean estado;
    private ClienteFeignDTO clienteFeignDTO;

}
