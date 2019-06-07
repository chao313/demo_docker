package demo.spring.boot.docker.controller.pub;

import demo.spring.boot.docker.redis.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisCacheController {

    @Autowired
    private RedisCacheService redisCacheService;

    @GetMapping(value="/redis/set-up-cache")
    public String redisSetupCache(){
        return  redisCacheService.redisSetupCache();
    }

    @GetMapping(value="/redis/clean-up-cache")
    public String redisCleanupCache(){
        return  redisCacheService.redisCleanupCache();
    }
}