package br.com.gabriel.abambevchallenge.gatewayapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health")
public class HealthCheckController {

    @GetMapping("check")
    public String check() {
        return "OK!";
    }
}
