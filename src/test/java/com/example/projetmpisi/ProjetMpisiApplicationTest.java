package com.example.projetmpisi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjetMpisiApplicationTest {



    @Test
    void main() {
        ProjetMpisiApplication.main(new String[]{});
    }

    @Test
    void contextLoads() {}

    @Test
    void constructor() {
        new ProjetMpisiApplication();
    }
}