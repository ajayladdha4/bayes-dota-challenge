package gg.bayes.challenge.match.engine.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import gg.bayes.challenge.match.engine.MatchProcesser;


@Component
public class DamageMatchProcesser implements MatchProcesser{
	
	private Pattern pattern = Pattern.compile("\\[\\b(.*)\\] \\b(.*) \\b(hits\\s*) \\b(.*) \\b(with) \\b(.*) \\b(for) \\b(.*) \\b(damage)\\b(.*)");
	
	private String queryTemplate = "insert into dim_damage_events (match_id, timestamp, hero, damaged_hero, damaged_weapon, damage_score, score_before_damage, score_after_damage) values (\'%1$s\', \'%2$s\', \'%3$s\', \'%4$s\', \'%5$s\',\'%6$s\',\'%7$s\',\'%8$s\')";
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
		String timestamp = matcher.group(1);
    	String hero = matcher.group(2).replace("npc_dota_hero_", "");
    	String opHero = matcher.group(4).replace("npc_dota_hero_", "");
    	String damage_weapon = matcher.group(6);
    	String damageScore = matcher.group(8);
    	String scoreBeforeAfter = matcher.group(10);
    	String scoreBefore = "-1";
    	String scoreAfter = "-1";
    	if(!StringUtils.isBlank(matcher.group(10))) {
    		try {
    			String[] beforeAfterSplit = scoreBeforeAfter.trim().replace("(", "").replace(")", "").split("->");
    			// System.out.println(beforeAfterSplit[0]);
        		scoreBefore = beforeAfterSplit[0];
        		scoreAfter = beforeAfterSplit[1];
			} catch (Exception e) {}
    	}
    	return String.format(getDBQueryTemplate(), matchId, timestamp, hero, opHero, damage_weapon, damageScore, scoreBefore, scoreAfter);
    }
}