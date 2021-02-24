package com.dev.theatre.service.dtomapper;

import com.dev.theatre.model.TheatreStage;
import com.dev.theatre.model.dto.TheatreStageRequestDto;
import com.dev.theatre.model.dto.TheatreStageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TheatreStageMapper {
    public TheatreStageResponseDto toDto(TheatreStage theatreStage) {
        TheatreStageResponseDto responseDto = new TheatreStageResponseDto();
        responseDto.setId(theatreStage.getId());
        responseDto.setCapacity(theatreStage.getCapacity());
        responseDto.setDescription(theatreStage.getDescription());
        return responseDto;
    }

    public TheatreStage fromDto(TheatreStageRequestDto theatreStageRequestDto) {
        TheatreStage theatreStage = new TheatreStage();
        theatreStage.setCapacity(theatreStageRequestDto.getCapacity());
        theatreStage.setDescription(theatreStageRequestDto.getDescription());
        return theatreStage;
    }
}
