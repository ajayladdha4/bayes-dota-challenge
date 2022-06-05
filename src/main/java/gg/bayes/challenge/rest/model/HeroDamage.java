package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class HeroDamage {
    private String target;
    
    @JsonProperty("damage_instances")
    private long damageInstances;
    @JsonProperty("total_damage")
    private long totalDamage;
}
