package com.ResQ.ResourceService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table (name = "resources")

public class Resource {
    @Id
    private Integer resource_id;
    private String category;
    private Integer count;
}
