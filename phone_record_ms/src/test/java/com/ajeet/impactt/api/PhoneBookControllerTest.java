package com.ajeet.impactt.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

/**
 * @author <a href="mailto:AjeetKumar.Singh1@britebill.com">Ajeet</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class PhoneBookControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testPutAll() {
        webTestClient.put()
            .uri("/phonebook/all")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just("[\n" +
                "    {\n" +
                "        \"number\": 35799123456,\n" +
                "        \"name\": \"John Smith\",\n" +
                "        \"email\": \"j.smith@impactechs.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"number\": 35799123457,\n" +
                "        \"name\": \"Lennox Jeffery\",\n" +
                "        \"email\": \"l.jeffery@impactechs.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"number\": 35799123459,\n" +
                "        \"name\": \"Sadia Bryan\",\n" +
                "        \"email\": \"s.bryan@impactechs.com\"\n" +
                "    }\n" +
                "]"), String.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody().equals("SUCCESS");
    }

    @Test
    public void testPut() {
        webTestClient.put()
            .uri("/phonebook")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just("{\n" +
                "        \"number\": 35799123458,\n" +
                "        \"name\": \"Tyrell Larsen\",\n" +
                "        \"email\": \"t.larsen@impactechs.com\"\n" +
                "    }"), String.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody().equals("SUCCESS");
    }

    @Test
    public void testPost() {
        webTestClient.post()
            .uri("/phonebook")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just("{\n" +
                "        \"number\": 35799123458,\n" +
                "        \"name\": \"Tyrell Larsen\",\n" +
                "        \"email\": \"t.larsen@impactechs.com\"\n" +
                "    }"), String.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody().equals("SUCCESS");
    }

    @Test
    public void testGet() {
        webTestClient.get()
            .uri("/phonebook")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    public void testGetSingle() {
        webTestClient.get()
            .uri("/phonebook/35799123458")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    public void testDelete() {
        webTestClient.delete()
            .uri("/phonebook/35799123458")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk();
    }
}
