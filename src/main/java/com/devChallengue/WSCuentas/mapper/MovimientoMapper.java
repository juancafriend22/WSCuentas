package com.devChallengue.WSCuentas.mapper;

import com.devChallengue.WSCuentas.dto.MovimientoDTO;
import com.devChallengue.WSCuentas.model.Movimiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    MovimientoDTO toDTO(Movimiento movimiento);
    Movimiento toEntity(MovimientoDTO dto);
}
