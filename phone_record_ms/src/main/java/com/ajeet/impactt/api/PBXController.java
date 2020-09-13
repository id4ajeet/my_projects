
package com.ajeet.impactt.api;

import com.ajeet.impactt.service.dto.CallDataDto;
import com.ajeet.impactt.service.ifc.ICDRSyncService;
import com.ajeet.impactt.service.ifc.ICallDataRepoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@RestController
public class PBXController {

    private final ICDRSyncService cdrSyncService;
    private final ICallDataRepoService callDataRepoService;

    @Autowired
    public PBXController(ICDRSyncService cdrSyncService, ICallDataRepoService callDataRepoService) {
        this.cdrSyncService = cdrSyncService;
        this.callDataRepoService = callDataRepoService;
    }

    @GetMapping(path = "/sync", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String sync() {
        return cdrSyncService.sync();
    }

    @PostMapping(path = "/webhook", consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public String post(@RequestBody CallDataDto request) {
        boolean save = callDataRepoService.save(request);
        return save ? "SUCCESS" : "FAILED";
    }
}
