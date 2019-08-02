package com.vincent.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private RedisClient redisClient;

    @PutMapping("/set")
    public String set(@RequestParam("k") String k, @RequestParam("v") String v) throws Exception{
        redisClient.set(k, v);
        return "success";
    }

    @GetMapping("/get")
    public String get(@RequestParam("k") String k) throws Exception {
        String s = redisClient.get(k);
        return s;
    }

}
