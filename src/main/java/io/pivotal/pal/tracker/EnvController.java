package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private String port, memory, instanceIndex, instanceAddress;


    public EnvController(@Value("${cf.port:NOT SET}")  String s,
                         @Value("${cf.memory.limit:NOT SET}") String s1,
                         @Value("${cf.instance.index:NOT SET}") String s2,
                         @Value("${instance.addr:NOT SET}") String s3) {
        this.port = s;
        this.memory = s1;
        this.instanceIndex = s2;
        this.instanceAddress = s3;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> env = new HashMap<>();
        env.put("PORT", this.port);
        env.put("MEMORY_LIMIT", this.memory);
        env.put("CF_INSTANCE_INDEX", this.instanceIndex);
        env.put("CF_INSTANCE_ADDR", this.instanceAddress);
        return env;
    }
}
