package com.devChallengue.WSCuentas.service.impl;

import com.devChallengue.WSCuentas.dto.MovimientoDTO;
import com.devChallengue.WSCuentas.mapper.MovimientoMapper;
import com.devChallengue.WSCuentas.model.Cuenta;
import com.devChallengue.WSCuentas.model.Movimiento;
import com.devChallengue.WSCuentas.repository.CuentaRepository;
import com.devChallengue.WSCuentas.repository.MovimientoRepository;
import com.devChallengue.WSCuentas.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IMovimientoServiceImpl implements IMovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoMapper movimientoMapper;
    @Override
    public MovimientoDTO registrarMovimiento(Long cuentaId, MovimientoDTO movimientoDTO) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Movimiento movimiento = movimientoMapper.toEntity(movimientoDTO);
        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
        if (nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible");
        }
        // Actualizamos el saldo en la cuenta
        cuenta.setSaldoInicial(nuevoSaldo);

        cuentaRepository.save(cuenta);

        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());

        Movimiento saved = movimientoRepository.save(movimiento);
        return movimientoMapper.toDTO(saved);
    }

    @Override
    public List<MovimientoDTO> getMovimientosByCuentaAndFecha(Long cuentaId, Date fechaInicio, Date fechaFin) {
        List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(cuentaId, fechaInicio, fechaFin);
        return movimientos.stream().map(movimientoMapper::toDTO).collect(Collectors.toList());
    }
}
