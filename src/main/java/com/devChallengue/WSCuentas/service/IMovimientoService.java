package com.devChallengue.WSCuentas.service;

import com.devChallengue.WSCuentas.dto.MovimientoCustomResponseDTO;
import com.devChallengue.WSCuentas.dto.MovimientoDTO;

import java.time.LocalDate;
import java.util.List;


public interface IMovimientoService {
    MovimientoDTO registrarMovimiento(Long cuentaId, MovimientoDTO movimientoDTO);
    List<MovimientoCustomResponseDTO> getMovimientosByCuentaAndFecha(Long cuentaId, LocalDate fechaInicio, LocalDate fechaFin);
}
