package com.devChallengue.WSCuentas.controlador;

import com.devChallengue.WSCuentas.modelo.Cuenta;
import com.devChallengue.WSCuentas.servicio.CuentaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaControlador {
    private final CuentaServicio cuentaServicio;

    public CuentaControlador(CuentaServicio cuentaServicio){
        this.cuentaServicio = cuentaServicio;
    }

    @GetMapping
    public List<Cuenta> obtenerCuentas(){
        return cuentaServicio.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable Long id){
        Optional<Cuenta> cuenta = cuentaServicio.obtenerPorId(id);
        return cuenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {

        return cuentaServicio.guardar(cuenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
