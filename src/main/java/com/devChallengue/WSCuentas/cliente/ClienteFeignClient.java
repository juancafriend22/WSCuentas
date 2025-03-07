package com.devChallengue.WSCuentas.cliente;
import com.devChallengue.WSCuentas.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "WSClientes", url = "http://localhost:8081")
public interface ClienteFeignClient {
    @GetMapping("/clientes/{id}")
    ClienteDTO obtenerCliente(@PathVariable Long id);
}
