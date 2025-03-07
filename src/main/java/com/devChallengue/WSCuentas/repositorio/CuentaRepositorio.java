package com.devChallengue.WSCuentas.repositorio;

import com.devChallengue.WSCuentas.modelo.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta,Long> {
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

}
