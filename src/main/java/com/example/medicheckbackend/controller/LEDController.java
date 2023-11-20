package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.service.LEDService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LEDController {

    private final LEDService ledService;

    @GetMapping("/LED/test")
    public void testLed(@RequestParam int num) throws IOException {
        ledService.OnLED(num);
    }
}
