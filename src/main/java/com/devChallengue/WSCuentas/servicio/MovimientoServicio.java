package com.devChallengue.WSCuentas.servicio;

import com.devChallengue.WSCuentas.modelo.Cuenta;
import com.devChallengue.WSCuentas.modelo.Movimiento;
import com.devChallengue.WSCuentas.repositorio.CuentaRepositorio;
import com.devChallengue.WSCuentas.repositorio.MovimientoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServicio {

    private final MovimientoRepositorio movimientoRepositorio;
    private final CuentaRepositorio cuentaRepositorio;


    public MovimientoServicio(MovimientoRepositorio movimientoRepositorio, CuentaRepositorio cuentaRepositorio) {
        this.movimientoRepositorio = movimientoRepositorio;
        this.cuentaRepositorio = cuentaRepositorio;
    }

    @Transactional
    public Movimiento registrarMovimiento(Cuenta cuenta, Movimiento movimiento){
        //Busca la cuenta
        Optional<Cuenta> cuentaOpt = cuentaRepositorio.findByNumeroCuenta(cuenta.getNumeroCuenta());
        if(cuentaOpt.isEmpty()){
            throw new RuntimeException("Cuenta no encontrada");
        }

        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
        //Validamos si hay saldo suficiente para retirar
        if (movimiento.getValor() < 0 && nuevoSaldo < 0){
            throw new RuntimeException("Saldo no disponible");
        }
        //Actualizar saldo de la cuenta
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepositorio.save(cuenta);
        //Registrar al movimiento
        movimiento.setCuenta(cuenta);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setFecha(LocalDateTime.now());
        return movimientoRepositorio.save(movimiento);
    }

    public Optional<Movimiento> obtenerMovimientosEspecifico (Long cuentaId){
        return movimientoRepositorio.findById(cuentaId);
    }

    public List<Movimiento> obtenerMovimientosTodos (){
        return movimientoRepositorio.findAll();
    }

    public List<Movimiento> reporteEstadoCuenta(Date fechaInicial, Date fechaFinal, Long clienteId){
        return movimientoRepositorio.findAll();
    }
}
