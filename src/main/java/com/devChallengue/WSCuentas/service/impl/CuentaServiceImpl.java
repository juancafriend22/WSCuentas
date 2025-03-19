package com.devChallengue.WSCuentas.service.impl;

import com.devChallengue.WSCuentas.excepciones.AccountCreationException;
import com.devChallengue.WSCuentas.excepciones.AccountNotFoundExcepcion;
import com.devChallengue.WSCuentas.excepciones.ClientNotFoundException;
import com.devChallengue.WSCuentas.service.ClienteFeignClient;
import com.devChallengue.WSCuentas.dto.ClienteFeignDTO;
import com.devChallengue.WSCuentas.dto.CuentaDTO;
import com.devChallengue.WSCuentas.mapper.CuentaMapper;
import com.devChallengue.WSCuentas.model.Cuenta;
import com.devChallengue.WSCuentas.repository.CuentaRepository;
import com.devChallengue.WSCuentas.service.ICuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class CuentaServiceImpl implements ICuentaService {

    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;
    private final ClienteFeignClient clienteFeign;

    @Override
    public CuentaDTO createCuenta(CuentaDTO cuentaDTO) {

        ClienteFeignDTO clienteF = clienteFeign.getClienteById(cuentaDTO.getClienteFeignDTO().getId());
        if (clienteF == null) {
            throw new ClientNotFoundException("El cliente con ID " + cuentaDTO.getClienteFeignDTO().getId() + " no existe");

        }
        try {
            Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);

            Cuenta cuentaSaved = cuentaRepository.save(cuenta);
            CuentaDTO savedCuentaDTO = cuentaMapper.toDTO(cuentaSaved);
            savedCuentaDTO.setClienteFeignDTO(clienteF);
            return savedCuentaDTO;
        } catch (Exception e) {
            throw new AccountCreationException("Error al crear cuenta :( ");
        }
    }

    @Override
    public CuentaDTO updateCuenta(Long id, CuentaDTO cuentaDTO) {
        Cuenta cuentaExistente = cuentaRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundExcepcion("Cuenta " + id + " no encontrada"));
        ClienteFeignDTO clienteF = clienteFeign.getClienteById(cuentaDTO.getClienteFeignDTO().getId());
        if (clienteF == null) {
            throw new ClientNotFoundException("El cliente  no existe");

        }
        try {
            cuentaExistente.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
            cuentaExistente.setTipoCuenta(cuentaDTO.getTipoCuenta());
            cuentaExistente.setEstado(cuentaDTO.getEstado());
            cuentaExistente.setSaldoInicial(cuentaDTO.getSaldoInicial());
            cuentaExistente.setEstado(cuentaDTO.getEstado());
            cuentaExistente.setClienteId(cuentaDTO.getClienteFeignDTO().getId());

            Cuenta cuentaActu = cuentaRepository.save(cuentaExistente);

            // Convierte la entidad actualizada de regreso a dto
            CuentaDTO updatedCuentaDTO = cuentaMapper.toDTO(cuentaActu);
            updatedCuentaDTO.setClienteFeignDTO(clienteF);
            return updatedCuentaDTO;


        } catch (Exception e) {
            throw new AccountCreationException("Error en actualizacion de cuenta");

        }
    }

    @Override
    public void deleteCuenta(Long id) {
        if(!cuentaRepository.existsById(id)){
            throw new AccountNotFoundExcepcion("Cuenta "+id+" no encontrada");
        }
        cuentaRepository.deleteById(id);
    }

    @Override
    public CuentaDTO getCuentaById(Long id) {
        // Obtener la cuenta desde la base de datos
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundExcepcion("Cuenta " + id + " no encontrada"));

        // Mapear la cuenta a CuentaDTO
        CuentaDTO cuentaDTO = cuentaMapper.toDTO(cuenta);

        // Obtener la información del cliente usando el Feign Client
        ClienteFeignDTO clienteF = clienteFeign.getClienteById(cuenta.getClienteId());
        cuentaDTO.setClienteFeignDTO(clienteF);

        return cuentaDTO;

    }

    @Override
    public List<CuentaDTO> getAllCuentas() {
        List<Cuenta> accounts = cuentaRepository.findAll();
        if (accounts.isEmpty()) {
            throw new AccountNotFoundExcepcion("No existen cuentas guardadas");
        }
        return accounts.stream()
                .map(cuenta -> {
                    CuentaDTO cuentaDTO = cuentaMapper.toDTO(cuenta);
                    // Obtener la información del cliente usando el Feign Client
                    ClienteFeignDTO clienteF = clienteFeign.getClienteById(cuenta.getClienteId());
                    cuentaDTO.setClienteFeignDTO(clienteF);
                    return cuentaDTO;
                })
                .collect(Collectors.toList());

    }
}