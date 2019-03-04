package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @Autowired
    private CacheManager cacheManager;

    @Cacheable(value = "messages", key = "#name")
    @GetMapping("cache/{name}")
    public String cache(@PathVariable String name) {
        log.info("inside..... " + name);
        return "cached";
    }

    @GetMapping("kill/{name}")
    public String kill(@PathVariable String name) {
        clearAllCaches(name);
        return "kill";
    }

    public void clearAllCaches(String name) {
        Cache messages = cacheManager.getCache("messages");
        log.info("{}", messages.get(name));
        messages.evict(name);
        log.info("Cleared all caches");
        log.info("{}", messages.get(name));

    }


}
