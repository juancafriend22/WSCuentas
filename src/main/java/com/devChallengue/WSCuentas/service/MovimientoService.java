package com.devChallengue.WSCuentas.service;

import com.devChallengue.WSCuentas.dto.MovimientoDTO;
import com.devChallengue.WSCuentas.model.Cuenta;
import com.devChallengue.WSCuentas.model.Movimiento;
import com.devChallengue.WSCuentas.repository.CuentaRepository;
import com.devChallengue.WSCuentas.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface MovimientoService {
    MovimientoDTO registrarMovimiento(Long cuentaId, MovimientoDTO movimientoDTO);
    List<MovimientoDTO> getMovimientosByCuentaAndFecha(Long cuentaId, Date fechaInicio, Date fechaFin);
}
