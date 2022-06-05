package gg.bayes.challenge.match.engine.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import gg.bayes.challenge.match.engine.MatchProcesser;


@Component
public class KilledMatchProcesser implements MatchProcesser{
	
	private static Pattern pattern = Pattern.compile("\\[\\b(.*)\\] \\b(.*) is killed by \\b(.*)");
	
	private String queryTemplate = "insert into dim_killed_events (match_id, timestamp, hero, killed_hero) values (\'%1$s\', \'%2$s\', \'%3$s\', \'%4$s\')";
	
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
		 // Get the group matched using group() method
		// Get the group matched using group() method
		String timestamp =matcher.group(1);
      	String hero = matcher.group(2).replace("npc_dota_hero_", "");
      	String killedHero = matcher.group(3);
      	return String.format(getDBQueryTemplate(), matchId, timestamp, hero, killedHero);
    }

}
