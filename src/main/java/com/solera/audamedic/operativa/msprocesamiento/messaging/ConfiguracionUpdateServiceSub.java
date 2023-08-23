package com.solera.audamedic.operativa.msprocesamiento.messaging;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ConfiguracionUpdateServiceSub {

    private final CacheManager cacheManager;
    private Map<String, String> entityToCacheName;

    public ConfiguracionUpdateServiceSub(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @PostConstruct
    public void init() {
        entityToCacheName = new HashMap<>();
        // Mapea nombres de entidad a nombres de caché aquí
        entityToCacheName.put("Proveedor", "portafoliosByProveedorId");
        // Añade más entidades y nombres de caché según sea necesario
    }

    @RabbitListener(queues = "${rabbitmq.queue.updateconf}")
    public void handleEntityUpdateEvent(ConfiguracionUpdatedEvent event) {
        if(event.getAllUpdated()) {
            clearAllCaches();
        } else {
            List<String> updatedEntities = event.getUpdatedEntityNames();

            for (String entityName : updatedEntities) {
                String cacheName = entityToCacheName.get(entityName);
                if (cacheName != null) {
                    clearCacheForEntity(cacheName);
                }
            }
        }
    }

    public void clearCacheForEntity(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        }
        log.info("Cache cleared for cache: " + cacheName);
    }

    public void clearAllCaches() {
        for (String cacheName : entityToCacheName.values()) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        }
        log.info("All caches was cleared");
    }
}
