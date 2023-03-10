package com.education.springbootconditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootConditionalApplicationTests {

    @Autowired
    private TestRestTemplate template;

    @Container
    private GenericContainer<?> myFirstApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private GenericContainer<?> mySecondApp = new GenericContainer<>("prodapp:1.0")
            .withExposedPorts(8081);

    @Test
    void devappDockerTest(){

        final Integer devappPort = myFirstApp.getMappedPort(8080);

        ResponseEntity<String> forFrstEntity = template.getForEntity(
                "http://localhost:" + devappPort + "/profile",
                String.class
        );

        System.out.println("devapp == " + forFrstEntity.getBody());

        Assertions.assertEquals("Current profile is dev", forFrstEntity.getBody());

    }

    @Test
    void prodappDockerTest() {

        final Integer prodappPost = mySecondApp.getMappedPort(8081);



        ResponseEntity<String> forSecEntity = template.getForEntity(
                "http://localhost:" + prodappPost + "/profile",
                String.class
        );

        System.out.println("prodapp == " + forSecEntity.getBody());

        Assertions.assertEquals("Current profile is production", forSecEntity.getBody());
    }

}
