package com.devChallengue.WSCuentas.controller;

import com.devChallengue.WSCuentas.dto.MovimientoDTO;
import com.devChallengue.WSCuentas.model.Cuenta;
import com.devChallengue.WSCuentas.model.Movimiento;
import com.devChallengue.WSCuentas.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping("/cuenta/{cuentaId}")
    public ResponseEntity<MovimientoDTO> registrarMovimiento(@PathVariable Long cuentaId,
                                                             @RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO registrado = movimientoService.registrarMovimiento(cuentaId, movimientoDTO);
        return new ResponseEntity<>(registrado, HttpStatus.CREATED);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<MovimientoDTO>> getMovimientosByCuentaAndFecha(
            @PathVariable Long cuentaId,
            @RequestParam Date fechaInicio,
            @RequestParam Date fechaFin) {
        List<MovimientoDTO> movimientos = movimientoService.getMovimientosByCuentaAndFecha(cuentaId, fechaInicio, fechaFin);
        return ResponseEntity.ok(movimientos);
    }
}
