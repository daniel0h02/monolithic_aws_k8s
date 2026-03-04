package com.example.monolithic.common.ctrl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping("/alive")
    public String getMethodName() {
        return "alive";
    }
    
}
