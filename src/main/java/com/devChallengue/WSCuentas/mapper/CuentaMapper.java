package com.devChallengue.WSCuentas.mapper;
import com.devChallengue.WSCuentas.dto.ClienteFeignDTO;
import com.devChallengue.WSCuentas.dto.CuentaDTO;
import com.devChallengue.WSCuentas.model.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    @Mapping(target = "clienteFeignDTO", source = "clienteId", qualifiedByName = "longToClienteFeignDTO")
    CuentaDTO toDTO(Cuenta cuenta);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clienteId", source = "clienteFeignDTO.id")
    Cuenta toEntity(CuentaDTO dto);

    @Named("longToClienteFeignDTO")
    default ClienteFeignDTO longToClienteFeignDTO(Long clienteId) {
        if (clienteId == null) {
            return null;
        }
        ClienteFeignDTO dto = new ClienteFeignDTO();
        dto.setId(clienteId);
        return dto;
    }
}
