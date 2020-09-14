
package com.ajeet.impactt.service.impl;

import com.ajeet.impactt.service.dto.CallDataDto;
import com.ajeet.impactt.service.dto.SyncDataDto;
import com.ajeet.impactt.service.dto.SyncResponse;
import com.ajeet.impactt.service.error.SyncException;
import com.ajeet.impactt.service.ifc.ICDRSyncService;
import com.ajeet.impactt.service.ifc.ICallDataRepoService;
import com.ajeet.impactt.service.ifc.IPbxRestClient;
import com.ajeet.impactt.service.ifc.ISyncDataRepoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import reactor.core.publisher.Mono;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class CDRSyncService implements ICDRSyncService {

    @Autowired
    private IPbxRestClient client;

    @Autowired
    private ICallDataRepoService callDataRepoService;

    @Autowired
    private ISyncDataRepoService syncDataRepoService;

    @Override
    public String sync() {
        client.callGet().subscribe(response -> {

            HttpHeaders headers = response.headers().asHttpHeaders();
            saveSyncDate(headers.get("date"));

            Mono<SyncResponse> bodyToMono = response.bodyToMono(SyncResponse.class);
            bodyToMono.subscribe(body -> {

                List<CallDataDto> data = body.getData();
                callDataRepoService.saveAll(data);
            }, ex -> {
                throw new SyncException("Failed to Sync", ex);
            });
        }, ex -> {
            throw new SyncException("Failed to Sync", ex);
        });
        return "SYNC Triggered";
    }

    private void saveSyncDate(List<String> date) {
        SyncDataDto dto = new SyncDataDto();
        if (!date.isEmpty()) {
            try {
                dto.setSyncDate(getDateFmt().parse(date.get(0)));
            } catch (ParseException e) {
                dto.setSyncDate(new Date());
            }
        } else {
            dto.setSyncDate(new Date());
        }
        syncDataRepoService.save(dto);
    }

    private DateFormat getDateFmt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat;
    }
}
