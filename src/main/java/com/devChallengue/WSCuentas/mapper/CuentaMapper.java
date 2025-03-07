package com.devChallengue.WSCuentas.mapper;
import com.devChallengue.WSCuentas.dto.CuentaDTO;
import com.devChallengue.WSCuentas.model.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    CuentaDTO toDTO(Cuenta cuenta);
    @Mapping(target = "id", ignore = true)
    Cuenta toEntity(CuentaDTO dto);

}
