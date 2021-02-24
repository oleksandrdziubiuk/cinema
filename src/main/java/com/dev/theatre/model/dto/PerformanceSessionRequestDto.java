package com.dev.theatre.model.dto;

import javax.validation.constraints.NotNull;

public class PerformanceSessionRequestDto {
    @NotNull
    private String showTime;
    @NotNull
    private Long performanceId;
    @NotNull
    private Long theatreStageId;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public Long getTheatreStageId() {
        return theatreStageId;
    }

    public void setTheatreStageId(Long theatreStageId) {
        this.theatreStageId = theatreStageId;
    }
}
