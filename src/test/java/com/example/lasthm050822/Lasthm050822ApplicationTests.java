package com.example.lasthm050822;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

@TestConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Lasthm050822ApplicationTests {
    private final String host = "http://localhost:";
    @Autowired
    TestRestTemplate testRestTemplate;

    @Container
    public static final GenericContainer<?> devapp = new
            GenericContainer<>("devapp")
            .withExposedPorts(8080);

    @Container
    public static final GenericContainer<?> prodapp = new
            GenericContainer<>("prodapp").withExposedPorts(8081);

    @Test
    void contextLoadsProdApp() {
        Integer port = prodapp.getMappedPort(8081);
        System.out.printf("Port: %s ", port);
        String result = testRestTemplate.getForObject(host +
                port + "/profile", String.class);
        Assertions.assertEquals("ProductionProfile", result);
    }

    @Test
    void contextLoadsDevApp() {
        Integer port = devapp.getMappedPort(8080);
        System.out.println(port);
     String result = testRestTemplate.getForObject(host +
             port + "/profile", String.class);
     Assertions.assertEquals("DevProfile", result);
    }

    @BeforeAll
    public static void startAll() {
        devapp.start();
        prodapp.start();
    }
    @AfterAll
    public static void stopAll(){
        devapp.stop();
        prodapp.stop();
    }



}
