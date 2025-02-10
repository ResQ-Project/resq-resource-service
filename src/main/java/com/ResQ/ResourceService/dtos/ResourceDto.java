package com.ResQ.ResourceService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceDto {
    private Integer resource_id;
    private String category;
    private Integer fullCount;
    private Integer availableUnits;
}
