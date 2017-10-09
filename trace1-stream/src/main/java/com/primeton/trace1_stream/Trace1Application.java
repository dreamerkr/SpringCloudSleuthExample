/*******************************************************************************
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2017年9月19日 下午1:47:58
 *******************************************************************************/
package com.primeton.trace1_stream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class Trace1Application {
    
    private static Logger log = Logger.getLogger(Trace1Application.class); 
    
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
    @GetMapping("/trace3span")
    public String callHome() {
        log.log(Level.INFO, "calling home");
        return restTemplate().getForEntity("http://localhost:8084/trace4span", String.class).getBody();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Trace1Application.class, args);
    }

}
