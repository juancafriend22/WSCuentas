package com.devChallengue.WSCuentas.controller;

import com.devChallengue.WSCuentas.dto.CuentaDTO;
import com.devChallengue.WSCuentas.service.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private ICuentaService ICuentaService;

    @PostMapping
    public ResponseEntity<CuentaDTO> createCuenta(@RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO created = ICuentaService.createCuenta(cuentaDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTO> updateCuenta(@PathVariable Long id, @RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO updated = ICuentaService.updateCuenta(id, cuentaDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        ICuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> getCuentaById(@PathVariable Long id) {
        CuentaDTO cuenta = ICuentaService.getCuentaById(id);
        return ResponseEntity.ok(cuenta);
    }

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> getAllCuentas() {
        List<CuentaDTO> cuentas = ICuentaService.getAllCuentas();
        return ResponseEntity.ok(cuentas);
    }
}