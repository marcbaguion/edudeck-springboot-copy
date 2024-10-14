package com.it332.edudeck.Controller;

import java.util.HashMap;
import java.util.Map;
@RestController
public class ApiController {

    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/secure-endpoint")
    public ResponseEntity<String> secureEndpoint(@RequestHeader("API-Key") String requestApiKey) {
        if (apiKey.equals(requestApiKey)) {
            return ResponseEntity.ok("Authorized Access");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized Access");
        }
    }
}
