
package com.ajeet.impactt.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class ReportDataDto {

    private String uuid;

    @JsonProperty("successful_calls_minutes")
    private long successfulCallMinutes;

    @JsonProperty("answered_call_count")
    private long answeredCallCount;

    @JsonProperty("unmatched_call_count")
    private long unansweredCallCount;

    @JsonProperty("total_call")
    private long totalCount;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getSuccessfulCallMinutes() {
        return successfulCallMinutes;
    }

    public void setSuccessfulCallMinutes(long successfulCallMinutes) {
        this.successfulCallMinutes = successfulCallMinutes;
    }

    public long getAnsweredCallCount() {
        return answeredCallCount;
    }

    public void setAnsweredCallCount(long answeredCallCount) {
        this.answeredCallCount = answeredCallCount;
    }

    public long getUnansweredCallCount() {
        return unansweredCallCount;
    }

    public void setUnansweredCallCount(long unansweredCallCount) {
        this.unansweredCallCount = unansweredCallCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
