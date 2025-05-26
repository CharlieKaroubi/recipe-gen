package com.charlie.recipegen.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NutritionalProfile {

    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fats;


}
