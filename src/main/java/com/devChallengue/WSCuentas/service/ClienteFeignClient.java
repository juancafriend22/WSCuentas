package com.devChallengue.WSCuentas.service;

import com.devChallengue.WSCuentas.dto.ClienteFeignDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientes-servicio", url = "http://microservicio-clientes:8080")
public interface ClienteFeignClient {
    @GetMapping("/clientes/{id}")
    ClienteFeignDTO getClienteById(@PathVariable("id") Long id) ;

}
