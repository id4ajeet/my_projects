
package com.ajeet.impactt.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class ReportDto {

    private Date syncDate;

    @JsonProperty("data")
    private List<ReportDataDto> cdrReport;

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    public List<ReportDataDto> getCdrReport() {
        return cdrReport;
    }

    public void setCdrReport(List<ReportDataDto> cdrReport) {
        this.cdrReport = cdrReport;
    }
}
