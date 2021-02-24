package com.dev.theatre.controller;

import com.dev.theatre.model.PerformanceSession;
import com.dev.theatre.model.dto.PerformanceSessionRequestDto;
import com.dev.theatre.model.dto.PerformanceSessionResponseDto;
import com.dev.theatre.service.PerformanceSessionService;
import com.dev.theatre.service.dtomapper.PerformanceSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance-sessions")
@Validated
public class PerformanceSessionController {
    private final PerformanceSessionService performanceSessionService;
    private final PerformanceSessionMapper performanceSessionMapper;

    public PerformanceSessionController(PerformanceSessionService performanceSessionService,
                                        PerformanceSessionMapper performanceSessionMapper) {
        this.performanceSessionService = performanceSessionService;
        this.performanceSessionMapper = performanceSessionMapper;
    }

    @GetMapping("/available")
    public List<PerformanceSessionResponseDto> getAvailableSession(@RequestParam Long performanceId,
                                                                   @RequestParam
                                                             @DateTimeFormat(pattern = "dd.MM.yyyy")
                                                                     LocalDate date) {
        return performanceSessionService.findAvailableSessions(performanceId, date).stream()
                .map(performanceSessionMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createPerformanceSession(@Valid @RequestBody
                                                     PerformanceSessionRequestDto requestDto) {
        PerformanceSession performanceSession = performanceSessionMapper.fromDto(requestDto);
        performanceSessionService.add(performanceSession);
    }

    @PutMapping("/{id}")
    public void updatePerformanceSession(@Valid @RequestBody
                                                     PerformanceSessionRequestDto requestDto,
                                         @PathVariable @Min(1) Long id) {
        PerformanceSession performanceSession = performanceSessionMapper.fromDto(requestDto);
        performanceSession.setId(id);
        performanceSessionService.update(performanceSession);
    }

    @DeleteMapping("/{id}")
    public void deletePerformanceSession(@PathVariable @Min(1) Long id) {
        performanceSessionService.delete(id);
    }
}
