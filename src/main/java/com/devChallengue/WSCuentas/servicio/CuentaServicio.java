package com.devChallengue.WSCuentas.servicio;

import com.devChallengue.WSCuentas.cliente.ClienteFeignClient;
import com.devChallengue.WSCuentas.dto.ClienteDTO;
import com.devChallengue.WSCuentas.modelo.Cuenta;
import com.devChallengue.WSCuentas.repositorio.CuentaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServicio {
    private final CuentaRepositorio cuentaRepositorio;
    private final ClienteFeignClient clienteFeignClient;

    public CuentaServicio(CuentaRepositorio cuentaRepositorio,ClienteFeignClient clienteFeignClient) {
        this.cuentaRepositorio = cuentaRepositorio;
        this.clienteFeignClient = clienteFeignClient;
    }

    public List<Cuenta> obtenerTodas(){
        return cuentaRepositorio.findAll();
    }
    public Optional<Cuenta> obtenerPorId(Long id){
        return cuentaRepositorio.findById(id);
    }
    public Cuenta guardarCuenta(Cuenta cuenta){
        return cuentaRepositorio.save(cuenta);
    }

    public void eliminar(Long id){
        cuentaRepositorio.deleteById(id);
    }
    public Cuenta guardar(Cuenta cuenta) {
        // Verificar si el cliente existe en el Microservicio de Clientes
        ClienteDTO cliente = clienteFeignClient.obtenerCliente(cuenta.getClienteId());

        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado en el Microservicio de Clientes");
        }else{
            System.out.println("Encontré al cliente en el otro microservicio");
        }

        return cuentaRepositorio.save(cuenta);
    }


}
