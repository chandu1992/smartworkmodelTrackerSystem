package com.jarvis.worksmartmoduelcompliancetracker.config;


import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KeyVaultConfig {



    static Logger logger = LoggerFactory.getLogger(KeyVaultConfig.class);

    @Value("${spring.cloud.azure.keyvault.secret.endpoint}")
    private String keyVaultUrl;

    @Bean
    public SecretClient secretClient() {
        logger.info("Inside Client Secret");

        return new SecretClientBuilder()
                .vaultUrl(keyVaultUrl)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }
}
