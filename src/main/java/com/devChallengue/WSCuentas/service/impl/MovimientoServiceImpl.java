package com.devChallengue.WSCuentas.service.impl;

import com.devChallengue.WSCuentas.dto.ClienteFeignDTO;
import com.devChallengue.WSCuentas.dto.MovimientoCustomResponseDTO;
import com.devChallengue.WSCuentas.dto.MovimientoDTO;
import com.devChallengue.WSCuentas.mapper.MovimientoCustomMapper;
import com.devChallengue.WSCuentas.mapper.MovimientoMapper;
import com.devChallengue.WSCuentas.model.Cuenta;
import com.devChallengue.WSCuentas.model.Movimiento;
import com.devChallengue.WSCuentas.repository.CuentaRepository;
import com.devChallengue.WSCuentas.repository.MovimientoRepository;
import com.devChallengue.WSCuentas.service.ClienteFeignClient;
import com.devChallengue.WSCuentas.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements IMovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoMapper movimientoMapper;

    @Autowired
    private ClienteFeignClient clienteFeign;

    @Autowired
    private MovimientoCustomMapper movimientoCustomMapper;

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
        movimiento.setFecha(LocalDate.now());

        Movimiento saved = movimientoRepository.save(movimiento);
        return movimientoMapper.toDTO(saved);
    }

    @Override
    public List<MovimientoCustomResponseDTO> getMovimientosByCuentaAndFecha(Long cuentaId, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(cuentaId, fechaInicio, fechaFin);
        return movimientos.stream().map(movimiento -> {
            Cuenta cuenta = movimiento.getCuenta();
            ClienteFeignDTO clienteFeignDTO = clienteFeign.getClienteById(cuenta.getClienteId());

            return movimientoCustomMapper.toCustomDTO(movimiento, cuenta, clienteFeignDTO);
        }).collect(Collectors.toList());
    }
}
