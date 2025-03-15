package com.devChallengue.WSCuentas.controller;

import com.devChallengue.WSCuentas.dto.MovimientoCustomResponseDTO;
import com.devChallengue.WSCuentas.dto.MovimientoDTO;
import com.devChallengue.WSCuentas.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private IMovimientoService movimientoService;

    @PostMapping("/cuenta/{cuentaId}")
    public ResponseEntity<MovimientoDTO> registrarMovimiento(@PathVariable Long cuentaId,
                                                             @RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO registrado = movimientoService.registrarMovimiento(cuentaId, movimientoDTO);
        return new ResponseEntity<>(registrado, HttpStatus.CREATED);
    }
    @GetMapping("/reporte/{cuentaId}")
    public ResponseEntity<List<MovimientoCustomResponseDTO>> getMovimientosByCuentaAndFecha(
            @PathVariable Long cuentaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        List<MovimientoCustomResponseDTO> movimientos = movimientoService.getMovimientosByCuentaAndFecha(cuentaId, fechaInicio, fechaFin);
        return ResponseEntity.ok(movimientos);
    }

}
