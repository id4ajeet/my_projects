
package com.ajeet.impactt.service.config;

import com.ajeet.impactt.service.ifc.*;
import com.ajeet.impactt.service.impl.*;
import com.ajeet.impactt.service.mapper.EntityDtoMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@Configuration
public class ServiceConfig {

    @Bean
    public IPhoneBookRepoService getPhoneBookRepoService() {
        return new PhoneBookRepoService();
    }

    @Bean
    public ICallDataRepoService getCallDataRepoService() {
        return new CallDataRepoService();
    }

    @Bean
    public ISyncDataRepoService getSyncDataRepoService() {
        return new SyncDataRepoService();
    }

    @Bean
    public ICDRSyncService getCdrSyncService() {
        return new CDRSyncService();
    }

    @Bean
    public ICDRFilter getCdrFilter() {
        return new UnknownCallsFilter();
    }

    @Bean
    public ICdrReportService getICdrReportService() {
        return new CdrReportService();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public IEntityDtoMapper entityDtoMapper() {
        return new EntityDtoMapper();
    }

    @Bean
    public IPbxRestClient getDocRenderRestClient(@Value("${pbx.server.baseUrl:}") String baseUrl,
                                                 @Value("${pbx.server.host:localhost}") String host,
                                                 @Value("${pbx.server.port:3030}") int port,
                                                 @Value("${pbx.server.connectionTimeout:60000}") int connectionTimeout,
                                                 @Value("${pbx.server.responseReadTimeout:120000}") int responseReadTimeout) {
        if (baseUrl != null && !baseUrl.isEmpty()) {
            return new PbxRestClient(baseUrl, connectionTimeout, responseReadTimeout);
        }
        return new PbxRestClient(host, port, connectionTimeout, responseReadTimeout);
    }
}
