package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.service.DataHubService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DataHubController {

    private final DataHubService dataHubService;

    @Operation(summary = "약 TOP 5 전송, 사용 X")
    @PostMapping("/send/dataHub")
    public void sendDataHub() {
        dataHubService.sendData();
    }

    @Operation(summary = "eCost, nCost 전송")
    @PostMapping("/send/expirationCost")
    public void sendExpirationCost(@RequestParam int eCost, @RequestParam int nCost) {
        dataHubService.sendExpirationCost(eCost, nCost);
    }
}
