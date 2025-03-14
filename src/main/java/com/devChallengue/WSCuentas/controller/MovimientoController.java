package com.devChallengue.WSCuentas.controller;

import com.devChallengue.WSCuentas.dto.MovimientoDTO;
import com.devChallengue.WSCuentas.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private IMovimientoService IMovimientoService;

    @PostMapping("/cuenta/{cuentaId}")
    public ResponseEntity<MovimientoDTO> registrarMovimiento(@PathVariable Long cuentaId,
                                                             @RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO registrado = IMovimientoService.registrarMovimiento(cuentaId, movimientoDTO);
        return new ResponseEntity<>(registrado, HttpStatus.CREATED);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<MovimientoDTO>> getMovimientosByCuentaAndFecha(
            @PathVariable Long cuentaId,
            @RequestParam Date fechaInicio,
            @RequestParam Date fechaFin) {
        List<MovimientoDTO> movimientos = IMovimientoService.getMovimientosByCuentaAndFecha(cuentaId, fechaInicio, fechaFin);
        return ResponseEntity.ok(movimientos);
    }
}
