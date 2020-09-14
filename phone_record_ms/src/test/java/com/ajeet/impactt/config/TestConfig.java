/*
 * (c) Copyright 2020 Brite:Bill Ltd.
 *
 * 23 Windsor Place, Dublin 2, Ireland
 * info@britebill.com
 * +353 1 661 9426
 */
package com.ajeet.impactt.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * @author <a href="mailto:AjeetKumar.Singh1@britebill.com">Ajeet</a>
 */
@Configuration
public class TestConfig {

    @Bean(value = "pbx-cdr-mock-server")
    public WireMockServer getDocumentRenderMockServer(@Value("${pbx.server.port:3030}") int port){
        WireMockConfiguration options = options()
            .port(port)
            .usingFilesUnderClasspath("src/test/resources/wiremock");
        return new WireMockServer(options);
    }
}
