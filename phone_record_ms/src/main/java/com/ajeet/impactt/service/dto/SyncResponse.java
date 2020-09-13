
package com.ajeet.impactt.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class SyncResponse {

    private int total;

    @JsonProperty("data")
    private List<CallDataDto> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CallDataDto> getData() {
        return data;
    }

    public void setData(List<CallDataDto> data) {
        this.data = data;
    }
}
