package br.com.gabriel.abambevchallenge.gatewayapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health")
@Api(value = "Heath Controller")
public class HealthCheckController {

    @GetMapping("check")
    @ApiOperation(value = "Check microservice status")
    public String check() {
        return "OK!";
    }
}
