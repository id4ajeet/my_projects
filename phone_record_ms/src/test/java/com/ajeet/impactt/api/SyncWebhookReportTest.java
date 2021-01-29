package com.ajeet.impactt.api;

import com.ajeet.impactt.config.TestConfig;
import com.github.tomakehurst.wiremock.WireMockServer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SyncWebhookReportTest extends TestConfig {

    @Autowired
    @Qualifier("pbx-cdr-mock-server")
    private WireMockServer mockServer;

    @Autowired
    private WebTestClient webTestClient;

    private WebClient webClient;

    @Before
    public void setUp() {
        mockServer.start();
        webClient = WebClient.builder().
            baseUrl("http://localhost:9080")
            .exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                    .defaultCodecs()
                    .maxInMemorySize(1024 * 1024))
                .build())
            .build();
    }

    @After
    public void tearDown() {
        mockServer.stop();
    }

    @Test
    @Order(2)
    public void testSync() {
        storePhoneBook();
        webTestClient.get()
            .uri("sync")
            .exchange()
            .expectStatus().isOk()
            .expectBody().equals("SYNC Triggered");
    }

    @Test
    @Order(3)
    public void testWebhook() {
        webTestClient.post()
            .uri("/webhook")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just("{\n" +
                "    \"uuid\": \"970f1baa-1ce8-4bbc-b0fb-bbc6647eb672\",\n" +
                "    \"domain_name\": \"mockpbx.impactpbx.com\",\n" +
                "    \"caller_name\": \"\",\n" +
                "    \"caller_number\": 35725262136,\n" +
                "    \"destination_number\": 35799123459,\n" +
                "    \"direction\": \"outbound\",\n" +
                "    \"call_start\": \"2020-09-13T20:35:11.000Z\",\n" +
                "    \"ring_start\": \"2020-09-13T20:35:14.000Z\",\n" +
                "    \"answer_start\": \"2020-09-13T20:35:20.000Z\",\n" +
                "    \"call_end\": \"2020-09-13T20:37:14.000Z\",\n" +
                "    \"duration\": 123,\n" +
                "    \"recording\": \"1d9ca29a-b586-4ad4-9405-417504981192\",\n" +
                "    \"click_to_call\": false,\n" +
                "    \"click_to_call_data\": \"\",\n" +
                "    \"action\": \"HANGUP\"\n" +
                "}"), String.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody().equals("SUCCESS");
    }

    @Test
    @Order(4)
    public void testReportSingle() {
        storePhoneBook();
        syncData();
        webTestClient.get()
            .uri("/report/970f1baa-1ce8-4bbc-b0fb-bbc6647eb672")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    @Order(5)
    public void testReportAll() {
        storePhoneBook();
        syncData();
        webTestClient.get()
            .uri("/report")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody().equals("");
    }

    private void storePhoneBook() {
        webClient.put()
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
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    private void syncData() {
        webClient.get()
            .uri("/sync")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}
