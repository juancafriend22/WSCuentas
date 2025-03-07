package com.devChallengue.WSCuentas.repository;

import com.devChallengue.WSCuentas.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId,Date fechaInicio, Date fechaFin);
}
