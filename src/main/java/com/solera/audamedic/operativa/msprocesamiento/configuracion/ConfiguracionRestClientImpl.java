package com.solera.audamedic.operativa.msprocesamiento.configuracion;

import com.solera.audamedic.operativa.msprocesamiento.domain.PortafolioDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ConfiguracionRestClientImpl implements ConfiguracionClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ConfiguracionRestClientImpl(RestTemplateBuilder restTemplateBuilder, @Value("${ms.configuracion.base.url}") String baseUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
    }

    @Override
    @Cacheable(value = "portafoliosByProveedorId", key = "#id", unless = "#result==null")
    public List<PortafolioDTO> getPortafoliosByProveedorId(Long id) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .path("/portafolios/proveedor/")
                    .path(id.toString());
            ResponseEntity<PortafolioDTO[]> response = restTemplate.getForEntity(builder.toUriString(), PortafolioDTO[].class);
            return response.getBody() != null ? Arrays.asList(response.getBody()) : new ArrayList<>();
        } catch (RestClientException e) {
            // if es algo requerido lanzar excepción en vez de retornar empty, recordar usar excepciones personalizadas.
            log.warn(e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}
