
package com.ajeet.impactt.api;

import com.ajeet.impactt.service.dto.ReportDto;
import com.ajeet.impactt.service.ifc.ICdrReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@RestController
@RequestMapping("/report")
public class ReportCDRController {

    private final ICdrReportService reportService;

    @Autowired
    public ReportCDRController(ICdrReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(path = "/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReportDto generate(@PathVariable String uuid) {
        return reportService.generate(uuid);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReportDto generateAll() {
        return reportService.generateAll();
    }
}
