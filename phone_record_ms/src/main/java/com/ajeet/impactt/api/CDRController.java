
package com.ajeet.impactt.api;

import com.ajeet.impactt.service.dto.CallDataDto;
import com.ajeet.impactt.service.ifc.ICallDataRepoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@RestController
@RequestMapping("/cdr")
public class CDRController {

    private final ICallDataRepoService callDataService;

    @Autowired
    public CDRController(ICallDataRepoService callDataService) {
        this.callDataService = callDataService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CallDataDto> get() {
        return callDataService.findAll();
    }

    @GetMapping(path = "/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CallDataDto> get(@PathVariable String uuid) {
        return callDataService.findByUuid(uuid);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public String post(@RequestBody CallDataDto request) {
        return callSave(request);
    }

    private String callSave(@RequestBody CallDataDto request) {
        boolean save = callDataService.save(request);
        return save ? "SUCCESS" : "FAILED";
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public String put(@RequestBody CallDataDto request) {
        return callSave(request);
    }

    @DeleteMapping(path = "/{uuid}")
    public void delete(@PathVariable String uuid) {
        callDataService.deleteBuUuid(uuid);
    }
}
