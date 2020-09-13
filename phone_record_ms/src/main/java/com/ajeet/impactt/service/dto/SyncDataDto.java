
package com.ajeet.impactt.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class SyncDataDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("sync_date")
    private Date syncDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }
}
