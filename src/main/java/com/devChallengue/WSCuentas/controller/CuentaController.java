package com.devChallengue.WSCuentas.controller;

import com.devChallengue.WSCuentas.dto.CuentaDTO;
import com.devChallengue.WSCuentas.mapper.CuentaMapper;
import com.devChallengue.WSCuentas.model.Cuenta;
import com.devChallengue.WSCuentas.service.ICuentaService;
import com.devChallengue.WSCuentas.service.impl.CuentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final ICuentaService cuentaService;

    // Inyección de dependencias por constructor
    public CuentaController(ICuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }
    @PostMapping
    public ResponseEntity<CuentaDTO> createAccount(@RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO created = cuentaService.createCuenta(cuentaDTO);
        System.out.println("El nombre del titular es ss"+created.getClienteFeignDTO().getNombre());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTO> updateCuenta(@PathVariable Long id, @RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO updated = cuentaService.updateCuenta(id, cuentaDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> getCuentaById(@PathVariable Long id) {
        CuentaDTO cuenta = cuentaService.getCuentaById(id);
        return ResponseEntity.ok(cuenta);
    }

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> getAllCuentas() {
        List<CuentaDTO> cuentas = cuentaService.getAllCuentas();
        return ResponseEntity.ok(cuentas);
    }
}