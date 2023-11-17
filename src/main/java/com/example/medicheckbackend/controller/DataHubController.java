package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.service.DataHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DataHubController {

    private final DataHubService dataHubService;

    @PostMapping("/send/dataHub")
    public void sendDataHub() {
        dataHubService.sendData();
    }
}
