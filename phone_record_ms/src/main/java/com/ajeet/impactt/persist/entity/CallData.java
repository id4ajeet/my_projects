
package com.ajeet.impactt.persist.entity;

import javax.persistence.*;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@Entity
@Table(name = "call_data")
public class CallData {
    @Id
    @SequenceGenerator(name = "call_data_id_seq", sequenceName = "call_data_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "call_data_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "domain_name")
    private String domainName;
    @Column(name = "caller_name")
    private String callerName;
    @Column(name = "caller_number")
    private long callerNumber;
    @Column(name = "destination_number")
    private long destinationNumber;
    @Column(name = "direction")
    private String direction;
    @Column(name = "call_start")
    private String callStart;
    @Column(name = "ring_start")
    private String ringStart;
    @Column(name = "answer_start")
    private String answerStart;
    @Column(name = "call_end")
    private String callEnd;
    @Column(name = "duration")
    private int duration;
    @Column(name = "recording")
    private String recording;
    @Column(name = "click_to_call")
    private boolean clickToCall;
    @Column(name = "click_to_call_data")
    private String clickToCallData;
    @Column(name = "action")
    private String action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public long getCallerNumber() {
        return callerNumber;
    }

    public void setCallerNumber(long callerNumber) {
        this.callerNumber = callerNumber;
    }

    public long getDestinationNumber() {
        return destinationNumber;
    }

    public void setDestinationNumber(long destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCallStart() {
        return callStart;
    }

    public void setCallStart(String callStart) {
        this.callStart = callStart;
    }

    public String getRingStart() {
        return ringStart;
    }

    public void setRingStart(String ringStart) {
        this.ringStart = ringStart;
    }

    public String getAnswerStart() {
        return answerStart;
    }

    public void setAnswerStart(String answerStart) {
        this.answerStart = answerStart;
    }

    public String getCallEnd() {
        return callEnd;
    }

    public void setCallEnd(String callEnd) {
        this.callEnd = callEnd;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public boolean isClickToCall() {
        return clickToCall;
    }

    public void setClickToCall(boolean clickToCall) {
        this.clickToCall = clickToCall;
    }

    public String getClickToCallData() {
        return clickToCallData;
    }

    public void setClickToCallData(String clickToCallData) {
        this.clickToCallData = clickToCallData;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
