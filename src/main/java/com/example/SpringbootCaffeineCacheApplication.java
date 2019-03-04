package com.example;

import com.github.benmanes.caffeine.cache.CaffeineSpec;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class SpringbootCaffeineCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCaffeineCacheApplication.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager("messages");
        manager.setCaffeineSpec(CaffeineSpec.parse("initialCapacity=100,maximumSize=500,expireAfterAccess=500s,recordStats"));
        return manager;
    }


}
