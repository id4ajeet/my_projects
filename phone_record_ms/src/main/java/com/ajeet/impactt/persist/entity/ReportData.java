
package com.ajeet.impactt.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@Entity
@Table(name = "cdr_report")
public class ReportData {

    @Id
    private String uuid;
    @Column(name = "successful_calls_minutes")
    private long successfulCallMinutes;
    @Column(name = "answered_call_count")
    private long answeredCallCount;
    @Column(name = "unmatched_call_count")
    private long unansweredCallCount;
    @Column(name = "total_call")
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
