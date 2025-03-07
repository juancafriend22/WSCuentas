package com.devChallengue.WSCuentas.repositorio;

import com.devChallengue.WSCuentas.modelo.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimientoRepositorio extends JpaRepository<Movimiento, Long> {

    //Optional<Movimiento> findByMovimientoCuenta(String numeroCuenta);
}
