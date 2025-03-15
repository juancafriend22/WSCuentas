package com.devChallengue.WSCuentas.service.impl;

import com.devChallengue.WSCuentas.excepciones.AccountCreationException;
import com.devChallengue.WSCuentas.excepciones.AccountNotFoundExcepcion;
import com.devChallengue.WSCuentas.service.ClienteFeignClient;
import com.devChallengue.WSCuentas.dto.ClienteFeignDTO;
import com.devChallengue.WSCuentas.dto.CuentaDTO;
import com.devChallengue.WSCuentas.mapper.CuentaMapper;
import com.devChallengue.WSCuentas.model.Cuenta;
import com.devChallengue.WSCuentas.repository.CuentaRepository;
import com.devChallengue.WSCuentas.service.ICuentaService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Autowired
    private ClienteFeignClient clienteFeign; // Inyección del Feign Client

    @Override
    public CuentaDTO createCuenta(CuentaDTO cuentaDTO) {

        ClienteFeignDTO clienteF = clienteFeign.getClienteById(cuentaDTO.getClienteFeignDTO().getId());
        if (clienteF == null) {
            throw new AccountNotFoundExcepcion("El cliente con ID " + cuentaDTO.getClienteFeignDTO().getId() + " no existe");

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
            throw new AccountNotFoundExcepcion("El cliente con ID " + cuentaDTO.getClienteFeignDTO().getId() + " no existe");

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
            updatedCuentaDTO.setClienteFeignDTO(clienteF); // Ensure client info is set in DTO
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
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundExcepcion("Cuenta "+id+" no encontrada"));

        return cuentaMapper.toDTO(cuenta);
    }

    @Override
    public List<CuentaDTO> getAllCuentas() {
        List <Cuenta> accounts = cuentaRepository.findAll();
        if(accounts.isEmpty()){
            throw new RuntimeException("No existen cuentas guardadas");
        }
        return accounts
                .stream()
                .map(cuentaMapper::toDTO)
                .collect(Collectors.toList());

    }
}