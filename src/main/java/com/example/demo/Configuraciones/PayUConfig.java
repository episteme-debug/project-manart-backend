package com.example.demo.Configuraciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "payu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayUConfig {
    private String apiKey;
    private String merchantId;
    private String accountId;
    private String apiLogin;
    private boolean test;
    private String currency;

}