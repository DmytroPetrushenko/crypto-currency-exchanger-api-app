package com.test.task.sql.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametersForClientRequestDto {
    private Integer lastHours;
    private Integer maxRespArrSize;

    public ParametersForClientRequestDto(Integer lastHours, Integer maxRespArrSize) {
        this.lastHours = lastHours;
        this.maxRespArrSize = maxRespArrSize;
    }
}
