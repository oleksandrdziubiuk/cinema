package com.dev.theatre.controller;

import com.dev.theatre.model.Performance;
import com.dev.theatre.model.dto.PerformanceRequestDto;
import com.dev.theatre.model.dto.PerformanceResponseDto;
import com.dev.theatre.service.PerformanceService;
import com.dev.theatre.service.dtomapper.PerformanceMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performances")
public class PerformanceController {
    private final PerformanceService performanceService;
    private final PerformanceMapper performanceMapper;

    public PerformanceController(PerformanceService performanceService,
                                 PerformanceMapper performanceMapper) {
        this.performanceService = performanceService;
        this.performanceMapper = performanceMapper;
    }

    @GetMapping
    public List<PerformanceResponseDto> getAllPerformances() {
        return performanceService.getAll().stream()
                .map(performanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createPerformance(@Valid @RequestBody PerformanceRequestDto performanceRequestDto) {
        Performance performance = performanceMapper.fromDto(performanceRequestDto);
        performanceService.add(performance);
    }
}
