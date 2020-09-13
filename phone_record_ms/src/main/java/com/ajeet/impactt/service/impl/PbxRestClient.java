
package com.ajeet.impactt.service.impl;

import com.ajeet.impactt.service.ifc.IPbxRestClient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class PbxRestClient implements IPbxRestClient {
    public static final String BASE_URL = "http://%s:%s";
    private final int connectionTimeout;
    private final int responseReadTimeout;
    private WebClient client;
    private final String baseUrl;

    public PbxRestClient(String host, int port, int connectionTimeout, int responseReadTimeout) {
        this.connectionTimeout = connectionTimeout;
        this.responseReadTimeout = responseReadTimeout;
        this.baseUrl = String.format(BASE_URL, host, port);
    }

    public PbxRestClient(String baseUrl, int connectionTimeout, int responseReadTimeout) {
        this.connectionTimeout = connectionTimeout;
        this.responseReadTimeout = responseReadTimeout;
        this.baseUrl = baseUrl;
    }

    @PostConstruct
    public void init() {
        HttpClient httpClient = HttpClient.create()
            .tcpConfiguration(wClient ->
                wClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
                    .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(responseReadTimeout, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(responseReadTimeout, TimeUnit.MILLISECONDS))));

        client = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .baseUrl(baseUrl)
            .build();
    }

    @Override
    public Mono<ClientResponse> callGet() {
        return client.get()
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .exchange();
    }


}
