package com.capitalone.nsb.marketing.stockpile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.Properties;
import java.util.TimeZone;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.capitalone.nsb.marketing.stockpile"})
public class Application {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ConfigurableEnvironment env;

    @Value("${http.read.timeout:3000}")
    int readTimeout;

    @Value("${http.connect.timeout:30000}")
    int connectTimeout;

    public static void main(String [] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(readTimeout);
        requestFactory.setConnectTimeout(connectTimeout);
        return new RestTemplate(requestFactory);
    }

    @PostConstruct
    private void configureApplication() {
        // Putting these properties here makes them available via the Spring Boot Actuator /env endpoint
        Properties props = new Properties();
        props.put("java.time.ZoneId.systemDefault()", ZoneId.systemDefault());
        props.put("java.util.TimeZone.getDefault()", TimeZone.getDefault());
        props.put("org.joda.time.DateTimeZone.getDefault()", DateTimeZone.getDefault());
        env.getPropertySources().addFirst(new PropertiesPropertySource("time-zone-defaults", props));

        objectMapper.getFactory().enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
    }
}
