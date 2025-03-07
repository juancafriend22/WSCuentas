package com.devChallengue.WSCuentas.controlador;

import com.devChallengue.WSCuentas.modelo.Cuenta;
import com.devChallengue.WSCuentas.modelo.Movimiento;
import com.devChallengue.WSCuentas.servicio.MovimientoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientoControlador {

    private final MovimientoServicio movimientoServicio;

    public MovimientoControlador(MovimientoServicio movimientoServicio) {
        this.movimientoServicio = movimientoServicio;
    }

    @GetMapping
    public List<Movimiento> obtenerMovimientosTotales(){
        return movimientoServicio.obtenerMovimientosTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento>  obtenerMovimientoCuenta(@PathVariable Long cuentaId){
        Optional <Movimiento> movimiento = movimientoServicio.obtenerMovimientosEspecifico(cuentaId);
        return movimiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Movimiento registrarMovimiento(@RequestBody Cuenta cuenta, Movimiento movimiento) {
        return movimientoServicio.registrarMovimiento(cuenta,movimiento);
    }
}
