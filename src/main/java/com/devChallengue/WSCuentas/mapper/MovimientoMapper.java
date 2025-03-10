package com.devChallengue.WSCuentas.mapper;

import com.devChallengue.WSCuentas.dto.MovimientoDTO;
import com.devChallengue.WSCuentas.model.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    @Mapping(source = "cuenta.id", target = "cuentaId")
    MovimientoDTO toDTO(Movimiento movimiento);

    @Mapping(source = "cuentaId", target = "cuenta.id")
    Movimiento toEntity(MovimientoDTO dto);
}
