package gg.bayes.challenge.match.engine.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import gg.bayes.challenge.match.engine.MatchProcesser;

@Component
public class BuyMatchProcesser implements MatchProcesser{
	
	private Pattern pattern = Pattern.compile("\\[\\b(.*)\\] \\b(.*) buys item \\b(.*)");
	
	private String queryTemplate = "insert into dim_buy_events (match_id, timestamp, hero, item) values (\'%1$s\', \'%2$s\', \'%3$s\', \'%4$s\')";
	
	@Override
	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public String getDBQueryTemplate() {
		return queryTemplate;
	}

	@Override
	public String populateQuery(Matcher matcher, Long matchId) {
		String timestamp =matcher.group(1);
    	String hero = matcher.group(2).replace("npc_dota_hero_", "");
    	String boughtItem = matcher.group(3);
    	return String.format(getDBQueryTemplate(), matchId,timestamp, hero, boughtItem);
    }

}
