package com.devChallengue.WSCuentas.service;

import com.devChallengue.WSCuentas.dto.MovimientoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface IMovimientoService {
    MovimientoDTO registrarMovimiento(Long cuentaId, MovimientoDTO movimientoDTO);
    List<MovimientoDTO> getMovimientosByCuentaAndFecha(Long cuentaId, LocalDate fechaInicio, LocalDate fechaFin);
}
