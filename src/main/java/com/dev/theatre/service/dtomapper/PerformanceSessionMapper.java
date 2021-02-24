package com.dev.theatre.service.dtomapper;

import com.dev.theatre.model.PerformanceSession;
import com.dev.theatre.model.dto.PerformanceSessionRequestDto;
import com.dev.theatre.model.dto.PerformanceSessionResponseDto;
import com.dev.theatre.service.PerformanceService;
import com.dev.theatre.service.TheatreStageService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class PerformanceSessionMapper {
    private final PerformanceService performanceService;
    private final TheatreStageService theatreStageService;

    public PerformanceSessionMapper(PerformanceService performanceService,
                                    TheatreStageService theatreStageService) {
        this.performanceService = performanceService;
        this.theatreStageService = theatreStageService;
    }

    public PerformanceSessionResponseDto toDto(PerformanceSession performanceSession) {
        PerformanceSessionResponseDto responseDto = new PerformanceSessionResponseDto();
        responseDto.setId(performanceSession.getId());
        responseDto.setPerformanceTitle(performanceSession.getPerformance().getTitle());
        responseDto.setPerformanceId(performanceSession.getPerformance().getId());
        responseDto.setTheatreStageId(performanceSession.getTheatreStage().getId());
        responseDto.setShowTime(performanceSession.getShowTime().toString());
        return responseDto;
    }

    public PerformanceSession fromDto(PerformanceSessionRequestDto requestDto) {
        PerformanceSession performanceSession = new PerformanceSession();
        performanceSession.setPerformance(performanceService.get(requestDto.getPerformanceId()));
        LocalDateTime localDate = LocalDateTime.parse(requestDto.getShowTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        performanceSession.setShowTime(localDate);
        performanceSession.setTheatreStage(theatreStageService.get(requestDto.getTheatreStageId()));
        return performanceSession;
    }
}
