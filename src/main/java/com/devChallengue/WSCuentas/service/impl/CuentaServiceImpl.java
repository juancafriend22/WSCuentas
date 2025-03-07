package com.devChallengue.WSCuentas.service.impl;

import com.devChallengue.WSCuentas.cliente.ClienteFeignClient;
import com.devChallengue.WSCuentas.dto.ClienteFeignDTO;
import com.devChallengue.WSCuentas.dto.CuentaDTO;
import com.devChallengue.WSCuentas.mapper.CuentaMapper;
import com.devChallengue.WSCuentas.model.Cuenta;
import com.devChallengue.WSCuentas.repository.CuentaRepository;
import com.devChallengue.WSCuentas.service.CuentaService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Autowired
    private ClienteFeignClient clienteFeign; // Inyección del Feign Client

    @Override
    public CuentaDTO createCuenta(CuentaDTO cuentaDTO) {
        // Validar que el cliente exista utilizando el Feign Client
        ClienteFeignDTO cliente = clienteFeign.getClienteById(cuentaDTO.getClienteFeignDTO().getId());
        if (cliente == null) {
            throw new RuntimeException("El cliente con ID " + cuentaDTO.getClienteFeignDTO().getId() + " no existe");
        }
        // Convertir Request DTO a Entidad
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);
        // Guardar la entidad en la base de datos
        Cuenta saved = cuentaRepository.save(cuenta);
        // Convertir Entidad a Response DTO
        return cuentaMapper.toDTO(saved);
    }

    @Override
    public CuentaDTO updateCuenta(Long id, CuentaDTO cuentaDTO) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        // Actualizamos los campos deseados
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.getEstado());
        cuenta.setClienteId(cuentaDTO.getClienteFeignDTO().getId());
        Cuenta updated = cuentaRepository.save(cuenta);
        return cuentaMapper.toDTO(updated);
    }

    @Override
    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public CuentaDTO getCuentaById(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return cuentaMapper.toDTO(cuenta);
    }

    @Override
    public List<CuentaDTO> getAllCuentas() {
        return cuentaRepository.findAll()
                .stream()
                .map(cuentaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
