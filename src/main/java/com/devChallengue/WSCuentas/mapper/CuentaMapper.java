package com.devChallengue.WSCuentas.mapper;
import com.devChallengue.WSCuentas.dto.ClienteFeignDTO;
import com.devChallengue.WSCuentas.dto.CuentaDTO;
import com.devChallengue.WSCuentas.model.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    @Mapping(source = "clienteId", target = "clienteFeignDTO.id")
    CuentaDTO toDTO(Cuenta cuenta);

    //@Mapping(target = "id", ignore = true)
    @Mapping(source = "clienteFeignDTO.id", target = "clienteId")
    Cuenta toEntity(CuentaDTO dto);


}