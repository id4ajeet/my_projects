package com.ajeet.impactt.service.ifc;

import org.springframework.web.reactive.function.client.ClientResponse;

import reactor.core.publisher.Mono;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public interface IPbxRestClient {
    Mono<ClientResponse> callGet();
}
