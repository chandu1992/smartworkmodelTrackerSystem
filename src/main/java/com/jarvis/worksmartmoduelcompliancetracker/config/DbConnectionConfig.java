package com.jarvis.worksmartmoduelcompliancetracker.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.jarvis.worksmartmoduelcompliancetracker.constant.ApplicationConstant;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DbConnectionConfig {
    final Logger logger = LoggerFactory.getLogger(DbConnectionConfig.class);

    @Autowired
    private Environment env;

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${spring.cloud.azure.keyvault.secret.endpoint}")
    private String keyvaultUrl;

    @Bean
    public SecretClient secretClient() {
        return new SecretClientBuilder()
                .vaultUrl(keyvaultUrl)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getMySQLDatasource() {

        if (profile.equals("prod")) {
            logger.info("PROD environment is active");

            return DataSourceBuilder.create()
                    .type(HikariDataSource.class)
                    .url(env.getProperty(ApplicationConstant.SPRING_DATASOURCE_URL))
                    .username(env.getProperty(ApplicationConstant.SPRING_DATASOURCE_USERNAME))
                    .password(secretClient().getSecret("onduty-prod-mysql-kv").getValue())
                    .build();

        } else if (profile.equals("uat")) {
            logger.info("UAT environment is active");

            return DataSourceBuilder.create()
                    .type(HikariDataSource.class)
                    .url(env.getProperty(ApplicationConstant.SPRING_DATASOURCE_URL))
                    .username(env.getProperty(ApplicationConstant.SPRING_DATASOURCE_USERNAME))
                    .password(secretClient().getSecret("onduty-uat-mysql-kv").getValue())
                    .build();

        } else {
            logger.info("Local environment is active");

            return DataSourceBuilder.create()
                    .type(HikariDataSource.class)
                    .url(env.getProperty(ApplicationConstant.SPRING_DATASOURCE_URL))
                    .username(env.getProperty(ApplicationConstant.SPRING_DATASOURCE_USERNAME))
                    .password(env.getProperty("spring.datasource.password"))
                    .build();
        }
    }
}
