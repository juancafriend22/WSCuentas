package com.devChallengue.WSCuentas.mapper;


import com.devChallengue.WSCuentas.dto.ClienteFeignDTO;
import com.devChallengue.WSCuentas.dto.MovimientoCustomResponseDTO;
import com.devChallengue.WSCuentas.model.Cuenta;
import com.devChallengue.WSCuentas.model.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface MovimientoCustomMapper {

    @Mapping(source = "movimiento.fecha", target = "fecha", qualifiedByName = "formatDate")
    @Mapping(source = "clienteFeignDTO.nombre", target = "cliente")
    @Mapping(source = "cuenta.numeroCuenta", target = "numeroCuenta")
    @Mapping(source = "cuenta.tipoCuenta", target = "tipo")
    @Mapping(source = "cuenta.saldoInicial", target = "saldoInicial")
    @Mapping(source = "cuenta.estado", target = "estado")
    @Mapping(source = "movimiento.valor", target = "movimiento")
    @Mapping(source = "movimiento.saldo", target = "saldoDisponible")
    MovimientoCustomResponseDTO toCustomDTO(Movimiento movimiento, Cuenta cuenta, ClienteFeignDTO clienteFeignDTO);

    @Named("formatDate")
    default String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
    }
}
