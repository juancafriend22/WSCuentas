package com.devChallengue.WSCuentas.cliente;
import com.devChallengue.WSCuentas.dto.ClienteFeignDTO;
import com.devChallengue.WSCuentas.dto.CuentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "clientes-servicio", url = "http://localhost:8081")
public interface ClienteFeignClient {
    @GetMapping("/clientes/{id}")
    ClienteFeignDTO getClienteById(@PathVariable("id") Long id);
}
