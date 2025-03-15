package com.devChallengue.WSCuentas.service;

import com.devChallengue.WSCuentas.dto.CuentaDTO;

import java.util.List;


public interface ICuentaService {
    CuentaDTO createCuenta(CuentaDTO cuentaDTO);
    CuentaDTO updateCuenta(Long id, CuentaDTO cuentaDTO);
    void deleteCuenta(Long id);
    CuentaDTO getCuentaById(Long id);
    List<CuentaDTO> getAllCuentas();

}