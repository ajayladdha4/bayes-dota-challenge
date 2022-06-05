package gg.bayes.challenge.rest.model;

import gg.bayes.challenge.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class HeroItems {
    private String item;
    private Long timestamp;
    public HeroItems(String item, String timestamp) {
		super();
		this.item = item;
		this.timestamp = Util.parseDate(timestamp);
	}
}
